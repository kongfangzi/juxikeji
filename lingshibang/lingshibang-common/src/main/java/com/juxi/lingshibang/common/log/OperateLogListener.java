package com.juxi.lingshibang.common.log;


import com.alibaba.fastjson.JSON;
import com.juxi.lingshibang.common.base.Constants;
import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.base.dto.OperateLogDTO;
import com.juxi.lingshibang.common.enums.JwtEnum;
import com.juxi.lingshibang.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 注解形式的监听 异步监听日志事件
 */

@Slf4j
@Component
public class OperateLogListener {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    @Order
    @EventListener(OperateLogEvent.class)
    public void saveSysLog(OperateLogEvent event) {
        OperateLogDTO operateLog = (OperateLogDTO) event.getSource();
        //保存日志
        HttpHeaders httpHeaders = RequestUtil.buildHearder();
        httpHeaders.add(JwtEnum.WEB_FRONT.getTokenName(),operateLog.getLoginToken());
        HttpEntity requestEntity = new HttpEntity(JSON.toJSONString(operateLog),
                httpHeaders);
        restTemplate.exchange(Constants.RpcRequestAddress.operateLogSave,
                HttpMethod.POST, requestEntity, Result.class).getBody();
    }
}
