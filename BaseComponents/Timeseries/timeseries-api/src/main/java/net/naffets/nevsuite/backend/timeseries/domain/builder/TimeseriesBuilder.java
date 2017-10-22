package net.naffets.nevsuite.backend.timeseries.domain.builder;

import net.naffets.nevsuite.backend.framework.core.base.AbstractDataTransferObjectBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesValueDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesReferenceDTO;

import java.util.List;

/**
 * @author br4sk4 / created on 22.10.2017
 */
public class TimeseriesBuilder extends AbstractDataTransferObjectBuilder<TimeseriesDTO> {

    public TimeseriesBuilder() {
        super(new TimeseriesDTO());
    }

    @Override
    public TimeseriesBuilder fromDTO(TimeseriesDTO timeseries) {
        dto.setReference(timeseries.getReference());
        dto.setValueMap(timeseries.getValueMap());
        return this;
    }

    public TimeseriesBuilder withReference(TimeseriesReferenceDTO reference) {
        dto.setReference(reference);
        return this;
    }

    public TimeseriesBuilder withValueMap(List<TimeseriesValueDTO> data) {
        dto.setValueMap(data);
        return this;
    }
}
