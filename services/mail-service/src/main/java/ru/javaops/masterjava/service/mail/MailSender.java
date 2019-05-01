package ru.javaops.masterjava.service.mail;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.List;

@Slf4j
public class MailSender {
    private static Config mail = ConfigFactory.load("mail").getConfig("mail");

    static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        log.info("Send mail to \'" + to + "\' cc \'" + cc + "\' subject \'" + subject + (log.isDebugEnabled() ? "\nbody=" + body : ""));
        Email email = new SimpleEmail();
        try {
            email.setMsg(body);
            email.setSubject(subject);
            for (Addressee a : to) {
                email.addTo(a.getEmail(), a.getName());
            }
            for (Addressee a : cc) {
                email.addCc(a.getEmail(), a.getName());
            }
            email.setCharset("UTF-8");
            email.setHostName(mail.getString("host"));
            email.setSmtpPort(mail.getInt("port"));
            email.setAuthentication(mail.getString("username"), mail.getString("password"));
            email.setSSLCheckServerIdentity(mail.getBoolean("useSSL"));
            email.setSSLOnConnect(mail.getBoolean("useSSL"));
            email.setStartTLSEnabled(mail.getBoolean("useTLS"));
            email.setFrom(mail.getString("username"), mail.getString("fromName"));
            email.setDebug(mail.getBoolean("debug"));

            email.send();
        } catch (EmailException e) {
            log.warn("The email haven't been sent", e);
        }
    }
}
