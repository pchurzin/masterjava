package ru.javaops.masterjava.service.mail.handlers;

import com.sun.xml.ws.api.handler.MessageHandlerContext;
import ru.javaops.masterjava.service.mail.MailWSClient;
import ru.javaops.masterjava.web.AuthUtil;
import ru.javaops.masterjava.web.handler.SoapServerSecurityHandler;

import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

public class SoapServerMailSecurityHandler extends SoapServerSecurityHandler {
    @Override
    protected void authorize(MessageHandlerContext context) throws SecurityException {
        Map<String, List<String>> headers = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);

//        HttpServletRequest request = (HttpServletRequest) mCtx.get(MessageContext.SERVLET_REQUEST);
//        HttpServletResponse response = (HttpServletResponse) mCtx.get(MessageContext.SERVLET_RESPONSE);

        int code = AuthUtil.checkBasicAuth(headers, MailWSClient.AUTH_HEADER);
        if (code != 0) {
            context.put(MessageContext.HTTP_RESPONSE_CODE, code);
            throw new SecurityException();
        }
    }
}
