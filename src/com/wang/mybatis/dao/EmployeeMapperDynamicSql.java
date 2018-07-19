package com.wang.mybatis.dao;

import com.wang.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSql {

    public List<Employee> getEmpsByCondationIf(Employee employee);

    public List<Employee> getEmpsByCondationChoose(Employee employee);

    public boolean updateEmp(Employee employee);

    public List<Employee> getEmpsByCondationForEach(@Param("ids") List<Integer> ids);

    public boolean addEmps(@Param("emps") List<Employee> employeeList);

}
