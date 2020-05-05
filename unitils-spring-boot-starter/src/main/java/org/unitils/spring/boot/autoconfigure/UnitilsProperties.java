package org.unitils.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SpringBoot 配置初始化测试类
 * @Author: rayminr
 * @Date:Created in 2020-05-03
 */
@ConfigurationProperties(prefix = "unitils.spring.boot")
public class UnitilsProperties {

    private String msg = "default msg";

    public String getMsg() {

        return msg;

    }

    public void setMsg(String msg) {

        this.msg = msg;

    }

}
