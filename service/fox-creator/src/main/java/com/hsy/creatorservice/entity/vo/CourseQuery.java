package com.hsy.creatorservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseQuery {
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "分类ID")
    private String subjectId;

    @ApiModelProperty(value = "分类父级ID")
    private String subjectParentId;
}
