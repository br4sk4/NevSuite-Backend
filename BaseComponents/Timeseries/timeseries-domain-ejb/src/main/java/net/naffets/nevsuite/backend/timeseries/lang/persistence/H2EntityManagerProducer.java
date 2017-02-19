package net.naffets.nevsuite.backend.timeseries.lang.persistence;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.lang.annotation.H2PersistenceUnit;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @author br4sk4
 * created on 25.06.2016
 */
@Stateful
@H2PersistenceUnit
public class H2EntityManagerProducer implements EntityManagerProducer {

    @PersistenceUnit(unitName = "NevSuite-H2-PU")
    protected EntityManagerFactory entityManagerFactoryH2PU;

    @Override
    @Produces
    @H2PersistenceUnit
    @RequestScoped
    public EntityManager createEntityManager() {
        return this.entityManagerFactoryH2PU.createEntityManager();
    }

    @Override
    public void disposeEntityManager(@Disposes @H2PersistenceUnit EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

}
