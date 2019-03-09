package ru.javaops.masterjava.web;

import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.User;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

//@WebServlet("/")
//@MultipartConfig(location = "/tmp")
public class FileUpload extends HttpServlet {

    private static final Comparator<User> USER_COMPARATOR = Comparator.comparing(User::getValue).thenComparing(User::getEmail);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part uploaded = req.getPart("uploaded");
        if (uploaded == null) {
            resp.sendRedirect("/");
            return;
        }
        if (!"text/xml".equals(uploaded.getContentType())) {
            resp.sendRedirect("/");
            return;
        }

        Set<User> users;
        try (InputStream is = uploaded.getInputStream()) {
            boolean isJaxb = req.getParameterMap().containsKey("jaxb");
            users = isJaxb ? getUsers(is) : getUsersByStax(is);
        } catch (JAXBException | XMLStreamException e) {
            throw new ServletException(e);
        }
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req, resp);
    }

    private Set<User> getUsers(InputStream is) throws JAXBException {
        JaxbParser parser = new JaxbParser(ObjectFactory.class);
        parser.setSchema(Schemas.ofClasspath("payload.xsd"));
        Payload payload = parser.unmarshal(is);
        TreeSet<User> users = new TreeSet<>(USER_COMPARATOR);
        users.addAll(payload.getUsers().getUser());
        return users;
    }

    private Set<User> getUsersByStax(InputStream is) throws XMLStreamException, JAXBException {
        Set<User> users = new TreeSet<>(USER_COMPARATOR);
        StaxStreamProcessor processor = new StaxStreamProcessor(is);
        JaxbParser parser = new JaxbParser(User.class);
        while (processor.doUntil(XMLEvent.START_ELEMENT, "User")) {
            User user = parser.unmarshal(processor.getReader(), User.class);
            users.add(user);
        }
        return users;
    }
}
