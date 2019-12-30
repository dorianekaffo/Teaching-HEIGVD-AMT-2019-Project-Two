package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.exception.RessourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEntityService<T, U> {

    public T create(T entity);

    public T update(U id, T entity) throws RessourceNotFoundException;

    public void delete(U id) throws RessourceNotFoundException;

    public T get(U id) throws RessourceNotFoundException;

    public Page<T> get(Pageable pgble);
}
