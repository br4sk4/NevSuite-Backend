package net.naffets.nevsuite.backgroundprocesses.domain.service;


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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

/**
 * @author br4sk4
 * created on 05.07.2016
 */
@Component
public class BackgroundProcessTask implements Job {

    private static Logger logger = LogManager.getLogger(BackgroundProcessTask.class.getName());

    @Autowired
    private BackgroundProcessesDomainService backgroundProcessesDomainService;

    @Autowired
    private EventSourcingDomainServiceFacade eventSourcingDomainClient;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BackgroundProcessDTO dto = createBackgroundProcessDTO(jobExecutionContext.getJobDetail().getJobDataMap().getString("jobname"));

        BackgroundProcess backgroundProcess = backgroundProcessesDomainService.insertBackgroundProcess(dto);
        eventSourcingDomainClient.pushEvent(EventNotificationDTO.builder()
                .descriptor(EventQualifier.E_101.toString())
                .referencedObjectId(backgroundProcess.getPrimaryKey())
                .referencedObjectType(backgroundProcess.asReference().getTypeDiscriminator())
                .build());

        logger.info("Running " + dto.name);

        backgroundProcess = backgroundProcessesDomainService.updateBackgroundProcess(finishBackgroundProcessDTO(dto));
        eventSourcingDomainClient.pushEvent(EventNotificationDTO.builder()
                .descriptor(EventQualifier.E_102.toString())
                .referencedObjectId(backgroundProcess.getPrimaryKey())
                .referencedObjectType(backgroundProcess.asReference().getTypeDiscriminator())
                .build());
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
