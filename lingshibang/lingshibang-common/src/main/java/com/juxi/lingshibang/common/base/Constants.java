package com.juxi.lingshibang.common.base;

/**
 * 常量类
 * @author yks
 * @date 2020-02-24
 */
public interface Constants {

    String SUCCESS = "success";

    String LOGIN_TOKEN="loginToken";
    /**
     * 默认当前页
     */
    int DEFAULT_CURRENT = 1;

    /**
     * 默认每页记录数
     */
    int DEFAULT_SIZE = 10;

    int TRUE_VALUE = 1;

    int FALSE_VALUE = 0;

    String HTTP_POST_METHOD = "POST";

    String HTTP_GET_METHOD = "GET";

    String pic_type_jpg = "jpg";

    String pic_type_png = "png";

    /**
     * redis 键
     */
    interface redisKey {
        /**
         * redis 分隔符
         */
        String REDIS_KEY_SPLIT = ":";
        /**
         * 应用名
         */
        String APP_NAME = "mes";
        /**
         * 登录相关
         */
        String SUB_NAME_LOGIN = "login";
        String SUB_NAME_LOGIN_JWT = "login_jwt";
        String SUB_NAME_LOGIN_USER = "login_user";
        String SUB_NAME_USER_PERMISSIONS = "user_permissions";
        String SUB_NAME_SCHEMA_TABLES = "schema_tables";
        String USER_LOGIN_SOURCE = "login_source";
        /**
         * 模板相关
         */
        String SUB_NAME_TEMPLATE_FORMAT = "template_format";
    }


    /**
     * 微服务名
     */
    interface RpcServiceName {

        /**
         * 权限模块
         */
        String AUTH = "http://auth-provider";

        /**
         * 自定义模块
         */
        String AUTOFORMS = "http://autoforms-provider";

        /**
         * 客户模块
         */
        String CUSTOMER = "http://customer-provider";

        /**
         * 工厂建模
         */
        String  CONSTRRUCTION= "http://construction-provider";

    }

    /**
     * 微服务调用地址
     */
    interface RpcRequestAddress {
        /**
         * 业务类别查询
         */
        String getBusinessTypes = RpcServiceName.AUTOFORMS.
                concat("/api/autoforms/business-type/getBusinessTypes");

        /**
         *  业务类别新增
         */
        String addBusinessType= RpcServiceName.AUTOFORMS.
                concat("/api/autoforms/business-type/addBusinessType");

        /**
         * 新增企业schema库
         */
        String createDataBase = RpcServiceName.AUTH.concat("/api/auth/routing-data-source/createDataBase");

        /**
         * 新增企业schema库表
         */
        String createTable = RpcServiceName.AUTH.concat("/api/auth/routing-data-source/createTable");

        /**
         * 初始化企业schema库表数据
         */
        String insertData = RpcServiceName.AUTH.concat("/api/auth/routing-data-source/insertData");

        /**
         * 初始化路由表数据
         */
        String initRoutingData = RpcServiceName.AUTH.concat("/api/auth/routing-data-source/initRoutingData");

        /**
         * 新增|更新企业权限数据
         */
        String addOrUpdateComPermiss = RpcServiceName.AUTH.concat("/api/auth/company-permission/addOrUpdateComPermiss");

        /**
         * 获取用户信息
         */
        String getUserInfoById = RpcServiceName.AUTH.concat("/api/auth/user/getUserInfoById");

        /**
         * 获取用户信息
         */
        String getUserInfoList = RpcServiceName.AUTH.concat("/api/auth/user/listByCondition");


        /**
         * schema表查询
         */
        String getSchemaTables = RpcServiceName.AUTH.concat("/api/auth/tables/getSchemaTables");

        /**
         * 根据编码查询数据项
         */
        String getDictByPrefixCode = RpcServiceName.AUTH.concat("/api/auth/dictionary/getByPrefixCode");

        /**
         * 根据编码查询数据项
         */
        String getDictItemsByDictionaryId = RpcServiceName.AUTH.concat("/api/auth/dictionary-item/listByCondition");

        /**
         * 根据编码查询数据项
         */
        String getDictItemsByCode = RpcServiceName.AUTH.concat("/api/auth/dictionary-item/listByCode");

        /**
         * 操作日志保存
         */
        String  operateLogSave= RpcServiceName.CONSTRRUCTION.concat("/api/construction/operate-log/save");

        /**
         * 查询用户
         */
        String getUserInfo= RpcServiceName.AUTH.concat("/api/auth/user/getUserInfo");
    }


}
