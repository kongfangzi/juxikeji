package com.juxi.lingshibang.admin.common.config;//
//package com.juxi.lingshibang.admin.common.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.DelegatingFilterProxy;
//import com.google.common.collect.Maps;
//import com.juxi.lingshibang.common.xss.CSRFFilter;
//import com.juxi.lingshibang.common.xss.XssFilter;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.DispatcherType;
//
///**
// * Filter配置
// *
// * @author Mark sunlightcs@gmail.com
// */
//@Configuration
//public class FilterConfig {
//    @Value("#{'${referer-domains}'}")
//    private String domains;
//    @Bean
//    public FilterRegistrationBean shiroFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
//        registration.addInitParameter("targetFilterLifecycle", "true");
//        registration.setEnabled(true);
//        registration.setOrder(Integer.MAX_VALUE - 1);
//        registration.addUrlPatterns("/*");
//        return registration;
//    }
//
//    @Bean
//    public FilterRegistrationBean xssFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        registration.setFilter(new XssFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("xssFilter");
//        registration.setOrder(Integer.MAX_VALUE);
//        return registration;
//    }
//    @Bean
//    public FilterRegistrationBean csrfFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        registration.setFilter(new CSRFFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("csrfFilter");
//        registration.setEnabled(true);
//        Map<String, String> initParameters = Maps.newHashMap();
//        initParameters.put("referer", domains);
//        registration.setInitParameters(initParameters);
//        registration.setOrder(Integer.MAX_VALUE-2);
//        return registration;
//    }
//}
