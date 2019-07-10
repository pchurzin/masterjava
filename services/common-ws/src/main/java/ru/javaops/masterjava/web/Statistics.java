package ru.javaops.masterjava.web;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Statistics {
    public enum RESULT {
        SUCCESS, FAIL
    }

    public static void count(String payload, long startTime, RESULT result) {
        long now = System.currentTimeMillis();
        int ms = (int) (now - startTime);
        log.info("\n" + payload + "\n" + result.name() + "\nexecution time(ms): " + ms);
        // place for statistics staff

    }
}
