package com.sk.scott.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_address")
public class address {
    @TableId(type = IdType.ID_WORKER)
    @TableField("address_id")
    private Long addressId;
    private String address;
    private String remark;
    @TableField("a_status")
    private int aStatus;
}
