package net.naffets.nevsuite.backend.framework.webservice;

import net.naffets.nevsuite.backend.framework.domain.service.BackendComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
