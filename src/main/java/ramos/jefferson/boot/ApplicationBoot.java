package ramos.jefferson.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "ramos.jefferson.boot")
public class ApplicationBoot {
    
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args);
    }
    
}
