package com.half.movie.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class MysqlGenerator {
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/movie-dao";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true); // 默认 false ,是否覆盖已生成文件
        gc.setOpen(false); //默认true ,是否打开输出目录
        gc.setEnableCache(false); // 默认false,是否开启二级缓存
        gc.setAuthor("lmm"); // 作者
        gc.setSwagger2(true); //默认false
        gc.setBaseResultMap(true); // 默认false
        gc.setDateType(DateType.TIME_PACK); // 时间策略 默认TIME_PACK
        gc.setBaseColumnList(true); //默认false  和basemodel相似
        gc.setEntityName("%s");
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setIdType(IdType.ASSIGN_UUID); // 指定生成的主键类型
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/half_movie?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        //dsc.setPassword("");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent("com.half.movie");
        // 这个地址是生成的配置文件的包路径
        //pc.setEntity("entity");
        //pc.setService("service");
        //pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        //pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        //String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/com/half/movie/dao/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

//		 //配置自定义输出模板
        // 不需要其他的类型时，直接设置为null就不会成对应的模版了
        templateConfig.setEntity(null);
        templateConfig.setService(null);
        templateConfig.setController(null);
        templateConfig.setServiceImpl(null);
// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也
        // 可以自定义模板名称 只要放到目录下，名字不变 就会采用这个模版 下面这句有没有无所谓
        // 模版去github上看地址：
        /**https://github.com/baomidou/mybatis-plus/tree/3.0/mybatis-plus-generator/src/main/resources/templates*/
        //templateConfig.setEntity("/templates/entity.java");
        //不需要模板生成xml文件
        //templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false); //是否大写命名 默认false
        strategy.setSkipView(true); //是否跳过试图 默认false
        strategy.setNaming(NamingStrategy.underline_to_camel);//表映射 驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);// 字段映射 驼峰
        strategy.setEntityLombokModel(true);//默认false
        strategy.setRestControllerStyle(true);//默认false
        strategy.setEntitySerialVersionUID(true); //默认true
        strategy.setEntityColumnConstant(true); //默认false
        strategy.setInclude(scanner("表名")); //表名，用，隔开  需要生产
        //     sc.setExclude(""); //                 不需要生成  二选一
        strategy.setEntityTableFieldAnnotationEnable(true); // 默认false 注释
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setLogicDeleteFieldName("dr"); // 逻辑删除字段名称
        strategy.setSuperEntityClass("com.half.movie.commonvo.BaseEntity");
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // 设置继承的父类字段
        strategy.setSuperEntityColumns("id", "modifiedtime", "modifier", "creationtime", "creator");
        strategy.setTablePrefix(pc.getModuleName() + "_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
