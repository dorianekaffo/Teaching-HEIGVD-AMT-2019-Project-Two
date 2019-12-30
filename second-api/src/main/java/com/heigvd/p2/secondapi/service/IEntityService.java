package com.heigvd.p2.secondapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEntityService<T> {

    public T create(T entity);

    public T update(Long id, T entity);

    public void delete(Long id);

    public T get(Long id);

    public Page<T> getAll(Pageable pgble);
}
