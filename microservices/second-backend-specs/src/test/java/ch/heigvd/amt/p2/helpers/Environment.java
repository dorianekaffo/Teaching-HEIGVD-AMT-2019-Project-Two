package ch.heigvd.amt.p2.helpers;

import ch.heigvd.amt.p2.api.CourseApi;
import ch.heigvd.amt.p2.api.EnrollmentApi;
import ch.heigvd.amt.p2.api.StudentApi;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Environment {

    private final String JWT_SECRET;
    private final int JWT_EXPIRY_PERIOD_IN_MS;

    private StudentApi studentApi = new StudentApi();
    private CourseApi courseApi = new CourseApi();
    private EnrollmentApi enrollmentApi = new EnrollmentApi();

    public StudentApi getStudentApi() {
        return studentApi;
    }

    public CourseApi getCourseApi() {
        return courseApi;
    }

    public EnrollmentApi getEnrollmentApi() {
        return enrollmentApi;
    }

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));

        String url = properties.getProperty("ch.heigvd.amt.p2.second-server.url");
        JWT_SECRET = properties.getProperty("jwt.secret");
        JWT_EXPIRY_PERIOD_IN_MS = Integer.parseInt(properties.getProperty("jwt.default_validity_period"));

        // -- Initialisation de APIs
        studentApi.getApiClient().setBasePath(url);
        courseApi.getApiClient().setBasePath(url);
        enrollmentApi.getApiClient().setBasePath(url);

    }

    public String getFakeToken(String email, Role role) {

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.MILLISECOND, JWT_EXPIRY_PERIOD_IN_MS);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate.getTime())
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .claim("role", role)
                .compact();
    }

}
