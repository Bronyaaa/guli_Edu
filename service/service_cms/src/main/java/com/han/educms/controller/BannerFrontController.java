package com.han.educms.controller;

import com.han.commonutils.R;
import com.han.educms.entity.CrmBanner;
import com.han.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台banner显示
 * @author Han
 * @create 2020-10-03-8:24 上午
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @Cacheable(key = "'selectIndexList'",value = "banner")
    @GetMapping("getAllBanner")
    public R getAllBanner(){
        //查询所有banner
        List<CrmBanner> list =  bannerService.selectAllBanner();
        return R.ok().data("list",list);
    }
}
