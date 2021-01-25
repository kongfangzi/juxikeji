package com.juxi.lingshibang.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 用户账号表
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@TableName("tb_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
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
    private Boolean status;

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
     * 类型 1：企业管理员  2：操作员
     */
    private Boolean type;

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
    private Boolean isDelete;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }
    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }
    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
    public String getInviterCode() {
        return inviterCode;
    }

    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }
    public LocalDateTime getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(LocalDateTime inviteTime) {
        this.inviteTime = inviteTime;
    }
    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
    public Integer getPoundageType() {
        return poundageType;
    }

    public void setPoundageType(Integer poundageType) {
        this.poundageType = poundageType;
    }
    public LocalDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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
    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

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
