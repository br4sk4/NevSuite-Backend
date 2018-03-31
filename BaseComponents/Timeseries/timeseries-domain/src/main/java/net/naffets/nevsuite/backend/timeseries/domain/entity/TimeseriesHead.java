package net.naffets.nevsuite.backend.timeseries.domain.entity;

import lombok.*;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.AbstractReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 11.06.2015
 */
@Entity
@Table(name = "t_meta_timeseries_head")
@AttributeOverride(name = "primaryKey", column = @Column(name = "mets_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class TimeseriesHead extends AbstractEntityBean implements Serializable {

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

    @Builder
    public TimeseriesHead(
            String primaryKey,
            String identifier,
            TimeseriesType type,
            TimeseriesDerivationType derivationType,
            TimeseriesRastertype rasterType,
            TimeseriesBlocksize blockSize,
            TimeseriesPersistence persistence,
            TimeseriesPeriodicity periodicity) {
        super(primaryKey);
        this.identifier = identifier;
        this.type = type;
        this.derivationType = derivationType;
        this.rasterType = rasterType;
        this.blockSize = blockSize;
        this.persistence = persistence;
        this.periodicity = periodicity;
    }

    @Override
    public Reference asReference() {
        return new AbstractReference(this) {
            @Override
            public String getRepresentableName() {
                return identifier;
            }

            @Override
            public String getTypeDiscriminator() {
                return this.getClass().getSimpleName();
            }
        };
    }
}
