package com.xyz.caofancpu.d8ger.test.D8AutoCode;

import com.xyz.caofancpu.d8ger.test.YesNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * TicketMo对应的SwaggerApi增强Vo对象
 *
 * @author d8ger
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel
public class TicketVo {

    @ApiModelProperty(value = "ID", required = false, example = "", position = 0)
    private Integer id;

    @ApiModelProperty(value = "startStationName", required = false, example = "", position = 1)
    private String startStationName;

    @ApiModelProperty(value = "endStationName", required = false, example = "", position = 2)
    private String endStationName;

    @ApiModelProperty(value = "ticket's price", required = false, example = "", position = 3)
    private BigDecimal money;

    @ApiModelProperty(value = "orderTime (type by java.util.Date)", required = false, example = "", position = 4)
    private Date orderTime;

    @ApiModelProperty(value = "ridingTime (type by java.time.LocalDateTime)", required = false, example = "", position = 5)
    private LocalDateTime ridingTime;

    @ApiModelProperty(value = "mile", required = false, example = "", position = 6)
    private Long mile;

    @ApiModelProperty(value = "isSuccess", required = false, example = "", position = 7)
    private Boolean success;

    @ApiModelProperty(value = "deleted", required = false, example = "", position = 8)
    private YesNoEnum deleted;

    @ApiModelProperty(value = "分页页码", required = false, example = "1", position = 9)
    private Integer pageNum;

    @ApiModelProperty(value = "分页大小", required = false, example = "10", position = 10)
    private Integer pageSize;

}