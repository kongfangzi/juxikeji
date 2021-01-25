package com.juxi.lingshibang.admin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 支付账户日志表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
@TableName("tb_pay_account_log")
public class PayAccountLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 支付账号编码
     */
    private String accountCode;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 收支类型：0支出，1收入
     */
    private Integer type;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 修改时间
     */
    private LocalDateTime updatetime;

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
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "PayAccountLog{" +
            "id=" + id +
            ", accountCode=" + accountCode +
            ", amount=" + amount +
            ", type=" + type +
            ", createtime=" + createtime +
            ", updatetime=" + updatetime +
        "}";
    }
}
