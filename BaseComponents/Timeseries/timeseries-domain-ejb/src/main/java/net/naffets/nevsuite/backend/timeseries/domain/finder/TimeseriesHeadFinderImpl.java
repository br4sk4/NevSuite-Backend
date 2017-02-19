package net.naffets.nevsuite.backend.timeseries.domain.finder;

import com.mysema.query.jpa.impl.JPAQuery;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.QTimeseriesHeadImpl;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author br4sk4
 * created on 14.07.2016
 */
public class TimeseriesHeadFinderImpl implements TimeseriesHeadFinder {

    private EntityManager em;

    public TimeseriesHeadFinderImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public TimeseriesHead<TimeseriesHeadDTO> byUuid(String uuid) {
        QTimeseriesHeadImpl timeseriesHeadCriteria = QTimeseriesHeadImpl.timeseriesHeadImpl;

        TimeseriesHead<TimeseriesHeadDTO> entity = new JPAQuery(em).from(timeseriesHeadCriteria)
                .where(timeseriesHeadCriteria.uuid.eq(uuid))
                .uniqueResult(timeseriesHeadCriteria);

        if (entity != null) em.refresh(entity);

        return entity;
    }

    @Override
    public List<TimeseriesHead<TimeseriesHeadDTO>> byIdentifier(String identifier) {
        QTimeseriesHeadImpl timeseriesHeadCriteria = QTimeseriesHeadImpl.timeseriesHeadImpl;

        identifier = identifier.equals("") ? "%" : identifier;

        List<TimeseriesHead<TimeseriesHeadDTO>> entities = new JPAQuery(em).from(timeseriesHeadCriteria)
                .where(timeseriesHeadCriteria.identifier.like(identifier))
                .orderBy(timeseriesHeadCriteria.identifier.asc())
                .list(timeseriesHeadCriteria)
                .stream()
                .collect(Collectors.toList());

        entities.stream().forEach(em::refresh);

        return entities;
    }

    @Override
    public List<TimeseriesHead<TimeseriesHeadDTO>> all() {
        QTimeseriesHeadImpl timeseriesHeadCriteria = QTimeseriesHeadImpl.timeseriesHeadImpl;

        List<TimeseriesHead<TimeseriesHeadDTO>> entities = new JPAQuery(em)
                .from(timeseriesHeadCriteria)
                .orderBy(timeseriesHeadCriteria.identifier.asc())
                .list(timeseriesHeadCriteria)
                .stream()
                .collect(Collectors.toList());

        entities.stream().forEach(em::refresh);

        return entities;
    }

}
