package com.mantou.model;

import lombok.Data;

/**
 * @Author: wkh
 * @CreateTime: 2024-07-15
 * @Description: 静态模版配置
 * @Version: 1.0
 */
@Data
public class MainTemplateConfig {

    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 作者注释
     */
    private String author="mantou";

    /**
     * 输出信息
     */
    private String outputText="sum = ";

}
