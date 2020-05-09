package org.unitils.spring.boot;

import org.springframework.context.ConfigurableApplicationContext;
import org.unitils.spring.util.ApplicationContextFactory;

import java.util.List;

/**
 * 改写@SpringApplicationContext({"org.unitils"}) ，支持以包名方式加载产生ApplicationContext
 * @Author: rayminr
 * @Date:Created in 2020-05-03
 */
public class SpringBootApplicationContextFactory implements ApplicationContextFactory {

    public ConfigurableApplicationContext createApplicationContext(List<String> basePackages) {
        return new SpringBootAnnotationConfigApplicationContext(basePackages.toArray(new String[basePackages.size()]));
    }

}
