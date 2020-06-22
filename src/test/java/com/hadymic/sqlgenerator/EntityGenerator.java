package com.hadymic.sqlgenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class EntityGenerator {
    @Test
    public void entityGenerator() {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D:\\test");//这里写你自己的java目录
        gc.setFileOverride(true);//是否覆盖
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("hadymic");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"ad_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{"ad_adslot","ad_animation","ad_app","ad_click_area","ad_data","ad_deep_link","ad_diff_data","ad_download_conf","ad_dynamic_creative","ad_event","ad_ext","ad_filter_word","ad_icon","ad_image","ad_jsonrootbean","ad_media_ext","ad_option","ad_playable_style","ad_session_param","ad_tpl_info","ad_tpl_info_data","ad_values","ad_video"});
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setEntity("com.hadymic.sqlgenerator.entity");
        pc.setMapper("com.hadymic.sqlgenerator.dao");
        pc.setXml("com.hadymic.sqlgenerator.dao.mapping");
        pc.setService("TTT");       //本项目没用，生成之后删掉
        pc.setServiceImpl("TTT");   //本项目没用，生成之后删掉
        pc.setController("TTT");    //本项目没用，生成之后删掉
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);

        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}
