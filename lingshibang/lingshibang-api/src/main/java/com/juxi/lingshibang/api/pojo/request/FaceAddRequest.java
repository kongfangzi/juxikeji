package com.juxi.lingshibang.api.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongqiang.chai
 * 向华为人脸库中添加图片请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceAddRequest {
    private String image_base64;
}
