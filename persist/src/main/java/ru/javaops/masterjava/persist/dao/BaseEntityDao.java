package ru.javaops.masterjava.persist.dao;

import ru.javaops.masterjava.persist.PersistException;
import ru.javaops.masterjava.persist.model.BaseEntity;

public abstract class BaseEntityDao<T extends BaseEntity> implements AbstractDao {
    public T save(T entity) {
        saveDependents(entity);
        if (entity.isNew()) {
            int id = insert(entity);
            entity.setId(id);
        } else {
            int updatedRows = update(entity);
            if (updatedRows != 1) {
                throw new PersistException("Can't update " + entity);
            }
        }
        return entity;
    }

    public abstract T getById(int key);

    protected abstract int insert(T entity);

    protected abstract int update(T entity);

    protected void saveDependents(T entity) {

    }
}
