-- 用户表
CREATE TABLE smart_user (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  username varchar(50) NOT NULL COMMENT '用户名,可重复',
  password    varchar(50) NOT NULL COMMENT '用户密码,MD5加密 ',
  sex varchar(5) NOT NULL ,
  role int(1) NOT NULL COMMENT '0-普通用户 1-管理员',
  phone  varchar(20)  NOT NULL COMMENT '手机号,用于登录',
  email  varchar(50)  NOT NULL,
  face_data text NOT NULL COMMENT '人脸数据',
  avatar_url varchar(500) NOT NULL COMMENT '头像数据',
  face_url varchar(500) NOT NULL COMMENT '人脸数据',
  create_time datetime    NOT NULL COMMENT '创建时间',
  update_time datetime    NOT NULL COMMENT '最后一次更新时间',
  UNIQUE phone_pwd_index (phone, password) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8;

-- 会议室表
CREATE TABLE smart_room (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  room_number varchar(20) NOT NULL COMMENT '门牌号',
  content int NOT NULL COMMENT '容量',
  machine_number varchar(20) NOT NULL COMMENT '设备号',
  status int(1) NOT NULL COMMENT '状态 1-空闲 2-使用 3-维护',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  KEY num_index (room_number) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8;

-- 会议表
CREATE TABLE smart_meeting (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '会议id',
  meeting_name varchar(200) NOT NULL COMMENT '会议名称',
  meeting_intro varchar(500) default null COMMENT '会议简介',
  user_id int(11) NOT NULL COMMENT '与会人员id',
  room_id int(11) NOT NULL COMMENT '会议室id',
  user_status int(1) NOT NULL COMMENT '用户状态 1-正常 2-缺勤 3-迟到 4-请假',
  status int(1) NOT NULL COMMENT '会议状态  1-已结束  2-正在进行 3-未开始',
  master_id int(11) NOT NULL COMMENT '会议组织人id',
  start_time datetime NOT NULL COMMENT '会议开始时间',
  end_time datetime NOT NULL COMMENT '会议结束时间',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  KEY user_id_index (user_id) USING BTREE,
  KEY status_index (status) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8;





insert into smart_room values (default,'FZ101','0000000001',1,now(),now());
insert into smart_room values (default,'FZ102','0000000002',1,now(),now());
insert into smart_room values (default,'FZ103','0000000003',1,now(),now());
insert into smart_room values (default,'FZ104','0000000004',1,now(),now());
insert into smart_room values (default,'FZ105','0000000005',1,now(),now());
insert into smart_room values (default,'FZ106','0000000006',1,now(),now());
insert into smart_room values (default,'FZ107','0000000007',1,now(),now());
insert into smart_room values (default,'FZ108','0000000008',1,now(),now());


insert into smart_meeting values (default,'寒假会议','1000',1000,0,now(),now(),now(),now());
insert into smart_meeting values (default,'寒假会议','1002',1000,0,now(),now(),now(),now());
insert into smart_meeting values (default,'纳新会议','1000',1002,1,now(),now(),now(),now());
insert into smart_meeting values (default,'纳新会议','1002',1002,1,now(),now(),now(),now());