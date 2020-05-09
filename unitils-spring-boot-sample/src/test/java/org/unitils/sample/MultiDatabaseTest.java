package org.unitils.sample;


import org.junit.Assert;
import org.junit.Test;
import org.unitils.database.SQLUnitils;

import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.DataSets;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.dbunit.annotation.ExpectedDataSets;
import org.unitils.sample.demo1.model.Sample;
import org.unitils.sample.demo2.model.Msg;


/**
 * Test Multiple Databases with DBUnit.
 *
 */
public class MultiDatabaseTest extends BaseTest{



    @Test
    @DataSets(value= {
            @DataSet(databaseName=DATABASE1, value = "/data/testMutiDataSetsDb1.xls"),
            @DataSet(databaseName=DATABASE2, value = "/data/testMutiDataSetsDb2.xls")
    })
    public void testMultipleDataSetsDatabase1() {

        Sample sample = sampleService.getValueById(1);
        Msg msg = msgService.getMsgById(1000);
        Assert.assertEquals("Sample",sample.getValue());
        Assert.assertEquals("正常",msg.getMsg());
    }

    @Test
    @ExpectedDataSets({
            @ExpectedDataSet(databaseName=DATABASE1, value = "MultiDatabaseIntTest.testMultipleExpectedDataSetsOnMultipleDatabases_1.xls"),
            @ExpectedDataSet(databaseName=DATABASE2, value= "MultiDatabaseIntTest.testMultipleExpectedDataSetsOnMultipleDatabases_2.xls")
    })
    public void testMultipleExpectedDataSetsOnMultipleDatabases() {
        SQLUnitils.executeUpdate("INSERT INTO person (personid, personname) values ('5', 'Andre');", testDataSource1);
        SQLUnitils.executeUpdate("INSERT INTO person (personid, personname) values ('6', 'Charles');", testDataSource1);
        SQLUnitils.executeUpdate("INSERT INTO person (personid, personname) values ('8', 'Luc');", testDataSource2);
        SQLUnitils.executeUpdate("INSERT INTO person (personid, personname) values ('9', 'Jean-Michel');", testDataSource2);
    }

}
