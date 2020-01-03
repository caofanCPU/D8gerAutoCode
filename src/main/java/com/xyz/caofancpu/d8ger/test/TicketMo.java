package com.xyz.caofancpu.d8ger.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class TicketMo {
    /**
     * ID
     */
    private Integer id;
    /**
     * 起始站名称
     */
    private String startStationName;
    /**
     * 到站名称
     */
    private String endStationName;
    /**
     * 票价
     */
    private BigDecimal money;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 乘车时间
     */
    private LocalDateTime ridingTime;
    /**
     * 里程
     */
    private Long mile;
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 是否删除
     */
    private YesNoEnum deleted;

}