package com.hsy.creatorservice.entity;

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
 * 创作者
 * </p>
 *
 * @author hsy
 * @since 2022-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FoxCreator对象", description="创作者")
public class FoxCreator implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创作者ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "创作者昵称")
    private String name;

    @ApiModelProperty(value = "创作者介绍")
    private String intro;

    @ApiModelProperty(value = "资历")
    private String career;

    @ApiModelProperty(value = "头衔 1普通创作者 2高级创作者")
    private Integer level;

    @ApiModelProperty(value = "创作者头像")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private Integer sort;

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
