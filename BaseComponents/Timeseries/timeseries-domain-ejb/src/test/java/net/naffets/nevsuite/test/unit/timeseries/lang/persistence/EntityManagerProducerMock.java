package net.naffets.nevsuite.test.unit.timeseries.lang.persistence;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.lang.annotation.PersistenceUnitMock;
import org.apache.deltaspike.jpa.api.entitymanager.PersistenceUnitName;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author br4sk4
 * created on 27.06.2016
 */
@RequestScoped
@PersistenceUnitMock
public class EntityManagerProducerMock implements EntityManagerProducer {

    @Inject
    @PersistenceUnitName(value = "mock")
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }

    public void disposeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

}
