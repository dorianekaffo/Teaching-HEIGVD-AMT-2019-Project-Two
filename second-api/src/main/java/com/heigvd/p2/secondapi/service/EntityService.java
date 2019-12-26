package com.heigvd.p2.secondapi.service;

public interface EntityService<T> {

    public T create(T entity);

    public T update(Long id, T entity);

    public void delete(Long id);

    public T get(Long id);
}
