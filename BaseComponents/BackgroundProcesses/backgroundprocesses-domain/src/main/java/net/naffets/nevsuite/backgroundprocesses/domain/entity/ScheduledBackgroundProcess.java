package net.naffets.nevsuite.backgroundprocesses.domain.entity;

import lombok.*;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.BaseReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author br4sk4
 * created on 03.07.2016
 */
@Entity
@Table(name = "T_BGPR_PROCESS_SCHEDULE")
@AttributeOverride(name = "primaryKey", column = @Column(name = "bpsc_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ScheduledBackgroundProcess extends AbstractEntityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "BPSC_NAME")
    protected String name;
    @Column(name = "BPSC_ACTIVE")
    protected Boolean active;
    @Column(name = "BPSC_SERIES")
    protected Boolean series;
    @Column(name = "BPSC_PERIOD_UNIT")
    protected String periodUnit;
    @Column(name = "BPSC_PERIOD_VALUE")
    protected String periodValue;
    @Column(name = "BPSC_START")
    private Timestamp start;

    @Builder
    public ScheduledBackgroundProcess(
            String primaryKey,
            String name,
            Boolean active,
            Boolean series,
            String periodUnit,
            String periodValue,
            Timestamp start) {
        super(primaryKey);
        this.name = name;
        this.active = active;
        this.series = series;
        this.periodUnit = periodUnit;
        this.periodValue = periodValue;
        this.start = start;
    }

    @Override
    public Reference asReference() {
        return new BaseReference(this) {
            @Override
            public String getRepresentableName() {
                return name;
            }
        };
    }
}
