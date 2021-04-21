package com.han.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Han
 * @create 2020-09-25-8:03 下午
 */
public interface VodService {

    String uploadVideoAlyi(MultipartFile file);

    //删除多个阿里云视频的方法
    void removeMoreAlyVideo(List videoIdList);
}
