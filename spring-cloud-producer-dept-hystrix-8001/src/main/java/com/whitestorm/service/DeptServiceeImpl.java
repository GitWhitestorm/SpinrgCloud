package com.whitestorm.service;

import com.whitestorm.dao.DeptDao;
import com.whitestorm.springcloud.entities.Dept;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class DeptServiceeImpl implements DeptServicee {

    @Autowired
    private DeptDao deptDao;
    @Override
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }

    @Override
    public List<Dept> queryAll()  {
        return deptDao.queryAll();
    }
}
