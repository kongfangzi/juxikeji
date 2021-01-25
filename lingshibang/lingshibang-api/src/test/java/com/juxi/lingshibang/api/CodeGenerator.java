package com.juxi.lingshibang.api;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 代码自动生成功能类
 */
public class CodeGenerator {

    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        //String projectPath = System.getProperty("user.dir");
        String projectPath = "E:/lingshibang/lingshibang-api";
        //        String projectPath = "F:/guniuworkspace/mes3.0/backend/auth";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("liufeng");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://81.69.43.241:3306/juxi_test?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("juxi202012");
        mpg.setDataSource(dsc);

        //包配置
        PackageConfig pc = new PackageConfig();
        //设置生产代码的地方，一般是业务bus 模块
        pc.setModuleName("");
        pc.setParent("com.juxi.lingshibang");
        mpg.setPackageInfo(pc);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //这里还可以设置模板的配置
        mpg.setTemplate(new TemplateConfig().setXml(null));

        //生成策略配置
        StrategyConfig strategy = new StrategyConfig();
        //类名生成策略：驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //字段名生成方式：驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //配置继承的父类
        strategy.setSuperEntityClass("com.juxi.lingshibang.common.model.BaseEntity");
        strategy.setSuperControllerClass("com.juxi.lingshibang.common.base.BaseController");
//        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //需要生成的表
//        strategy.setInclude("sys_area","sys_dict_data","sys_dict_type","sys_log_exception","sys_logininfor","sys_menu","sys_oper_log","sys_permission","sys_role"
//        ,"sys_role_menu","sys_role_permission","sys_role_user");
        strategy.setInclude("tb_appraise","tb_chat_message","tb_chat_message_backup","tb_cps_order_info"
                ,"tb_cps_user_info","tb_follows","tb_order_action_log","tb_order_info","tb_order_return","tb_pay_account","tb_pay_account_log","tb_user","tb_user_behavior_data"
                ,"tb_user_info_extend","tb_withdraw");
        //strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        //过滤表前缀
        strategy.setTablePrefix("tb_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
