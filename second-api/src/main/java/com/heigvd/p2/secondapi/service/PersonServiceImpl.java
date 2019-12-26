package com.heigvd.p2.secondapi.service;

import com.heigvd.p2.secondapi.repository.PersonRepository;
import com.heigvd.p2.secondapi.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements EntityService<Person> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person create(Person entity) {
        return this.personRepository.save(entity);
    }

    @Override
    public Person update(Long id, Person entity) {
        if (this.personRepository.existsById(id) && entity.getId() != null) {
            return this.personRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        this.personRepository.deleteById(id);
    }

    @Override
    public Person get(Long id) {
        return this.personRepository.findById(id).get();
    }
}
