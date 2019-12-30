package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.exception.RessourceNotFoundException;
import ch.heigvd.p2.firstapi.model.Code;
import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.repository.ICodeRepository;
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

    public boolean check(User user, String codeString) throws RessourceNotFoundException {
        Code code = this.codeRepository.findByOwnerAndCodeAndExpired(user, codeString, false).orElseThrow(
                () -> new RessourceNotFoundException("Code", "User - CodeString - Expired", user + " - " + codeString + " - " + false));

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
