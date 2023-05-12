package com.sk.scott.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName("course")
public class Course {

    private Long cid;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }


    @TableField("cname")
    private String cName;

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }


    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @TableField("cstatus")
    private Integer CStatus;

    public Integer getCStatus() {
        return CStatus;
    }

    public void setCStatus(Integer CStatus) {
        this.CStatus = CStatus;
    }
}
