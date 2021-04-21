package com.han.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Han
 * @create 2020-09-11-12:07 上午
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.han"})
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args) { SpringApplication.run(OssApplication.class, args);
    }

}
