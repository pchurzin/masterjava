package ru.javaops.masterjava.persist.model;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity {
    @NonNull
    private String name;

    @NonNull
    private String description;
}
