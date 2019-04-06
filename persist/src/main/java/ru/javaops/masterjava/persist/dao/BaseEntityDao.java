package ru.javaops.masterjava.persist.dao;

import ru.javaops.masterjava.persist.model.BaseEntity;

public abstract class BaseEntityDao<T extends BaseEntity> implements AbstractDao {
    public T insert(T entity) {
        if (entity.isNew()) {
            int id = insertGeneratedId(entity);
            entity.setId(id);
        } else {
            insertWitId(entity);
        }
        return entity;
    }

    abstract int insertGeneratedId(T entity);

    abstract void insertWitId(T entity);
}
