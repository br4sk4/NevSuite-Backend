package net.naffets.nevsuite.backend.framework.webservice;

import net.naffets.nevsuite.backend.framework.domain.entity.Person;
import net.naffets.nevsuite.backend.framework.domain.service.BackendComponentService;
import net.naffets.nevsuite.backend.framework.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@RestController
@RequestMapping("/ComponentService")
public class BackendComponentWebservice {

    @Autowired
    private BackendComponentService componentService;

    @Autowired
    private PersonService personService;

    @RequestMapping("/respond")
    public String respond() {
        return componentService.respond();
    }

    @RequestMapping("/person")
    public List<Person> findPerson() {
        return personService.findAll();
    }

    @RequestMapping("/person/{id}")
    public Person findPerson(@PathVariable(name = "id") String id) {
        return personService.findByPrimaryKey(id);
    }

}
