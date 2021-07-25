package com.whitestorm.controller;

import com.whitestorm.common.CommonResult;
import com.whitestorm.service.DeptServiceeImpl;
import com.whitestorm.springcloud.entities.Dept;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j
public class DeptController {

    @Autowired
    private DeptServiceeImpl deptServicee;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public CommonResult addDept(@RequestBody Dept dept){
        boolean flag = deptServicee.addDept(dept);
        if(flag){
            return CommonResult.success(dept);
        }else{
            return CommonResult.fail();
        }
    }
    @GetMapping("/dept/get/{id}")
    public CommonResult getDeptById(@PathVariable("id") Long id){
        Dept dept = deptServicee.queryById(id);
        return CommonResult.success(dept);
    }
    @GetMapping("/dept/getall")
    public CommonResult getAll(){
        List<Dept> deptList = deptServicee.queryAll();
        return  CommonResult.success(deptList);
    }
    //注册进来的微服务，获取一些信息
    @GetMapping("/dept/discovery")
    public Object discovery(){
        //获取微服务列表的清单
        List<String> services = client.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        List<ServiceInstance> instances = client.getInstances("SPRING-CLOUD-PRODUCER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"+
                            instance.getPort()+"\t"+
                            instance.getUri()+"\t"+
                            instance.getServiceId()
            );
        }
        return this.client;
    }

}
