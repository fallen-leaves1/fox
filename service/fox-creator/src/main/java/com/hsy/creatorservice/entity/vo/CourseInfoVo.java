package com.hsy.creatorservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {
    @ApiModelProperty(value = "合集ID")
    private String id;

    @ApiModelProperty(value = "创作者ID")
    private String creatorId;

    @ApiModelProperty(value = "分类ID")
    private String subjectId;

    @ApiModelProperty(value = "一级分类ID")
    private String subjectParentId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "设置为0则可免费观看")
    // 0.01
    private BigDecimal price;

    @ApiModelProperty(value = "总时长")
    private Integer lessonNum;

    @ApiModelProperty(value = "封面图片路径")
    private String cover;

    @ApiModelProperty(value = "简介")
    private String description;
}
