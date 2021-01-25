package com.juxi.lingshibang.admin.common.aspect;
//
//package com.juxi.common.aspect;
//
//import com.faw.redflag.common.annotation.SysLog;
//import com.google.gson.Gson;
//
//
//import com.faw.redflag.modules.sys.entity.SysLogEntity;
//import com.faw.redflag.modules.sys.entity.SysUserEntity;
//import com.faw.redflag.modules.sys.service.SysLogService;
//import com.faw.redflag.common.utils.HttpContextUtils;
//import com.faw.redflag.common.utils.IPUtils;
//
//import org.apache.shiro.SecurityUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.util.Date;
//
///**
// * 系统日志，切面处理类
// *
// * @author Mark sunlightcs@gmail.com
// */
//@Aspect
//@Component
//public class SysLogAspect {
//	private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);
//	@Autowired
//	private SysLogService sysLogService;
//
//	@Pointcut("@annotation(com.faw.redflag.common.annotation.SysLog)")
//	public void logPointCut() {
//		logger.info("暂时方法为空");
//
//	}
//
//	@Around("logPointCut()")
//	public Object around(ProceedingJoinPoint point) throws Throwable {
//		long beginTime = System.currentTimeMillis();
//		//执行方法
//		Object result = point.proceed();
//		//执行时长(毫秒)
//		long time = System.currentTimeMillis() - beginTime;
//
//		//保存日志
//		saveSysLog(point, time);
//
//		return result;
//	}
//
//	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//
//		SysLogEntity sysLog = new SysLogEntity();
//		SysLog syslog = method.getAnnotation(SysLog.class);
//		if(syslog != null){
//			//注解上的描述
//			sysLog.setOperation(syslog.value());
//		}
//
//		//请求的方法名
//		String className = joinPoint.getTarget().getClass().getName();
//		String methodName = signature.getName();
//		sysLog.setMethod(className + "." + methodName + "()");
//
//		//请求的参数
//		Object[] args = joinPoint.getArgs();
//		try{
//			String params = new Gson().toJson(args[0]);
//			sysLog.setParams(params);
//		}catch (Exception e){
//		logger.info("错误代码为"+e.getMessage());
//		}
//
//		//获取request
//		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//		//设置IP地址
//		sysLog.setIp(IPUtils.getIpAddr(request));
//
//		//用户名
//		String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
//		sysLog.setUsername(username);
//
//		sysLog.setTime(time);
//		sysLog.setCreateDate(new Date());
//		//保存系统日志
//		sysLogService.save(sysLog);
//	}
//}
