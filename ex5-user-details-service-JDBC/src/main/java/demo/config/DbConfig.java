package demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-read")
    public HikariConfig dbConfiguration() {
        return new HikariConfig();
    }

    @Bean
    public DataSource getDataSource() {
        return new HikariDataSource(dbConfiguration());
    }
}
