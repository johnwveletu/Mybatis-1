package com.wang.mybatis.dao;

import com.wang.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAutomatic {
    @Select("select * from mybatis_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}
