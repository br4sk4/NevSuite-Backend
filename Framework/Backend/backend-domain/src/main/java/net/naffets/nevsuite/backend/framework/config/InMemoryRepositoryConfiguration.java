package net.naffets.nevsuite.backend.framework.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author br4sk4 / created on 13.10.2017
 */
@Configuration
@EnableJpaRepositories(basePackages = "net.naffets.nevsuite.backend.framework.domain.repository.temporary", entityManagerFactoryRef = "inMemoryEntityManagerFactory")
public class InMemoryRepositoryConfiguration {

    @Bean
    @ConfigurationProperties("datasource.mem")
    public DataSourceProperties inMemoryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix="datasource.mem")
    public DataSource inMemoryDataSource() {
        return inMemoryDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean inMemoryEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(inMemoryDataSource())
                .packages("net.naffets.nevsuite.backend.framework.domain")
                .persistenceUnit("inMemory")
                .properties(jpaProperties())
                .build();
    }

    private Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "create-drop");
        return props;
    }

}
