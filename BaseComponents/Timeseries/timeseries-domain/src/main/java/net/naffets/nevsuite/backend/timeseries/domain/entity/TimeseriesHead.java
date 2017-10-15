package net.naffets.nevsuite.backend.timeseries.domain.entity;

import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 11.06.2015
 */
@Entity
@Table(name = "T_META_TIMESERIES_HEAD")
public class TimeseriesHead implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "METS_ID")
    protected String primaryKey;

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

    public String getPrimaryKey() {
        return this.primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
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

}
