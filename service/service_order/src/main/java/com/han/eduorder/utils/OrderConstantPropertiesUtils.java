package com.han.eduorder.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Han
 * @create 2020-09-11-12:19 上午
 */
//当项目已经启动，spring接口，spring加载以后，执行接口一个方法
@Component
public class OrderConstantPropertiesUtils implements InitializingBean {
    //读取配置文件内容
    @Value("${weixin.pay.appid}")
    private String appid;
    @Value("${weixin.pay.partner}")
    private String partner;
    @Value("${weixin.pay.partnerkey}")
    private String partnerkey;
    @Value("${weixin.pay.notifyurl}")
    private String notifyurl;

    //定义公开静态常量
    public static String APP_ID;
    public static String PARTNER;
    public static String PARTNER_KEY;
    public static String NOTIFY_URL;
    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appid;
        PARTNER = partner;
        PARTNER_KEY = partnerkey;
        NOTIFY_URL = notifyurl;
    }
}
