package com.whitestorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//服务发现
@EnableDiscoveryClient
public class DeptProducer8083 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProducer8083.class,args);
    }
}
