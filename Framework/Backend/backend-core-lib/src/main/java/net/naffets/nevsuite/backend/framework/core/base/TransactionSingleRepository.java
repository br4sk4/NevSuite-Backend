package net.naffets.nevsuite.backend.framework.core.base;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.core.api.EntityManagerRepository;
import net.naffets.nevsuite.backend.framework.core.api.Finder;

/**
 * @author br4sk4
 * created on 16.09.2015
 */
public class TransactionSingleRepository<E> extends EntityManagerRepositoryAbstract<E> implements EntityManagerRepository<E> {

    private EntityManagerRepository<E> decoratedRepository;

    public TransactionSingleRepository(EntityManagerRepository<E> decoratedRepository) {
        super((EntityManagerProducer) null);
        this.decoratedRepository = decoratedRepository;
    }

    @Override
    public Finder<E> find() {
        return decoratedRepository.find();
    }

    @Override
    public String insert(E entity) {
        String result;

        if (!this.decoratedRepository.isTransactionActive())
            this.decoratedRepository.beginTransaction();

        result = this.decoratedRepository.insert(entity);

        if ("OK".equals(result))
            this.decoratedRepository.commitTransaction();
        else
            this.decoratedRepository.rollbackTransaction();

        return result;
    }

    @Override
    public String update(E entity) {
        String result;

        if (!this.decoratedRepository.isTransactionActive())
            this.decoratedRepository.beginTransaction();

        result = this.decoratedRepository.update(entity);

        if ("OK".equals(result))
            this.decoratedRepository.commitTransaction();
        else
            this.decoratedRepository.rollbackTransaction();

        return result;
    }

    @Override
    public String delete(E entity) {
        String result;

        if (!this.decoratedRepository.isTransactionActive())
            this.decoratedRepository.beginTransaction();

        result = this.decoratedRepository.delete(entity);

        if ("OK".equals(result))
            this.decoratedRepository.commitTransaction();
        else
            this.decoratedRepository.rollbackTransaction();

        return result;
    }

}
