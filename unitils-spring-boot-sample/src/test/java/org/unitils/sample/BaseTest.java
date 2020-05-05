package org.unitils.sample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsBlockJUnit4ClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.sample.controller.SampleController;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;


@RunWith(UnitilsBlockJUnit4ClassRunner.class)
@SpringApplicationContext({"applicationContext-test.xml"})
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

}
