package net.naffets.nevsuite.backend.timeseries.domain.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.core.base.EntityManagerRepositoryAbstract;
import net.naffets.nevsuite.backend.framework.lang.annotation.OraclePersistenceUnit;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.Rasterblock;
import net.naffets.nevsuite.backend.timeseries.domain.finder.RasterblockFinder;
import net.naffets.nevsuite.backend.timeseries.domain.finder.RasterblockFinderImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
@Stateless
public class RasterblockRepositoryImpl extends EntityManagerRepositoryAbstract<Rasterblock<RasterblockDTO>> implements RasterblockRepository {

    @Inject
    public RasterblockRepositoryImpl(@OraclePersistenceUnit EntityManagerProducer entityManagerProducer) {
        super(entityManagerProducer);
    }

    @Override
    public RasterblockFinder find() {
        return new RasterblockFinderImpl(new JPAQuery(entityManager));
    }

    public String insert(Rasterblock<RasterblockDTO> rasterblock) {
        try {
            super.insert(rasterblock);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }

    public String update(Rasterblock<RasterblockDTO> rasterblock) {
        try {
            Rasterblock persistentRasterblock = this.find().byUuid(rasterblock.getUuid());

            if (persistentRasterblock != null)
                rasterblock.setPrimaryKey(persistentRasterblock.getPrimaryKey());

            super.update(rasterblock);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }

    public String delete(Rasterblock<RasterblockDTO> rasterblock) {
        try {
            Rasterblock<RasterblockDTO> persistentRasterblock = this.find().byUuid(rasterblock.getUuid());

            if (persistentRasterblock != null) {
                super.delete(persistentRasterblock);
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }

}
