package com.ucbos.performance;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class OrderConstants {

	private static Logger LOGGER = Logger.getLogger("OrderConstants.class.getName()");

	static String SiteID = "3903";
	static String itemsList = "1234,42323,94545,57834,4534";

	static long startItemRange = 7000000;

	static long endItemRange = 7999999;

	static long startQtyRange = 10;

	static long endQtyRange = 100;

	

	static String ORDER_TEMPLATE_FILENAME = "singleinstock.xml";
	static String Rabbit_Messaging_FileName = "singleOrder.json";
	public static String Rabbit_HostName, Rabbit_UserName, Rabbit_Passowrd, Rabbit_ExchangeName, Rabbit_QueueName,
			Rabbit_RoutingKey, OSS_Endpoint, OSS_SiteId;

	protected static String REPORT_NAME = "ORDER_PUBLISHED_REPORT.csv";
	static Properties prop;
	static {
		try {
			InputStream is = null;

			prop = new Properties();
			is = OrderConstants.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(is);
			SiteID = getPropertyValue("SiteID");
			startQtyRange = Long.valueOf(getPropertyValue("startQtyRange"));
			endQtyRange = Long.valueOf(getPropertyValue("endQtyRange"));
			ORDER_TEMPLATE_FILENAME = getPropertyValue("ORDER_TEMPLATE_FILENAME");
			itemsList = getPropertyValue("itemList");
			REPORT_NAME = getPropertyValue("REPORT_NAME");
						LOGGER.info("SiteID :" + SiteID + " StartItemRange " + startItemRange + endItemRange + " StartQtyRange "
					+ startQtyRange + " EndQtyRange" + endQtyRange + " itemsList :"+itemsList);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}

}
