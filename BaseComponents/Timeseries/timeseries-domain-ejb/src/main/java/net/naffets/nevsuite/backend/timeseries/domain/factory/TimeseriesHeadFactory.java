package net.naffets.nevsuite.backend.timeseries.domain.factory;

import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHeadImpl;
import net.naffets.nevsuite.backend.timeseries.lang.exception.InvalidTimeseriesConfigException;

import javax.enterprise.context.RequestScoped;

/**
 * @author br4sk4
 * created on 02.07.2015
 */
@RequestScoped
public class TimeseriesHeadFactory {

    public static TimeseriesHeadImpl fromDTO(TimeseriesHeadDTO dto) {
        TimeseriesHeadImpl entity = new TimeseriesHeadImpl();

        entity.setPrimaryKey(dto.getPrimaryKey() > 0 ? dto.getPrimaryKey() : null);
        entity.setUuid(dto.getUuid());
        entity.setIdentifier(dto.getIdentifier());
        entity.setType(dto.getType() != null ? TimeseriesType.valueOf(dto.getType()) : null);
        entity.setDerivationType(dto.getDerivationType() != null ? TimeseriesDerivationType.valueOf(dto.getDerivationType()) : null);
        entity.setPersistence(dto.getPersistence() != null ? TimeseriesPersistence.valueOf(dto.getPersistence()) : null);
        entity.setPeriodicity(dto.getPeriodicity() != null ? TimeseriesPeriodicity.valueOf(dto.getPeriodicity()) : null);
        entity.setBlockSize(dto.getBlockSize() != null ? TimeseriesBlocksize.valueOf(dto.getBlockSize()) : null);
        entity.setRasterType(dto.getRasterType() != null ? TimeseriesRastertype.valueOf(dto.getRasterType()) : null);

        return entity;
    }

    public TimeseriesHead<TimeseriesHeadDTO> createEmpty() {
        TimeseriesHeadImpl entity = new TimeseriesHeadImpl();
        entity.setPrimaryKey(null);
        entity.setUuid(null);
        return entity;
    }

    public TimeseriesHead<TimeseriesHeadDTO> createNew() {
        TimeseriesHeadImpl entity = new TimeseriesHeadImpl();
        entity.setPrimaryKey(null);
        return entity;
    }

    public TimeseriesHead createTimeseriesHead(String identifier,
                                               TimeseriesType type,
                                               TimeseriesDerivationType derivationType,
                                               TimeseriesRastertype rasterType,
                                               TimeseriesBlocksize blockSize,
                                               TimeseriesPersistence persistence,
                                               TimeseriesPeriodicity periodicity) throws InvalidTimeseriesConfigException {

        TimeseriesHeadImpl ts = new TimeseriesHeadImpl(
                identifier,
                type,
                derivationType,
                rasterType,
                blockSize,
                persistence,
                periodicity);

        try {
            ts.validate();
        } catch (Exception e) {
            if (e.getClass().equals(InvalidTimeseriesConfigException.class))
                throw (InvalidTimeseriesConfigException) e;
            else
                e.printStackTrace();
        }

        return ts;
    }

}
