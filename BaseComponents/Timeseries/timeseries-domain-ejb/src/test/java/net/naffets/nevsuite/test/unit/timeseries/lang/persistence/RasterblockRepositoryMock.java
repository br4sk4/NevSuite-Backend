package net.naffets.nevsuite.test.unit.timeseries.lang.persistence;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.lang.annotation.PersistenceUnitMock;
import net.naffets.nevsuite.backend.timeseries.domain.repository.RasterblockRepositoryImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * @author br4sk4
 * created on 24.10.2016
 */
@RequestScoped
@PersistenceUnitMock
public class RasterblockRepositoryMock extends RasterblockRepositoryImpl {

    @Inject
    public RasterblockRepositoryMock(@PersistenceUnitMock EntityManagerProducer entityManagerProducer) {
        super(entityManagerProducer);
    }

}
