package org.unitils.sample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.UnitilsBlockJUnit4ClassRunner;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.sample.controller.SampleController;
import org.unitils.sample.test1.service.SampleService;
import org.unitils.sample.test2.service.MsgService;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import javax.sql.DataSource;

/**
 * SpringApplicationContext注解可以通过两种方式指定
 * 1）包名，此时需要在unitils.properties中配置SpringBootApplicationContextFactory
 * 2）xml配置文件，unitils.properties中默认ClassPathXmlApplicationContextFactory，无需特别指定
 */
@RunWith(UnitilsBlockJUnit4ClassRunner.class)
//@SpringApplicationContext({"org.unitils"})
@SpringApplicationContext({"applicationContext-test.xml"})
public class BaseTest {

    protected static final String DATABASE1 = "database1";
    protected static final String DATABASE2 = "database2";

    @Autowired
    protected DataSource testDataSource1;

    @TestDataSource(DATABASE2)
    protected DataSource testDataSource2;

    @SpringBeanByType
    protected SampleController sampleController ;

    @SpringBeanByType
    protected SampleService sampleService ;

    @SpringBeanByType
    protected MsgService msgService ;

    @Test
    @DataSet(value = {"/data/getValue.xls"})
    public void test_getValue(){
        String name = sampleController.getDbValue(1);
        Assert.assertNotNull(name);
        Assert.assertTrue("Sample".equals(name));
    }

    @Test
    @DataSet(databaseName="database2", value = {"/data/testMutiDataSetsDb2.xls"})
    @Transactional(TransactionMode.ROLLBACK)
    public void test_getMsg(){
        String name = sampleController.getDbMsg(1000);
        Assert.assertNotNull(name);
        Assert.assertTrue("成功".equals(name));
    }

    @Test
    public void test_msg(){
        String name = sampleController.getProMsg();
        Assert.assertNotNull(name);
        Assert.assertTrue("spring boot starter".equals(name));
    }

}
