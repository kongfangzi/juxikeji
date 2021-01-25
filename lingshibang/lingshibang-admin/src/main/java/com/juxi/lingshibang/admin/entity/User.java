package com.juxi.lingshibang.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;
import lombok.Data;

/**
 * <p>
 * 用户账号表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@TableName("tb_user")
@Data
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 企业
     */
    private String company;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 角色
     */
    @TableField(exist = false)
    private String roles;

    /**
     * 密码
     */
    private String password;

    /**
     * 加盐值
     */
    private String salt;

    /**
     * 性别：0女，1男，2未知
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String telPhone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 微信号
     */
    private String weixinId;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 身份:0 访客，1
     */
    private Integer identity;

    /**
     * 化身
     */
    private String avatar;

    /**
     * 状态：1.生效 2. 修改 3.待验证 0 逻辑删除
     */
    private int status;

    /**
     * 用户积分
     */
    private Integer score;

    /**
     * 头衔
     */
    private String rank;

    /**
     * 用户简介
     */
    private String summary;

    /**
     * 注册来源
     */
    private String source;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 邀请人userCode
     */
    private String inviterCode;

    /**
     * 邀请时间
     */
    private LocalDateTime inviteTime;

    /**
     * 类型 0：平台管理员  1：普通用户
     */
    private int type;

    /**
     * 费率类型：
     */
    private Integer poundageType;

    /**
     * 到期时间
     */
    private LocalDateTime dueTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否删除 0-否 1-是
     */
    private Integer isDelete;

    /**
     * 预留字符串字段
     */
    private String strtag;

    /**
     * 预留整型字段
     */
    private Integer inttag;

    /**
     * 修改人
     */
    private String updator;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", userCode=" + userCode +
            ", company=" + company +
            ", userName=" + userName +
            ", nickName=" + nickName +
            ", password=" + password +
            ", salt=" + salt +
            ", gender=" + gender +
            ", telPhone=" + telPhone +
            ", idCard=" + idCard +
            ", birthday=" + birthday +
            ", weixinId=" + weixinId +
            ", constellation=" + constellation +
            ", identity=" + identity +
            ", avatar=" + avatar +
            ", status=" + status +
            ", score=" + score +
            ", rank=" + rank +
            ", summary=" + summary +
            ", source=" + source +
            ", invitationCode=" + invitationCode +
            ", inviterCode=" + inviterCode +
            ", inviteTime=" + inviteTime +
            ", type=" + type +
            ", poundageType=" + poundageType +
            ", dueTime=" + dueTime +
            ", creator=" + creator +
            ", createTime=" + createTime +
            ", isDelete=" + isDelete +
            ", strtag=" + strtag +
            ", inttag=" + inttag +
            ", updator=" + updator +
            ", updateTime=" + updateTime +
        "}";
    }
}
