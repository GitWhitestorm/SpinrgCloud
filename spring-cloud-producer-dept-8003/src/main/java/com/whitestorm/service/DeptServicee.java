package com.whitestorm.service;

import com.whitestorm.springcloud.entities.Dept;

import java.util.List;

public interface DeptServicee {
    public boolean addDept(Dept dept);
    public Dept queryById(Long id);
    public List<Dept> queryAll();
}
