-- 用户表
CREATE TABLE smart_user (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  username varchar(50) NOT NULL COMMENT '用户名',
  password    varchar(50) NOT NULL COMMENT '用户密码,MD5加密 ',
  sex varchar(5) NOT NULL ,
  phone  varchar(20)  NOT NULL,
  email  varchar(50)  NOT NULL,
  face_data text NOT NULL COMMENT '人脸数据',
  avatar_url varchar(500) NOT NULL COMMENT '头像数据',
  face_url varchar(500) NOT NULL COMMENT '人脸数据',
  create_time datetime    NOT NULL COMMENT '创建时间',
  update_time datetime    NOT NULL COMMENT '最后一次更新时间',
  UNIQUE KEY user_name_unique (username) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8;

-- 会议室表
CREATE TABLE smart_room (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  roomNumber varchar(20) NOT NULL COMMENT '门牌号',
  machineNumber varchar(20) NOT NULL COMMENT '设备号',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8;

-- 会议表
CREATE TABLE smart_meeting (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '会议id',
  meetingName varchar(200) NOT NULL COMMENT '会议名称',
  user_id int(11) NOT NULL COMMENT '与会人员id',
  room_id int(11) NOT NULL COMMENT '会议室id',
  start_time datetime NOT NULL COMMENT '会议开始时间',
  end_time datetime NOT NULL COMMENT '会议结束时间',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY user_id_unique (user_id) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8;
