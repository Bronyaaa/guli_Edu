package com.han.eduService.mapper;

import com.han.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.han.eduService.entity.frontvo.CourseWebVo;
import com.han.eduService.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-09-16
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);

    //根据课程id 编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
