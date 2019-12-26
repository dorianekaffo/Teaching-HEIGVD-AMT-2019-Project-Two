package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.model.Code;
import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.repository.ICodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;

public class CodeService {

    @Autowired
    private ICodeRepository codeRepository;

    @Value("${code.default_validity_period}")
    private int DEFAULT_VALIDITY_IN_MS;

    public Code create(User user) {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.MILLISECOND, DEFAULT_VALIDITY_IN_MS);
        Code code = new Code(user, expiryDate.getTime());
        return this.codeRepository.save(code);
    }

    public Code create(User user, int validityPeriod) {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.MILLISECOND, validityPeriod);
        Code code = new Code(user, expiryDate.getTime());
        return this.codeRepository.save(code);
    }

    public boolean check(User user, String codeString) {
        Code code = this.codeRepository.findByUserAndCodeAndExpired(user, codeString, false).get();

        if (code != null) {
            if (code.getExpiryDate().before(Calendar.getInstance().getTime())) {
                code.setExpired(true);
                this.codeRepository.save(code);
                return false;
            }

            return true;
        }
        return false;
    };
}
