package ru.javaops.masterjava.web.handler;

import com.sun.xml.ws.api.handler.MessageHandlerContext;

public class SoapServerSecurityHandler extends SoapBaseHandler {

    @Override
    public boolean handleMessage(MessageHandlerContext context) {
        if (isOutbound(context)) {
            return true;
        }
        authorize(context);
        return true;
    }

    @Override
    public boolean handleFault(MessageHandlerContext context) {
        return true;
    }

    protected void authorize(MessageHandlerContext context) throws SecurityException {

    }
}
