package com.xyz.caofancpu.d8ger.test.D8AutoCode;

import com.xyz.caofancpu.d8ger.test.D8AutoCode.TicketExample;
import com.xyz.caofancpu.d8ger.test.D8AutoCode.TicketMo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TicketMo对应的Mapper
 *
 * @author d8ger
 */
@Mapper
public interface TicketMapper {

    /**
     * 根据条件查询列表
     *
     * @param ticketExample
     * @return
     */
    List<TicketMo> selectByExample(TicketExample ticketExample);

    /**
     * 批量更新, 根据主键更新非null字段
     *
     * @param ticketMoList
     * @return
     */
    int updateBatchByPrimaryKeySelective(List<TicketMo> ticketMoList);

    /**
     * 根据条件更新非null字段
     *
     * @param ticketMo
     * @param ticketExample
     * @return
     */
    int updateByExampleSelective(@Param("record") TicketMo ticketMo, @Param("example") TicketExample ticketExample);

    /**
     * 根据条件删除记录
     *
     * @param ticketExample
     * @return
     */
    int deleteByExample(TicketExample ticketExample);

    /**
     * 根据条件统计记录
     *
     * @param ticketExample
     * @return 记录条数
     */
    int countByExample(TicketExample ticketExample);

    /**
     * 增加单条记录, 并为入参设置ID
     *
     * @param ticketMo
     * @return
     */
    int insertWithId(TicketMo ticketMo);

    /**
     * 批量增加记录, 并为入参设置ID
     *
     * @param ticketMoList
     * @return
     */
    int insertBatchWithId(List<TicketMo> ticketMoList);

    /**
     * Ticket列表查询
     *
     * @param ticketMo
     * @return
     */
    List<TicketMo> queryTicketMoList(TicketMo ticketMo);

    /**
     * 根据ID查询对象
     *
     * @param id
     * @return
     */
    <T extends Number> TicketMo selectByPrimaryKey(T id);

    /**
     * 根据主键只更新非null字段
     *
     * @param ticketMo
     * @return
     */
    int updateByPrimaryKeySelective(TicketMo ticketMo);

    /**
     * 根据ID删除记录
     *
     * @param id
     * @return
     */
    <T extends Number> int deleteByPrimaryKey(T id);
}