package net.naffets.nevsuite.backend.timeseries.domain.assembler;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.Timeseries;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesValueDTO;
import net.naffets.nevsuite.framework.core.base.AbstractAssembler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author br4sk4 / created on 04.11.2017
 */
@Service
public class TimeseriesAssembler<T> extends AbstractAssembler<Timeseries<T>, TimeseriesDTO> {

    @Override
    public TimeseriesDTO toDTO(Timeseries<T> timeseries) {
        return this.convert(timeseries, null, null);
    }

    public TimeseriesDTO toDTO(Timeseries<T> timeseries, Instant timestampFrom, Instant timestampTo) {
        return this.convert(timeseries, timestampFrom, timestampTo);
    }

    public String toJson(Timeseries<T> timeseries, Instant timestampFrom, Instant timestampTo) {
        return super.marshal(this.convert(timeseries, timestampFrom, timestampTo), jsonMediaType);
    }

    public String toXml(Timeseries<T> timeseries, Instant timestampFrom, Instant timestampTo) {
        return super.marshal(this.convert(timeseries, timestampFrom, timestampTo), xmlMediaType);
    }

    private TimeseriesDTO convert(Timeseries<T> timeseries, Instant timestampFrom, Instant timestampTo) {
        Instant from = timestampFrom != null ? timestampFrom : timeseries.getSpan().getTimestampFrom();
        Instant to = timestampTo != null ? timestampTo : timeseries.getSpan().getTimestampTo();

        if (isNull(from)) from = Instant.MIN;
        if (isNull(to)) to = Instant.MAX;

        return TimeseriesDTO.builder()
                .identifier(timeseries.getTimeseriesIdentifier())
                .valueMap(timeseries.getValueMap(new TimeseriesInterval(from, to)).keySet().stream()
                        .sorted(Instant::compareTo)
                        .map(timestamp -> TimeseriesValueDTO.builder()
                                .timestamp(timestamp.toString())
                                .value(timeseries.getValue(timestamp) != null ? timeseries.getValue(timestamp).toString() : "0")
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
