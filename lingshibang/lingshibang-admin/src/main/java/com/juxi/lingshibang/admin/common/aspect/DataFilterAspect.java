package com.juxi.lingshibang.admin.common.aspect;
//
//package com.juxi.common.aspect;
//
//
//import com.faw.redflag.common.annotation.DataFilter;
//import com.faw.redflag.common.exception.RRException;
//import com.faw.redflag.common.utils.Constant;
//import com.faw.redflag.modules.sys.entity.SysUserEntity;
//import com.faw.redflag.modules.sys.service.SysDeptService;
//import com.faw.redflag.modules.sys.service.SysRoleDeptService;
//import com.faw.redflag.modules.sys.service.SysUserRoleService;
//import com.faw.redflag.modules.sys.shiro.ShiroUtils;
//import org.apache.commons.lang.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
///**
// * 数据过滤，切面处理类
// *
// * @author Mark sunlightcs@gmail.com
// */
//@Aspect
//@Component
//public class DataFilterAspect {
//    private static final Logger logger = LoggerFactory.getLogger(DataFilterAspect.class);
//    @Autowired
//    private SysDeptService sysDeptService;
//    @Autowired
//    private SysUserRoleService sysUserRoleService;
//    @Autowired
//    private SysRoleDeptService sysRoleDeptService;
//
//    @Pointcut("@annotation(com.faw.redflag.common.annotation.DataFilter)")
//    public void dataFilterCut() {
//    logger.info("暂时为空");
//    }
//
//    @Before("dataFilterCut()")
//    public void dataFilter(JoinPoint point) throws Throwable {
//        Object params = point.getArgs()[0];
//        if(params != null && params instanceof Map){
//            SysUserEntity user = ShiroUtils.getUserEntity();
//
//            //如果不是超级管理员，则进行数据过滤
//            if(user.getUserId() != Constant.SUPER_ADMIN){
//                Map map = (Map)params;
//                map.put(Constant.SQL_FILTER, getSQLFilter(user, point));
//            }
//
//            return ;
//        }
//
//        throw new RRException("数据权限接口，只能是Map类型参数，且不能为NULL");
//    }
//
//    /**
//     * 获取数据过滤的SQL
//     */
//    private String getSQLFilter(SysUserEntity user, JoinPoint point){
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);
//        //获取表的别名
//        String tableAlias = dataFilter.tableAlias();
//        if(StringUtils.isNotBlank(tableAlias)){
//            tableAlias +=  ".";
//        }
//
//        //部门ID列表
//        Set<Long> deptIdList = new HashSet<>();
//
//        //用户角色对应的部门ID列表
//        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
//        if(!roleIdList.isEmpty()){
//            List<Long> userDeptIdList = sysRoleDeptService.queryDeptIdList(roleIdList.toArray(new Long[roleIdList.size()]));
//            deptIdList.addAll(userDeptIdList);
//        }
//
//        //用户子部门ID列表
//        if(dataFilter.subDept()){
//            List<Long> subDeptIdList = sysDeptService.getSubDeptIdList(user.getDeptId());
//            deptIdList.addAll(subDeptIdList);
//        }
//
//        StringBuilder sqlFilter = new StringBuilder();
//        sqlFilter.append(" (");
//
//        if(!deptIdList.isEmpty()){
//            sqlFilter.append(tableAlias).append(dataFilter.deptId()).append(" in(").append(StringUtils.join(deptIdList, ",")).append(")");
//        }
//
//        //没有本部门数据权限，也能查询本人数据
////        if(!dataFilter.user()){
////            if(deptIdList.isEmpty()){
////                sqlFilter.append(" or ");
////            }
////            sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(user.getUserId());
////        }
//
//        sqlFilter.append(")");
//
//        if("()".equals(sqlFilter.toString().trim())){
//            return null;
//        }
//
//        return sqlFilter.toString();
//    }
//}
