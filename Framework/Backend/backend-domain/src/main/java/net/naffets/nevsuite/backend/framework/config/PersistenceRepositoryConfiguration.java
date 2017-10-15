package net.naffets.nevsuite.backend.framework.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * @author br4sk4 / created on 13.10.2017
 */
@Configuration
@EnableJpaRepositories(basePackages = "net.naffets.nevsuite.backend.framework.domain.repository.persistent")
public class PersistenceRepositoryConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("datasource.main")
    public DataSourceProperties mainDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix="datasource.main")
    public DataSource mainDataSource() {
        return mainDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(mainDataSource())
                .packages("net.naffets.nevsuite.backend.framework.domain")
                .persistenceUnit("main")
                .build();
    }

}
