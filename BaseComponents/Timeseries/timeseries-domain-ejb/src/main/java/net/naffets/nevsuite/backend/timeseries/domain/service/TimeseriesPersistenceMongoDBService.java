package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.timeseries.domain.facade.TimeseriesPersistenceMongoDBServiceFacade;

import javax.ejb.Local;

/**
 * @author br4sk4
 * created on 30.10.2016
 */
@Local
public interface TimeseriesPersistenceMongoDBService extends TimeseriesPersistenceMongoDBServiceFacade {
}
