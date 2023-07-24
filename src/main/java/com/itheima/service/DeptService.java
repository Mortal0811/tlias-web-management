package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * ClassName: DeptService
 * Package: com.itheima.service
 * Description:
 *
 * @Author dhf
 * @Create 2023/7/18 1:55
 * @Version 17.0.6
 */
public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    Dept select(Integer id);

    void update(Dept dept);
}
