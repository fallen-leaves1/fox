package com.hsy.creatorservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class CreatorQuery {

        @ApiModelProperty(value = "创作者昵称")
        private String name;

        @ApiModelProperty(value = "头衔 1普通创作者 2高级创作者")
        private Integer level;

        @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
        private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

        @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
        private String end;

}
