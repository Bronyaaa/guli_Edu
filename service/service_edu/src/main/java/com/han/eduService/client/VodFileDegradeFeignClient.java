package com.han.eduService.client;

import com.han.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Han
 * @create 2020-09-29-3:51 下午
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    //出错之后会执行
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除出啜");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出啜");
    }
}
