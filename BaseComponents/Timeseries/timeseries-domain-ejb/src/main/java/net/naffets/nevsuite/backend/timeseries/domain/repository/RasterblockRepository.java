package net.naffets.nevsuite.backend.timeseries.domain.repository;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerRepository;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.Rasterblock;
import net.naffets.nevsuite.backend.timeseries.domain.finder.RasterblockFinder;

import javax.ejb.Local;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
@Local
public interface RasterblockRepository extends EntityManagerRepository<Rasterblock<RasterblockDTO>> {

    RasterblockFinder find();

}
