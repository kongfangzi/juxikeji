package com.juxi.lingshibang.api.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author hongqiang.chai
 * 华为人脸搜索请求体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaceSearchRequest {
    private String image_base64;
    private Double threshold=0.93;
    private Integer top_n=1;

}
