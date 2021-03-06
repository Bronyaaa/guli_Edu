package com.han.eduService.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Han
 * @create 2020-09-17-9:55 下午
 */
@Data
public class ChapterVo {
    private String id;

    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
