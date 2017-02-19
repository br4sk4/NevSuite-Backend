package net.naffets.nevsuite.backend.framework.domain.facade;

import net.naffets.nevsuite.backend.framework.core.api.ServiceFacade;
import net.naffets.nevsuite.backend.framework.lang.annotation.Facade;

import javax.ejb.Remote;

/**
 * @author br4sk4
 * created on 06.09.2015
 */
@Remote
@Facade(module = "backend-domain-ejb-0.11", implementation = "BackendComponentServiceImpl")
public interface BackendComponentServiceFacade extends ServiceFacade {

    String respond();

}
