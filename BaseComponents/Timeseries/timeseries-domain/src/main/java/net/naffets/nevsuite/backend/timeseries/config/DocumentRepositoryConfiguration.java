package net.naffets.nevsuite.backend.timeseries.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author br4sk4 / created on 21.10.2017
 */
@Configuration
@EnableMongoRepositories(basePackages = "net.naffets.nevsuite.backend.timeseries.domain.repository.document")
public class DocumentRepositoryConfiguration {
}
