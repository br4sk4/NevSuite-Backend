package net.naffets.nevsuite.backend.timeseries.domain.builder;

import net.naffets.nevsuite.backend.framework.core.base.AbstractDataTransferObjectBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.IntervalDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;

/**
 * @author br4sk4
 * created on 24.10.2016
 */
public class RasterblockBuilder extends AbstractDataTransferObjectBuilder<RasterblockDTO> {

    public RasterblockBuilder() {
        super(new RasterblockDTO());
    }

    @Override
    public AbstractDataTransferObjectBuilder<RasterblockDTO> fromDTO(RasterblockDTO rasterblockDTO) {
        this.dto.setPrimaryKey(rasterblockDTO.getPrimaryKey());
        this.dto.setTimeseriesIdentifier(rasterblockDTO.getTimeseriesIdentifier());
        this.dto.setInterval(rasterblockDTO.getInterval());
        this.dto.setValueMap(rasterblockDTO.getValueMap());

        return this;
    }

    public RasterblockBuilder withPrimaryKey(String value) {
        dto.setPrimaryKey(value);
        return this;
    }

    public RasterblockBuilder withTimeseriesIdentifier(String value) {
        dto.setTimeseriesIdentifier(value);
        return this;
    }

    public RasterblockBuilder withInterval(IntervalDTO value) {
        dto.setInterval(value != null
                ? new IntervalBuilder()
                .withTimestampFrom(value.getTimestampFrom())
                .withTimestampTo(value.getTimestampTo())
                .build()
                : null
        );

        return this;
    }

    public RasterblockBuilder withValueMap(String valueMap) {
        dto.setValueMap(valueMap);
        return this;
    }

}
