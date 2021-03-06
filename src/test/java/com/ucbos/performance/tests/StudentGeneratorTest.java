package com.ucbos.performance.tests;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.ucbos.performance.config.YmlConfigReader;
import com.ucbos.performance.generators.XMLDataGenerator;
import com.ucbos.performance.jmeter.LoadTestConstants;

public class StudentGeneratorTest {

    private static Logger logger = Logger.getLogger("StudentGeneratorTest.class.getName()");

    @Value("${loadtesting.pathconstants.studentpathconstant}")
    private String studentPathConstant;

    @Value("${loadtesting.testconstants.studentconstant}")
    private String studentConstant;

    @Before
    public void setItemDetailsYaml() throws Exception {
        YmlConfigReader.readYamlConfigurationAssumingListinSameDocument(LoadTestConstants.STUDENT);
    }

    @Test
    // @Ignore
    public void testAutoGenerateItemDetailsUsingFieldMapper() {

        FileCreationCheck fileCreationCheck = new FileCreationCheck();

        try {
            XMLDataGenerator doXMLGenerator = new XMLDataGenerator();

            boolean isFileGenerated = doXMLGenerator.generateOrderDataFiles();
            assertTrue(isFileGenerated);

            fileCreationCheck.filePresenceAndCountCheckTest(studentPathConstant, studentConstant);

        } catch (Exception e) {
            logger.severe("Exception Generating the data " + e.getMessage());
        }
    }

}
