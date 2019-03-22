package io.pine.cloud.examples.ecard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.pine.cloud.examples.ecard.infrastructure")
public class EcardApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcardApplication.class, args);
    }
}
