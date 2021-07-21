package com.whitestorm.controller;

import com.whitestorm.common.CommonResult;
import com.whitestorm.service.DeptServiceeImpl;
import com.whitestorm.spingcloud.entities.Dept;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j
public class DeptController {

    @Autowired
    private DeptServiceeImpl deptServicee;


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

}
