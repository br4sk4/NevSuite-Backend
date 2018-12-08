package net.naffets.nevsuite.backend.framework.webservice;

import net.naffets.nevsuite.backend.framework.domain.service.BackendComponentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ComponentService")
public class BackendComponentWebservice {

    private BackendComponentService componentService;

    @Inject
    public BackendComponentWebservice(
            BackendComponentService componentService) {
        this.componentService = componentService;
    }

    @RequestMapping("/respond")
    public String respond() {
        return componentService.respond();
    }

    @RequestMapping(value = "/services", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> services() {
        return componentService.services();
    }

    @RequestMapping(value = "/services/{service}/instances", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> instances(@PathVariable String service) {
        return componentService.instances(service);
    }

}
