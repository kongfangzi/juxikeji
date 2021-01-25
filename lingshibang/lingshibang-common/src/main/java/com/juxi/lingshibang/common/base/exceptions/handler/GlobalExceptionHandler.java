package com.juxi.lingshibang.common.base.exceptions.handler;



import com.juxi.lingshibang.common.base.Result;
import com.juxi.lingshibang.common.base.enums.CommonCodeEnum;
import com.juxi.lingshibang.common.base.exceptions.BaseException;
import com.juxi.lingshibang.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局的的异常拦截器
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleError(MethodArgumentNotValidException e) {
        log.warn("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error != null ? error.getField() : null, error != null ? error.getDefaultMessage() : null);
        return ResponseUtil.Failure(CommonCodeEnum.ILLEGAL_ARGUMENT.getValue(), message);
    }


    /**
     * 业务异常.
     *
     * 统一拦截业务异常  将 BaseException 信息 转化为 Result
     * 并将 http code 转化为 400  上层 feign.codec.ErrorDecoder 根据 http code 为 400 转化为 BaseException 抛出
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result businessException(BaseException e) {
        log.error("业务异常 code={} message={}",e.getCode(),e.getMessage());
        e.printStackTrace();
        return ResponseUtil.Failure(e.getMessage());
    }


    /**
     * 全局异常.
     * 系统异常 错误信息目前不向上抛出
     * @param e the e
     *
     * @return the wrapper
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result exception(Exception e) {
        log.error("服务内部异常 message={}",e.getMessage());
        e.printStackTrace();
        return ResponseUtil.Failure("服务器内部异常！");
    }
}
