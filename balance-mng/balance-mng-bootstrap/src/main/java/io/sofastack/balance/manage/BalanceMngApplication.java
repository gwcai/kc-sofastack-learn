package io.sofastack.balance.manage;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author yuanyuan
 * @since 2019/6/10
 */
@SpringBootApplication
public class BalanceMngApplication {
    public static void main(String[] args) {
        SpringApplication.run(BalanceMngApplication.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = createDataSource(properties,HikariDataSource.class);
        if (null != properties.getName() && properties.getName().length() > 0){
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

    protected static <T> T createDataSource(DataSourceProperties properties,Class<? extends DataSource> type){
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }
}
