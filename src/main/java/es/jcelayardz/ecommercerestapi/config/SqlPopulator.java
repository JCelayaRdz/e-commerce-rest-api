package es.jcelayardz.ecommercerestapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class SqlPopulator {

    @Bean
    public CommandLineRunner dataInitialization(DataSource dataSource) {
        return args -> {
            var populator = new ResourceDatabasePopulator();
            populator.addScripts(new ClassPathResource("schema.sql"), new ClassPathResource("sample-data.sql"));
            DatabasePopulatorUtils.execute(populator, dataSource);
        };
    }
}
