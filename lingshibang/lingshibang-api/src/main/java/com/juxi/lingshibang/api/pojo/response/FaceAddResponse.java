package com.juxi.lingshibang.api.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * @author hongqiang.chai
 * 人脸添加响应体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaceAddResponse {
    private String face_set_name;
    private String face_set_id;
//    private List<FaceSetFace> faces;
    private String error_code;
    private String error_msg;
}
