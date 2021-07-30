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

    String STUDENT_PATH = "C:/performance-tests/1Student.xml";
    String ASN_PATH = "C:/performance-tests/1ASN.xml";
    String ITEM_DETAILS_PATH = "C:/performance-tests/1ItemDetails.xml";
    String SAMPLE_DO_PATH = "C:/performance-tests/1Sample_DO.xml";
    String GENERIC_PATH = "C:/performance-tests";

    String ITEM_DETAIL_XML = "ItemDetails.xml";
    String ASN_XML = "ASN.XML";
    String STUDENT_XML = "Student.xml";
    String SAMPLE_DO_XML = "Sample_DO.xml";

    void setupTest(JavaSamplerContext context);

    SampleResult runTest(JavaSamplerContext context);

    Arguments getDefaultParameters();

    void teardownTest(JavaSamplerContext context);

}