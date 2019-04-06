package ru.javaops.masterjava.persist.dao.bind;

import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;
import ru.javaops.masterjava.persist.model.User;

import java.lang.annotation.*;

@BindingAnnotation(BindUser.UserBindingFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface BindUser {
    public static class UserBindingFactory implements BinderFactory {
        @Override
        public Binder build(Annotation annotation) {
            return new Binder<BindUser, User>() {
                @Override
                public void bind(SQLStatement q, BindUser bind, User arg) {
                    q.bind("id", arg.getId());
                    q.bind("fullName", arg.getFullName());
                    q.bind("email", arg.getEmail());
                    q.bind("flag", arg.getFlag());
                    q.bind("city", arg.getCity().getId());
                }
            };
        }
    }
}
