package com.han.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.han.eduService.entity.EduSubject;
import com.han.eduService.entity.excel.SubjectData;
import com.han.eduService.entity.subject.OneSubject;
import com.han.eduService.entity.subject.TwoSubject;
import com.han.eduService.listener.SubjectExcelListener;
import com.han.eduService.mapper.EduSubjectMapper;
import com.han.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-09-12
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectsLists = baseMapper.selectList(wrapperOne);
        //查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> twoSubjectsLists = baseMapper.selectList(wrapperTwo);

        //创建list集合用于储存最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        //封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值
        //封装到要求的list集合里面List<OneSubject> finalSubjects
        for (EduSubject eduSubject : oneSubjectsLists) {
            //把eduS离main值获取出来，放到OneS对象里面
            //多个OneS放到finalS中
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());f
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject, oneSubject);
            finalSubjectList.add(oneSubject);

            //在一级分类李循环遍历查询所有的二级分类
            //创建list集合封装每一个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int i1 = 0; i1 < twoSubjectsLists.size(); i1++) {
                //获取每个二级分类
                EduSubject twoSubject = twoSubjectsLists.get(i1);
                //判断二级分类pid与一级是否一样
                if (twoSubject.getParentId().equals(eduSubject.getId())) {
                    //把twoSubject放到把TwoSubject放到，放到twoFinalSubjectList里面
                    TwoSubject twoSubject1 = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject, twoSubject1);
                    twoFinalSubjectList.add(twoSubject1);
                }
            }

            //把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);


        }
        //封装二级分类
        return finalSubjectList;

    }
}
