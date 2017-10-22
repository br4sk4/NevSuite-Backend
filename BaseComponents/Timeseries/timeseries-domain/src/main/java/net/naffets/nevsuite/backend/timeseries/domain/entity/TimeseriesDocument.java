package net.naffets.nevsuite.backend.timeseries.domain.entity;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;
import net.naffets.nevsuite.backend.framework.core.api.Reference;
import net.naffets.nevsuite.backend.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
@Entity
@Table(name = "t_meta_timeseries_document")
@AttributeOverride(name = "primaryKey", column = @Column(name = "tdoc_id"))
public class TimeseriesDocument<DTO extends DataTransferObject> extends AbstractEntityBean<DTO> implements Serializable {

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

    public String getTimeseriesIdentifier() {
        return timeseriesIdentifier;
    }

    public void setTimeseriesIdentifier(String timeseriesIdentifier) {
        this.timeseriesIdentifier = timeseriesIdentifier;
    }

    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }

    public Instant getInstantVersion() {
        return this.version != null ? version.toInstant() : null;
    }

    public void setInstantVersion(Instant version) {
        this.version = version != null ? Timestamp.from(version) : null;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public String getValueMap() {
        return valueMap;
    }

    public void setValueMap(String valueMap) {
        this.valueMap = valueMap;
    }

    @Override
    public DTO asDTO() {
        return null;
    }

    @Override
    public Reference asReference() {
        return null;
    }

}
