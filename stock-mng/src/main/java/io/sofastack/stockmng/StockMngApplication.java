package io.sofastack.stockmng;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootApplication
public class StockMngApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockMngApplication.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = createDataSource(properties,HikariDataSource.class);
        if (null != properties.getName() && properties.getName().length() > 0){
            dataSource.setPoolName(properties.getName());
        }
        return new DataSourceProxy(dataSource);
    }

    protected static <T> T createDataSource(DataSourceProperties properties,Class<? extends DataSource> type){
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    @Bean
    @Primary
    public GlobalTransactionScanner globalTransactionScanner(){
        return new GlobalTransactionScanner("kc-balance-mng","my_test_tx_group");
    }
}
