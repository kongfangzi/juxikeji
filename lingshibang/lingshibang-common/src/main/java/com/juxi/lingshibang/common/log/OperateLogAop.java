package com.juxi.lingshibang.common.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juxi.lingshibang.common.base.Constants;
import com.juxi.lingshibang.common.base.dto.OperateLogDTO;
import com.juxi.lingshibang.common.base.enums.CommonCodeEnum;
import com.juxi.lingshibang.common.enums.JwtEnum;
import com.juxi.lingshibang.common.enums.ModuleEnum;
import com.juxi.lingshibang.common.util.IPv4Util;
import com.juxi.lingshibang.common.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class OperateLogAop {

    /**
     * 事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 操作日志对象
     */
    private OperateLogDTO operateLog = new OperateLogDTO();


    @Pointcut("@annotation(com.juxi.lingshibang.common.log.OpLogAnnotation)")
    public void pointCut() {
    }

    @AfterReturning(pointcut = "pointCut()", returning = "ret")
    public void afterReturning(JoinPoint joinPoint, Object ret) {
        //得到request对象
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //处理完请求，返回内容
        String jsonStr= JSONObject.toJSONString(ret);
        JSONObject result= JSONObject.parseObject(jsonStr);
        //log.info("RESPONSE : " +jsonStr);
        try {
            if(CommonCodeEnum.SUCCESS.getValue().equals(result.getInteger("code"))) {
                OpLogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(OpLogAnnotation.class);
                ModuleEnum module = logAnnotation.module();
                if (StringUtils.isEmpty(module)) {
                    throw new RuntimeException("没有指定日志module");
                }
                String loginToken=request.getHeader(JwtEnum.WEB_FRONT.getTokenName());
                if(StringUtils.isEmpty(loginToken)){
                    loginToken=getLoginToken(result);
                }

                operateLog.setOperateSource(logAnnotation.source().getCode());
                operateLog.setOperationId(module.getOperationId());
                operateLog.setOperationName(module.getOperationName());
                operateLog.setModuleName(module.getModuleName());
                operateLog.setContent(parseOperateContent(joinPoint, logAnnotation.desc()));
                operateLog.setIp(IPv4Util.getRealIPAddress(request));
                operateLog.setLoginToken(loginToken);

                // 发布事件
                applicationContext.publishEvent(new OperateLogEvent(operateLog));

            }
        } catch (Exception e) {
            log.error("操作日志保存:{}",e.getMessage());
        }
    }

    /**
     * 操作内容解析
     * @param joinPoint
     * @param desc
     * @return
     */
    private String parseOperateContent(JoinPoint joinPoint, String desc) {
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Class[] parameterTypes = methodSignature.getParameterTypes();
        try {
            String paramTemplate, paramTarget = "-";
            for (int i = 0; i < parameterNames.length; i++) {
                if (!desc.contains("{") || !desc.contains("}")) {
                    return desc;
                }
                paramTemplate = "{".concat(parameterNames[i]).concat("}");
                if (desc.contains(paramTemplate)) {
                    paramTarget = JSON.toJSONString(args[i]);
                } else if (desc.contains("{".concat(parameterNames[i]))) {
                    String str = desc.substring(desc.indexOf("{".concat(parameterNames[i])));
                    paramTemplate = str.substring(0, str.indexOf("}") + 1);
                    String[] arr = paramTemplate.substring(1, paramTemplate.length() - 1).split("\\.");
                    if (arr.length == 2) {
                        Field field = parameterTypes[i].getDeclaredField(arr[1]);
                        field.setAccessible(true);
                        paramTarget = String.valueOf(field.get(args[i]));
                    }
                    --i;
                } else {
                    continue;
                }
                desc = desc.replace(paramTemplate, paramTarget);
            }
            return desc;
        } catch (Exception e) {
            log.error("日志操作内容解析错误：", e);
            return "";
        }
    }

    /**
     * 获取登录token
     * @param result
     * @return
     */
    private String  getLoginToken(JSONObject result){
            if(JSONUtil.isNotBlank(result)){
                JSONArray jsonArray=result.getJSONArray("data");
                if(JSONUtil.isNotBlank(jsonArray)){
                    return  jsonArray.getJSONObject(0).getString(Constants.LOGIN_TOKEN);
                }
            }
            return  null;
    }

}
