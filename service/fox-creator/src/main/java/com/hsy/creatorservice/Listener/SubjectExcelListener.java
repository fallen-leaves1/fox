package com.hsy.creatorservice.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsy.creatorservice.entity.FoxSubject;
import com.hsy.creatorservice.entity.excel.SubjectData;
import com.hsy.creatorservice.service.FoxSubjectService;
import com.hsy.servicebase.exception.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    public FoxSubjectService subjectService;
    public SubjectExcelListener() {}
    public SubjectExcelListener(FoxSubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new GuliException(201,"文件数据为空");
        }
        FoxSubject oneSubject = this.existOneSubject(this.subjectService,subjectData.getOneSubjectName());
        if (oneSubject == null){
            oneSubject = new FoxSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(oneSubject);
        }
        String pid = oneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        FoxSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new FoxSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);
        }
    }

//   判断一级分类
    private FoxSubject existOneSubject(FoxSubjectService foxSubjectService,String name){
        QueryWrapper<FoxSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        FoxSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }
    //   判断二级分类
    private FoxSubject existTwoSubject(FoxSubjectService foxSubjectService,String name,String pid){
        QueryWrapper<FoxSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        FoxSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
