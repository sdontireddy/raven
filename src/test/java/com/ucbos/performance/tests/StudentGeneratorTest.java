package com.ucbos.performance.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.ucbos.performance.config.YmlConfigReader;
import com.ucbos.performance.generators.XMLDataGenerator;
import com.ucbos.performance.jmeter.LoadTestConstants;
import com.ucbos.performance.models.NodeValue;
import com.ucbos.performance.models.YmlNode;

public class StudentGeneratorTest {

    private static Logger logger = Logger.getLogger("StudentGeneratorTest.class.getName()");

    @Before
    public void setItemDetailsYaml() throws Exception {
         YmlConfigReader.readYamlConfigurationAssumingListinSameDocument(LoadTestConstants.STUDENT);
    }

    @Test
    // @Ignore
    public void testAutoGenerateItemDetailsUsingFieldMapper() {

        try {
            XMLDataGenerator doXMLGenerator = new XMLDataGenerator();

            boolean isFileGenerated = doXMLGenerator.generateOrderDataFiles();
            assertTrue(isFileGenerated);

        } catch (Exception e) {
            logger.severe("Exception Generating the data " + e.getMessage());
        }
    }

}
