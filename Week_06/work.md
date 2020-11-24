# 作业提交

## 1. 用户表

```sql
CREATE TABLE `ycz`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(45) NOT NULL COMMENT '用户名',
  `sex` VARCHAR(2) NOT NULL COMMENT '性别',
  `remainder` BIGINT NULL COMMENT '账户余额',
  `tel` VARCHAR(18) NULL COMMENT '电话号',
  `addr` VARCHAR(300) NULL COMMENT '地址',
  `email` VARCHAR(45) NULL COMMENT '邮件',
  `createtime` DATETIME NULL COMMENT '创建时间',
  PRIMARY KEY (`id`));
```
## 2. 商品表
```sql
CREATE TABLE `ycz`.`commodity` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(45) NOT NULL COMMENT '商品名',
  `type` int NOT NULL COMMENT '商品类型',
  `price` BIGINT NULL COMMENT '商品价格',
  `stock` BIGINT  NULL COMMENT '商品库存',
  `total`BIGINT  NULL COMMENT '商品总量',
  `commodityinfo` VARCHAR(300)NULL COMMENT '商品详细信息',
  `createtime` DATETIME NULL COMMENT '创建时间',
  PRIMARY KEY (`id`));
```
## 3. 订单表
```sql
CREATE TABLE `ycz`.`order` (
  `id` BIGINT NOT NULL COMMENT 'id',
  `userId` BIGINT NOT NULL COMMENT '用户id',
  `commodityId` BIGINT NULL COMMENT '商品id',
  `orderstarttime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `orderstate` INT NULL COMMENT '订单状态',
  PRIMARY KEY (`id`));
```





