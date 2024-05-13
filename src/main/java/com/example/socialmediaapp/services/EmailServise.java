package com.example.socialmediaapp.services;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServise {

    private static final String username = "eldana956@gmail.com";
    private static final String password = "himt yqhk asjf xmis";

    public boolean sendEmail(String emailId, String twoFaCode) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
        message.setSubject("Two Factor Authentication code");
        message.setText("Your Two Factor Authentication code is: "+twoFaCode);
        Transport.send(message);
        return true;
    }
}