package com.xyz.caofancpu.d8ger.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class TicketMo {
    /**
     * ID
     */
    private Long id;
    /**
     * startStationName
     */
    private String startStationName;
    /**
     * endStationName
     */
    private String endStationName;
    /**
     * ticket price
     */
    private BigDecimal money;
    /**
     * orderTime (type by java.util.Date)
     */
    private Date orderTime;
    /**
     * ridingTime (type by java.time.LocalDateTime)
     */
    private LocalDateTime ridingTime;
    /**
     * mile
     */
    private Long mile;
    /**
     * isSuccess
     */
    private Boolean success;
    /**
     * deleted
     */
    private YesNoEnum deleted;
//    private Date updateTime;
//
//    private Date createTime;

}