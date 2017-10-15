package net.naffets.nevsuite.backend.timeseries.domain.builder;

import net.naffets.nevsuite.backend.framework.core.base.AbstractDataTransferObjectBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.ValueListDTO;

/**
 * @author br4sk4
 * created on 22.10.2016
 */
public class TimeseriesBuilder extends AbstractDataTransferObjectBuilder<TimeseriesDTO> {

    public TimeseriesBuilder() {
        super(new TimeseriesDTO());
    }

    @Override
    public AbstractDataTransferObjectBuilder<TimeseriesDTO> fromDTO(TimeseriesDTO timeseriesDTO) {
        dto.setHead(timeseriesDTO.getHead());
        dto.setValueMap(timeseriesDTO.getValueMap());
        return this;
    }

    public TimeseriesBuilder withTimeseriesHead(TimeseriesHeadDTO value) {
        dto.setHead(value);
        return this;
    }

    public TimeseriesBuilder withValueMap(ValueListDTO value) {
        dto.setValueMap(value);
        return this;
    }

}
