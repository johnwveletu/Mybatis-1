package com.wang.mybatis;

import com.wang.mybatis.beans.Employee;
import com.wang.mybatis.dao.EmployeeMapper;
import com.wang.mybatis.dao.EmployeeMapperAutomatic;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        //配置文件映射
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println("EmployeeMapper : " + employee);

        //注解映射
        EmployeeMapperAutomatic employeeMapperAutomatic = sqlSession.getMapper(EmployeeMapperAutomatic.class);
        Employee employee1 = employeeMapperAutomatic.getEmpById(1);
        System.out.println("EmployeeMapperAutomatic : " + employee1);

        sqlSession.close();
    }


}
