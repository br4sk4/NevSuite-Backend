package net.naffets.nevsuite.backend.timeseries.domain.factory;

import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.lang.exception.InvalidTimeseriesConfigException;

/**
 * @author br4sk4
 * created on 02.07.2015
 */
public class TimeseriesHeadFactory {

    public static TimeseriesHead fromDTO(TimeseriesHeadDTO dto) {
        TimeseriesHead entity = new TimeseriesHead();

        entity.setPrimaryKey(dto.getPrimaryKey());
        entity.setIdentifier(dto.getIdentifier());
        entity.setType(dto.getType() != null ? TimeseriesType.valueOf(dto.getType()) : null);
        entity.setDerivationType(dto.getDerivationType() != null ? TimeseriesDerivationType.valueOf(dto.getDerivationType()) : null);
        entity.setPersistence(dto.getPersistence() != null ? TimeseriesPersistence.valueOf(dto.getPersistence()) : null);
        entity.setPeriodicity(dto.getPeriodicity() != null ? TimeseriesPeriodicity.valueOf(dto.getPeriodicity()) : null);
        entity.setBlockSize(dto.getBlockSize() != null ? TimeseriesBlocksize.valueOf(dto.getBlockSize()) : null);
        entity.setRasterType(dto.getRasterType() != null ? TimeseriesRastertype.valueOf(dto.getRasterType()) : null);

        return entity;
    }

    public TimeseriesHead createEmpty() {
        TimeseriesHead entity = new TimeseriesHead();
        entity.setPrimaryKey(null);
        return entity;
    }

    public TimeseriesHead createNew() {
        TimeseriesHead entity = new TimeseriesHead();
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

        TimeseriesHead ts = new TimeseriesHead(
                identifier,
                type,
                derivationType,
                rasterType,
                blockSize,
                persistence,
                periodicity);

        try {
            // validation
        } catch (Exception e) {
            if (e.getClass().equals(InvalidTimeseriesConfigException.class))
                throw (InvalidTimeseriesConfigException) e;
            else
                e.printStackTrace();
        }

        return ts;
    }

}
