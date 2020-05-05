package org.unitils.spring.boot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.unitils.spring.util.ApplicationContextFactory;

import java.util.List;

/**
 * 在@SpringApplicationContext({"org.unitils"}) 以包名方式加载时，加载ApplicationContext
 * @Author: rayminr
 * @Date:Created in 2020-05-03
 */
public class SpringBootApplicationContextFactory implements ApplicationContextFactory {

    private static Log logger = LogFactory.getLog(SpringBootApplicationContextFactory.class);

    public ConfigurableApplicationContext createApplicationContext(List<String> basePackages) {
        logger.info("=========================================load from SpringBootApplicationContextFactory， basePackages = " + basePackages);
        return new SpringBootAnnotationConfigApplicationContext(basePackages.toArray(new String[basePackages.size()]));
    }

}
