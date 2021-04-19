package com.ucbos.performance;


import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class LoadOrders extends AbstractJavaSamplerClient implements Serializable, LoadTestConstants {

	private static final long serialVersionUID = 1L;

	private Logger LOG = Logger.getLogger(LoadOrders.class);

	private static Integer numberOfOrders;
	

	private static boolean executeSetup = true;
	private static boolean haveToWait = true;
	private static int threadCount = 0;
	private static int finishedThreadCount = 1;

	private static DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss");

	@Override
	public void setupTest(JavaSamplerContext context) {

		// Execute Only once per thread
		if (executeSetup) {

			try {

				executeSetup = false;

				Thread.currentThread().setName("Thread-" + threadCount++);
				System.out.println("Initializing  LoadThread  " + Thread.currentThread().getName() + " time : "
						+ df.format(new Date()));
				LOG.warn("Initializing  LoadThread  " + Thread.currentThread().getName() + "time : "
						+ df.format(new Date()));
				numberOfOrders = Integer.parseInt(context.getParameter(NUMBER_OF_ORDERS));

				
				haveToWait = false;
			} catch (Exception e) {
				System.out.println("Exception setting up test data  " + e);

				e.printStackTrace();
				LOG.error("Exception setting up test data ,Stop executing", e);
				System.exit(0);
			} finally {
				haveToWait = false;
			}
		} else {
			blockAllThreads();

			if (!haveToWait) {
				Thread.currentThread().setName("Thread-" + threadCount++);
				System.out.println("Initializing  LoadThread  " + Thread.currentThread().getName() + " time : "
						+ df.format(new Date()));
				LOG.warn("Initializing  LoadThread  " + Thread.currentThread().getName() + " time : "
						+ df.format(new Date()));

			}

		}

	}

	private void blockAllThreads() {
		while (haveToWait) {
			try {
				Thread.sleep(10000);
				System.out.print(".WAITING" + haveToWait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public SampleResult runTest(JavaSamplerContext context) {
		blockAllThreads();
		LOG.info("Start Executing ?" + haveToWait);
		try {
			OrderXMLGenerator doXMLGenerator = new OrderXMLGenerator();
			System.out.println("**************************************************************************************" );

			LOG.warn("**************************************************************************************" );
			System.out.println("Auto generating "+context.getParameter(NUMBER_OF_ORDERS)+" DO Files with "+context.getParameter(NUMBER_OF_LINEITEMS)+" line items using" +context.getParameter("SAMPLE_FILE") +" under data/DO folder" );
			LOG.warn("Auto generating "+context.getParameter(NUMBER_OF_ORDERS)+" DO Files with "+context.getParameter(NUMBER_OF_LINEITEMS)+" line items using" +context.getParameter("SAMPLE_FILE") +" under data/DO folder" );
			//Hey Here is my Sample XML file , Path
			//Generate 2 XML files in the same location and each file to have 4 LineItems
			boolean isFileGenerated = doXMLGenerator.autoGenerateOrderDataFiles("", context.getParameter("SAMPLE_FILE"),Integer.parseInt(context.getParameter(NUMBER_OF_ORDERS)),Integer.parseInt(context.getParameter(NUMBER_OF_LINEITEMS)));
			System.out.println("Auto generating Data Files are under" + "../data//DO/");
			assertTrue(isFileGenerated);

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return generatedResult();
	}

	private SampleResult generatedResult() {
		SampleResult result = new SampleResult();
		result.sampleStart();

		try {
			//
			result.sampleEnd();
			result.setSuccessful(true);
			result.setResponseMessage("Successfully performed action");
			result.setResponseCodeOK();
		} catch (Exception e) {
			result.sampleEnd();
			result.setSuccessful(false);
			result.setResponseMessage("Exception: " + e);

			// get stack trace as a String to return as document data
			java.io.StringWriter stringWriter = new java.io.StringWriter();
			e.printStackTrace(new java.io.PrintWriter(stringWriter));
			result.setResponseData(stringWriter.toString());
			result.setDataType(SampleResult.TEXT);
			result.setResponseCode("500");
		}
		return result;
	}

	

	/**
	 * Method helps to rotate previous report
	 */
	private void rotateReports(String reportName) {
		try {
			System.out.println("Start Rotating existing report");
			File fileToMove = new File(reportName);
			DateFormat df = new SimpleDateFormat("dd-MM-yyyyHHmmss");
			Date date = new Date();
			df.format(date);
			String rotateReportname = reportName + df.format(date);
			System.out.println("File Path " + fileToMove.getAbsolutePath());
			if (fileToMove.exists()) {
				System.out.println("Rotating existing report");
				fileToMove.renameTo(new File(rotateReportname));
			}
		} catch (Exception e) {
			LOG.error("Error rotating the report");
		}
	}



	

	@Override
	public Arguments getDefaultParameters() {

		Arguments defaultParameters = new Arguments();


		defaultParameters.addArgument(NUMBER_OF_ORDERS, "10");
		defaultParameters.addArgument(NUMBER_OF_LINEITEMS, "2");


		return defaultParameters;
	}

	@Override
	public void teardownTest(JavaSamplerContext context) {

		System.out.println(
				"Teardown  LoadThread  " + Thread.currentThread().getName() + "Date time : " + df.format(new Date()));
		LOG.warn("Teardown  LoadThread  " + Thread.currentThread().getName() + "Date time : " + df.format(new Date()));
	
		//Wait for all the threads to be completed
		
		//Only for last thread try to close the connections
		System.out.println("finishedThreadCount " + finishedThreadCount);
		System.out.println("threadCount " + threadCount);
		
	
		if(finishedThreadCount>= threadCount){
			System.out.println("Start Closing the connection...");
			try{
		//	OrderPublisher.destroy();
				System.exit(0);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("Start Closing the connection...DONE");
		}
		finishedThreadCount++;
		System.out.println("Start Closing the connection..." + threadCount);
	}

	
}
