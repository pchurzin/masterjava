package ru.javaops.masterjava.webapp;

import lombok.extern.slf4j.Slf4j;
import ru.javaops.masterjava.service.mail.GroupResult;
import ru.javaops.masterjava.service.mail.MailWSClient;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/send")
@MultipartConfig
@Slf4j
public class SendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;
        try {
            log.info("Start sending");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String[] users = req.getParameterValues("users");
            String subject = req.getParameter("subject");
            String body = req.getParameter("body");

            Set<DataHandler> attachments = new HashSet<>();
            for (Part p : req.getParts()) {
                if (!"attachment".equals(p.getName()) || p.getSize() == 0) {
                    continue;
                }
                ByteArrayDataSource ds = new ByteArrayDataSource(p.getInputStream(), p.getContentType());
                ds.setName(p.getSubmittedFileName());
                DataHandler dh = new DataHandler(ds);
                attachments.add(dh);
            }

            GroupResult groupResult = MailWSClient.sendBulk(MailWSClient.split(users), subject, body, attachments);
            result = groupResult.toString();
            log.info("Processing finished with result: {}", result);
        } catch (Exception e) {
            log.error("Processing failed", e);
            result = e.toString();
        }
        resp.getWriter().write(result);
    }
}
