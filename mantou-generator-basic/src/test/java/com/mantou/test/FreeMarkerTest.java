package com.mantou.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wkh
 * @CreateTime: 2024-07-12
 * @Description:
 * @Version: 1.0
 */
public class FreeMarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        // 创建Configuration对象，并指定版本号为2.3.32
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 设置模板加载目录为"src/main/resources/templates"
        //Configuration.setDirectoryForTemplateLoading() 方法用于设置模板文件的加载目录
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置默认编码为UTF-8
        //Configuration.setDefaultEncoding() 方法用于设置默认编码格式
        configuration.setDefaultEncoding("UTF-8");
        // 设置默认数字格式为"0.######"，即保留6位小数
        configuration.setNumberFormat("0.######");


        Template template = configuration.getTemplate("myweb.html.ftl");

        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2023);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://codefather.cn");
        menuItem1.put("label", "编程导航");
        Map<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "https://laoyujianli.com");
        menuItem2.put("label", "老鱼简历");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems", menuItems);

        // 创建输出流
        Writer out =new FileWriter("myweb.html");
        // 输出文件
        template.process(dataModel,out);

        // 关闭输出流
        out.close();
    }
}
