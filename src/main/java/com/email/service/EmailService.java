package com.email.service;
import lombok.Data;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Data
@Service
public class EmailService {
    public boolean sendEmail(String email, String message, String to ) {
        boolean sessionDebug = false;

        String from = "anshikadivya332@gmail.com";

        //Variable for gmail
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        //Setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: to get the session object
      Session session = Session.getInstance(properties, new Authenticator() {
                  @Override
                  protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication("anshika.pandey@tetruscorp.com", "******");
                  }
              });


        session.setDebug(true);

        //Step 2: compose the message

        MimeMessage m = new MimeMessage(session);
        try {
            //from email
            m.setFrom(from);
            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //adding subject to message
            to=to.replaceAll("\\s","_"); //replace whitespace with underscore
            to=to.replaceAll("\\p{Cntrl}",""); //remove control characters
            //adding subject to message
            m.setSubject("Welcome to "+to+" Training");
            //adding text to message
            m.setText(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //Step 3: send the message
        try {
            Transport.send(m);
            System.out.println("Sent success........." +
                    "...........");
            sessionDebug = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
       return sessionDebug;
    }
}

