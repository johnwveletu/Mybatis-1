package com.wang.mybatis.dao;

import com.wang.mybatis.beans.Employee;

public interface EmployeeMapperPlus {

    /**
     * 自定义某个javaBean的封装规则，利用resultMap标签
     * @param id
     * @return
     */
    Employee getEmpById(Integer id);

    /**
     * 关联查询
     * @param id
     * @return
     */
    Employee getEmpAndDeptById(Integer id);

    /**
     * 关联分步查询
     * @param id
     * @return
     */
    Employee getEmpAndDeptByIdStep(Integer id);

    /**
     * 利用鉴别器
     * @param id
     * @return
     */
    Employee getEmpAndDeptByIdStepDiscriminator(Integer id);

}
