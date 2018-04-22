package net.naffets.nevsuite.backgroundprocesses.domain.service;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.naffets.nevsuite.eventsourcing.domain.facade.EventSourcingDomainServiceFacade;
import net.naffets.nevsuite.backgroundprocesses.domain.dto.BackgroundProcessDTO;
import net.naffets.nevsuite.backgroundprocesses.domain.entity.BackgroundProcess;
import net.naffets.nevsuite.eventsourcing.domain.basictype.EventQualifier;
import net.naffets.nevsuite.eventsourcing.domain.dto.EventNotificationDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.Instant;
import java.util.UUID;

/**
 * @author br4sk4
 * created on 05.07.2016
 */
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BackgroundProcessTask implements Job {

    private static Logger logger = LogManager.getLogger(BackgroundProcessTask.class.getName());

    @Inject
    private BackgroundProcessesDomainService backgroundProcessesDomainService;

    @Inject
    private EventSourcingDomainServiceFacade eventSourcingDomainServiceFacade;

    @Inject
    public BackgroundProcessTask(
            BackgroundProcessesDomainService backgroundProcessesDomainService,
            EventSourcingDomainServiceFacade eventSourcingDomainServiceFacade) {
        this.backgroundProcessesDomainService = backgroundProcessesDomainService;
        this.eventSourcingDomainServiceFacade = eventSourcingDomainServiceFacade;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BackgroundProcessDTO dto = createBackgroundProcessDTO(jobExecutionContext.getJobDetail().getJobDataMap().getString("jobname"));
        BackgroundProcess backgroundProcess = backgroundProcessesDomainService.insertBackgroundProcess(dto);

        try {
            eventSourcingDomainServiceFacade.pushEvent(EventNotificationDTO.builder()
                    .descriptor(EventQualifier.E_101.toString())
                    .referencedObjectId(backgroundProcess.getPrimaryKey())
                    .referencedObjectType(backgroundProcess.asReference().getTypeDiscriminator())
                    .build());

            logger.info("Running " + dto.name);

            eventSourcingDomainServiceFacade.pushEvent(EventNotificationDTO.builder()
                    .descriptor(EventQualifier.E_102.toString())
                    .referencedObjectId(backgroundProcess.getPrimaryKey())
                    .referencedObjectType(backgroundProcess.asReference().getTypeDiscriminator())
                    .build());
        } finally {
            backgroundProcessesDomainService.updateBackgroundProcess(finishBackgroundProcessDTO(dto));
        }
    }

    private BackgroundProcessDTO createBackgroundProcessDTO(String name) {
        BackgroundProcessDTO processDTO = new BackgroundProcessDTO();
        processDTO.primaryKey = UUID.randomUUID().toString();
        processDTO.name = name;
        processDTO.start = Instant.now().toString();
        processDTO.status = "RUNNING";

        return processDTO;
    }

    private BackgroundProcessDTO finishBackgroundProcessDTO(BackgroundProcessDTO processDTO) {
        processDTO.status = "FINISHED";
        processDTO.end = Instant.now().toString();

        return processDTO;
    }

}
