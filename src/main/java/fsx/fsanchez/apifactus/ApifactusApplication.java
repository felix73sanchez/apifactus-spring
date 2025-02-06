package fsx.fsanchez.apifactus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApifactusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApifactusApplication.class, args);
    }

}
