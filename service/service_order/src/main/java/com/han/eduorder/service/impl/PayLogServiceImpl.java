package com.han.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.han.eduorder.entity.Order;
import com.han.eduorder.entity.PayLog;
import com.han.eduorder.mapper.PayLogMapper;
import com.han.eduorder.service.OrderService;
import com.han.eduorder.service.PayLogService;
import com.han.eduorder.utils.HttpClient;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.han.eduorder.utils.OrderConstantPropertiesUtils;
import com.han.service_base.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {
    @Autowired
    private OrderService orderService;

    //生成微信支付二维码接口
    @Override
    public Map creativeNative(String orderNo) {
        //工具类获取值
        String appId = OrderConstantPropertiesUtils.APP_ID;
        String notifyUrl = OrderConstantPropertiesUtils.NOTIFY_URL;
        String partner = OrderConstantPropertiesUtils.PARTNER;
        String partnerKey = OrderConstantPropertiesUtils.PARTNER_KEY;
        try {
            //1 根据订单号查询订单信息
            QueryWrapper<Order> wrapper = new QueryWrapper<Order>();
            wrapper.eq("order_no",orderNo);
            Order order = orderService.getOne(wrapper);
            //2 使用map设置生成二维码需要参数
            Map m = new HashMap();
            m.put("appid",appId);
            m.put("notifyUrl",notifyUrl);
            m.put("partner",partner);
            m.put("partnerKey",partnerKey);
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle()); //课程标题
            m.put("out_trade_no", orderNo); //订单号
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("trade_type", "NATIVE");
            //3 发送httpclient请求，传递参数xml格式，微信支付提供的固定 的地址
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //设置xml格式的参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m,partnerKey));
            client.setHttps(true);
            //执行post请求发送
            client.post();

            //4 得到发送请求返回结果
            String xml = client.getContent();

            //返回内容，是使用xml格式返回
            Map<String,String> resultMap = WXPayUtil.xmlToMap(xml);

            //最终返回数据的封装
            Map map = new HashMap();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));  //返回二维码操作状态码
            map.put("code_url", resultMap.get("code_url"));        //二维码地址

            return map;

        }catch (Exception e){
            throw new GuliException(20001,"生成二维码失败");

        }
    }

    //查询订单支付状态
    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        String appId = OrderConstantPropertiesUtils.APP_ID;
        String partnerKey = OrderConstantPropertiesUtils.PARTNER_KEY;

        try {
            //1 封装参数
            Map m = new HashMap();
            m.put("appId",appId);
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2 发送httpClient
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            httpClient.setXmlParam(WXPayUtil.generateSignedXml(m,partnerKey));
            httpClient.setHttps(true);
            httpClient.post();

            //3 得到请求返回的内容
            String xml = httpClient.getContent();
            Map<String,String> resultMap = WXPayUtil.xmlToMap(xml);
            //转成map
            //返回
            return resultMap;
        } catch (Exception e) {
            return null;
        }
    }

    //添加支付记录和更新订单状态
    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //从map获取订单号
        String orderNo = map.get("out_trade_no");
        //根据订单号查询订单信息
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);

        //更新订单表订单状态
        if (order.getStatus().intValue() == 1){
            return;
        }
        order.setStatus(1);
        orderService.updateById(order);

        //向支付表添加支付记录
        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);//订单号
        payLog.setPayTime(new Date()); //订单完成时间
        payLog.setPayType(1);//支付类型 1微信
        payLog.setTotalFee(order.getTotalFee());//总金额(分)

        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id")); //流水号
        payLog.setAttr(JSONObject.toJSONString(map));

    }
}