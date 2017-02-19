package net.naffets.nevsuite.test.unit.timeseries.lang.persistence;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.lang.annotation.PersistenceUnitMock;
import net.naffets.nevsuite.backend.timeseries.domain.repository.TimeseriesHeadRepositoryImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * @author br4sk4
 * created on 27.06.2016
 */
@RequestScoped
@PersistenceUnitMock
public class TimeseriesHeadRepositoryMock extends TimeseriesHeadRepositoryImpl {

    @Inject
    public TimeseriesHeadRepositoryMock(@PersistenceUnitMock EntityManagerProducer entityManagerProducer) {
        super(entityManagerProducer);
    }

}
