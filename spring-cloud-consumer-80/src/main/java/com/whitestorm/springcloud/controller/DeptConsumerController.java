package com.whitestorm.springcloud.controller;


import com.whitestorm.springcloud.common.CommonResult;
import com.whitestorm.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeptConsumerController {

    @Autowired
        //  提供远程访问http服务的方法
    private RestTemplate restTemplate;

//    private static final  String REST_URL_PREFIX = "http://localhost:8001";

    private static final  String REST_URL_PREFIX = "http://SPRING-CLOUD-PRODUCER-DEPT/";
    @RequestMapping("/consumer/dept/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"dept/get/"+id,CommonResult.class);
    }
    @RequestMapping("/consumer/dept/getall")
    public CommonResult getall(){
        return restTemplate.getForObject(REST_URL_PREFIX+"dept/getall",CommonResult.class);
    }
    @RequestMapping("/consumer/dept/add")
    public CommonResult getall(@RequestBody Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"dept/add",dept, CommonResult.class);
    }
}
