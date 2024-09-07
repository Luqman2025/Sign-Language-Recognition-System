package com.example.imagepro;
import android.os.AsyncTask;
import android.util.Log;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "EmailSender";

    @Override
    protected Void doInBackground(Void... voids) {
        // Email sending code goes here
        // Make sure to replace these values with your own SMTP server configuration
        String host = "mail.smtp.host";
        String port = "587"; // or whatever port your SMTP server uses
        String username = "letscommunicateofficially@gmail.com";
        String password = "hkdpoiprrzxjbkfs";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("recipient@example.com"));
            message.setSubject("Subject");
            message.setText("Body");

            Transport.send(message);

            Log.d(TAG, "Email sent successfully");
        } catch (MessagingException e) {
            Log.e(TAG, "Error sending email", e);
        }

        return null;
    }
}
