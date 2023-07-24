package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;

import java.time.LocalDate;
import java.util.List;

/**
 * ClassName: EmpService
 * Package: com.itheima.service
 * Description:
 *
 * @Author dhf
 * @Create 2023/7/18 1:56
 * @Version 17.0.6
 */
public interface EmpService {

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
