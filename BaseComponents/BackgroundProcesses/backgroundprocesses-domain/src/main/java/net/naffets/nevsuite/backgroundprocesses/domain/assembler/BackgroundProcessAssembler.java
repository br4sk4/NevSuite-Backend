package net.naffets.nevsuite.backgroundprocesses.domain.assembler;

import net.naffets.nevsuite.backgroundprocesses.domain.dto.BackgroundProcessDTO;
import net.naffets.nevsuite.backgroundprocesses.domain.entity.BackgroundProcess;
import net.naffets.nevsuite.framework.core.base.AbstractAssembler;

/**
 * @author br4sk4
 * created on 17.04.18
 */
public class BackgroundProcessAssembler extends AbstractAssembler<BackgroundProcess, BackgroundProcessDTO> {

    @Override
    public BackgroundProcessDTO toDTO(BackgroundProcess backgroundProcess) {
        return BackgroundProcessDTO.builder()
                .primaryKey(backgroundProcess.getPrimaryKey())
                .name(backgroundProcess.getName())
                .start(backgroundProcess.getStart().toString())
                .end(backgroundProcess.getEnd().toString())
                .status(backgroundProcess.getStatus().toString())
                .build();
    }
}
