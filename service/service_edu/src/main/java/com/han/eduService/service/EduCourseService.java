package com.han.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.han.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.han.eduService.entity.frontvo.CourseFrontVo;
import com.han.eduService.entity.frontvo.CourseWebVo;
import com.han.eduService.entity.vo.CourseInfoVo;
import com.han.eduService.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-09-16
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息的方法

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
