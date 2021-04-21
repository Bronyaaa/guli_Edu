package com.han.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.han.commonutils.R;
import com.han.eduService.entity.EduTeacher;
import com.han.eduService.entity.vo.TeacherQuery;
import com.han.eduService.service.EduTeacherService;
import com.han.service_base.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-09-05
 */
@RestController
@RequestMapping("/eduService/teacher")
@CrossOrigin //解决跨域
public class EduTeacherController {

    //访问地址：http://localhost:8001/eduService/teacher/findALL
    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1 查询讲师表所有数据
    //rest风格
    @GetMapping("findALL")
    public R findAllTeacher(){
        //调用service方法查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);

    }

    //2逻辑删除讲师的方法
    @DeleteMapping("{id}")
    //表示id值需要通过路径传递
    public R removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else  {
            return R.error();
        }
    }

    //分页查询方法
    //current 当前页 limit 每页数据数
    @GetMapping("PageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current
                            ,@PathVariable long limit){

        //首先创建配置对象
        Page<EduTeacher> teacherPage = new Page<>(current,limit);


        try {
            int i = 10/0;
        }catch (Exception e){
            //执行自定义异常
            throw new GuliException(20001,"执行了自定义异常处理");
        }
        //调用方法
        //调用时，底层封装，把分页所有数据封装到pageTeacher对象里面
        teacherService.page(teacherPage,null);
        long total = teacherPage.getTotal();//总记录数
        List<EduTeacher> records =  teacherPage.getRecords();//数据list集合
        //方法一
//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);
        //方法二
        return R.ok().data("total",total).data("rows",records);
    }

    //4 多条件组合查询带分页
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    //因为用了RequestBody注解 上面要用post提交，用get是获取不到的
    public R pageTeacherCondition(@PathVariable long current
            , @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery){

        //创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //wrapper
        //与MyBatis动态sql类似
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        //这里没有加！导致以name为条件时total一直位0
        if(!StringUtils.isEmpty(name)){
            //构建条件
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询功能
        teacherService.page(teacherPage,wrapper);
        long total = teacherPage.getTotal();//总记录数
        List<EduTeacher> records = teacherPage.getRecords();//数据集合
        return R.ok().data("total",total).data("rows",records);

    }

    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
//    @GetMapping("getTeacher")错误！！
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);

    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }


}

