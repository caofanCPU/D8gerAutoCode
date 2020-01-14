package com.xyz.caofancpu.d8ger.test.D8AutoCode;

import com.xyz.caofancpu.d8ger.test.YesNoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * TicketMo
 *
 * @author d8ger
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TicketMo {

    /**
     * ID
     */
    private Integer id;

    /**
     * startStationName
     */
    private String startStationName;

    /**
     * endStationName
     */
    private String endStationName;

    /**
     * ticket's price
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

}