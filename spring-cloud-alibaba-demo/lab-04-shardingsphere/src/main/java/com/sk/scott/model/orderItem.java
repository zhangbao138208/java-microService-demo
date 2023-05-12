package com.sk.scott.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_order_item")
public class orderItem {
    @TableField("order_item_id")
    private Long orderItemId;
    private Long userId;
    private String remark;
    @TableField("o_status")
    private int oStatus;

    private Number amount;
}
