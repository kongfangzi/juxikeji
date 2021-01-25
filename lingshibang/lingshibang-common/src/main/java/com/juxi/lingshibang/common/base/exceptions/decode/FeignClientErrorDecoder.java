package com.juxi.lingshibang.common.base.exceptions.decode;//package com.juxi.lingshibang.common.base.exceptions.decode;
//
//import com.alibaba.fastjson.JSONObject;
//import com.juxi.lingshibang.common.base.Result;
//import com.juxi.lingshibang.common.base.exceptions.BaseException;
//import com.juxi.lingshibang.common.base.exceptions.BaseException;
//import feign.Response;
//import feign.Util;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//
//import java.io.IOException;
//
///**
// * @Author: DaleShay
// * @Date: 2020/3/12 17:22
// * @Description: fegin调用 httpStatus!=200 异常时的拦截
// * 将 httpStatus == 400 业务异常的Result（底层已经由GlobalExceptionHandler拦截 BaseException处理）重新转换成 BaseException抛出
// * <p>
// * BaseException 继承自 HystrixBadRequestException 所以不会再走熔断
// */
//@Slf4j
//public class FeignClientErrorDecoder implements feign.codec.ErrorDecoder {
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        //http码
//        int httpStatus = response.status();
//        String errorContent;
//        try {
//            errorContent = Util.toString(response.body().asReader());
//        } catch (IOException e) {
//            log.error("handle error exception");
//            return e;
//        }
//        if (httpStatus == HttpStatus.BAD_REQUEST.value()) {
//            return contentToBaseException(errorContent);
//        } else {
//            //一般来说只会有500了
//            return contentToException(errorContent);
//        }
//    }
//
//    /**
//     *
//     * @param errorContent 底层已统一拦截封装成 Result 类格式
//     * @return
//     */
//    private BaseException contentToBaseException(String errorContent) {
//        Result result = JSONObject.toJavaObject(JSONObject.parseObject(errorContent), Result.class);
//
//        log.error("Feign调用错误：{}",errorContent);
//
//        return new BaseException(result);
//    }
//
//    /**
//     *
//     * @param errorContent 底层已统一拦截封装成 Result 类格式
//     * @return
//     */
//    private Exception contentToException(String errorContent) {
//        Result result = JSONObject.toJavaObject(JSONObject.parseObject(errorContent), Result.class);
//        log.error("Feign调用错误：{}",errorContent);
//        return new RuntimeException(result.getMsg());
//    }
//
//}