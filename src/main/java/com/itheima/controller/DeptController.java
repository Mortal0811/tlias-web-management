package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: DeptController
 * Package: com.itheima.controller
 * Description:
 *
 * @Author dhf
 * @Create 2023/7/18 1:57
 * @Version 17.0.6
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;
//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        deptService.delete(id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        log.info("根据id查询数据：{}",id);
        Dept deptList = deptService.select(id);
        return Result.success(deptList);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success(dept);
    }
}
