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
 * Test case for Sample_DO file generation
 * 
 * @author Rudreshkumar
 *
 */
public class sampleDoGeneratorTest {

    private static Logger LOGGER = Logger.getLogger("sampleDoGeneratorTest.class.getName()");

    @Value("${loadtesting.pathconstants.sampledopathconstant}")
    private String sampleDoPathConstant;

    @Value("${loadtesting.testconstants.sampledoconstants}")
    private String sampleDoConstant;

    @Before
    public void setUp() throws Exception {
        YmlConfigReader.readYamlConfigurationAssumingListinSameDocument(LoadTestConstants.SAMPLE_DO);
    }

    @Test
    // @Ignore
    public void testAutoGeneratePODataFilesUsingFieldMapper() {

        FileCreationCheck fileCreationCheck = new FileCreationCheck();

        try {
            XMLDataGenerator doXMLGenerator = new XMLDataGenerator();
            boolean isFileGenerated = doXMLGenerator.generateOrderDataFiles();
            assertTrue(isFileGenerated);

            fileCreationCheck.filePresenceAndCountCheckTest(sampleDoPathConstant, sampleDoConstant);

        } catch (Exception e) {
            LOGGER.severe("Exception Generating the data " + e.getMessage());
        }
    }
}