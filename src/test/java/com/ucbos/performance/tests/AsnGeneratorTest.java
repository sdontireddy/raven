package com.ucbos.performance.tests;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.ucbos.performance.config.YmlConfigReader;
import com.ucbos.performance.generators.XMLDataGenerator;
import com.ucbos.performance.jmeter.LoadTestConstants;

/**
 * Test case for ASN file generation
 * 
 * @author Rudreshkumar
 *
 */
public class AsnGeneratorTest {

    private static Logger logger = Logger.getLogger("AsnGeneratorTest.class.getName()");

    @Value("${loadtesting.pathconstants.asnpathconstant}")
    private String asnPathConstant;

    @Value("${loadtesting.testconstants.asnconstant}")
    private String asnConstant;

    @Value("${loadtesting.pathconstants.genericpath}")
    private String genericPath;

    @Before
    public void setItemDetailsYaml() throws Exception {
        YmlConfigReader.readYamlConfigurationAssumingListinSameDocument(LoadTestConstants.ASN);
    }

    @Test
    // @Ignore
    public void testAutoGenerateItemDetailsUsingFieldMapper() {

        FileCreationCheck fileCreationCheck = new FileCreationCheck();

        try {
            XMLDataGenerator doXMLGenerator = new XMLDataGenerator();

            boolean isFileGenerated = doXMLGenerator.generateOrderDataFiles();
            assertTrue(isFileGenerated);

            fileCreationCheck.filePresenceAndCountCheckTest(asnPathConstant, asnConstant);

        } catch (Exception e) {
            logger.severe("Exception Generating the data " + e.getMessage());
        }
    }
}