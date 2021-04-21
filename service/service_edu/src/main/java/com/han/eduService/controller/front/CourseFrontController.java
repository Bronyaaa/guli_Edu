package com.han.eduService.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.han.commonutils.R;
import com.han.commonutils.orderVo.CourseWebVoOrder;
import com.han.eduService.entity.EduCourse;
import com.han.eduService.entity.chapter.ChapterVo;
import com.han.eduService.entity.frontvo.CourseFrontVo;
import com.han.eduService.entity.frontvo.CourseWebVo;
import com.han.eduService.service.EduChapterService;
import com.han.eduService.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Han
 * @create 2020-10-12-4:35 下午
 */
@RestController
@RequestMapping("/eduService/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    //1条件查询带分页的查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo){
       Page<EduCourse> pageCourse = new Page<>(page,limit);
       Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
       return R.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        //根据课程ID，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //根据课程id，查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }

    //根据课程ID查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
