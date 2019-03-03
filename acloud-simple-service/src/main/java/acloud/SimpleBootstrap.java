package acloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 应用启动类
 */
@SpringBootApplication
@MapperScan("acloud.simple.service.impl.dao")
public class SimpleBootstrap {
    public static void main(String[] args) {

        SpringApplication.run(SimpleBootstrap.class, args);
        
        
    }
}
