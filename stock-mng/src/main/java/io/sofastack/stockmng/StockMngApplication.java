package io.sofastack.stockmng;

import com.zaxxer.hikari.HikariDataSource;
import io.sofastack.registrymng.facade.RegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@ImportResource({"classpath*:META-INF/io-sofastack-stockmng/*.xml"})
@SpringBootApplication
public class StockMngApplication {
    private static final Logger logger = LoggerFactory.getLogger(StockMngApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(StockMngApplication.class);
        ApplicationContext applicationContext = application.run(args);
        callRpc(applicationContext);
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

    private static void callRpc(ApplicationContext applicationContext){
        //3. 调用 SOFARest 服务
        final RegistryService registryService = (io.sofastack.registrymng.facade.RegistryService) applicationContext.getBean("registryService");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String response = registryService.hello("niko");
                        //如果需要体验 SofaRest 功能，取消注释掉以下代码即可
                        printMsg("Response from myserver-app: " + response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            //ignore
                        }
                    }

                }

            }
        }).start();
    }

    private static void printMsg(String msg) {
        System.out.println(msg);
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }
}
