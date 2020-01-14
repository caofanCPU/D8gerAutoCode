package com.xyz.caofancpu.d8ger.test.D8AutoCode;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * TicketMo对应的ServiceImpl
 *
 * @author d8ger
 */
@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Resource
    private TicketMapper ticketMapper;

    /**
     * 插入单条记录
     *
     * @param ticketMo
     * @return
     */
    @Override
    public int add(TicketMo ticketMo) {
        ticketMo.setId(null);
        return ticketMapper.insertWithId(ticketMo);
    }

    /**
     * 批量插入
     *
     * @param ticketMoList
     * @return
     */
    @Override
    public int batchAdd(List<TicketMo> ticketMoList) {
        ticketMoList.forEach(item -> item.setId(null));
        return ticketMapper.insertBatchWithId(ticketMoList);
    }

    /**
     * 查询列表, 如果携带分页参数则返回分页后的列表
     *
     * @param ticketMo
     * @param pageParams 可选分页参数
     * @return
     */
    @Override
    public List<TicketMo> queryTicketMoList(TicketMo ticketMo, Integer... pageParams) {
        if (Objects.nonNull(pageParams) && pageParams.length > 0) {
            int pageNum = pageParams[0];
            int pageSize = pageParams.length > 1 ? pageParams[1] : 10;
            PageHelper.startPage(pageNum, pageSize);
        }
        return ticketMapper.queryTicketMoList(ticketMo);
    }

    /**
     * 根据id更新非null字段
     *
     * @param ticketMo
     * @return
     */
    @Override
    public int updateSelectiveById(TicketMo ticketMo) {
        return ticketMapper.updateByPrimaryKeySelective(ticketMo);
    }

    /**
     * 根据id物理删除
     *
     * @param id
     * @return
     */
    @Override
    public <T extends Number> int delete(T id) {
        return ticketMapper.deleteByPrimaryKey(id);
    }

}