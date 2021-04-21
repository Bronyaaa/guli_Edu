package com.han.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Han
 * @create 2020-09-12-4:51 下午
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //1设置写入文件夹地址和excel文件名称
//        String filename = "/Users/jinyanhan/Desktop/write.xlsx";
//
//        //2调用easyexcel方法实现写操作
//        //第一个参数文件路径名称，第二个是参数实体类class
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
        //实现exel读操作
        String filename = "/Users/jinyanhan/Desktop/write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    //方法返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            DemoData data = new DemoData();
            data.setSno(i);
            data.setName("lucy" + i);
            list.add(data);
        }
        return list;
    }
}
