package net.naffets.nevsuite.backend.timeseries.domain.entity;

import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
@Entity
@Table(name = "T_META_RASTERBLOCK")
public class Rasterblock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "MRBL_ID")
    protected String primaryKey;

    @Column(name = "MRBL_TS_IDENTIFIER")
    protected String timeseriesIdentifier;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "timestampFrom", column = @Column(name = "MRBL_TIMESTAMP_FROM")),
            @AttributeOverride(name = "timestampTo", column = @Column(name = "MRBL_TIMESTAMP_TO"))
    })
    protected Interval interval;

    @Lob
    @Column(name = "MRBL_VALUEMAP")
    protected String valueMap;

    public Rasterblock() {
        this.interval = new Interval();
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTimeseriesIdentifier() {
        return timeseriesIdentifier;
    }

    public void setTimeseriesIdentifier(String timeseriesIdentifier) {
        this.timeseriesIdentifier = timeseriesIdentifier;
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
}
