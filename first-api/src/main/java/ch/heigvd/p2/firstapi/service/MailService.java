package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.model.Code;
import ch.heigvd.p2.firstapi.model.User;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.email.Recipient;
import org.simplejavamail.mailer.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Message;
import java.util.ArrayList;
import java.util.List;

@Component
public class MailService {

    @Autowired
    private ITemplateEngine templateEngine;

    @Autowired
    private Mailer mailer;

    public void sendPasswordResetMail(User user) {

        final Context ctx = new Context();
        ctx.setVariable("user", user);
        ctx.setVariable("reset_link", "");
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
        ctx.setVariable("code", new Code(user));
        ctx.setVariable("user", user);
        final String htmlContent = this.templateEngine.process(
                "auth_code", ctx);

        // -- Information sur le destinataire
        List<Recipient> to = new ArrayList<>();
        to.add(new Recipient(user.getFirstname(), user.getEmail(), Message.RecipientType.BCC));

        String subject = "Réinitialisation du mot de passe";

        this.sendMail(to, subject, htmlContent);
    }

    private void sendMail(List<Recipient> to, String subject, String content) {
        Email email = EmailBuilder.startingBlank()
                .from(new Recipient("Mailer", "", Message.RecipientType.BCC))
                .to(to)
                .withSubject(subject)
                .withHTMLText(content)
                .buildEmail();

        mailer.sendMail(email);
    }
}
