package ru.javaops.masterjava.persist.model;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class City extends BaseEntity {
    @NonNull
    private String key;

    @NonNull
    private String title;
}
