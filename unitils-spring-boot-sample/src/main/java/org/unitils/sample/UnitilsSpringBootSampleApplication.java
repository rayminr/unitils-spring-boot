package org.unitils.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "org.unitils.sample.mapper")
public class UnitilsSpringBootSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitilsSpringBootSampleApplication.class, args);
    }

}
