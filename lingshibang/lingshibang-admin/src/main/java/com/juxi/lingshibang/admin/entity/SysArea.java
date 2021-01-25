package com.juxi.lingshibang.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.juxi.lingshibang.common.model.BaseEntity;

/**
 * <p>
 * 地区码表
 * </p>
 *
 * @author liufeng
 * @since 2020-11-24
 */
public class SysArea extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 地区Id
     */
    @TableId(value = "area_id", type = IdType.AUTO)
    private Integer areaId;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 地区名
     */
    private String areaName;

    /**
     * 地区级别（1:省份province,2:市city,3:区县district,4:街道street）
     */
    private Integer level;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市中心点（即：经纬度坐标）
     */
    private String center;

    /**
     * 地区父节点
     */
    private Integer parentId;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SysArea{" +
            "areaId=" + areaId +
            ", areaCode=" + areaCode +
            ", areaName=" + areaName +
            ", level=" + level +
            ", cityCode=" + cityCode +
            ", center=" + center +
            ", parentId=" + parentId +
        "}";
    }
}
