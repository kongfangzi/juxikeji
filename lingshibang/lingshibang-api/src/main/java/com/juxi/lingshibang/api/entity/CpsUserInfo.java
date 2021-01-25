package com.juxi.lingshibang.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * cps会员信息后台管理表
 * </p>
 *
 * @author liufeng
 * @since 2021-01-10
 */
@TableName("tb_cps_user_info")
public class CpsUserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userCode;

    /**
     * cps用户角色类型：1公众号运营，2个人分享，3机构推广分成，4第三方平台
     */
    private Integer roleType;

    private Integer cpsSubjectId;

    /**
     * 有效状态：0无效，1有效
     */
    private Integer status;

    /**
     * 最近一次结算收益总和
     */
    private BigDecimal lastSettlementProfit;

    /**
     * 累计结算收益
     */
    private BigDecimal totalSettlementProfit;

    /**
     * 主图地址
     */
    private String mainPic;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 城市
     */
    private String city;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 更新时间
     */
    private LocalDateTime updatetime;

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
    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
    public Integer getCpsSubjectId() {
        return cpsSubjectId;
    }

    public void setCpsSubjectId(Integer cpsSubjectId) {
        this.cpsSubjectId = cpsSubjectId;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public BigDecimal getLastSettlementProfit() {
        return lastSettlementProfit;
    }

    public void setLastSettlementProfit(BigDecimal lastSettlementProfit) {
        this.lastSettlementProfit = lastSettlementProfit;
    }
    public BigDecimal getTotalSettlementProfit() {
        return totalSettlementProfit;
    }

    public void setTotalSettlementProfit(BigDecimal totalSettlementProfit) {
        this.totalSettlementProfit = totalSettlementProfit;
    }
    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "CpsUserInfo{" +
            "id=" + id +
            ", userCode=" + userCode +
            ", roleType=" + roleType +
            ", cpsSubjectId=" + cpsSubjectId +
            ", status=" + status +
            ", lastSettlementProfit=" + lastSettlementProfit +
            ", totalSettlementProfit=" + totalSettlementProfit +
            ", mainPic=" + mainPic +
            ", longitude=" + longitude +
            ", latitude=" + latitude +
            ", city=" + city +
            ", address=" + address +
            ", createtime=" + createtime +
            ", updatetime=" + updatetime +
        "}";
    }
}
