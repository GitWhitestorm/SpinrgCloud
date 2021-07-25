package com.whitestorm.springcloud;

import com.whitestorm.router.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient

    //在微服务启动的时候就能去加载我们自定义的Ribbon类
    //MyRibbonRule最好不要放在项目包中，否则会被扫描到 覆盖所有ribbon负载均衡规则
@RibbonClient(name = "SPRING-CLOUD-PRODUCER-DEPT",configuration = MyRibbonRule.class)
public class DeptConsumer80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80.class);
    }


}
