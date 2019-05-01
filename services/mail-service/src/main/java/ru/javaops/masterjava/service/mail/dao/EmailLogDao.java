package ru.javaops.masterjava.service.mail.dao;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import ru.javaops.masterjava.persist.dao.AbstractDao;
import ru.javaops.masterjava.service.mail.model.EmailLog;

public abstract class EmailLogDao implements AbstractDao {

    @SqlUpdate("TRUNCATE email_log")
    @Override
    public abstract void clean();

    @SqlUpdate("INSERT INTO email_log (\"to\", cc, subject, result) VALUES(:to, :cc, :subject, :result)")
    public abstract void insert(@BindBean EmailLog emailLog);
}
