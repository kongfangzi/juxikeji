package com.juxi.lingshibang.api.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 报表通用请求实体
 * @author hongqiang.chai
 * @date 2019/11/8 22:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiRequest {
    private Date start;//起始时间
    private Date end;//截止时间
    private String dealerCode;//经销商编码
}
