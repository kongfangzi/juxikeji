package com.juxi.lingshibang.common.xss;//
//
//package com.juxi.lingshibang.common.xss;
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class CSRFFilter implements Filter {
//    private FilterConfig filterConfig = null;
//    @Override
//    public void destroy() {
//        this.filterConfig = null;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        System.out.println("==进入CSRF过滤器===");
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        // 从http头中获取Referer
//        String referer = req.getHeader("Referer");
//        // 系统配置的referer头信息
//        String myReferer = filterConfig.getInitParameter("referer");
//        int count = 0;
//        if (myReferer !=null){
//            if (myReferer.trim().length() > 0) {
//                String[] myReferers = myReferer.split(",");
//                for (int i = 0; i < myReferers.length; i++) {
//                    if (referer != null && !referer.trim().startsWith(myReferers[i])) {
//                        count++;
//                    } else {
//                        chain.doFilter(request, response);
//                        break;
//                    }
//                }
//                if (count == myReferers.length) {
//                    System.out.println("检测到您发送的请求可能为跨站伪造请求1:" + HttpServletResponse.SC_BAD_REQUEST);
//                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//                    return;
//                }
//            }
//        }
//
//        System.out.println("==结束CSRF过滤器===");
//    }
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        this.filterConfig = filterConfig;
//    }
//}
