package com.mantou.generator;

import com.mantou.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Author: wkh
 * @CreateTime: 2024-07-15
 * @Description: 动静结合
 * @Version: 1.0
 */
public class MainGenerator {

    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        //输入路径
        String inputPath = projectPath + File.separator + "mantou-generator-demo-project"+File.separator+"acm-template";
        String outputPath= projectPath;
        //生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
        //生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "mantou-generator-basic" + File.separator+"src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/mantou/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);

    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("mantou");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果: ");
        doGenerator(mainTemplateConfig);
    }
}
