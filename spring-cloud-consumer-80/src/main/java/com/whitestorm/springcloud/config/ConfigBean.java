package com.whitestorm.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

//  配置负载均衡实现RestTemplate  默认情况下为轮询
    @LoadBalanced // Ribbon

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


}
