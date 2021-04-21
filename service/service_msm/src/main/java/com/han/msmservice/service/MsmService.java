package com.han.msmservice.service;

import java.util.Map;

/**
 * @author Han
 * @create 2020-10-06-5:45 下午
 */
public interface MsmService {
    //发送短信的方法
    boolean send(Map<String, Object> param, String phone);
}
