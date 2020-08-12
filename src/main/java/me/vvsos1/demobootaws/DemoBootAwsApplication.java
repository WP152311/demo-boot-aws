package me.vvsos1.demobootaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class DemoBootAwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBootAwsApplication.class, args);
    }

}
