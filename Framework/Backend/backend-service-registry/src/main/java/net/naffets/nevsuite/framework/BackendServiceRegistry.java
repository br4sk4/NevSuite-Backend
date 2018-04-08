package net.naffets.nevsuite.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author br4sk4 / created on 08.04.2018
 */
@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class BackendServiceRegistry extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BackendServiceRegistry.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendServiceRegistry.class, args);
    }

}
