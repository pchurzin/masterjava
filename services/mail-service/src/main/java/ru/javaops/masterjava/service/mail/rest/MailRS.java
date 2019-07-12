package ru.javaops.masterjava.service.mail.rest;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.validator.constraints.NotBlank;
import ru.javaops.masterjava.service.mail.Attachment;
import ru.javaops.masterjava.service.mail.GroupResult;
import ru.javaops.masterjava.service.mail.MailServiceExecutor;
import ru.javaops.masterjava.service.mail.MailWSClient;
import ru.javaops.masterjava.service.mail.util.Attachments;
import ru.javaops.masterjava.web.WebStateException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;

@Path("/")
public class MailRS {
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test";
    }

    @POST
    @Path("send")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public GroupResult send(@NotBlank @FormDataParam("users") String users,
                            @FormDataParam("subject") String subject,
                            @NotBlank @FormDataParam("body") String body,
                            @FormDataParam("attach") InputStream file,
                            @FormDataParam("attach") FormDataContentDisposition disposition) throws WebStateException {
        if (file == null) {
            return MailServiceExecutor.sendBulk(MailWSClient.split(users), subject, body, Collections.emptyList());
        } else {
            String name = disposition.getFileName();
            // UTF-8 encoding workaround: https://java.net/jira/browse/JERSEY-3032
            try {
                name = new String(name.getBytes("ISO8859_1"), "UTF-8");
            } catch (UnsupportedEncodingException ignored) {

            }

            Attachment attachment = Attachments.getAttachment(name, file);
            return MailWSClient.sendBulk(MailWSClient.split(users), subject, body, Collections.singletonList(attachment));
        }
    }
}