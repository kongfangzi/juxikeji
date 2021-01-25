package com.juxi.lingshibang.common.base;

import com.alibaba.fastjson.JSONObject;
import com.juxi.lingshibang.common.des.EncryptDESCBC;
import com.juxi.lingshibang.common.enums.ErrorCodeEnum;
import com.juxi.lingshibang.common.enums.OpCodeEnum;
import com.juxi.lingshibang.common.exception.BizException;
import com.juxi.lingshibang.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@Component
@RestControllerAdvice
@Order(-1)
public class BaseController {

//    protected EncryptDESCBC encrypt = new EncryptDESCBC();
//
//    @Value("${rest.encrypt-key}")
//    private String encryptKey;
//
//    @Value("${rest.report-encrypt-key}")
//    private String reportEncryptKey;


    @ExceptionHandler(value = {BizException.class, BindException.class, MissingServletRequestParameterException.class})
    public Result paramExceptionHandler(Exception e) {
        log.info("请求参数错误：", e);
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            log.error("执行业务逻辑，发生异常 errorCode={},errorMsg={}", bizException.getErrorCode(),
                    bizException.getMessage());
            return ResponseUtil.Failure(bizException);
        }
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ResponseUtil.Failure(ErrorCodeEnum.INNER_ERROE);
    }

    protected void putResultOpCode(Map<String, Object> result, OpCodeEnum code) {
        if (result != null) {
            if (code != null) {
                putResultOpCode(result, code.getCode(), code.getMsg());
            } else {
                putResultOpCode(result, OpCodeEnum.OP_FAILED.getCode(), OpCodeEnum.OP_FAILED.getMsg());
            }
        }
    }

    protected void putResultOpCode(Map<String, Object> result, int code, String msg) {
        result.put("code", code);
        result.put("msg", msg);
    }

//    protected String formatResultEasy(JSONObject json, String key) {
//        if (json != null) {
//            //return EncryptEasy.encode(json.toJSONString(), key,);
//        }
//        return null;
//    }

//    protected String formatResult(JSONObject json) {
//        if (json != null) {
//            try {
//                byte[] bytes = json.toJSONString().getBytes("UTF-8");
//                return encrypt.encryptToBase64(bytes,encryptKey,reportEncryptKey);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//    protected String formatResult(JSONObject json,String key) {
//        if (json != null) {
//            try {
//                byte[] bytes = json.toJSONString().getBytes("UTF-8");
//                return encrypt.encryptToBase64(bytes, key,reportEncryptKey);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//    protected <T> String formatResult(T t) {
//        if (t != null) {
//            try {
//                JSONObject json = (JSONObject) JSONObject.toJSON(t);
//                if (json != null) {
//                    byte[] bytes = json.toJSONString().getBytes("UTF-8");
//                    return encrypt.encryptToBase64(bytes, encryptKey,reportEncryptKey);
//                }
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

}
