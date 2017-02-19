package net.naffets.nevsuite.backend.timeseries.domain.builder;

import net.naffets.nevsuite.backend.framework.core.base.AbstractDataTransferObjectBuilder;
import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.domain.dto.ValueDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.ValueListDTO;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author br4sk4
 * created on 09.11.2016
 */
public class ValueMapBuilder extends AbstractDataTransferObjectBuilder<ValueListDTO> {

    public ValueMapBuilder() {
        super(new ValueListDTO());
    }

    @Override
    public AbstractDataTransferObjectBuilder<ValueListDTO> fromDTO(ValueListDTO valueListDTO) {
        this.dto.getValue().addAll(valueListDTO.getValue());
        return this;
    }

    public ValueMapBuilder withValueMap(HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap) {
        SortedSet<Instant> keySet = new TreeSet<>(valueMap.keySet());
        this.dto.getValue().addAll(keySet.stream()
                .map(timestamp -> {
                    ValueStatusPair<BigDecimal, byte[]> vsp = valueMap.get(timestamp);
                    ValueDTO dto = new ValueDTO();
                    dto.setTimestamp(timestamp);
                    dto.setValue(vsp.getValue());
                    dto.setStatus(new ByteArrayInputStream(vsp.getStatus()).toString());
                    return dto;
                }).collect(Collectors.toList()));

        return this;
    }

    public ValueMapBuilder withValue(ValueDTO valueDTO) {
        this.dto.getValue().add(valueDTO);
        return this;
    }

    public ValueMapBuilder withValueList(ValueListDTO valueListDTO) {
        this.dto.getValue().addAll(valueListDTO.getValue());
        return this;
    }

}
