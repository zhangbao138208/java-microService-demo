package com.sk.scott.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang.builder.ToStringBuilder;

@TableName("course_1")
public class course {
    @TableId(type = IdType.ID_WORKER)
    @TableField("cid")
    private Long cid;

    @TableField("cname")
    private String cname;



    private Long userId;

    @TableField("cstatus")
    private int cStatus;

    public course() {
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCStatus() {
        return cStatus;
    }

    public void setCStatus(int cStatus) {
        this.cStatus = cStatus;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
