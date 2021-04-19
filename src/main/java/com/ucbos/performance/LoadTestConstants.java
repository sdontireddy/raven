package com.ucbos.performance;

import java.io.File;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public interface LoadTestConstants {

	String HOTMARKET_PRODUCT_ID = "HOTMARKET_PRODUCT_ID";
	String DROPSHIP_PRODUCT_ID = "DROPSHIP_PRODUCT_ID";
	String INSTOCK_PRODUCT_ID = "INSTOCK_PRODUCT_ID";
	String INSTOCK_PERCENTAGE = "INSTOCK_PERCENTAGE";
	String HOTMARKET_PERCENTAGE = "HOTMARKET_PERCENTAGE";
	String INSTOCK_MULTIPLE_PERCENTAGE = "INSTOCK_MULTIPLE_PERCENTAGE";
	String HOTMARKET_MULTIPLE_PERCENTAGE = "HOTMARKET_MULTIPLE_PERCENTAGE";
	String DROPSHIP_MULTIPLE_PERCENTAGE = "DROPSHIP_MULTIPLE_PERCENTAGE";
	String DROPSHIP_PERCENTAGE = "DROPSHIP_PERCENTAGE";
	String MIXED_PERCENTAGE = "MIXED_PERCENTAGE";
	String NUMBER_OF_ORDERS = "NUMBER_OF_ORDERS";
	String NUMBER_OF_LINEITEMS = "NUMBER_OF_LINEITEMS";
	String ORDERTYPE = "ORDERTYPE";

	String PUBLISHED_ORDERS_REPORT = ".." + File.separator + "results" + File.separator + "ORDERS_PUBLISHED_REPORT.csv";
	String TEST_PRODUCTS_REPORT = ".." + File.separator + "results" + File.separator + "TEST_PRODUCTS_REPORT.csv";
		String AUTO_GENERATE_PRODUCT = "XXXXXXXX";

	String ITEM_ID_LIST = "ITEM_ID_LIST";
	String ITEM_QTY_LIST= "ITEM_QTY_LIST";
	String TCSORDER = "TCSORDER";
	String SINGLEITEM_PERCENTAGE_3C = "SINGLEITEM_PERCENTAGE_3C";

	void setupTest(JavaSamplerContext context);

	SampleResult runTest(JavaSamplerContext context);

	Arguments getDefaultParameters();

	void teardownTest(JavaSamplerContext context);

}