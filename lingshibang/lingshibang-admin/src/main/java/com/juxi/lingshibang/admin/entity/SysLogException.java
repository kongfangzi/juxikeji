package com.juxi.lingshibang.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 系统_异常_日志表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public class SysLogException extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "except_id", type = IdType.AUTO)
    private Integer exceptId;

    /**
     * 会话ID
     */
    private String sessionid;

    /**
     * 操作名称
     */
    private String operationname;

    /**
     * 异常消息
     */
    private String errmsg;

    /**
     * 异常堆栈
     */
    private String errstacktrace;

    /**
     * 创建时间
     */
    private Long createtime;

    /**
     * 预留字符串字段
     */
    private String strtag;

    /**
     * 预留整型字段
     */
    private Integer inttag;

    public Integer getExceptId() {
        return exceptId;
    }

    public void setExceptId(Integer exceptId) {
        this.exceptId = exceptId;
    }
    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname;
    }
    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public String getErrstacktrace() {
        return errstacktrace;
    }

    public void setErrstacktrace(String errstacktrace) {
        this.errstacktrace = errstacktrace;
    }
    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
    public String getStrtag() {
        return strtag;
    }

    public void setStrtag(String strtag) {
        this.strtag = strtag;
    }
    public Integer getInttag() {
        return inttag;
    }

    public void setInttag(Integer inttag) {
        this.inttag = inttag;
    }

    @Override
    public String toString() {
        return "SysLogException{" +
            "exceptId=" + exceptId +
            ", sessionid=" + sessionid +
            ", operationname=" + operationname +
            ", errmsg=" + errmsg +
            ", errstacktrace=" + errstacktrace +
            ", createtime=" + createtime +
            ", strtag=" + strtag +
            ", inttag=" + inttag +
        "}";
    }
}
