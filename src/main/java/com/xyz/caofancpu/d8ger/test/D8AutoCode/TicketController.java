package com.xyz.caofancpu.d8ger.test.D8AutoCode;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * TicketMo控制器
 *
 * @author d8ger
 */
@RestController
@Api(tags = {"TicketMo模块接口"})
@ApiSort(0)
@Slf4j
public class TicketController {

    @Resource
    private TicketService ticketService;

    @PostMapping(value = "/d8gerAutoCoding/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "TicketMo新增记录")
    public Object add(@Valid @RequestBody TicketVo ticketVo) {
        // 转换数据
        TicketMo ticketMo = JSONObject.parseObject(JSONObject.toJSONString(ticketVo), TicketMo.class);
        ticketService.add(ticketMo);
        return ticketMo.getId();
    }

    @PostMapping(value = "/d8gerAutoCoding/batchAdd")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "TicketMo批量新增")
    public Object batchAdd(@Valid @RequestBody List<TicketVo> ticketVoList) {
        List<TicketMo> ticketMoList = new ArrayList<>(ticketVoList.size());
        for (TicketVo ticketVo : ticketVoList) {
            ticketMoList.add(JSONObject.parseObject(JSONObject.toJSONString(ticketVo), TicketMo.class));
        }
        return ticketService.batchAdd(ticketMoList);
    }

    @PostMapping(value = "/d8gerAutoCoding/queryTicketMoList")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "TicketMo列表查询")
    public Object queryTicketMoList(@Valid @RequestBody TicketVo ticketVo) {
        // 转换数据
        TicketMo ticketMo = JSONObject.parseObject(JSONObject.toJSONString(ticketVo), TicketMo.class);
        return ticketService.queryTicketMoList(ticketMo);
    }

    @PostMapping(value = "/d8gerAutoCoding/queryTicketMoPage")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "TicketMo分页查询")
    public Object queryTicketMoPage(@Valid @RequestBody TicketVo ticketVo) {
        // 转换数据
        TicketMo ticketMo = JSONObject.parseObject(JSONObject.toJSONString(ticketVo), TicketMo.class);
        List<TicketMo> resultTicketMoList = ticketService.queryTicketMoList(ticketMo, ticketVo.getPageNum(), ticketVo.getPageSize());
        return PageInfo.of(resultTicketMoList);
    }

    @PostMapping(value = "/d8gerAutoCoding/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "TicketMo修改记录")
    public Object update(@Valid @RequestBody TicketVo ticketVo) {
        // 转换数据
        TicketMo ticketMo = JSONObject.parseObject(JSONObject.toJSONString(ticketVo), TicketMo.class);
        return ticketService.updateSelectiveById(ticketMo);
    }

    @PostMapping(value = "/d8gerAutoCoding/delete")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "TicketMo删除记录")
    public Object delete(@Valid @RequestBody TicketVo ticketVo) {
        return ticketService.delete(ticketVo.getId());
    }


}
