package ru.javaops.masterjava.service.mail;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class Mail implements Serializable {
    String to;
    String subject;
    String body;
    List<Attachment> attachments;
}
