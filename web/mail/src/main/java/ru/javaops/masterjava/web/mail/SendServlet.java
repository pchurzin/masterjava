package ru.javaops.masterjava.web.mail;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.thymeleaf.context.WebContext;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.UserDao;
import ru.javaops.masterjava.persist.model.User;
import ru.javaops.masterjava.service.mail.Addressee;
import ru.javaops.masterjava.service.mail.MailWSClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.javaops.masterjava.common.web.ThymeleafListener.engine;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
@Slf4j
public class SendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao dao = DBIProvider.getDao(UserDao.class);
        List<User> users = dao.getWithLimit(Integer.MAX_VALUE);
        resp.setCharacterEncoding("utf-8");
        final WebContext webContext = new WebContext(req, resp, req.getServletContext(), req.getLocale(),
                ImmutableMap.of("message", "Send email params", "users", users));
        engine.process("send", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subj = req.getParameter("subj");
        String body = req.getParameter("body");
        String[] emails = req.getParameterValues("emails");
        boolean isBulk = req.getParameter("isBulk") != null;
        String result = "";
        Set<Addressee> addressees = StreamEx.of(emails).map(Addressee::new).toSet();
        if (isBulk) {
            result = MailWSClient.sendBulk(addressees, subj, body).toString();
        } else {
            result = MailWSClient.sendToGroup(addressees, Collections.emptySet(), subj, body);
        }

        final WebContext webContext = new WebContext(req, resp, req.getServletContext(), req.getLocale(),
                ImmutableMap.of("message", result));
        engine.process("send", webContext, resp.getWriter());
    }
}
