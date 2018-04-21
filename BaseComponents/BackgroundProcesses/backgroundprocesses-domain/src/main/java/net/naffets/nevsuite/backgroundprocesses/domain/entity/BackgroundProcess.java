package net.naffets.nevsuite.backgroundprocesses.domain.entity;

import lombok.*;
import net.naffets.nevsuite.backgroundprocesses.domain.basictype.BackgroundProcessStatus;
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
@Table(name = "T_BGPR_PROCESS")
@AttributeOverride(name = "primaryKey", column = @Column(name = "bprc_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class BackgroundProcess extends AbstractEntityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "BPRC_NAME")
    protected String name;

    @Column(name = "BPRC_START")
    protected Timestamp start;

    @Column(name = "BPRC_END")
    protected Timestamp end;

    @Column(name = "BPRC_STATUS")
    @Enumerated(EnumType.STRING)
    protected BackgroundProcessStatus status;

    @Builder
    public BackgroundProcess(
            String primaryKey,
            String name,
            Timestamp start,
            Timestamp end,
            BackgroundProcessStatus status) {
        super(primaryKey);
        this.name = name;
        this.start = start;
        this.end = end;
        this.status = status;
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
