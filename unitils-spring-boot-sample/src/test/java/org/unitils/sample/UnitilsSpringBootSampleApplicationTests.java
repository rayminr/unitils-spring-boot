package org.unitils.sample;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.unitils.sample.controller.SampleController;

@SpringBootTest
class UnitilsSpringBootSampleApplicationTests {

    @Autowired
    private SampleController sampleController ;

    @Test
    void contextLoads() {
        String name = sampleController.getValue(1);
        Assert.assertNotNull(name);
        //System.out.println("=================  " + name);
    }

}