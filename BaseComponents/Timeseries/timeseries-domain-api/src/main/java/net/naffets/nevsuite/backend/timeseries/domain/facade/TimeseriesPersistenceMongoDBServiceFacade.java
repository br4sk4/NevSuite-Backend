package net.naffets.nevsuite.backend.timeseries.domain.facade;

import net.naffets.nevsuite.backend.framework.core.api.ServiceFacade;
import net.naffets.nevsuite.backend.framework.lang.annotation.Facade;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.strategy.PersistenceStrategy;

import javax.ejb.Remote;

/**
 * @author br4sk4
 * created on 30.10.2016
 */
@Remote
@Facade(module = "timeseries-domain-ejb-0.11", implementation = "TimeseriesPersistenceMongoDBServiceImpl")
public interface TimeseriesPersistenceMongoDBServiceFacade extends ServiceFacade, PersistenceStrategy {
}
