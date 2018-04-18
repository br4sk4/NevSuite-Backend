package net.naffets.nevsuite.backgroundprocesses.webservice;

import net.naffets.nevsuite.backgroundprocesses.domain.service.BackgroundProcessesComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author br4sk4
 * created on 18.04.18
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ComponentService")
public class BackgroundProcessesComponentWebservice {

    @Autowired
    private BackgroundProcessesComponentService componentService;

    @RequestMapping("/respond")
    public String respond() {
        return componentService.respond();
    }
}
