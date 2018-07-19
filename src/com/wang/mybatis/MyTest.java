package com.wang.mybatis;

import com.wang.mybatis.beans.Employee;
import com.wang.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
public class MyTest {

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

    /**
     * 测试增删改
     * 1、mybatis允许增删改直接定义以下类型返回值
     * 		Integer、Long、Boolean、void
     * 2、我们需要手动提交数据
     * 		sqlSessionFactory.openSession();===》手动提交
     * 		sqlSessionFactory.openSession(true);===》自动提交
     */
    @Test
    public void testAddEmp(){
////        获取自动提交的sqlSession_
//        SqlSession sqlSession_ = sqlSessionFactory.openSession(true);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee();
            employee.setLastName("testAddEmp");
            employee.setEmail("163@qq.com");
            employeeMapper.addEmp(employee);
            System.out.println(employee.getId());
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testUpdateEmp(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            employeeMapper.updateEmp("testUpdate111",3);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testDeleteEmp(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            boolean flag = employeeMapper.deleteEmp(4);
            System.out.println(flag);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelectEmpPojo(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee();
            employee.setId(1);
            employee.setLastName("hello");
            Employee employee1 = employeeMapper.getEmpByIdAndLastName(employee);
            System.out.println(employee1);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelectEmpMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",1);
            map.put("lastName","hello");
            Employee employee1 = employeeMapper.getEmpByMap(map);
            System.out.println(employee1);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelectEmpLike(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> employeeList = employeeMapper.getEmpBylastNameLike("%test%");
            System.out.println(employeeList);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelectEmpResultTypeMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String,Object> map = employeeMapper.getEmpByIdResultTypeMap(1);
            System.out.println(map);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelectEmpAsMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Integer, Employee> map = employeeMapper.getEmpBylastNameLikeAsMap("%test%");
            System.out.println(map);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }

    }


}
