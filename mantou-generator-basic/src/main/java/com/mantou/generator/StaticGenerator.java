package com.mantou.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @Author: wkh
 * @CreateTime: 2024-07-10
 * @Description: 静态
 * @Version: 1.0
 */
public class StaticGenerator {

    public static void copyFilesByHutool(String inoutPath, String outPath) {
        FileUtil.copy(inoutPath, outPath, false);
    }

    public static void main(String[] args) {
        //获取整个项目的根路径
        //获取当前项目路径
        String projectPath = System.getProperty("user.dir");
        //输入路径:ACM 示例代码模版目录
        String inputPath = projectPath+ File.separator+ "mantou-generator-demo-project"+File.separator+"acm-template";
        //输出路径: 直接输出到项目的根目录
        String outputPath = projectPath;
        copyFilesByRecursive(inputPath,outputPath);

    }

    public static void copyFilesByRecursive(String inputPath, String outputPath){
        File inputFile= new File(inputPath);
        File outputFile= new File(outputPath);
        try {
            copyFileByRecursive(inputFile,outputFile);
        } catch (Exception e) {
            System.err.println("文件复制失败");
            e.printStackTrace();
        }
    }

    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        if(inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            //如果是目录,首先创建目标目录
            if(!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            //获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            //无子文件,直接结束
            if(ArrayUtil.isEmpty(files)){
                return;
            }
            for (File file : files) {
                //递归复制
                copyFileByRecursive(file, destOutputFile);
            }
        }else {
            //是文件,直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
