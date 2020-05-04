package org.unitils.spring.boot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.unitils.spring.util.ApplicationContextFactory;

import java.util.List;

/**
 * springboot方式加载ApplicationContext实现
 * @Author: rayminr
 * @Date:Created in 2020-05-03
 */
public class SpringBootApplicationContextFactory implements ApplicationContextFactory {

    private static Log logger = LogFactory.getLog(SpringBootApplicationContextFactory.class);

    public ConfigurableApplicationContext createApplicationContext(List<String> basePackages) {
        logger.info("========================================= basePackages = " + basePackages);
        return new AnnotationConfigApplicationContext(basePackages.toArray(new String[basePackages.size()]));
    }

}
