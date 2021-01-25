package com.juxi.lingshibang.common.util.template;

import com.alibaba.fastjson.JSONObject;
import com.juxi.lingshibang.common.base.Constants;
import com.juxi.lingshibang.common.redis.RedisUtil;
import com.juxi.lingshibang.common.util.StringUtil;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * 自定义模板工具类
 */
@UtilityClass
public class TemplateUtil{


    /**
     * 生成模板redis 缓存企业自定义模板 的 key
     * @param companyId
     * @return
     */
    public String getRedisCacheTemplateFormatKey(String mouldCode,String companyId) {
        return Constants.redisKey.APP_NAME
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(Constants.redisKey.SUB_NAME_TEMPLATE_FORMAT)
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(companyId)
                .concat(Constants.redisKey.REDIS_KEY_SPLIT)
                .concat(mouldCode);
    }

    /**
     * 缓存登录用户权限相关信息
     * @param stringRedisTemplate
     * @param template
     * @param companyId
     * @param mouldCode
     * @return
     */
    public boolean cacheTemplateFormat(StringRedisTemplate stringRedisTemplate,
                                       JSONObject template,
                                       String companyId,
                                       String mouldCode){
        String cacheValue = JSONObject.toJSONString(template);
        return RedisUtil.storeObject(stringRedisTemplate,
                getRedisCacheTemplateFormatKey(mouldCode,companyId),
                cacheValue);
    }

    /**
     * 根据模板编码和模板数据组装数据标题
     * @param mouldCode
     * @param templateData
     * @return
     */
    public JSONObject dataTitleProcessing(StringRedisTemplate stringRedisTemplate,
                                          String mouldCode,
                                          String companyId,
                                          JSONObject templateData){
        //从缓存中获取自定义模板
        String templateFormat=RedisUtil.getObject(stringRedisTemplate,
                       getRedisCacheTemplateFormatKey(mouldCode,companyId));
        if(StringUtil.isNotBlank(templateFormat)){
            JSONObject templateJSONObject=JSONObject.parseObject(templateFormat);
            String dataTitleStr=templateJSONObject.getString("dataTitle");
            String[] fieldIds= StringUtil.isNotBlank(dataTitleStr)? dataTitleStr.split(","):null;
            if(fieldIds!=null&&templateData!=null){
                 String dataTitleValue="";
                 for(String fieldId:fieldIds){
                     dataTitleValue=dataTitleValue.
                             concat(templateData.getString(fieldId)).
                             concat(" ");
                 }
                templateData.put("dataTitle",dataTitleValue);
            }
        }
        return templateData;
    }
    
}
