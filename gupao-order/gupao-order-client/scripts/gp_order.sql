DROP TABLE orders;;/*SkipError*/
CREATE TABLE orders(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '订单主键 订单id' ,
    order_no VARCHAR(64)    COMMENT '订单号' ,
    user_id VARCHAR(64) NOT NULL   COMMENT '用户id' ,
    total_amount INT NOT NULL   COMMENT '订单总价格 订单总价格，单位:分' ,
    real_pay_amount INT NOT NULL   COMMENT '实际支付总价格 实际支付总价格，单位:分' ,
    pay_method INT NOT NULL   COMMENT '支付方式 1:微信 2:支付宝' ,
    left_msg VARCHAR(128)    COMMENT '买家留言' ,
    extand VARCHAR(32)    COMMENT '扩展字段' ,
    is_delete INT NOT NULL  DEFAULT 0 COMMENT '逻辑删除状态 1: 删除 0:未删除' ,
    created_time DATETIME NOT NULL   COMMENT '创建时间' ,
    updated_time DATETIME NOT NULL   COMMENT '更新时间' ,
    order_status VARCHAR(32)    COMMENT '订单状态 订单交易的状态' ,
    pay_time DATETIME    COMMENT '支付成功时间 支付成功时间' ,
    close_time DATETIME    COMMENT '交易关闭时间 交易关闭时间' ,
    success_time DATETIME    COMMENT '交易成功时间 交易成功时间' ,
    delivery_time DATETIME    COMMENT '发货时间 发货时间' ,
    order_type VARCHAR(32)    COMMENT '订单类型 区分是否需要发货' ,
    channel_source VARCHAR(32)    COMMENT '渠道 区分三方渠道过来的订单' ,
    is_apple_pay INT NOT NULL  DEFAULT 0 COMMENT '是否苹果支付' ,
    PRIMARY KEY (id)
) COMMENT = '订单表 ';;

ALTER TABLE orders ADD INDEX IDX_ORDERS_CREATEDTIME(created_time);;
ALTER TABLE orders COMMENT '订单表';;
DROP TABLE order_details;;/*SkipError*/
CREATE TABLE order_details(
    id INT NOT NULL   COMMENT '主键id 主键id' ,
    order_no VARCHAR(64) NOT NULL   COMMENT '归属订单号 订单号' ,
    item_id VARCHAR(64) NOT NULL   COMMENT '商品id 商品id' ,
    item_img VARCHAR(128) NOT NULL   COMMENT '商品图片 商品图片' ,
    item_name VARCHAR(32) NOT NULL   COMMENT '商品名称 商品名称' ,
    item_type VARCHAR(32) NOT NULL   COMMENT '商品类型 商品类型' ,
    item_spec_id VARCHAR(32) NOT NULL   COMMENT '规格id 规格id' ,
    item_spec_name VARCHAR(32) NOT NULL   COMMENT '规格名称 规格名称' ,
    price_discount INT NOT NULL   COMMENT '成交价格 成交价格，单位:分' ,
    buy_counts INT NOT NULL   COMMENT '购买数量 购买数量' ,
    price_normal INT    COMMENT '原始价格 原始价格，单位:分' ,
    created_time DATETIME NOT NULL   COMMENT '创建时间 创建时间' ,
    updated_time DATETIME    COMMENT '更新时间 更新时间' ,
    vip_course_type INT    COMMENT '报名学科 报名学科，1-java构架 2-人工智能 3-大数据 4-软件测试..' ,
    PRIMARY KEY (id)
) COMMENT = '订单详情表 ';;

