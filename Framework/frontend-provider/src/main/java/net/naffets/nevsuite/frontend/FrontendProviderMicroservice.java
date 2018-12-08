package net.naffets.nevsuite.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Properties;

/**
 * @author br4sk4 / created on 12.05.2018
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
public class FrontendProviderMicroservice extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FrontendProviderMicroservice.class);
    }

    public static void main (String[] args) {
        SpringApplication app = new SpringApplication(FrontendProviderMicroservice.class);
        Properties properties = new Properties();
        properties.setProperty("spring.resources.static-locations", "classpath:/webapp");
        app.setDefaultProperties(properties);
        app.run(args);
    }

}
