package com.han.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Han
 * @create 2020-09-12-4:50 下午
 */
@Data
public class DemoData {
    //设置excel表头名称
    @ExcelProperty("学生编号")
    private Integer sno;
    @ExcelProperty("学生姓名")
    private String name;


}
