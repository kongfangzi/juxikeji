package com.juxi.lingshibang.api.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author hongqiang.chai
 * 华为人脸检测请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceDetectionRequest {
    private String image_base64;
    private String attributes;
}
