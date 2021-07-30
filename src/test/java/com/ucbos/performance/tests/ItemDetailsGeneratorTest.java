package com.ucbos.performance.tests;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.ucbos.performance.config.YmlConfigReader;
import com.ucbos.performance.generators.XMLDataGenerator;
import com.ucbos.performance.jmeter.LoadTestConstants;

public class ItemDetailsGeneratorTest {

    private static Logger logger = Logger.getLogger(ItemDetailsGeneratorTest.class.getName());

    @Before
    public void setItemDetailsYaml() throws Exception {
        YmlConfigReader.readYamlConfigurationAssumingListinSameDocument(LoadTestConstants.ITEM_DETAILS);
    }

    @Test
    // @Ignore
    public void testAutoGenerateItemDetailsUsingFieldMapper() {

        FileCreationCheck fileCreationCheck = new FileCreationCheck();

        try {
            XMLDataGenerator doXMLGenerator = new XMLDataGenerator();

            boolean isFileGenerated = doXMLGenerator.generateOrderDataFiles();
            assertTrue(isFileGenerated);

            fileCreationCheck.filePresenceAndCountCheckTest(LoadTestConstants.ITEM_DETAILS_PATH,
                LoadTestConstants.GENERIC_PATH, LoadTestConstants.ITEM_DETAIL_XML);

        } catch (Exception e) {
            logger.severe("Exception Generating the data " + e.getMessage());
        }
    }

}
