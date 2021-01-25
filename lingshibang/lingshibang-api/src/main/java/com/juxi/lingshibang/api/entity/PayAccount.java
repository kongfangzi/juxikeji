package com.juxi.lingshibang.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 支付账户表
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@TableName("tb_pay_account")
public class PayAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 支付账号
     */
    private String accountCode;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 创建人
     */
    private String createtor;

    /**
     * 修改时间
     */
    private LocalDateTime updatetime;

    /**
     * 修改人
     */
    private String updatetor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
    public String getCreatetor() {
        return createtor;
    }

    public void setCreatetor(String createtor) {
        this.createtor = createtor;
    }
    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }
    public String getUpdatetor() {
        return updatetor;
    }

    public void setUpdatetor(String updatetor) {
        this.updatetor = updatetor;
    }

    @Override
    public String toString() {
        return "PayAccount{" +
            "id=" + id +
            ", accountCode=" + accountCode +
            ", userCode=" + userCode +
            ", balance=" + balance +
            ", createtime=" + createtime +
            ", createtor=" + createtor +
            ", updatetime=" + updatetime +
            ", updatetor=" + updatetor +
        "}";
    }
}
