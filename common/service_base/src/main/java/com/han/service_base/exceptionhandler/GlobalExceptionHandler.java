package com.han.service_base.exceptionhandler;

import com.han.commonutils.R;
import com.han.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Han
 * @create 2020-09-06-3:15 下午
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理..");

    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理..");

    }
    //自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody//为了返回数据
    public R error(GuliException e){
        //下面这句话是搭配@Slf4j注解写的
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        //这里为啥是getMsg不是getMessage呢 因为GuliException中状态码和异常信息分别是code和msg
        return R.error().code(e.getCode()).message(e.getMsg());

    }
}
