package com.juxi.lingshibang.api.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * @author hongqiang.chai
 * 人脸检测响应体
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FaceDetectionResponse {
//    private List<DetectFace> faces;
    private String error_code;
    private String error_msg;
}
