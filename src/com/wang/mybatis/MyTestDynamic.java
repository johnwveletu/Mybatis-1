package com.wang.mybatis;

import com.wang.mybatis.beans.Department;
import com.wang.mybatis.beans.Employee;
import com.wang.mybatis.dao.EmployeeMapperDynamicSql;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 1、接口式编程
 * 	原生：		Dao		====>  DaoImpl
 * 	mybatis：	Mapper	====>  xxMapper.xml
 *
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * 		（将接口和xml进行绑定）
 * 		EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * 		sql映射文件：保存了每一个sql语句的映射信息：
 * 					将sql抽取出来。
 */
public class MyTestDynamic {

    private SqlSessionFactory sqlSessionFactory = null;

    /**
     * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。
     * 3、将sql映射文件注册在全局配置文件中
     * 4、写代码：
     * 		1）、根据全局配置文件得到SqlSessionFactory；
     * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
     * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
     */
    @Before
    public void getSqlSessionFactory() throws IOException {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testCondationIf(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSql employeeMapperDynamicSql = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            Employee employee = new Employee();
            employee.setId(1);
            employee.setLastName("");
            employee.setEmail(null);
            List<Employee> employeeList = employeeMapperDynamicSql.getEmpsByCondationIf(employee);
            System.out.println(employeeList);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testCondationChoose(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSql employeeMapperDynamicSql = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            Employee employee = new Employee();
            employee.setId(1);
            employee.setLastName("");
            employee.setEmail(null);
            List<Employee> employeeList = employeeMapperDynamicSql.getEmpsByCondationChoose(employee);
            System.out.println(employeeList);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testCondationSet(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSql employeeMapperDynamicSql = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            Employee employee = new Employee();
            employee.setId(null);
            employee.setLastName("helloSet");
            employee.setEmail(null);
            boolean state = employeeMapperDynamicSql.updateEmp(employee);
            System.out.println(state);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testCondationForeach(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSql employeeMapperDynamicSql = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            List<Integer> ids = new ArrayList<Integer>();
            ids.add(1);
            ids.add(2);
            ids.add(5);
            List<Employee> employeeList = employeeMapperDynamicSql.getEmpsByCondationForEach(ids);
            System.out.println(employeeList);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testCondationForeachInsert(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSql employeeMapperDynamicSql = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            List<Employee> emps = new ArrayList<Employee>();
            Employee employee_1= new Employee();
            Employee employee_2 = new Employee();
            Department department_1 = new Department();
            Department department_2 = new Department();
            employee_1.setLastName("testForeach_1");
            employee_1.setEmail("163@foreach.com");
            department_1.setId(1);
            employee_1.setDepartment(department_1);
            emps.add(employee_1);
            employee_2.setLastName("testForeach_2");
            employee_2.setEmail("163@foreach.com");
            department_2.setId(2);
            employee_2.setDepartment(department_2);
            emps.add(employee_2);
            boolean state = employeeMapperDynamicSql.addEmps(emps);
            System.out.println(state);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }




}
