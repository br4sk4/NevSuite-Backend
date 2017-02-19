package net.naffets.nevsuite.backend.framework.core.base;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.core.api.EntityManagerRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author br4sk4
 * created on 16.09.2015
 */
public abstract class EntityManagerRepositoryAbstract<E> implements EntityManagerRepository<E> {

    protected EntityManager entityManager;

    @Inject
    public EntityManagerRepositoryAbstract(EntityManagerProducer entityManagerProducer) {
        if (entityManagerProducer != null)
            entityManager = entityManagerProducer.createEntityManager();
    }

    public EntityManagerRepositoryAbstract(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String insert(E entity) {
        try {
            entityManager.persist(entity);
        } catch (Exception e) {
            return e.getCause() + e.getMessage();
        }

        return "OK";
    }

    public String update(E entity) {
        try {
            entityManager.merge(entity);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }


    public String delete(E entity) {
        try {
            entityManager.remove(entity);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }

    public void beginTransaction() {
        if (!entityManager.getTransaction().isActive())
            entityManager.getTransaction().begin();
    }

    public void commitTransaction() {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();
    }

    public void rollbackTransaction() {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().rollback();
    }

    public boolean isTransactionActive() {
        return entityManager.getTransaction().isActive();
    }

}
