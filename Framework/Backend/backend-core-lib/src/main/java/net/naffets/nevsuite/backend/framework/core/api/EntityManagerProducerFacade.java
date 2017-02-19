package net.naffets.nevsuite.backend.framework.core.api;

import javax.persistence.EntityManager;

/**
 * @author br4sk4
 * created on 23.06.2016
 */
public interface EntityManagerProducerFacade {

    EntityManager createEntityManager();

    void disposeEntityManager(EntityManager entityManager);

}
