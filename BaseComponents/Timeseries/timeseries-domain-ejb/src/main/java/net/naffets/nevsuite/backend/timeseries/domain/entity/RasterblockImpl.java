package net.naffets.nevsuite.backend.timeseries.domain.entity;

import net.naffets.nevsuite.backend.framework.core.api.Reference;
import net.naffets.nevsuite.backend.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.backend.framework.core.base.AbstractReference;
import net.naffets.nevsuite.backend.timeseries.domain.builder.IntervalBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.builder.RasterblockBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
@Entity
@Table(name = "T_META_RASTERBLOCK")
public class RasterblockImpl extends AbstractEntityBean<Rasterblock, RasterblockDTO> implements Rasterblock<RasterblockDTO>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "MRBL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mrbl")
    @SequenceGenerator(name = "mrbl", sequenceName = "S_MRBL", allocationSize = 500)
    protected Long primaryKey;

    @Column(name = "MRBL_UUID")
    @Basic(optional = false)
    protected String uuid;

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

    public RasterblockImpl() {
        this.interval = new Interval();
    }

    @Override
    public Long getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getTimeseriesIdentifier() {
        return timeseriesIdentifier;
    }

    @Override
    public void setTimeseriesIdentifier(String timeseriesIdentifier) {
        this.timeseriesIdentifier = timeseriesIdentifier;
    }

    @Override
    public Interval getInterval() {
        return interval;
    }

    @Override
    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    @Override
    public String getValueMap() {
        return valueMap;
    }

    @Override
    public void setValueMap(String valueMap) {
        this.valueMap = valueMap;
    }

    @Override
    public RasterblockDTO asDTO() {
        return new RasterblockBuilder()
                .withPrimaryKey(this.getPrimaryKey())
                .withUuid(this.getUuid())
                .withTimeseriesIdentifier(this.getTimeseriesIdentifier())
                .withInterval(this.getInterval() != null ? new IntervalBuilder()
                        .withTimestampFrom(this.getInterval().getInstantFrom())
                        .withTimestampTo(this.getInterval().getInstantTo())
                        .build()
                        : null)
                .withValueMap(this.getValueMap())
                .build();
    }

    @Override
    public Rasterblock<RasterblockDTO> fromDTO(RasterblockDTO dto) {
        this.setPrimaryKey(dto.getPrimaryKey() > 0 ? dto.getPrimaryKey() : null);
        this.setUuid(dto.getUuid());
        this.setTimeseriesIdentifier(dto.getTimeseriesIdentifier());
        this.setInterval(dto.getInterval() != null ? new Interval(
                dto.getInterval().getTimestampFrom(),
                dto.getInterval().getTimestampTo())
                : null);
        this.setValueMap(dto.getValueMap());

        return this;
    }

    @Override
    public Reference asReference() {
        return new AbstractReference(this) {
            @Override
            public String getRepresentableName() {
                return "Rasterblock";
            }

            @Override
            public String getTypeDiscriminator() {
                return "MRBL";
            }
        };
    }
}
