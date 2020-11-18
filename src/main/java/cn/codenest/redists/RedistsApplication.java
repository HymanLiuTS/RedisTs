package cn.codenest.redists;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
//这里可以关闭数据源的加载？但是貌似没用
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RedistsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedistsApplication.class, args);
    }

}
