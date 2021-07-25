package com.whitestorm.springcloud.service;


import com.whitestorm.springcloud.common.CommonResult;
import com.whitestorm.springcloud.entities.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "spring-cloud-producer-dept",fallbackFactory = DeptClientServiceFallBackMethods.class)
@Service
public interface DeptClientService {

    @GetMapping("/dept/get/{id}")
    public CommonResult queryById(@PathVariable("id") Long id);
    @GetMapping("/dept/getall/{id}")
    public CommonResult queryAll();
    @PostMapping("/dept/add")
    public CommonResult addDept(@RequestBody Dept dept);
}
