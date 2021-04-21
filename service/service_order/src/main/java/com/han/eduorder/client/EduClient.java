package com.han.eduorder.client;

import com.han.commonutils.orderVo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Han
 * @create 2020-10-14-9:02 下午
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
    @PostMapping("/eduService/coursefront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
