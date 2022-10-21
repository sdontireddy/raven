package com.ucbos.performance.jmeter;

import java.io.File;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public interface LoadTestConstants {

    String NUMBER_OF_ORDERS = "NUMBER_OF_ORDERS";
    String NUMBER_OF_LINEITEMS = "NUMBER_OF_LINEITEMS";

    String PUBLISHED_ORDERS_REPORT = ".." + File.separator + "results" + File.separator + "ORDERS_PUBLISHED_REPORT.csv";
    String TEST_PRODUCTS_REPORT = ".." + File.separator + "results" + File.separator + "TEST_PRODUCTS_REPORT.csv";

    String RANDOM_NUMBER = "randomNumber";
    String RANDOM_STRING = "randomString";
    String RANDOM_INTEGER_NUMBER = "randomIntegerNumber";
    String RANDOM_DECIMAL_NUMBER = "randomDecimalNumber";
    String DATE_TIME = "dateTime";
    String RANDOM_FROM_LIST = "randomFromList";
    String BOOLEAN = "boolean";
    String STATIC = "static";
    String INTEGER = "integer";
    String DECIMAL = "decimal";
    String STRING_COUNTER = "stringCounter";
    String INCREMENT = "increment";

    String ITEM_DETAILS = "ItemDetails.yaml";
    String ASN = "ASN.yaml";
    String SAMPLE_DO = "mapping.yaml";
    String STUDENT = "Student.yaml";

    void setupTest(JavaSamplerContext context);

    SampleResult runTest(JavaSamplerContext context);

    Arguments getDefaultParameters();

    void teardownTest(JavaSamplerContext context);
}