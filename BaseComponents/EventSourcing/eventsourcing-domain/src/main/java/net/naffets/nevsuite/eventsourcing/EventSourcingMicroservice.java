package net.naffets.nevsuite.eventsourcing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author br4sk4
 * created on 20.04.18
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
public class EventSourcingMicroservice extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EventSourcingMicroservice.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingMicroservice.class, args);
    }

}
