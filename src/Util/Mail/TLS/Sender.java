package Util.Mail.TLS;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Nirchenko Kostya for GoogleMapsDirectionApplication.
 *
 * @since at 19.07.2017
 */
public class Sender {

    /*private String username;
    private String password;
    private Properties props;

    public Sender(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void send(String subject, String text, String toEmail) {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kosmos12346@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/

    final String username = "nirchenko.kostya@gmail.com";
    final String password = "lol341250";
public void send(String subject, String text) {
    Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

		try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("support_gmda@gmda.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(username));
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
}
