package net.naffets.nevsuite.backgroundprocesses.domain.service;

import net.naffets.nevsuite.backgroundprocesses.domain.assembler.BackgroundProcessAssembler;
import net.naffets.nevsuite.backgroundprocesses.domain.basictype.BackgroundProcessStatus;
import net.naffets.nevsuite.backgroundprocesses.domain.dto.BackgroundProcessDTO;
import net.naffets.nevsuite.backgroundprocesses.domain.dto.ScheduledBackgroundProcessDTO;
import net.naffets.nevsuite.backgroundprocesses.domain.entity.BackgroundProcess;
import net.naffets.nevsuite.backgroundprocesses.domain.entity.ScheduledBackgroundProcess;
import net.naffets.nevsuite.backgroundprocesses.domain.repository.BackgroundProcessRepository;
import net.naffets.nevsuite.backgroundprocesses.domain.repository.ScheduledBackgroundProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author br4sk4
 * created on 03.07.2016
 */
@Service
public class BackgroundProcessesDomainService {

    @Autowired
    private ScheduledBackgroundProcessRepository scheduledBackgroundProcessRepository;

    @Autowired
    private BackgroundProcessRepository backgroundProcessRepository;

    @Autowired
    private BackgroundProcessSchedulerService schedulerService;

    public BackgroundProcessDTO findBackgroundProcessByUuid(String uuid) {
        return new BackgroundProcessAssembler().toDTO(backgroundProcessRepository.findById(uuid).orElse(null));
    }

    public void scheduleBackgroundProcess(ScheduledBackgroundProcessDTO dto) {
        ScheduledBackgroundProcess scheduledBackgroundProcess = ScheduledBackgroundProcess.builder()
                .primaryKey(dto.primaryKey)
                .name(dto.name)
                .active(Boolean.valueOf(dto.active))
                .series(Boolean.valueOf(dto.series))
                .periodUnit(dto.periodUnit)
                .periodValue(dto.periodValue)
                .start(Timestamp.from(Instant.parse(dto.start)))
                .build();

        scheduledBackgroundProcessRepository.save(scheduledBackgroundProcess);

        schedulerService.scheduleBackgroundProcess(
                BackgroundProcessTask.class,
                dto.name,
                Instant.parse(dto.start),
                Boolean.valueOf(dto.active),
                Boolean.valueOf(dto.series),
                dto.periodUnit,
                Integer.parseInt(dto.periodValue)
        );
    }

    public BackgroundProcess insertBackgroundProcess(BackgroundProcessDTO dto) {
        BackgroundProcess backgroundProcess = BackgroundProcess.builder()
                .primaryKey(dto.primaryKey)
                .name(dto.name)
                .start(Timestamp.from(Instant.parse(dto.start)))
                .status(BackgroundProcessStatus.valueOf(dto.status))
                .build();

        return backgroundProcessRepository.save(backgroundProcess);
    }

    public BackgroundProcess updateBackgroundProcess(BackgroundProcessDTO dto) {
        BackgroundProcess backgroundProcess = BackgroundProcess.builder()
                .primaryKey(dto.primaryKey)
                .name(dto.name)
                .start(Timestamp.from(Instant.parse(dto.start)))
                .end(Timestamp.from(Instant.parse(dto.end)))
                .status(BackgroundProcessStatus.valueOf(dto.status))
                .build();

        return backgroundProcessRepository.save(backgroundProcess);
    }
}
