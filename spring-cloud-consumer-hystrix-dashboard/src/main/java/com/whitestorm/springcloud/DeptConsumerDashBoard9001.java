package com.whitestorm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication

//开启监控
@EnableHystrixDashboard
public class DeptConsumerDashBoard9001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerDashBoard9001.class,args);
    }

}
