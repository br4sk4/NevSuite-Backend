package net.naffets.nevsuite.backend.timeseries.domain.factory;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.domain.builder.ValueMapBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.ValueDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.Rasterblock;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
public class RasterblockFactory {

    public Rasterblock createNew() {
        Rasterblock entity = new Rasterblock();
        entity.setPrimaryKey(null);
        return entity;
    }

    public List<Rasterblock> fromValueMap(
            String timeseriesIdentifier,
            HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap
    ) {
        LinkedList<Rasterblock> rblList = new LinkedList<>();
        SortedSet<Instant> keySet = new TreeSet<>(valueMap.keySet());

        keySet.stream().filter(timestamp -> timestamp.atZone(ZoneId.of("CET")).toLocalTime().equals(LocalTime.MIDNIGHT)).forEach(day -> {
            Rasterblock rbl = new Rasterblock();
            rbl.setPrimaryKey(UUID.randomUUID().toString());
            rbl.setTimeseriesIdentifier(timeseriesIdentifier);
            rbl.setInterval(new Interval(day, day.atZone(ZoneId.of("CET")).plusDays(1).toInstant()));
            final ValueMapBuilder vmBuilder = new ValueMapBuilder();
            keySet.stream()
                    .filter(timestamp -> timestamp.isBefore(rbl.getInterval().getInstantTo()) && (timestamp.equals(rbl.getInterval().getInstantFrom()) || timestamp.isAfter(rbl.getInterval().getInstantFrom())))
                    .forEach(timestamp -> {
                        ValueDTO dto = new ValueDTO();
                        dto.setTimestamp(timestamp);
                        dto.setValue(valueMap.get(timestamp).getValue());
                        dto.setStatus(new String(valueMap.get(timestamp).getStatus(), Charset.forName("UTF-8")));
                        vmBuilder.withValue(dto);
                    });
            rbl.setValueMap(vmBuilder.toJson());
            rblList.add(rbl);
        });

        return rblList;
    }

    public List<Rasterblock> fromTimeseriesDTO(
            TimeseriesDTO timeseries
    ) {
        HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap = new HashMap<>();

        timeseries.getValueMap().getValue().stream()
                .sorted(Comparator.comparing(ValueDTO::getTimestamp))
                .forEach(valueDTO -> valueMap.put(
                        valueDTO.getTimestamp(),
                        new ValueStatusPair<>(
                                valueDTO.getValue(),
                                valueDTO.getStatus().getBytes(Charset.forName("UTF-8"))))
                );

        return this.fromValueMap(timeseries.getHead().getIdentifier(), valueMap);
    }

}
