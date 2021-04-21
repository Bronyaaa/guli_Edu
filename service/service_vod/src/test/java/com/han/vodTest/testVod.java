package com.han.vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @author Han
 * @create 2020-09-24-9:49 下午
 */
public class testVod {
    public static void main(String[] args) throws Exception {
        //根据视频id获取视频播放凭证
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4G6YW7aYhE6Me8hUsfvw","Bt2uOmtv5V6SynwDNA5hgYSqFYYWS9");
        //获取视频凭证request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        //向reques设置视频id
        request.setVideoId("3b931f14a75b406580bc77937d451cc9");
        //调用初始化对象的方法得到凭证
        response = client.getAcsResponse(request);
        System.out.println("playauth" + response.getPlayAuth());

    }
    public static void getPlayUrl()throws Exception{
        //1 根据视频id获取视频播放地址
        //初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4G6YW7aYhE6Me8hUsfvw","Bt2uOmtv5V6SynwDNA5hgYSqFYYWS9");
        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request对象里设置视频id
        request.setVideoId("3b931f14a75b406580bc77937d451cc9");
        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
