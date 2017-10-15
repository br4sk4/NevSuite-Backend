package net.naffets.nevsuite.backend.framework.domain.service;

import net.naffets.nevsuite.backend.framework.domain.entity.Person;
import net.naffets.nevsuite.backend.framework.domain.repository.persistent.PersonRepository;
import net.naffets.nevsuite.backend.framework.domain.repository.temporary.PersonInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author br4sk4 / created on 13.10.2017
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonInMemoryRepository inMemoryRepository;

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findByPrimaryKey(String uuid) {
        return repository.findOne(uuid);
    }

}
