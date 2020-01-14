
-- ----------------------------
-- D8ger-Sql-Auto-Generated
-- Table structure for `ticket`
-- @author d8ger
-- ----------------------------
-- DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`
(
    id int(11) unsigned auto_increment comment 'ID' primary key,
    start_station_name varchar(32) default '' null comment 'startStationName',
    end_station_name varchar(32) default '' null comment 'endStationName',
    money double(8, 2) default 0.00 null comment 'ticket's price',
    order_time datetime default CURRENT_TIMESTAMP null comment 'orderTime (type by java.util.Date)',
    riding_time datetime default CURRENT_TIMESTAMP null comment 'ridingTime (type by java.time.LocalDateTime)',
    mile bigint(20) default 0 null comment 'mile',
    success tinyint(1) default 0 null comment 'isSuccess',
    deleted int(4) default 0 null comment 'deleted',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment 'Ticket表' charset = utf8mb4;
