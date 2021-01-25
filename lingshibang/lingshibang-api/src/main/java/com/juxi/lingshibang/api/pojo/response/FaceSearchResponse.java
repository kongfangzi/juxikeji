package com.juxi.lingshibang.api.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * @author hongqiang.chai
 * 人脸搜索响应体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceSearchResponse {
//    private List<SearchFace> faces;
    private String error_code;
    private String error_msg;
}
