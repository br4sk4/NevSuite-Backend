package net.naffets.nevsuite.eventsourcing.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author br4sk4 / created on 13.10.2017
 */
@Configuration
@EnableJpaRepositories(basePackages = "net.naffets.nevsuite.eventsourcing.domain.repository")
public class RepositoryConfiguration {

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
                .packages("net.naffets.nevsuite.eventsourcing.domain")
                .persistenceUnit("main")
                .properties(jpaProperties())
                .build();
    }

    private Map<String, Object> jpaProperties() {
        DataSourceProperties dataSourceProperties = mainDataSourceProperties();
        Map<String, Object> properties = new HashMap<>();
        String autoGenerationStrategy = "validate";
        if (dataSourceProperties.getUrl().equals("jdbc:h2:mem:mainDB")) {
            properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            autoGenerationStrategy = "create-drop";
        }
        properties.put("hibernate.hbm2ddl.auto", autoGenerationStrategy);
        return properties;
    }

}
