package com.han.eduService.client;

import com.han.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Han
 * @create 2020-09-28-3:20 下午
 */
@Component
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)//调用的服务名称
public interface VodClient {
    //定义调用的方法路径
    //根据视频id删除阿里云视频
    @DeleteMapping("eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo (@PathVariable("id") String id);

    //定义删除多个视频的方法
    @DeleteMapping("eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoList") List<String> videoIdList);


}
