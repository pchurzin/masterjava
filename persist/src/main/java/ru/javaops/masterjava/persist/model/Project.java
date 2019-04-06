package ru.javaops.masterjava.persist.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity {
    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private List<Group> groups;
}
