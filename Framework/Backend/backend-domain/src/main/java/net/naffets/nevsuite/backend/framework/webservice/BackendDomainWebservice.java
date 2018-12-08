package net.naffets.nevsuite.backend.framework.webservice;

import net.naffets.nevsuite.backend.framework.domain.service.BackendDomainService;
import net.naffets.nevsuite.framework.domain.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * @author br4sk4 / created on 13.06.2018
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/DomainService")
public class BackendDomainWebservice {

    private BackendDomainService domainService;

    @Inject
    public BackendDomainWebservice(BackendDomainService domainService) {
        this.domainService = domainService;
    }

    @RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> findUsers() {
        return domainService.findUsers();
    }

    @RequestMapping(value = "/users/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO createUser() {
        return domainService.createUser();
    }

}
