package com.juxi.lingshibang.admin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 用户提现
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@TableName("tb_withdraw")
public class Withdraw extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 提现帐号
     */
    private String account;

    /**
     * 账号名称
     */
    private String accountName;

    /**
     * 提现类型：0微信提现，1支付宝提现，2银联提现
     */
    private Integer withdrawType;

    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 是否已支付：0否，1是
     */
    private Integer isPaid;

    /**
     * 平台付款时间
     */
    private LocalDateTime paytime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 当前个人账户余额
     */
    private BigDecimal balance;

    /**
     * 当前个人账户可用余额
     */
    private BigDecimal avaliableBalance;

    /**
     * 0 待处理 1 处理成功 2 拒绝
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public Integer getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(Integer withdrawType) {
        this.withdrawType = withdrawType;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }
    public LocalDateTime getPaytime() {
        return paytime;
    }

    public void setPaytime(LocalDateTime paytime) {
        this.paytime = paytime;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public BigDecimal getAvaliableBalance() {
        return avaliableBalance;
    }

    public void setAvaliableBalance(BigDecimal avaliableBalance) {
        this.avaliableBalance = avaliableBalance;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Withdraw{" +
            "id=" + id +
            ", userCode=" + userCode +
            ", account=" + account +
            ", accountName=" + accountName +
            ", withdrawType=" + withdrawType +
            ", amount=" + amount +
            ", isPaid=" + isPaid +
            ", paytime=" + paytime +
            ", remark=" + remark +
            ", balance=" + balance +
            ", avaliableBalance=" + avaliableBalance +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
