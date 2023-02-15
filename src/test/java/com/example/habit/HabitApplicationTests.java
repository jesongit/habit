package com.example.habit;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Property;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class HabitApplicationTests {

    @Autowired
    private Environment environment;

    @Test
    void test() {
        String path = System.getProperty("user.dir");
        System.out.println(path);
    }

    @Test
    void generator() {
        String path = System.getProperty("user.dir");
        String url = environment.getProperty("spring.datasource.url");
        String username = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");

        List<IFill> tabFillList = new ArrayList<>();
        tabFillList.add(new Property("createTime", FieldFill.INSERT));
        tabFillList.add(new Property("updateTime", FieldFill.INSERT_UPDATE));

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("posase")    // 设置作者
                            .disableOpenDir()   // 禁止打开输出目录
                            .enableSpringdoc()  // SpringDoc
                            .outputDir(path + "\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.habit") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    path + "\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
//                .injectionConfig(builder -> {
//                })
                .strategyConfig(builder -> {
                    builder.likeTable(new LikeTable( "t_"))
                            .addTablePrefix("t_")  // 设置过滤表前缀
                            .entityBuilder()            // entry 策略
                            .enableLombok()             // Lombok 模式 只会生成 Getter 和 Setter
                            .addTableFills(tabFillList)
//                            .enableTableFieldAnnotation() // 数据库自动名注解
                            .enableFileOverride()       // 文件覆盖
                            .mapperBuilder()            // mapper 策略
                            .enableFileOverride()
                            .serviceBuilder()           // service 策略
                            .formatServiceFileName("%sService")
                            .enableFileOverride()
                            .controllerBuilder()        // controller 策略
                            .enableRestStyle();         // 生成@RestController
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
