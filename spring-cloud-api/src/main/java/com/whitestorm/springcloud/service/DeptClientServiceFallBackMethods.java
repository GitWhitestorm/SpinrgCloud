package com.whitestorm.springcloud.service;


import com.whitestorm.springcloud.common.CommonResult;
import com.whitestorm.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DeptClientServiceFallBackMethods implements FallbackFactory {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public CommonResult queryById(Long id) {
                return new CommonResult("no id",404);
            }

            @Override
            public CommonResult queryAll() {
                return new CommonResult("出错",404);
            }

            @Override
            public CommonResult addDept(Dept dept) {
                return null;
            }
        };
    }
}
