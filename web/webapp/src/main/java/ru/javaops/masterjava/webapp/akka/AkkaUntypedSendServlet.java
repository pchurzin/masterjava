package ru.javaops.masterjava.webapp.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import lombok.extern.slf4j.Slf4j;
import ru.javaops.masterjava.service.mail.util.MailUtils;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.javaops.masterjava.webapp.WebUtil.createMailObject;
import static ru.javaops.masterjava.webapp.akka.AkkaWebappListener.akkaActivator;

@WebServlet(value = "/sendAkkaUntyped", loadOnStartup = 1, asyncSupported = true)
@Slf4j
@MultipartConfig
public class AkkaUntypedSendServlet extends HttpServlet {
    private ActorRef webappActor;
    private ActorRef mailActor;
    private static Map<Integer, AsyncContext> contexts = new ConcurrentHashMap<>();
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webappActor = akkaActivator.startActor(WebappActor.class, "mail-client");
        mailActor = akkaActivator.getActorRef("akka.tcp://MailService@127.0.0.1:2553/user/mail-actor");
    }

    private static void writeResultToResponse(int code, String result) throws IOException {
        if (contexts.containsKey(code)) {
            AsyncContext ac = contexts.get(code);
            PrintWriter writer = ac.getResponse().getWriter();
            writer.println(result);
            writer.flush();
            ac.complete();
            contexts.remove(code);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AsyncContext ac = req.startAsync();
        MailUtils.AkkaMailObject akkaMailObject = new MailUtils.AkkaMailObject(createMailObject(req), ac.hashCode());
        contexts.put(ac.hashCode(), ac);
        executor.submit(() -> mailActor.tell(akkaMailObject, webappActor));
    }

    public static class WebappActor extends AbstractActor {
        @Override
        public Receive createReceive() {
            return receiveBuilder().match(MailUtils.AkkaGroupResult.class,
                    akkaGroupResult -> {
                        log.info(akkaGroupResult.toString());
                        AkkaUntypedSendServlet.writeResultToResponse(akkaGroupResult.getCode(), akkaGroupResult.getGroupResult().toString());
                    })
                    .build();
        }
    }
}