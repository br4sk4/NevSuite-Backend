package net.naffets.nevsuite.backend.timeseries.domain.entity;

import net.naffets.nevsuite.backend.framework.core.api.Reference;
import net.naffets.nevsuite.backend.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.backend.framework.core.base.AbstractReference;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesHeadBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.lang.util.TimeseriesRasterblockungHelper;
import net.naffets.nevsuite.backend.timeseries.lang.validation.TimeseriesRasterblockungValidationConstraint;
import net.naffets.nevsuite.backend.timeseries.lang.validation.TimeseriesTypeValidationConstraint;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 11.06.2015
 */
@Entity
@Table(name = "T_META_TIMESERIES_HEAD")
public class TimeseriesHeadImpl extends AbstractEntityBean<TimeseriesHead, TimeseriesHeadDTO> implements TimeseriesHead<TimeseriesHeadDTO>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "METS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mets")
    @SequenceGenerator(name = "mets", sequenceName = "S_METS", allocationSize = 1)
    protected Long primaryKey;

    @Column(name = "METS_UUID")
    @Basic(optional = false)
    protected String uuid;

    @Column(name = "METS_IDENTIFIER")
    protected String identifier;

    // Type - Information
    @Enumerated(EnumType.STRING)
    @Column(name = "METS_TYPE")
    protected TimeseriesType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "METS_DERIVATIONTYPE")
    protected TimeseriesDerivationType derivationType;
    @Enumerated(EnumType.STRING)
    @Column(name = "METS_PERSISTENCE")
    protected TimeseriesPersistence persistence;
    @Enumerated(EnumType.STRING)
    @Column(name = "METS_PERIODICITY")
    protected TimeseriesPeriodicity periodicity;

    // Rasterblockung - Information
    @Enumerated(EnumType.STRING)
    @Column(name = "METS_BLOCKSIZE")
    protected TimeseriesBlocksize blockSize;
    @Enumerated(EnumType.STRING)
    @Column(name = "METS_RASTERTYPE")
    protected TimeseriesRastertype rasterType;

    public TimeseriesHeadImpl() {
    }

    public TimeseriesHeadImpl(String identifier,
                              TimeseriesType type,
                              TimeseriesDerivationType derivationType,
                              TimeseriesRastertype rasterType,
                              TimeseriesBlocksize blockSize,
                              TimeseriesPersistence persistence,
                              TimeseriesPeriodicity periodicity) {

        super();

        this.identifier = identifier;
        this.type = type;
        this.derivationType = derivationType;
        this.rasterType = rasterType;
        this.blockSize = blockSize;
        this.persistence = persistence;
        this.periodicity = periodicity;

        this.initializeVaidators();

    }

    private void initializeVaidators() {
        this.addValidationConstraint(new TimeseriesTypeValidationConstraint());
        this.addValidationConstraint(new TimeseriesRasterblockungValidationConstraint());
    }

    public void setBlockSize(TimeseriesBlocksize blockSize) {
        this.blockSize = blockSize;
    }

    public void setRasterType(TimeseriesRastertype rasterType) {
        this.rasterType = rasterType;
    }

    public Long getPrimaryKey() {
        return this.primaryKey;
    }

    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public TimeseriesType getType() {
        return type;
    }

    public void setType(TimeseriesType type) {
        this.type = type;
    }

    public TimeseriesDerivationType getDerivationType() {
        return derivationType;
    }

    public void setDerivationType(TimeseriesDerivationType derivationType) {
        this.derivationType = derivationType;
    }

    public TimeseriesBlocksize getBlocksize() {
        return blockSize;
    }

    public TimeseriesRastertype getRastertype() {
        return rasterType;
    }

    public TimeseriesPersistence getPersistence() {
        return persistence;
    }

    public void setPersistence(TimeseriesPersistence persistence) {
        this.persistence = persistence;
    }

    public TimeseriesPeriodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(TimeseriesPeriodicity periodicity) {
        this.periodicity = periodicity;
    }

    public Integer getBlockValueCount() {

        return TimeseriesRasterblockungHelper.getRasterblockungValueCount(
                this.getRastertype(),
                this.getBlocksize()
        ).intValue();
    }

    @Override
    public TimeseriesHeadDTO asDTO() {
        return new TimeseriesHeadBuilder()
                .withPrimaryKey(this.getPrimaryKey())
                .withUuid(this.getUuid())
                .withIdentifier(this.getIdentifier())
                .withType(this.getType() != null ? this.getType().toString() : null)
                .withDerivationType(this.getDerivationType() != null ? this.getDerivationType().toString() : null)
                .withPeriodicity(this.getPeriodicity() != null ? this.getPeriodicity().toString() : null)
                .withPersistence(this.getPersistence() != null ? this.getPersistence().toString() : null)
                .withRasterType(this.getRastertype() != null ? this.getRastertype().toString() : null)
                .withBlockSize(this.getBlocksize() != null ? this.getBlocksize().toString() : null)
                .build();
    }

    @Override
    public TimeseriesHead<TimeseriesHeadDTO> fromDTO(TimeseriesHeadDTO dto) {
        this.setPrimaryKey(dto.getPrimaryKey() > 0 ? dto.getPrimaryKey() : null);
        this.setUuid(dto.getUuid());
        this.setIdentifier(dto.getIdentifier());
        this.setType(dto.getType() != null ? TimeseriesType.valueOf(dto.getType()) : null);
        this.setDerivationType(dto.getDerivationType() != null ? TimeseriesDerivationType.valueOf(dto.getDerivationType()) : null);
        this.setPeriodicity(dto.getPeriodicity() != null ? TimeseriesPeriodicity.valueOf(dto.getPeriodicity()) : null);
        this.setPersistence(dto.getPersistence() != null ? TimeseriesPersistence.valueOf(dto.getPersistence()) : null);
        this.setRasterType(dto.getRasterType() != null ? TimeseriesRastertype.valueOf(dto.getRasterType()) : null);
        this.setBlockSize(dto.getBlockSize() != null ? TimeseriesBlocksize.valueOf(dto.getBlockSize()) : null);
        return this;
    }

    @Override
    public Reference asReference() {
        return new AbstractReference(this) {
            @Override
            public String getRepresentableName() {
                return "TimeseriesHead";
            }

            @Override
            public String getTypeDiscriminator() {
                return "METS";
            }
        };
    }

}
