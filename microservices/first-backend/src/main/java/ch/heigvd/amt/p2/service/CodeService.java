package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.model.Code;
import ch.heigvd.amt.p2.model.User;
import ch.heigvd.amt.p2.repository.ICodeRepository;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
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

    public boolean check(User user, String codeString) throws ResourceNotFoundException {
        Code code = this.codeRepository.findByOwnerAndCodeAndExpired(user, codeString, false).orElseThrow(
                () -> new ResourceNotFoundException("Code", "User - CodeString - Expired", user + " - " + codeString + " - " + false));

        if (code != null) {
            System.out.println("Expiry Date: " + code.getExpiryDate() + ", Current time: " + Calendar.getInstance().getTime() + " : Compare : " + code.getExpiryDate().before(Calendar.getInstance().getTime()));

            code.setExpired(true);
            this.codeRepository.save(code);

            if (code.getExpiryDate().before(Calendar.getInstance().getTime())) {
                return false;
            }

            return true;
        }

        return false;
    };
}
