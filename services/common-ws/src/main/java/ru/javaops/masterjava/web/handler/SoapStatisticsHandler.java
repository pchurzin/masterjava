package ru.javaops.masterjava.web.handler;

import com.sun.xml.ws.api.handler.MessageHandlerContext;
import ru.javaops.masterjava.web.Statistics;

public class SoapStatisticsHandler extends SoapBaseHandler {

    private static final String START = SoapStatisticsHandler.class.getSimpleName() + ".START";
    private static final String PAYLOAD = SoapStatisticsHandler.class.getSimpleName() + ".PAYLOAD";

    @Override
    public boolean handleMessage(MessageHandlerContext context) {
        if (isOutbound(context)) {
            addStats(context, Statistics.RESULT.SUCCESS);
        } else {
            Long start = System.currentTimeMillis();
            context.put(START, start);
            context.put(PAYLOAD, getMessageText(context.getMessage().copy()));
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageHandlerContext context) {
        addStats(context, Statistics.RESULT.FAIL);
        return true;
    }

    private void addStats(MessageHandlerContext context, Statistics.RESULT result) {
        long start = (long) context.get(START);
        String payload = (String) context.get(PAYLOAD);
        Statistics.count(payload, start, result);
    }
}
