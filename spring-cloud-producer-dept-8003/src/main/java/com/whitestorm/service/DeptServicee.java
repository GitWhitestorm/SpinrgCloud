package com.whitestorm.service;

import com.whitestorm.spingcloud.entities.Dept;

import java.util.List;

public interface DeptServicee {
    public boolean addDept(Dept dept);
    public Dept queryById(Long id);
    public List<Dept> queryAll();
}
