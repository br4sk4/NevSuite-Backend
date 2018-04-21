package net.naffets.nevsuite.backgroundprocesses.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author br4sk4
 * created on 21.04.18
 */
@Configuration
@EnableFeignClients(basePackages = "net.naffets.nevsuite.eventsourcing.domain.facade")
public class FacadeConfiguration {
}
