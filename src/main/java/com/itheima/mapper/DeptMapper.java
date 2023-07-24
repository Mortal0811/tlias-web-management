package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: DeptMapper
 * Package: com.itheima.mapper
 * Description:
 *
 * @Author dhf
 * @Create 2023/7/18 1:57
 * @Version 17.0.6
 */
@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);


    @Select("select * from dept where id = #{id}")
    Dept select(Integer id);


    @Update("update dept set name = #{name}, create_time = #{createTime}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
