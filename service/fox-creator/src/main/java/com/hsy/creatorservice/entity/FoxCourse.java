package com.hsy.creatorservice.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 创作者视频
 * </p>
 *
 * @author hsy
 * @since 2022-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FoxCourse对象", description="创作者视频")
public class FoxCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "集合ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "创作者ID")
    private String creatorId;

    @ApiModelProperty(value = "分类ID")
    private String subjectId;

    @ApiModelProperty(value = "分类父级ID")
    private String subjectParentId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总时长")
    private Integer lessonNum;

    @ApiModelProperty(value = "封面图片路径")
    private String cover;

    @ApiModelProperty(value = "贩卖数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}
