package com.juxi.lingshibang.api.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 报表通用响应实体
 * @author hongqiang.chai
 * @date 2019/11/8 22:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiResponse {
    private String gender;
    private int sum;
    private String age;
    private String dealer_code;
    private String dealer_name;
}
