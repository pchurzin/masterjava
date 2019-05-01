package ru.javaops.masterjava.service.mail;

import org.apache.commons.mail.EmailException;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.service.mail.dao.EmailLogDao;
import ru.javaops.masterjava.service.mail.model.EmailLog;

import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService")
public class MailServiceImpl implements MailService {
    private static EmailLogDao emailLogDao = DBIProvider.getDao(EmailLogDao.class);

    private static String getEmailString(List<Addressee> a) {
        List<String> emails = a.stream()
                .map(Addressee::getEmail)
                .collect(Collectors.toList());
        return String.join(";", emails);
    }

    public void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        EmailLog emailLog = new EmailLog(
                getEmailString(to),
                getEmailString(cc),
                subject, "no result");
        try {
            MailSender.sendMail(to, cc, subject, body);
            emailLog.setResult("Successful");
        } catch (EmailException e) {
            emailLog.setResult(e.getMessage());
        }
        emailLogDao.insert(emailLog);
    }
}