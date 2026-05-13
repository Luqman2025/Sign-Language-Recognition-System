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
        String host = BuildConfig.SMTP_HOST;
        String port = BuildConfig.SMTP_PORT;
        String username = BuildConfig.SMTP_USERNAME;
        String password = BuildConfig.SMTP_PASSWORD;

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
