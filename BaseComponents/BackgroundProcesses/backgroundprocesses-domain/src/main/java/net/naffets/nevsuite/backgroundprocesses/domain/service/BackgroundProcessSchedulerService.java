package net.naffets.nevsuite.backgroundprocesses.domain.service;

import net.naffets.nevsuite.backgroundprocesses.domain.repository.ScheduledBackgroundProcessRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author br4sk4
 * created on 04.07.2016
 */
@Service
public class BackgroundProcessSchedulerService {

    private static final Logger logger = LogManager.getLogger(BackgroundProcessSchedulerService.class.getName());

    @Autowired
    private ScheduledBackgroundProcessRepository scheduledBackgroundProcessRepository;

    @Autowired
    private BackgroundProcessTaskFactory jobFactory;

    private Scheduler scheduler;

    @PostConstruct
    public void startScheduler() {
        try {
            logger.info("Startup Scheduler");
            //StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            StdSchedulerFactory factory = new StdSchedulerFactory();
            factory.initialize(new ClassPathResource("quartz.properties").getInputStream());

            scheduler = factory.getScheduler();
            scheduler.setJobFactory(jobFactory);
            scheduler.start();

            scheduledBackgroundProcessRepository.findAll().forEach(process -> {
                scheduleBackgroundProcess(
                        BackgroundProcessTask.class,
                        process.getName(),
                        process.getStart().toInstant(),
                        process.getActive(),
                        process.getSeries(),
                        process.getPeriodUnit(),
                        Integer.parseInt(process.getPeriodValue())

                );
            });
        } catch (SchedulerException | IOException e) {
            logger.catching(Level.FATAL, e);
        }
    }

    @PreDestroy
    public void ShutDownScheduler() {
        try {
            logger.info("Shutdown Scheduler");
            scheduler.shutdown();
        } catch (SchedulerException e) {
            logger.catching(Level.FATAL, e);
        }
    }

    public void scheduleBackgroundProcess(
            Class<? extends Job> processType,
            String processName,
            Instant start,
            Boolean isActive,
            Boolean isSeries,
            String periodUnit,
            Integer periodValue) {

        Trigger trigger = null;
        logger.info("Scheduling Job: " + processName);

        JobDetail job = newJob(processType)
                .withIdentity(processName, "MyGroup")
                .usingJobData("jobname", processName)
                .build();

        if (isActive && isSeries) {
            trigger = newTrigger()
                    .withIdentity(processName + "Trigger", "MyGroup")
                    .startAt(Date.from(start))
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(periodValue)
                            .repeatForever())
                    .build();
        } else if (isActive) {
            trigger = newTrigger()
                    .withIdentity(processName + "Trigger", "MyGroup")
                    .startAt(Date.from(start))
                    .build();
        }

        try {
            if (isActive && trigger != null) scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            logger.catching(Level.FATAL, e);
        }

    }

}
