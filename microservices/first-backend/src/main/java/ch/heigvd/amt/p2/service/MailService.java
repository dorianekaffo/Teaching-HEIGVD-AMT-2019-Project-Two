package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.model.Code;
import ch.heigvd.amt.p2.model.User;
import ch.heigvd.amt.p2.security.TokenService;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.email.Recipient;
import org.simplejavamail.mailer.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class MailService {

    @Autowired
    private ITemplateEngine templateEngine;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private Mailer mailer;

    @Value("${code.default_validity_period}")
    private int CODE_DEFAULT_VALIDITY_PERIOD;

    @Value("${simplejavamail.smtp.username}")
    private String SENDER_ADDRESS;

    public void sendPasswordResetMail(User user, HttpServletRequest request) {

        String token = this.tokenService.generateToken(user, 3600000);
        String resetLink = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + "/users/reset_password?token=" + token;

        final Context ctx = new Context();
        ctx.setVariable("user", user);
        ctx.setVariable("reset_link", resetLink);
        final String htmlContent = this.templateEngine.process(
                "forgot_password", ctx);

        // -- Information sur le destinataire
        List<Recipient> to = new ArrayList<>();
        to.add(new Recipient(user.getFirstname(), user.getEmail(), Message.RecipientType.BCC));

        String subject = "Réinitialisation du mot de passe";

        this.sendMail(to, subject, htmlContent);
    }

    public void sendAuthCodeMail(User user) {

        final Context ctx = new Context();
        Code code = this.codeService.create(user, CODE_DEFAULT_VALIDITY_PERIOD);

        ctx.setVariable("code", code);
        ctx.setVariable("user", user);
        final String htmlContent = this.templateEngine.process(
                "code", ctx);

        // -- Information sur le destinataire
        List<Recipient> to = new ArrayList<>();
        to.add(new Recipient(user.getFirstname(), user.getEmail(), Message.RecipientType.BCC));

        String subject = "Réinitialisation du mot de passe";

        this.sendMail(to, subject, htmlContent);
    }

    private void sendMail(List<Recipient> to, String subject, String content) {
        Email email = EmailBuilder.startingBlank()
                .from(new Recipient("Mailer", SENDER_ADDRESS, Message.RecipientType.BCC))
                .to(to)
                .withSubject(subject)
                .withHTMLText(content)
                .buildEmail();

        mailer.sendMail(email);
    }
}
