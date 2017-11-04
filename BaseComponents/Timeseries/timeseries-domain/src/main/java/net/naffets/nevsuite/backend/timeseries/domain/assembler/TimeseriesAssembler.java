package net.naffets.nevsuite.backend.timeseries.domain.assembler;

import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesReferenceDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesValueDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author br4sk4 / created on 04.11.2017
 */
@Service
public class TimeseriesAssembler<T> {

    public TimeseriesBuilder assembleTimeseries(TimeseriesHead head, HashMap<Instant, T> valueMap) {
        TimeseriesReferenceDTO referenceDTO = new TimeseriesReferenceDTO();
        List<TimeseriesValueDTO> convertedValueMap = new LinkedList<>();

        referenceDTO.setPrimaryKey(head.getPrimaryKey());
        referenceDTO.setIdentifier(head.getIdentifier());

        valueMap.keySet().stream()
                .sorted(Instant::compareTo)
                .forEach(key -> {
                    TimeseriesValueDTO valueDTO = new TimeseriesValueDTO();
                    valueDTO.setTimestamp(key.toString());
                    valueDTO.setValue(valueMap.get(key).toString());
                    convertedValueMap.add(valueDTO);
                });

        return new TimeseriesBuilder()
                .withReference(referenceDTO)
                .withValueMap(convertedValueMap);
    }

}
