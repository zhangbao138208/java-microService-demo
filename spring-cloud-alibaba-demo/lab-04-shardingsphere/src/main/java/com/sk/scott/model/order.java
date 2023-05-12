package com.sk.scott.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;

@TableName("t_order")
@Getter
@Setter
public class order {
    @TableField("order_id")
    private Long orderId;
    private Long userId;
    private String remark;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
