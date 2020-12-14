package co.com.tallercloud.backlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceBacklogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBacklogApplication.class, args);
    }

}
