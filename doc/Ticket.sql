
-- ----------------------------
-- D8ger-Sql-Auto-Generated
-- Table structure for `ticket`
-- @author 郑承演
-- ----------------------------
-- DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`
(
    id bigint(20) unsigned auto_increment comment 'ID' primary key,
    start_station_name varchar(32) default '' null comment 'startStationName',
    end_station_name varchar(32) default '' null comment 'endStationName',
    money double(8, 2) default 0.00 null comment 'ticket price',
    order_time datetime default CURRENT_TIMESTAMP null comment 'orderTime (type by java.util.Date)',
    riding_time datetime default CURRENT_TIMESTAMP null comment 'ridingTime (type by java.time.LocalDateTime)',
    mile bigint(20) default 0 null comment 'mile',
    success tinyint(1) default 0 null comment 'isSuccess',
    deleted int(4) default 0 null comment 'deleted'
    create_time datetime default CURRENT_TIMESTAMP null comment 'createTime',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment 'updateTime'

)
    comment 'Ticket' charset = utf8mb4;
