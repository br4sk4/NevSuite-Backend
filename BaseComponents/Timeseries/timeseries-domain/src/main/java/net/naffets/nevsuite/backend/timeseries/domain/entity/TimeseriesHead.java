package net.naffets.nevsuite.backend.timeseries.domain.entity;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;
import net.naffets.nevsuite.backend.framework.core.api.Reference;
import net.naffets.nevsuite.backend.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 11.06.2015
 */
@Entity
@Table(name = "t_meta_timeseries_head")
@AttributeOverride(name = "primaryKey", column = @Column(name = "mets_id"))
public class TimeseriesHead<DTO extends DataTransferObject> extends AbstractEntityBean<DTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "mets_identifier")
    protected String identifier;
    @Enumerated(EnumType.STRING)
    @Column(name = "mets_type")
    protected TimeseriesType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "mets_derivationtype")
    protected TimeseriesDerivationType derivationType;
    @Enumerated(EnumType.STRING)
    @Column(name = "mets_persistence")
    protected TimeseriesPersistence persistence;
    @Enumerated(EnumType.STRING)
    @Column(name = "mets_periodicity")
    protected TimeseriesPeriodicity periodicity;
    @Enumerated(EnumType.STRING)
    @Column(name = "mets_blocksize")
    protected TimeseriesBlocksize blockSize;
    @Enumerated(EnumType.STRING)
    @Column(name = "mets_rastertype")
    protected TimeseriesRastertype rasterType;

    public TimeseriesHead() {
    }

    public TimeseriesHead(String identifier,
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
    }

    public void setBlockSize(TimeseriesBlocksize blockSize) {
        this.blockSize = blockSize;
    }

    public void setRasterType(TimeseriesRastertype rasterType) {
        this.rasterType = rasterType;
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

    @Override
    public DTO asDTO() {
        return null;
    }

    @Override
    public Reference asReference() {
        return null;
    }
}
