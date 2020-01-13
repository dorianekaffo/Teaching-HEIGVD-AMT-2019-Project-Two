package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEntityService<T, U> {

    public T create(T entity);

    public T update(U id, T entity) throws ResourceNotFoundException;

    public void delete(U id) throws ResourceNotFoundException;

    public T get(U id) throws ResourceNotFoundException;

    public Page<T> getAll(Pageable pgble);

    public long count();
}