ALTER TABLE order_details COMMENT '订单详情表';;
DROP TABLE order_pay;;/*SkipError*/
CREATE TABLE order_pay(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    order_no VARCHAR(32)    COMMENT '订单号' ,
    merchant_order_id VARCHAR(64) NOT NULL   COMMENT '商户订单号 商户订单号' ,
    user_id VARCHAR(32)    COMMENT '用户id' ,
    pay_method INT NOT NULL   COMMENT '支付方式 1：微信，2：支付宝' ,
    pay_status INT NOT NULL   COMMENT '支付状态 支付状态 10：未支付 20：已支付 30：支付失败 40：已退款' ,
    come_from VARCHAR(32)    COMMENT '来源渠道 1:PC，2:APP，3：小程序' ,
    amount INT NOT NULL   COMMENT '实际支付金额 实际支付的金额，单位:分' ,
    created_time DATETIME NOT NULL   COMMENT '创建时间 创建时间' ,
    updated_time VARCHAR(32)    COMMENT '更新时间 更新时间' ,
    is_delete VARCHAR(32)    COMMENT '逻辑删除状态 逻辑删除状态;1: 删除 0:未删除' ,
    PRIMARY KEY (id)
) COMMENT = '支付表 ';;

ALTER TABLE order_pay ADD INDEX IDX_ORDERS_CREATEDTIME(created_time);;
ALTER TABLE order_pay COMMENT '支付表';;
DROP TABLE orders_logs;;/*SkipError*/
CREATE TABLE orders_logs(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    order_no VARCHAR(32)    COMMENT '订单号 同时也是订单编号' ,
    user_id VARCHAR(64) NOT NULL   COMMENT '用户id' ,
    total_amount INT NOT NULL   COMMENT '订单总价格 订单总价格，单位:分' ,
    real_pay_amount INT NOT NULL   COMMENT '实际支付总价格 实际支付总价格，单位:分' ,
    pay_method INT NOT NULL   COMMENT '支付方式 1:微信 2:支付宝' ,
    left_msg VARCHAR(128)    COMMENT '买家留言' ,
    extand VARCHAR(32)    COMMENT '扩展字段' ,
    is_delete INT NOT NULL  DEFAULT 0 COMMENT '逻辑删除状态 1: 删除 0:未删除' ,
    created_time DATETIME NOT NULL   COMMENT '创建时间（成交时间）' ,
    updated_time DATETIME NOT NULL   COMMENT '更新时间' ,
    order_status VARCHAR(32)    COMMENT '订单状态 订单交易的状态' ,
    pay_time DATETIME    COMMENT '支付成功时间 支付成功时间' ,
    close_time DATETIME    COMMENT '交易关闭时间 交易关闭时间' ,
    success_time DATETIME    COMMENT '交易成功时间 交易成功时间' ,
    delivery_time DATETIME    COMMENT '发货时间 发货时间' ,
    order_type VARCHAR(32)    COMMENT '订单类型' ,
    channel_source VARCHAR(32)    COMMENT '渠道' ,
    is_apple_pay VARCHAR(32) NOT NULL  DEFAULT 0 COMMENT '是否苹果支付' ,
    PRIMARY KEY (id)
) COMMENT = '订单快照表 ';;

ALTER TABLE orders_logs COMMENT '订单快照表';;
DROP TABLE orders_supplement;;/*SkipError*/
CREATE TABLE orders_supplement(
    vip_course_type INT NOT NULL   COMMENT '报名学科 报名学科，1-java构架 2-人工智能 3-大数据 4-软件测试..' ,
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键 主键' ,
    order_no VARCHAR(32) NOT NULL   COMMENT '订单号 订单号' ,
    vip_course_id BIGINT NOT NULL   COMMENT '课程id 课程id，关联course表' ,
    vip_course_name VARCHAR(32) NOT NULL   COMMENT '课程名称 课程名称' ,
    recommend_code VARCHAR(32) NOT NULL   COMMENT '推荐码 推荐码' ,
    recommend_teacher_id INT    COMMENT '推荐老师id 推荐老师id' ,
    recommend_user_id INT    COMMENT '推荐学员id 推荐学员id' ,
    class_teacher VARCHAR(32)    COMMENT '听哪位老师课 听哪位老师课' ,
    vip_origin VARCHAR(32)    COMMENT '报名来源 报名来源' ,
    listen_time INT   DEFAULT 0 COMMENT '试听次数 试听次数' ,
    is_deleted INT    COMMENT '是否删除 是否删除' ,
    created_time DATETIME NOT NULL   COMMENT '创建时间 创建时间' ,
    updated_time DATETIME    COMMENT '更新时间 更新时间' ,
    solve_problem VARCHAR(512)    COMMENT '需解决的问题 需解决的问题' ,
    PRIMARY KEY (id)
) COMMENT = '订单补充表 用于存储第三方渠道的课程生成的订单信息';;

