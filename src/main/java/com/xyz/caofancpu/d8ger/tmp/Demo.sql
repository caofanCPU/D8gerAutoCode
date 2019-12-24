-- -----------------------------------------------------------
-- auto [CREATE] Generated on 2019-12-21 11:39:42 
-- -----------------------------------------------------------
-- DROP TABLE IF EXISTS `demo`; 
CREATE TABLE demo
(
    `id`        INTEGER(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`      VARCHAR(64)          NOT NULL DEFAULT '' COMMENT 'name',
    `school_no` BIGINT               NOT NULL DEFAULT -1 COMMENT 'schoolNo',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT 'demo';
