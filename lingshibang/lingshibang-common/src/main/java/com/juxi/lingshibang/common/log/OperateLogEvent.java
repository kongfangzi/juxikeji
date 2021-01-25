package com.juxi.lingshibang.common.log;

import com.juxi.lingshibang.common.base.dto.OperateLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 */
public class OperateLogEvent extends ApplicationEvent {

    public OperateLogEvent(OperateLogDTO operateLog) {
        super(operateLog);
    }

}
