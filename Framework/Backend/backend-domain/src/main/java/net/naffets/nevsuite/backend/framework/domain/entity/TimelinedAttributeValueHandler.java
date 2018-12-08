package net.naffets.nevsuite.backend.framework.domain.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author br4sk4 / created on 13.06.2018
 */
@Embeddable
@NoArgsConstructor
public class TimelinedAttributeValueHandler {

    private String stringValue;
    private Boolean booleanValue;
    private BigDecimal numericValue;

    @Builder
    public TimelinedAttributeValueHandler(
            String stringValue,
            Boolean booleanValue,
            BigDecimal numericValue) {
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.numericValue = numericValue;
        this.validate();
    }

    public String getValue() {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("stringValue", stringValue);
        valueMap.put("booleanValue", booleanValue);
        valueMap.put("numericValue", numericValue);
        Optional<Object> value = valueMap.values().stream()
                .filter(Objects::nonNull)
                .findFirst();
        return value.map(Object::toString).orElse(null);
    }

    private void validate() throws RuntimeException {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("stringValue", stringValue);
        valueMap.put("booleanValue", booleanValue);
        valueMap.put("numericValue", numericValue);

        long valueCount = valueMap.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .count();

        if (valueCount != 1) {
            throw new RuntimeException("The ValueHandler should handle at least one value!");
        }
    }

}
