package net.naffets.nevsuite.backend.framework.core.api;

/**
 * @author br4sk4
 * created on 16.09.2015
 */
public interface EntityManagerRepository<E> {

    Finder<E> find();

    String insert(E entity);

    String update(E entity);

    String delete(E entity);

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

    boolean isTransactionActive();

}
