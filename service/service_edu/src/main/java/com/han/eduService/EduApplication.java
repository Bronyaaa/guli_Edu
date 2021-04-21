package com.han.eduService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Han
 * @create 2020-09-05-4:36 下午
 */
@SpringBootApplication
//han是因为两个文件即eduApplication和eduService前缀都是com.han
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.han"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
