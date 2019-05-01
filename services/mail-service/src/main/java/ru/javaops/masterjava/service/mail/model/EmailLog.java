package ru.javaops.masterjava.service.mail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class EmailLog {
    @NonNull
    String to;

    String cc;

    @NonNull
    String subject;

    @NonNull
    String result;
}
