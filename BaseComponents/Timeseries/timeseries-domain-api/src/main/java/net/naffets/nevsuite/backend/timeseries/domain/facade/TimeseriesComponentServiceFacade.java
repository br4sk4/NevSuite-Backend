package net.naffets.nevsuite.backend.timeseries.domain.facade;

import net.naffets.nevsuite.backend.framework.core.api.ServiceFacade;
import net.naffets.nevsuite.backend.framework.lang.annotation.Facade;

import javax.ejb.Remote;

/**
 * @author br4sk4
 * created on 25.12.2016
 */
@Remote
@Facade(module = "timeseries-domain-ejb-0.11", implementation = "TimeseriesComponentServiceImpl")
public interface TimeseriesComponentServiceFacade extends ServiceFacade {

    String respond();

}
