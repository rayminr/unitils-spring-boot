package org.unitils.sample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsBlockJUnit4ClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.sample.controller.SampleController;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

/**
 * SpringApplicationContext注解可以通过两种方式指定
 * 1）包名，此时需要在unitils.properties中配置SpringBootApplicationContextFactory
 * 2）xml配置文件，unitils.properties中默认ClassPathXmlApplicationContextFactory，无需特别指定
 */
@RunWith(UnitilsBlockJUnit4ClassRunner.class)
@SpringApplicationContext({"org.unitils"})
//@SpringApplicationContext({"applicationContext-test.xml"})
public class BaseTest {

    @SpringBeanByType
    private SampleController sampleController ;

    @Test
    @DataSet(value = {"/data/getValue.xlsx"})
    public void test_getValue(){
        String name = sampleController.getValue(1);
        Assert.assertNotNull(name);
        Assert.assertTrue("Sample".equals(name));
    }

    @Test
    public void test_msg(){
        String name = sampleController.getValue();
        Assert.assertNotNull(name);
        Assert.assertTrue("spring boot starter".equals(name));
    }

}
