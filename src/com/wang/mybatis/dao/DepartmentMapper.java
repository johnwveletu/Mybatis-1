package com.wang.mybatis.dao;

import com.wang.mybatis.beans.Department;

public interface DepartmentMapper {

    Department getDeptById(Integer id);

    Department getDeptAndEmpById(Integer id);

    Department getDeptAndEmpByIdStep(Integer id);
}
