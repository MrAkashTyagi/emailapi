package com.email;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.Authenticator;

import java.util.Properties;

@Service
public class EmailService {


    public boolean sendEmail(String message, String subject, String to) {

        boolean f = false;

        String from = "your email";

        String host = "smtp.gmail.com";

//        get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties : " + properties);
//        setting important information to properties object

//        set host
        properties.put("mail.smtp.host", host);

//        set port
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

//        Step 1:

//        get the session object

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("youremail", "your password");
            }
        });


        session.setDebug(true);

//        Step 2:
//        compose the message

        MimeMessage m = new MimeMessage(session);

        try {
//            from email
            m.setFrom(from);

//            set recipient
            m.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));

//            adding subject to message
            m.setSubject(subject);

//            adding text to message
            m.setText(message);

//            Step: 3 send the message using transport class

            Transport.send(m);

            f = true;


            System.out.println("Send successfully ................");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;

    }


}
