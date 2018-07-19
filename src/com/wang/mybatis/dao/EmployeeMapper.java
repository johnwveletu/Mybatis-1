package com.wang.mybatis.dao;

import com.wang.mybatis.beans.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    Employee getEmpById(Integer id);

    void addEmp(Employee employee);

//  多个查询参数使用@Param绑定参数
    boolean updateEmp(@Param(value = "lastName") String lastName,@Param(value = "id") Integer id);

    boolean deleteEmp(Integer id);

    void addEmpToOracle(Employee employee);

    Employee getEmpByIdAndLastName(Employee employee);

    Employee getEmpByMap(Map<String, Object> map);

//    模糊查询
    List<Employee> getEmpBylastNameLike(String lastName);

//    返回一个Map
   Map<String, Object> getEmpByIdResultTypeMap(Integer id);

    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("id")
    Map<Integer, Employee> getEmpBylastNameLikeAsMap(String lastName);

    List<Employee> getEmpByDid(Integer deptId);


}
