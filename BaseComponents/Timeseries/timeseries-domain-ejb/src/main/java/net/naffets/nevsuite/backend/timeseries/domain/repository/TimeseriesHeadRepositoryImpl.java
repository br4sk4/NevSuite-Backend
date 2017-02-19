package net.naffets.nevsuite.backend.timeseries.domain.repository;

import net.naffets.nevsuite.backend.framework.core.api.EntityManagerProducer;
import net.naffets.nevsuite.backend.framework.core.base.EntityManagerRepositoryAbstract;
import net.naffets.nevsuite.backend.framework.lang.annotation.OraclePersistenceUnit;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.finder.TimeseriesHeadFinder;
import net.naffets.nevsuite.backend.timeseries.domain.finder.TimeseriesHeadFinderImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * User:    Braska<br />
 * Date:    10.11.14<br />
 * <br />
 * <br />
 */
@Stateless
public class TimeseriesHeadRepositoryImpl extends EntityManagerRepositoryAbstract<TimeseriesHead<TimeseriesHeadDTO>> implements TimeseriesHeadRepository {

    @Inject
    public TimeseriesHeadRepositoryImpl(@OraclePersistenceUnit EntityManagerProducer entityManagerProducer) {
        super(entityManagerProducer);
    }

    @Override
    public TimeseriesHeadFinder find() {
        return new TimeseriesHeadFinderImpl(entityManager);
    }

    public String insert(TimeseriesHead<TimeseriesHeadDTO> timeseriesHead) {
        try {
            super.insert(timeseriesHead);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }

    public String update(TimeseriesHead<TimeseriesHeadDTO> timeseriesHead) {
        try {
            TimeseriesHead persistentTimeseriesHead = this.find().byUuid(timeseriesHead.getUuid());

            if (persistentTimeseriesHead != null)
                timeseriesHead.setPrimaryKey(persistentTimeseriesHead.getPrimaryKey());

            super.update(timeseriesHead);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }

    public String delete(TimeseriesHead<TimeseriesHeadDTO> timeseriesHead) {
        try {
            TimeseriesHead<TimeseriesHeadDTO> persistentTimeseriesHead = this.find().byUuid(timeseriesHead.getUuid());

            if (persistentTimeseriesHead != null) {
                super.delete(persistentTimeseriesHead);
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }

}
