package org.unitils.spring.boot.autoconfigure;

/**
 * SpringBoot 自动加载测试类
 * @Author: rayminr
 * @Date:Created in 2020-05-03
 */
public class DemoService {

    private UnitilsProperties properties;

    public DemoService() {
    }

    public DemoService(UnitilsProperties properties) {
        this.properties = properties;
    }

    public String getMsg(){
        return properties.getMsg();
    }

}
