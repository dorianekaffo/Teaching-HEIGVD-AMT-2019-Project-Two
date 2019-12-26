package ch.heigvd.p2.firstapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntityService<T, U> {

    public T create(T entity);

    public T update(U id, T entity);

    public void delete(U id);

    public T get(U id);

    public Page<T> get(Pageable pgble);
}
