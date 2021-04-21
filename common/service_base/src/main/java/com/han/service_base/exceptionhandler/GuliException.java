package com.han.service_base.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Han
 * @create 2020-09-06-5:26 下午
 */
@Data
//下面注解能生成有参数构造方法
@AllArgsConstructor
//生成无参数构造
@NoArgsConstructor
public class GuliException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
