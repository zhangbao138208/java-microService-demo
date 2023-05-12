
CREATE TABLE `t_order_0` (
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT '0',
  `remark` varchar(50),
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `t_order_1` (
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT '0',
  `remark` varchar(50),
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_item_0` (
  `order_item_id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT '0',
  `remark` varchar(50),
  `o_status`TINYINT DEFAULT '0',
  `amount`  DECIMAL(19 , 2) not null,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `t_order_item_1` (
  `order_item_id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT '0',
  `remark` varchar(50),
  `o_status`TINYINT DEFAULT '0',
   `amount`  DECIMAL(19 , 2) not null,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_address` (
  `address_id` bigint(20) NOT NULL,
  `address`varchar(50),
  `remark` varchar(50),
  `a_status`TINYINT DEFAULT '0',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
