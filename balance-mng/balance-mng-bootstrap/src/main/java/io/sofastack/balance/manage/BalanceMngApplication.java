package io.sofastack.balance.manage;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.core.store.StoreMode;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.server.Server;
import io.seata.spring.annotation.GlobalTransactionScanner;
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
        startSeatServer();
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

    static Server server = null;
    private static void startSeatServer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                server = new Server();
                try {
                    server.main(new String[] {"8091", StoreMode.FILE.name(), "127.0.0.1"});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
