package net.naffets.nevsuite.backend.framework.domain.entity;

import lombok.*;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author br4sk4 / created on 13.06.2018
 */
@Entity
@Table(name = "T_TIMELINED_ATTRIBUTE")
@AttributeOverride(name = "primaryKey", column = @Column(name = "attribute_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class TimelinedAttributeValue<T extends AbstractEntityBean> extends AbstractEntityBean {

    @ManyToOne(targetEntity = AbstractTimelinedAttributeEntity.class)
    @JoinColumn(name = "user_id")
    private T entity;

    @Column(name = "attribute")
    private String attribute;

    @Column(name = "timestamp")
    private Instant timestamp;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "stringValue", column = @Column(name = "stringvalue")),
            @AttributeOverride(name = "booleanValue", column = @Column(name = "booleanvalue")),
            @AttributeOverride(name = "numericValue", column = @Column(name = "numericvalue"))
    })
    private TimelinedAttributeValueHandler valueHandler;

    @Builder
    public TimelinedAttributeValue(
            String attribute,
            Instant timestamp,
            Object value) {
        this.attribute = attribute;
        this.timestamp = timestamp;
        this.setValue(value);
    }

    public String getValue() {
        return this.valueHandler.getValue();
    }

    public final void setValue(Object value) {
        this.valueHandler = TimelinedAttributeValueHandler.builder()
                .stringValue(value instanceof String ? (String)value : null)
                .booleanValue(value instanceof Boolean ? (Boolean)value : null)
                .numericValue(value instanceof BigDecimal ? (BigDecimal)value : null)
                .build();
    }

    @Override
    public Reference asReference() {
        return null;
    }

}
