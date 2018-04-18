package net.naffets.nevsuite.backgroundprocesses.domain.service;


import net.naffets.nevsuite.backgroundprocesses.domain.dto.BackgroundProcessDTO;
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

    //private EventManagementDomainService eventManagementDomainServiceFacade;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BackgroundProcessDTO dto = createBackgroundProcessDTO(jobExecutionContext.getJobDetail().getJobDataMap().getString("jobname"));

        backgroundProcessesDomainService.insertBackgroundProcess(dto);
        //eventManagementDomainServiceFacade.notifyEvent(createEventNotificationDTO(EventQualifier.E_101, dto));

        logger.info("Running " + dto.name);

        backgroundProcessesDomainService.updateBackgroundProcess(finishBackgroundProcessDTO(dto));
        //eventManagementDomainServiceFacade.notifyEvent(createEventNotificationDTO(EventQualifier.E_102, dto));
    }

    /*
    private EventNotificationDTO createEventNotificationDTO(EventQualifier qualifier,
                                                            BackgroundProcessDTO backgroundProcess) {
        EventNotificationDTO notificationDTO = new EventNotificationDTO();
        EventDescriptorDTO descriptorDTO = eventManagementDomainServiceFacade.findEventDescriptorByQualifier(qualifier);

        notificationDTO.setUuid(UUID.randomUUID().toString());
        notificationDTO.setTimestamp(Instant.now());
        notificationDTO.setEventDescriptor(descriptorDTO);
        notificationDTO.setReferenceType("BPRC");
        notificationDTO.setReferenceUuid(backgroundProcess.getUuid());

        return notificationDTO;
    }
    */

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
