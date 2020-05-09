package org.unitils.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.unitils.UnitilsBlockJUnit4ClassRunner;


/**
 * Unitils支持springboot的自动装配类
 * @Author: rayminr
 * @Date:Created in 2020-05-03
 */
@Configuration
@ConditionalOnClass({UnitilsBlockJUnit4ClassRunner.class})
public class UnitilsAutoConfiguration {

}