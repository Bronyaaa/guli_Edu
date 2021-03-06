package com.han.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.han.vod.service.VodService;
import com.han.vod.utils.ConstantVodUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.ComponentUI;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Han
 * @create 2020-09-25-8:04 下午
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAlyi(MultipartFile file) {
        try {
            //fileName 上传文件原始名称
            String fileName = file.getOriginalFilename();
            //title 上传之后显示名称
            assert fileName != null;
            String title = fileName.substring(0,fileName.lastIndexOf("."));

            //inputStream 上传文件输入流"
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeMoreAlyVideo(List videoIdList) {

    }
}