ALTER TABLE orders_supplement COMMENT '订单补充表';;
DROP TABLE order_logistics;;/*SkipError*/
CREATE TABLE order_logistics(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '订单主键 同时也是订单编号' ,
    user_id VARCHAR(64) NOT NULL   COMMENT '用户id' ,
    order_no VARCHAR(32)    COMMENT '订单号' ,
    receiver_name VARCHAR(32) NOT NULL   COMMENT '收货人姓名' ,
    receiver_mobile VARCHAR(32) NOT NULL   COMMENT '收货人手机号' ,
    receiver_address VARCHAR(128) NOT NULL   COMMENT '收货地址' ,
    tracking_number VARCHAR(32)    COMMENT '快递单号 发货后的快递单号' ,
    track_company_name VARCHAR(32)    COMMENT '快递公司名称 快递公司名称' ,
    track_company_code VARCHAR(32)    COMMENT '快递公司编号 快递公司编号' ,
    post_amount INT   DEFAULT 0 COMMENT '邮费 默认可以为零，代表包邮' ,
    sender_name VARCHAR(32)    COMMENT '寄件人姓名' ,
    sender_address VARCHAR(32)    COMMENT '寄件人地址' ,
    sender_mobile VARCHAR(32)    COMMENT '寄件人手机号' ,
    created_time DATETIME NOT NULL   COMMENT '创建时间（成交时间）' ,
    updated_time DATETIME NOT NULL   COMMENT '更新时间' ,
    PRIMARY KEY (id)
) COMMENT = '物流信息表 ';;

ALTER TABLE order_logistics ADD INDEX IDX_ORDERS_CREATEDTIME(created_time);;
ALTER TABLE order_logistics ADD UNIQUE IDX_UK_ORDERS_TRACKINGNUMBER(tracking_number);;
ALTER TABLE order_logistics COMMENT '物流信息表';;
DROP TABLE products;;/*SkipError*/
CREATE TABLE products(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键id 唯一标识' ,
    item_img VARCHAR(128) NOT NULL   COMMENT '商品图片' ,
    item_name VARCHAR(32) NOT NULL   COMMENT '商品名称' ,
    item_type VARCHAR(32) NOT NULL   COMMENT '商品类型' ,
    price_vip INT    COMMENT '会员价 单位分' ,
    buy_counts INT NOT NULL   COMMENT '购买数量' ,
    price_normal INT    COMMENT '原始价格 单位:分' ,
    created_time DATETIME NOT NULL   COMMENT '创建时间' ,
    updated_time DATETIME    COMMENT '更新时间' ,
    vip_course_type VARCHAR(32)    COMMENT '报名学科 报名学科，1-java构架 2-人工智能 3-大数据 4-软件测试..' ,
    PRIMARY KEY (id)
) COMMENT = '商品表 ';;

ALTER TABLE products COMMENT '商品表';;

DROP TABLE express_basic_info ;;/*SkipError*/
CREATE TABLE express_basic_info(
   id INT NOT NULL AUTO_INCREMENT  COMMENT '主键 id' ,
   company_code VARCHAR(64)  NOT NULL  COMMENT '快递公司编码' ,
   company_name VARCHAR(64) NOT NULL   COMMENT '快递公司名称' ,
   company_type VARCHAR(64) NOT NULL   COMMENT '快递公司类型' ,
   PRIMARY KEY (id)
) COMMENT = '快递公司维护表 ';



