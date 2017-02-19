package net.naffets.nevsuite.backend.timeseries.domain.entity;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;
import net.naffets.nevsuite.backend.framework.core.api.EntityBean;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
public interface Rasterblock<DTO extends DataTransferObject> extends EntityBean<Rasterblock, DTO> {

    @Override
    Long getPrimaryKey();

    @Override
    void setPrimaryKey(Long primaryKey);

    @Override
    String getUuid();

    @Override
    void setUuid(String uuid);

    String getTimeseriesIdentifier();

    void setTimeseriesIdentifier(String timeseriesIdentifier);

    Interval getInterval();

    void setInterval(Interval interval);

    String getValueMap();

    void setValueMap(String valueMap);

    @Override
    Rasterblock<RasterblockDTO> fromDTO(DTO dto);

}
