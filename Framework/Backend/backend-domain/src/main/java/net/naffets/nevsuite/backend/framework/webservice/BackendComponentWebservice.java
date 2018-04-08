package net.naffets.nevsuite.backend.framework.webservice;

import net.naffets.nevsuite.backend.framework.domain.service.BackendComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ComponentService")
public class BackendComponentWebservice {

    @Autowired
    private BackendComponentService componentService;

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
