package com.ucbos.performance.tests;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.ucbos.performance.config.YmlConfigReader;
import com.ucbos.performance.generators.XMLDataGenerator;
import com.ucbos.performance.jmeter.LoadTestConstants;

public class sampleDoGeneratorTest {

    private static Logger LOGGER = Logger.getLogger("sampleDoGeneratorTest.class.getName()");

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

            fileCreationCheck.filePresenceAndCountCheckTest(LoadTestConstants.SAMPLE_DO_PATH,
                LoadTestConstants.GENERIC_PATH, LoadTestConstants.SAMPLE_DO_XML);

        } catch (Exception e) {
            LOGGER.severe("Exception Generating the data " + e.getMessage());
        }
    }

}
