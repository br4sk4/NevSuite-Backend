package net.naffets.nevsuite.backend.framework.domain.service;

import net.naffets.nevsuite.backend.framework.domain.facade.BackendComponentServiceFacade;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * @author br4sk4
 * created on 06.09.2015
 */
@Stateless
@Remote(BackendComponentServiceFacade.class)
@Local(BackendComponentService.class)
public class BackendComponentServiceImpl implements BackendComponentServiceFacade {

    public String respond() {
        return "May the force be with you!";
    }

}
