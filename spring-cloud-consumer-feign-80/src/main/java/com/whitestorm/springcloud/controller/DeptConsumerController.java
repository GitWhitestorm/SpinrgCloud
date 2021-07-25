package com.whitestorm.springcloud.controller;


import com.whitestorm.springcloud.common.CommonResult;
import com.whitestorm.springcloud.entities.Dept;
import com.whitestorm.springcloud.service.DeptClientService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DeptConsumerController {

        //  提供远程访问http服务的方法
//    private RestTemplate restTemplate;

//    private static final  String REST_URL_PREFIX = "http://localhost:8001";

    @Resource
    public DeptClientService service ;
//    private static final  String REST_URL_PREFIX = "http://SPRING-CLOUD-PRODUCER-DEPT/";
    @RequestMapping("/consumer/dept/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
      return service.queryById(id);
    }
    @RequestMapping("/consumer/dept/getall")
    public CommonResult getall(){
       return service.queryAll();
    }
    @RequestMapping("/consumer/dept/add")
    public CommonResult getall(@RequestBody Dept dept){
        return service.addDept(dept);
    }
}
