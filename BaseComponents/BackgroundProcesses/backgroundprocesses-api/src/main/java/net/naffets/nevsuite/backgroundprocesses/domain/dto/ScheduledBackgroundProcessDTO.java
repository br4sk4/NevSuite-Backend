package net.naffets.nevsuite.backgroundprocesses.domain.dto;

import lombok.Builder;
import net.naffets.nevsuite.framework.core.api.DataTransferObject;

import java.io.Serializable;

/**
 * @author br4sk4
 * created on 17.04.18
 */
public class ScheduledBackgroundProcessDTO implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    public String primaryKey;
    public String name;
    public String active;
    public String series;
    public String periodUnit;
    public String periodValue;
    public String start;

    @Builder
    public ScheduledBackgroundProcessDTO(
            String primaryKey,
            String name,
            String active,
            String series,
            String periodUnit,
            String periodValue,
            String start) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.active = active;
        this.series = series;
        this.periodUnit = periodUnit;
        this.periodValue = periodValue;
        this.start = start;
    }
}
