package com.baldur.model.dao.interfaces;

import java.util.List;

public interface DAO<E, PK> {

    E getById(PK id);

    List<E> getAll();

    E save(E entity);

    PK insert(E entity);

    int update(E entity);

    int delete(E entity);
}