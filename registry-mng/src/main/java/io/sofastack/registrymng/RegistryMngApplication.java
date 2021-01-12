package io.sofastack.registrymng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath*:META-INF/io-sofastack-registrymng/*.xml"})
@SpringBootApplication
public class RegistryMngApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistryMngApplication.class, args);
    }
}
