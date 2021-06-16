package com.ucbos.performance.tests;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Ignore;
import org.junit.Test;

import com.ucbos.performance.generators.XMLDataGenerator;

public class BulkDOXMLGeneratorTest {

	private static Logger LOGGER = Logger.getLogger("BulkDOXMLGeneratorTest.class.getName()");




	@Test
	@Ignore
	public void testAutoGeneratePODataFilesUsingFieldMapper() {
		try {
			XMLDataGenerator doXMLGenerator = new XMLDataGenerator();
			System.out.println("**************************************************************************************" );
			System.out.println("Auto generating 2 DO Files with 4 line items using Sample_DO.xml under data/DO folder" );
			//Hey Here is my Sample XML file , Path 
			//Generate 2 XML files in the same location and each file to have 4 LineItems
			//boolean isFileGenerated = doXMLGenerator.autoGenerateOrderDataFiles("data//DO//", "Sample_DO.xml", 2,4);
			boolean isFileGenerated = doXMLGenerator.autoGenerateOrderDataFiles("", "Sample_DO.xml", 2,4);
			System.out.println("Auto generating Data Files are under" + "data//DO/");
			assertTrue(isFileGenerated);

		} catch (Exception e) {
			LOGGER.severe("Exception Generating the data " + e.getMessage());
		}
	}

}