appid：2016101900719854
支付宝公钥： MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkFfFKse1xlZTDwS4LOc8snmZSUM8TBziEswrYkKVLbhvlYRe2NITbjfM+NJ74ZSkgDU5D5G8indwjw3U5RDAAKy5OOA0viCuKWi3HpS/doKrP2LQCB31zlmvrbKYGhMglZ8LMI+GgocKdBkoQdXToA2CNDu1iG1vOJuLDgp1Jg2vrchjEWmSw2rEE5rXVNXYtbc6ZixYnk9MBkFIOOkdhGG/c61Qmxz7jkHIsQt9XZAbZ9qKR2BEMHxKqwQUovvSwAxRqV0764z9DV0Q5g4Kig7w6KU9kqnibmHoK7Ddm7AcZUcDmTKrERCgmRxXeiZHTJKp/y5ncfnvb5jM/SkjqwIDAQAB
支付宝应用私钥：MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1PUJpftIybc4Ah55FcWyiZog+rTX56xZwhtaFiEaFwk7RljDqvLwq26i+/sVkhn6rfUs2HvUg0I9JzhV7X+s0MzAlI3/MSSpEfLsSmeIGySNCQABE9ADffzGEg1aH8pxoqfm7dw7uehcs1d+fsWCgmcbYZtlhg9b4nhCdRW9OKPSPPzvOJN79tCk/RPk3PBb2GgBhJrjU4vu9jS1vFKZCkCMWdDjonBHGwONtoP2n9KKY0t6sGChdjLe+6lZZuqU/izgcJFdA51yTRTzxDL4PPSXQbSEyhGzgHVLef4/2YtJfPJ1HXm1zJ4mHAl8ndMz2AQKFzJaipZP7MGW5DzflAgMBAAECggEAA2pTHQ/RwDeHgUUM+3wGswzBloTHFp2Sf65azoth4NVnjNWKuHG0Dg6zoF3GtH1AV9T1nOB6eBLWEGr8xqVpFkO8rYZ8uwnJ7R54nAIgW0ZhDreMCcCbX5rTAQ9Eiy1MO0CwvxKgSmgslaC2JRk/m+cp8xwFDqxgWbEI2R3c2hktV3Jxk45FbahgNgFesNGoynMX44MjBNsErM8i8v/ShFslECvSlezY6Rg420lA0SSwFR8MAkGaMtgTCA9ZxZkoYFzWSGMuJHkrO/H5ztZRrwAdf60fXrqli2eMjBbFdiHvWje5GluIMsGeZJ1lU0QukMdKRb1uEsH/aJfDiXuqYQKBgQDmuP+tR4xnSwS303C5oteCIgaIAuacx8z78hUwCmh3V2wfEPmw8d4sFjVxDf/HdDZ5Vmj+ZnEfqm5/GFL/ipTVxI8RHC0x/z+Bn2dogAYIjzSxTD7Sjp+C5YLf9p4DLUL2kSON+H/q6q4T7JniFkbV2vA4ONXh4TDwyvROy01mGQKBgQDJGGrf/Wci7qHKD6xODPYtk6I4mvr5BWiYzwgazMc7HIuSkdX+yrUElmYlKELf42r9+dYNmu2f7U8qvBHhfrwZX8k7rpzdm1i7a9epkl+HAGK6n2n/EpUNNQn6wQ1he4RK4ScWwPPz3AyaPp+jsU7AY9p4anXP3xVAMJ0Su1ghrQKBgQCXTcbN+dfHfVjQvGZ2NqYhfvLNiyl0s0poBu+CG7E6YihTjyxPr63FN6NASq/sOvfmRpaADuosm/XufyMe1eCY91fjHpk86mjZNpH3480RG+iwRJIGPuW4T46uFOq2pp39WFz6o3YvtksGYejgbT9yyysxk4QfpzATGOVyLrzJMQKBgCrC9ncK+5OCCwVcr9cpCnBGpIhE7HlnnQZhf+ezA+qd91ADku3LTRLcqHveQ9o4EdhP+CSMfVs0q1D7DRJWUMlUhQYb6190WToBhKqaZZXfjx8AJxoGaYuVa7nWs3G3QjpqBlXgUf/ZGxTxnCDC42wlz58rhEHfvjUYIU6KaANBAoGALBwmQ8oixEafkl+YwTNSs7fX37p4DAyNSTTqx8eTO4t/Awnk/2023QX5HDAB4Rz83NiXPfic0y+fYhai9P7nIpSlfqwo9ee4N+AtLw5lBu+R+pfnDgjIQlgEBqNhiICf0F8qiXY7L9p9AqegV8/L/dT57/eJvY++SEaZpKMARGw=