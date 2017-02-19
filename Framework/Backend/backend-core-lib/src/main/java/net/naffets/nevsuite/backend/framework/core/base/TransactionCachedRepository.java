package net.naffets.nevsuite.backend.framework.core.base;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.core.api.EntityManagerRepository;
import net.naffets.nevsuite.backend.framework.core.api.Finder;

/**
 * @author br4sk4
 * created on 16.09.2015
 */
public class TransactionCachedRepository<E> extends EntityManagerRepositoryAbstract<E> implements EntityManagerRepository<E> {

    private int cacheSize;
    private int cacheCounter;
    private EntityManagerRepository<E> decoratedRepository;

    public TransactionCachedRepository(EntityManagerRepository<E> decoratedRepository) {
        super((EntityManagerProducer) null);
        this.decoratedRepository = decoratedRepository;
        this.cacheSize = 500;
        this.cacheCounter = 0;
    }

    public TransactionCachedRepository(EntityManagerRepository<E> decoratedRepository, int cacheSize) {
        super((EntityManagerProducer) null);
        this.decoratedRepository = decoratedRepository;
        this.cacheSize = cacheSize;
        this.cacheCounter = 0;
    }

    @Override
    public Finder<E> find() {
        return decoratedRepository.find();
    }

    @Override
    public String insert(E entity) {
        String result;

        this.decoratedRepository.beginTransaction();
        result = this.decoratedRepository.insert(entity);

        cacheCounter++;
        if (cacheCounter >= cacheSize)
            this.flush();

        return result;
    }

    @Override
    public String update(E entity) {
        String result;

        this.decoratedRepository.beginTransaction();
        result = this.decoratedRepository.update(entity);

        cacheCounter++;
        if (cacheCounter >= cacheSize)
            this.flush();

        return result;
    }

    @Override
    public String delete(E entity) {
        String result;

        this.decoratedRepository.beginTransaction();
        result = this.decoratedRepository.delete(entity);

        cacheCounter++;
        if (cacheCounter >= cacheSize)
            this.flush();

        return result;
    }

    public void flush() {
        this.cacheCounter = 0;
        if (this.decoratedRepository.isTransactionActive())
            this.decoratedRepository.commitTransaction();
    }

}
