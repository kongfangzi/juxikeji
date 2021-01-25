package com.juxi.lingshibang.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.juxi.lingshibang.common.enums.SmsSignNameEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 短信发送基础参数
 * @author xiaoxiong
 */
@Data
@Accessors(chain = true)
public class BaseSmsRequest extends BaseEntity {

    @JSONField(serialize = false)
    private String phone;

    @JSONField(serialize = false)
    private SmsSignNameEnum signNameEnum;
}
