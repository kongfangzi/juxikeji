package com.juxi.lingshibang.common.base.exceptions.handler;//package io.highstore.component.common.exceptions.handler;
//
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//
///**
// * @Author: DaleShay
// * @Date: 2019/11/5 17:48
// * @Description: 记录 当前线程的方法和参数
// */
//@Slf4j
//@RestControllerAdvice
//public class RequestHandler implements RequestBodyAdvice {
//
//    public static final ThreadLocal<MethodEntry> MODEL_HOLDER = new ThreadLocal<>();
//
//
//    @Getter
//    class MethodEntry{
//        //当前线程方法
//        private Method method;
//
//        //当前线程参数
//        private Object param;
//
//        public MethodEntry(Method method,Object param){
//            this.method = method;
//            this.param = param;
//        }
//    }
//
//    @Override
//    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//        return true;
//    }
//
//    @Override
//    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
//        return httpInputMessage;
//    }
//
//    @Override
//    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//        Method method=methodParameter.getMethod();
//        log.debug("{}.{}:{}",method.getDeclaringClass().getSimpleName(),method.getName(),o);
//
//        MODEL_HOLDER.set(new MethodEntry(method,o));
//        return o;
//    }
//
//    @Override
//    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//        return o;
//    }
//}
