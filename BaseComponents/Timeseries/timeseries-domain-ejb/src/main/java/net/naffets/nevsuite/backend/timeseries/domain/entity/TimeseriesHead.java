package net.naffets.nevsuite.backend.timeseries.domain.entity;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;
import net.naffets.nevsuite.backend.framework.core.api.EntityBean;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;

/**
 * @author br4sk4
 * created on 12.09.2015
 */
public interface TimeseriesHead<DTO extends DataTransferObject> extends EntityBean<TimeseriesHead, DTO> {

    void setBlockSize(TimeseriesBlocksize blockSize);

    void setRasterType(TimeseriesRastertype rasterType);

    Long getPrimaryKey();

    void setPrimaryKey(Long setPrimaryKey);

    String getUuid();

    void setUuid(String uuid);

    String getIdentifier();

    void setIdentifier(String identifier);

    TimeseriesType getType();

    void setType(TimeseriesType type);

    TimeseriesDerivationType getDerivationType();

    void setDerivationType(TimeseriesDerivationType derivationType);

    TimeseriesBlocksize getBlocksize();

    TimeseriesRastertype getRastertype();

    TimeseriesPersistence getPersistence();

    void setPersistence(TimeseriesPersistence persistence);

    TimeseriesPeriodicity getPeriodicity();

    void setPeriodicity(TimeseriesPeriodicity periodicity);

    Integer getBlockValueCount();

    @Override
    TimeseriesHead<DTO> fromDTO(DTO dto);

}
