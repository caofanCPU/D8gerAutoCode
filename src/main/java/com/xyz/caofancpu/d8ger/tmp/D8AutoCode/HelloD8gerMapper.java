package com.xyz.caofancpu.d8ger.tmp.D8AutoCode;

@Mapper
public interface HelloD8gerMapper {

    /**
     * 添加记录
     *
     * @param helloD8ger
     * @return
     */
    int add(HelloD8ger helloD8ger);

    /**
     * 批量插入
     *
     * @param helloD8gerList
     * @return
     */
    int batchAdd(List<HelloD8ger> helloD8gerList);

    /**
     * 查询列表
     *
     * @param helloD8ger
     * @return
     */
    List<HelloD8ger> queryList(HelloD8ger helloD8ger);

    /**
     * 更新记录
     *
     * @param helloD8ger
     * @return
     */
    int update(HelloD8ger helloD8ger);

    /**
     * [WARN]: 主键id物理删除
     *
     * @param id
     * @return
     */
    <T extends Number> int deleteById(T id);

}