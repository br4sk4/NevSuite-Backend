package net.naffets.nevsuite.backend.timeseries.domain.builder;

import net.naffets.nevsuite.backend.framework.core.base.AbstractDataTransferObjectBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.IntervalDTO;

import java.time.Instant;

/**
 * @author br4sk4
 * created on 14.10.2016
 */
public class IntervalBuilder extends AbstractDataTransferObjectBuilder<IntervalDTO> {

    public IntervalBuilder() {
        super(new IntervalDTO());
    }

    @Override
    public AbstractDataTransferObjectBuilder<IntervalDTO> fromDTO(IntervalDTO intervalDTO) {
        this.dto.setTimestampFrom(intervalDTO.getTimestampFrom());
        this.dto.setTimestampTo(intervalDTO.getTimestampTo());
        return this;
    }

    public IntervalBuilder withTimestampFrom(Instant value) {
        dto.setTimestampFrom(value);
        return this;
    }

    public IntervalBuilder withTimestampTo(Instant value) {
        dto.setTimestampTo(value);
        return this;
    }

}
