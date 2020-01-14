package com.xyz.caofancpu.d8ger.test.D8AutoCode;

import java.util.List;

/**
 * TicketMo对应的Service接口定义
 *
 * @author d8ger
 */
public interface TicketService {

    /**
     * 插入单条记录
     *
     * @param ticketMo
     * @return
     */
    int add(TicketMo ticketMo);

    /**
     * 批量插入
     *
     * @param ticketMoList
     * @return
     */
    int batchAdd(List<TicketMo> ticketMoList);

    /**
     * 查询列表, 如果携带分页参数则返回分页后的列表
     *
     * @param ticketMo
     * @param pageParams 可选分页参数
     * @return
     */
    List<TicketMo> queryTicketMoList(TicketMo ticketMo, Integer... pageParams);

    /**
     * 根据id更新非null字段
     *
     * @param ticketMo
     * @return
     */
    int updateSelectiveById(TicketMo ticketMo);

    /**
     * 根据id物理删除
     *
     * @param id
     * @return
     */
    <T extends Number> int delete(T id);

}