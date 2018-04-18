package net.naffets.nevsuite.backgroundprocesses.domain.assembler;

import net.naffets.nevsuite.backgroundprocesses.domain.dto.ScheduledBackgroundProcessDTO;
import net.naffets.nevsuite.backgroundprocesses.domain.entity.ScheduledBackgroundProcess;
import net.naffets.nevsuite.framework.core.base.AbstractAssembler;

/**
 * @author br4sk4
 * created on 17.04.18
 */
public class ScheduledBackgroundProcessAssembler extends AbstractAssembler<ScheduledBackgroundProcess, ScheduledBackgroundProcessDTO> {

    @Override
    public ScheduledBackgroundProcessDTO toDTO(ScheduledBackgroundProcess scheduledBackgroundProcess) {
        return ScheduledBackgroundProcessDTO.builder()
                .primaryKey(scheduledBackgroundProcess.getPrimaryKey())
                .name(scheduledBackgroundProcess.getName())
                .active(scheduledBackgroundProcess.getActive().toString())
                .series(scheduledBackgroundProcess.getSeries().toString())
                .periodUnit(scheduledBackgroundProcess.getPeriodUnit())
                .periodValue(scheduledBackgroundProcess.getPeriodValue())
                .start(scheduledBackgroundProcess.getStart().toString())
                .build();
    }
}
