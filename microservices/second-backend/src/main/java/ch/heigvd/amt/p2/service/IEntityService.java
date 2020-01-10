package ch.heigvd.amt.p2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEntityService<T, U> {

    public T create(T entity);

    public T update(U id, T entity);

    public void delete(U id);

    public T get(U id);

    public Page<T> getAll(Pageable pgble);

    public long count();
}
