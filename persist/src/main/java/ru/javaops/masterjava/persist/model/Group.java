package ru.javaops.masterjava.persist.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Group extends BaseEntity {
    @NonNull
    private String name;

    @NonNull
    private GroupType type;

    @NonNull
    private Project project;

    public Group(Integer id, String name, GroupType groupType, Project project) {
        this(name, groupType,project);
        this.id = id;
    }
}