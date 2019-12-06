package com.luoxiaohao.mp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class CodeGenerator {

    @Test
    public void generateCode() {
        generate("sample_ods");
    }


    public void generate(String... tableNamesInclude) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)   // 是否支持AR模式
                .setAuthor("luoxhe")  // 作者
                .setOpen(false)         //是否打开输出目录
                .setOutputDir("D:\\idea_workspace\\test\\src\\main\\java")  // 生成路径
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig dsConfig  = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://10.0.5.118:3306/data_module?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8")
                .setUsername("root")
                .setPassword("dcg123456");

        //3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("tbl_")
                //.setEntityLombokModel(true)
                .setInclude(tableNamesInclude);  // 生成代码需要的表


        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.csa.etl.api")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("mapper");

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();

        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }
}
