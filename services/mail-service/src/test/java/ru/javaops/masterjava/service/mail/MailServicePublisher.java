package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import ru.javaops.masterjava.persist.DBITestProvider;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Endpoint;
import java.io.File;
import java.net.URL;
import java.util.List;

public class MailServicePublisher {

    public static void main(String[] args) {
        DBITestProvider.initDBI();

        Endpoint endpoint = Endpoint.create(new MailServiceImpl());
        URL resource = Resources.getResource("WEB-INF/wsdl/mailService.wsdl");
        List<Source> metadata = ImmutableList.of(
                new StreamSource(
                        new File(resource.getPath())));

        endpoint.setMetadata(metadata);
        endpoint.publish("http://localhost:8080/mail/mailService");
    }
}
