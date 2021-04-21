package com.han.eduService.controller;

import com.han.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author Han
 * @create 2020-09-09-5:08 下午
 */
@RestController
@RequestMapping("/eduService/user")
@CrossOrigin //解决跨域
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1599657179078&di=b4bdb2bd58162ea1c168fd194c33d364&imgtype=0&src=http%3A%2F%2Fdp.gtimg.cn%2Fdiscuzpic%2F0%2Fdiscuz_x5_gamebbs_qq_com_forum_201306_19_1256219xc797y90heepdbh.jpg%2F0");
    }


}
