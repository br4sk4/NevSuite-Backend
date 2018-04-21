package net.naffets.nevsuite.backend.timeseries.domain.entity;

import lombok.*;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.BaseReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
@Entity
@Table(name = "t_meta_timeseries_document")
@AttributeOverride(name = "primaryKey", column = @Column(name = "tdoc_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class TimeseriesDocument extends AbstractEntityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "tdoc_ts_identifier")
    protected String timeseriesIdentifier;

    @Column(name = "tdoc_version")
    protected Timestamp version;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "timestampFrom", column = @Column(name = "tdoc_timestamp_from")),
            @AttributeOverride(name = "timestampTo", column = @Column(name = "tdoc_timestamp_to"))
    })
    protected Interval interval;

    @Lob
    @Column(name = "tdoc_valuemap")
    protected String valueMap;

    @Builder
    public TimeseriesDocument(
            String primaryKey,
            String timeseriesIdentifier,
            Timestamp version,
            Interval interval,
            String valueMap) {
        super(primaryKey);
        this.timeseriesIdentifier = timeseriesIdentifier;
        this.version = version;
        this.interval = interval;
        this.valueMap = valueMap;
    }

    @Override
    public Reference asReference() {
        return new BaseReference(this) {
            @Override
            public String getRepresentableName() {
                return timeseriesIdentifier + " : " + interval.getInstantFrom() + " - " + interval.getInstantTo();
            }
        };
    }

}
