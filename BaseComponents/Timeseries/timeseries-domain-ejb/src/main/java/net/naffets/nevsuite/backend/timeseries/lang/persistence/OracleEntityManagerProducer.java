package net.naffets.nevsuite.backend.timeseries.lang.persistence;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.lang.annotation.OraclePersistenceUnit;

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
@OraclePersistenceUnit
public class OracleEntityManagerProducer implements EntityManagerProducer {

    @PersistenceUnit(unitName = "NevSuite-Oracle-PU")
    protected EntityManagerFactory entityManagerFactoryOraclePU;

    @Override
    @Produces
    @OraclePersistenceUnit
    @RequestScoped
    public EntityManager createEntityManager() {
        return this.entityManagerFactoryOraclePU.createEntityManager();
    }

    @Override
    public void disposeEntityManager(@Disposes @OraclePersistenceUnit EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
