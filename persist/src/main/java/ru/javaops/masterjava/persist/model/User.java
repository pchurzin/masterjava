package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends BaseEntity {
    @Column("full_name")
    @NonNull
    private String fullName;

    @NonNull
    private String email;

    @NonNull
    private UserFlag flag;

    @NonNull
    private City city;

    public User(Integer id, String fullName, String email, UserFlag flag, City city) {
        this(fullName, email, flag, city);
        this.id = id;
    }
}