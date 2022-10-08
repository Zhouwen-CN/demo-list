package com.yy;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.Collections;

@SpringBootTest
class MybatisPlusGenerateApplicationTests {

    @Autowired
    Environment env;

    @Test
    void contextLoads() {
        //获取数据库连接信息
        String url = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        String parentDir = System.getProperty("user.dir");
        String thisClassFullName = this.getClass().getCanonicalName();

        //拼接代码生成路径
        String codeGenerateDir = parentDir + "/src/main/java";
        String mapperGenerateDir = parentDir + "/src/main/resources/mapper";
        String pageName = thisClassFullName.substring(0, thisClassFullName.lastIndexOf("."));


        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("chen") // 设置作者  
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir()
                            .outputDir(codeGenerateDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(pageName) // 设置父包名
                            //.moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperGenerateDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_student") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_");// 设置过滤表前缀

                    //entity设置
                    builder.entityBuilder()
                            .enableLombok() // 开启lombok
                            .enableChainModel() // 开启链式模式
                            .logicDeleteColumnName("deleted")
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            .idType(IdType.AUTO)
                            .enableFileOverride(); // 覆盖已生成文件

                    //controller设置
                    builder.controllerBuilder().enableRestStyle().enableFileOverride();

                    //mapper设置
                    builder.mapperBuilder().enableFileOverride();

                    //service设置
                    builder.serviceBuilder().enableFileOverride();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
