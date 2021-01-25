package com.juxi.lingshibang.api.pojo.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorLogRequest {

	@NotBlank(message="摄像头编号不能为空")
	private String cameraCloudName;
	@NotBlank(message="销售顾问编号不能为空")
	private String staffId;
	/**
     * 发送状态0发送失败1发送成功
     */
	@NotBlank(message="发送状态不能为空")
	private  Byte sentStatus;
}
