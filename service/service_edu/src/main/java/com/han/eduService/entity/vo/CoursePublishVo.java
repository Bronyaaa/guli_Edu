package com.han.eduService.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Han
 * @create 2020-09-19-10:53 下午
 */
@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示

}
