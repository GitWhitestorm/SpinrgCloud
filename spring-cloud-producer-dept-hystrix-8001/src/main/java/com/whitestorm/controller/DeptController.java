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
//    @HystrixCommand(fallbackMethod = "hystrixGetDeptById")
    public CommonResult getDeptById(@PathVariable("id") Long id){
        Dept dept = deptServicee.queryById(id);
        if(dept == null){
            throw new RuntimeException("id=>"+id+",不存在改用户");
        }
        return new CommonResult("error",404);
    }
//    备选方案
//    public CommonResult hystrixGetDeptById(@PathVariable("id") Long id){
//         return new CommonResult("不出在该对象",404);
//    }
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
