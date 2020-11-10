package cn.codenest.redists;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedistsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedistsApplication.class, args);
    }

}
