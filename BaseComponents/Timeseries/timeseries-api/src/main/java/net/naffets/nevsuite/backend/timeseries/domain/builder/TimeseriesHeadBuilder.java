package net.naffets.nevsuite.backend.timeseries.domain.builder;

import net.naffets.nevsuite.backend.framework.core.base.AbstractDataTransferObjectBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;

/**
 * @author br4sk4
 * created on 16.10.2016
 */
public class TimeseriesHeadBuilder extends AbstractDataTransferObjectBuilder<TimeseriesHeadDTO> {

    public TimeseriesHeadBuilder() {
        super(new TimeseriesHeadDTO());
    }

    @Override
    public AbstractDataTransferObjectBuilder<TimeseriesHeadDTO> fromDTO(TimeseriesHeadDTO timeseriesHeadDTO) {
        this.dto.setPrimaryKey(timeseriesHeadDTO.getPrimaryKey());
        this.dto.setIdentifier(timeseriesHeadDTO.getIdentifier());
        this.dto.setType(timeseriesHeadDTO.getType());
        this.dto.setDerivationType(timeseriesHeadDTO.getDerivationType());
        this.dto.setPersistence(timeseriesHeadDTO.getPersistence());
        this.dto.setPeriodicity(timeseriesHeadDTO.getPeriodicity());
        this.dto.setBlockSize(timeseriesHeadDTO.getBlockSize());
        this.dto.setRasterType(timeseriesHeadDTO.getRasterType());
        return this;
    }

    public TimeseriesHeadBuilder withPrimaryKey(String value) {
        dto.setPrimaryKey(value);
        return this;
    }

    public TimeseriesHeadBuilder withIdentifier(String value) {
        dto.setIdentifier(value);
        return this;
    }

    public TimeseriesHeadBuilder withType(String value) {
        dto.setType(value);
        return this;
    }

    public TimeseriesHeadBuilder withDerivationType(String value) {
        dto.setDerivationType(value);
        return this;
    }

    public TimeseriesHeadBuilder withPersistence(String value) {
        dto.setPersistence(value);
        return this;
    }

    public TimeseriesHeadBuilder withPeriodicity(String value) {
        dto.setPeriodicity(value);
        return this;
    }

    public TimeseriesHeadBuilder withBlockSize(String value) {
        dto.setBlockSize(value);
        return this;
    }

    public TimeseriesHeadBuilder withRasterType(String value) {
        dto.setRasterType(value);
        return this;
    }

}
