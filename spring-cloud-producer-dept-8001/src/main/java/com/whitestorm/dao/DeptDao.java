package com.whitestorm.dao;


import com.whitestorm.spingcloud.entities.Dept;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface DeptDao {
    public boolean addDept(Dept dept);
    public Dept queryById(Long id);
    public List<Dept> queryAll();
}
