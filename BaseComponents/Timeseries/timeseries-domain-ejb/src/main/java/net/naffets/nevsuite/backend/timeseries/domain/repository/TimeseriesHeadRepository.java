package net.naffets.nevsuite.backend.timeseries.domain.repository;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerRepository;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.finder.TimeseriesHeadFinder;

import javax.ejb.Local;

/**
 * User:    Braska<br />
 * Date:    11.11.14<br />
 * <br />
 * <br />
 */
@Local
public interface TimeseriesHeadRepository extends EntityManagerRepository<TimeseriesHead<TimeseriesHeadDTO>> {

    TimeseriesHeadFinder find();

}
