package net.naffets.nevsuite.backend.timeseries.domain.assembler;

import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.framework.core.base.AbstractAssembler;

/**
 * @author br4sk4 / created on 16.10.2016
 */
public class TimeseriesHeadAssembler extends AbstractAssembler<TimeseriesHead, TimeseriesHeadDTO> {

    @Override
    public TimeseriesHeadDTO toDTO(TimeseriesHead timeseriesHead) {
        return TimeseriesHeadDTO.builder()
                .primaryKey(timeseriesHead.getPrimaryKey())
                .identifier(timeseriesHead.getIdentifier())
                .type(timeseriesHead.getType().toString())
                .derivationType(timeseriesHead.getDerivationType().toString())
                .persistence(timeseriesHead.getPersistence().toString())
                .periodicity(timeseriesHead.getPeriodicity().toString())
                .blockSize(timeseriesHead.getBlockSize().toString())
                .rasterType(timeseriesHead.getRasterType().toString())
                .build();
    }

}
