package com.ucbos.performance;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import com.ucbos.performance.models.NodeValue;
import com.ucbos.performance.models.YmlConfigModel;
import com.ucbos.performance.models.YmlConfigReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ucbos.utils.XMLUtil;

import static org.junit.Assert.assertTrue;

/**
 * Generates Order XML with given set of Product
 * 
 * Use init method to pass required Products information to be use
 * 
 * @author sdontireddy
 *
 */
public class OrderXMLGenerator extends OrderConstants {

	private static Logger LOGGER = Logger.getLogger("OrderXMLGenerator.class.getName()");
	private Properties properties;
	private static List<YmlConfigModel> fieldsToUpdate ;

	public OrderXMLGenerator() throws Exception {
		try {
			loadProperties("/field-mapping.properties");
		} catch (Exception e) {
			LOGGER.severe("Exception reading the field-mapping.properties file");
			throw new Exception();
		}
	}

	static {
		try {
			fieldsToUpdate = YmlConfigReader.readYamlConfigurationAssumingListinSameDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadProperties(String path) throws IOException {
		InputStream in = this.getClass().getResourceAsStream(path);
		properties = new Properties();
		properties.load(in);
		System.out.println(properties.stringPropertyNames());
	}

	public boolean autoGenerateOrderDataFiles(String path, String fileName, int numberFilestoCreate,
			int nummberOfLineItems) throws Exception {
		boolean returnFlag = false;
		String sampleFilePath = path + fileName;

		LOGGER.log(Level.INFO, "--------------Started----------------" + sampleFilePath);
		System.out.println("sampleFilePath" + sampleFilePath);
		long tStart = System.currentTimeMillis();
		IntStream.range(0, numberFilestoCreate).forEach(counter -> {
			try {
				String newFilePath = "../results//" + (counter + 1) + fileName;

				//XMLUtil xmlUtil = XMLUtil.init(sampleFilePath);
				InputStream stream = this.getClass().getClassLoader().getResourceAsStream(sampleFilePath);
				XMLUtil xmlUtil = new XMLUtil(stream);
				//TODO
				//Document updatedDocument = fillTheMappedValues(xmlUtil.getDocument(), xmlUtil, nummberOfLineItems);
				Document updatedDocument = fillTheMappedValuesYml(xmlUtil.getDocument(), xmlUtil, nummberOfLineItems);

				System.out.println("New File Path" + newFilePath);
				xmlUtil.createXMLFile(newFilePath, updatedDocument);

			} catch (Exception e) {
				LOGGER.severe("Exception generating PO XML files " + e.getMessage());
				e.printStackTrace();
			}
		});

		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		returnFlag = true;
		LOGGER.log(Level.INFO, "--------------Completed : Time Elapsed----------------" + elapsedSeconds);

		return returnFlag;

	}
	/***
	 * YAML configuration
	 */
	private Document fillTheMappedValuesYml(Document document, XMLUtil xmlUtil, int numberOfChildLineItems)
			throws Exception {
		System.out.println("Fill the Mapped Values");

		String expression = "";
		document = updateXMLDocwithLineItemItems(document, numberOfChildLineItems);
		Document updatedDocument = document;
		for (int i = 1; i <= numberOfChildLineItems; i++) {
			System.out.println("i ************" + i);

			for (YmlConfigModel xmlConfigNode : fieldsToUpdate) {
				System.out.println(xmlConfigNode);
				//For each of the node , we will have Name , Path , Value obj
				//First generate/calculate the with given values
				NodeValue nodeValueToGenerate = xmlConfigNode.getValue();
				System.out.println("Geneating value " + nodeValueToGenerate.getPrefix() +nodeValueToGenerate.getGeneratedvalue() + nodeValueToGenerate.getSuffix());
				String value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate.getGeneratedvalue())+nodeValueToGenerate.getSuffix();

				//we got the value we wanted to be updated with
				//just update the
				System.out.println("**********************Update" + xmlConfigNode.getPath() + "Value " + value);
				updatedDocument = xmlUtil.updateDocument(updatedDocument, xmlConfigNode.getPath(), value);
			}


		}

		return updatedDocument;

	}

	private String generateValue(String generateValuesBasedonString) {
		String value ="0";
		switch (generateValuesBasedonString){
			case "randomnumber": {
				value = OrderDataGenerator.getRandomNumberStr();
				break;
			}
			case "MMDDYY": {
				value = OrderDataGenerator.getDateFormat("MMddyy");
				break;
			}
			case "MMddyy:hh:mm:ss": {
				value = OrderDataGenerator.getDateFormat("MMddyy:hh:mm:ss");
				break;
			}
		}
		return  value;
	}

	/**
	 * Utility Method fils the required mappings
	 * 
	 * @param document
	 * @param xmlUtil
	 * @return
	 * @throws Exception
	 */
	private Document fillTheMappedValues(Document document, XMLUtil xmlUtil, int numberOfChildLineItems)
			throws Exception {
		System.out.println("Fill the Mapped Values");

		String tempDOID = "";
		String tempdate = "";
		long tmpQty = 0;
		String tempItemID = "";

		String expression = "";
		document = updateXMLDocwithLineItemItems(document, numberOfChildLineItems);
		Document updatedDocument = document;
		for (int i = 1; i <= numberOfChildLineItems; i++) {
			System.out.println("i ************" + i);
			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				System.out.println(key + " Value: " + value);
				// Generate autogenerated value for the key

				// Simple switch case auto generates different set of values
				// based
				// on the key
				// Use the generated value to update all the fields mapped to
				// the
				// key
				switch (key) {
				case "DistributionOrderId": {
					tempDOID = OrderDataGenerator.getNextDOID();
					break;
				}
				case "ItemName": {
					tempItemID = OrderDataGenerator.getItemIDFromPreConfiguredList();
					break;
				}
				case "OrderedDttm": {
					tempdate = OrderDataGenerator.getFutureDate(10);
					break;
				}
				case "OrderQty": {
					tmpQty = OrderDataGenerator.getQty();
					break;
				}

				}

				String[] fields = value.split(",");
				for (String field : fields) {
					// Replace each of the field value with new key value
					// generated

					System.out.println("key" + key + "field  : " + field);
					switch (key) {
					case "DistributionOrderId": {
						updatedDocument = xmlUtil.updateDocument(updatedDocument, value, tempDOID);
						break;
					}
					case "ItemName": {
						expression = "/tXML/Message/DistributionOrder/LineItem[" + i + "]/ItemName";
						updatedDocument = xmlUtil.updateDocument(updatedDocument, expression, tempItemID);
						break;
					}
					case "OrderQty": {
						expression = "/tXML/Message/DistributionOrder/LineItem[" + i + "]/Quantity/OrderQty";
						updatedDocument = xmlUtil.updateDocument(updatedDocument, expression, tmpQty);

						break;
					}
					case "OrderedDttm": {
						expression = "/tXML/Message/DistributionOrder/LineItem[" + i + "]/Quantity/OrderQty";
						updatedDocument = xmlUtil.updateDocument(updatedDocument, value, tempdate);
						break;
					}

					}
				}
			}
		}

		return updatedDocument;

	}

	/**
	 * Create number of OrderItems
	 * 
	 * @param rootDocument
	 * @param numberOfItems
	 * @return
	 */
	private Document updateXMLDocwithLineItemItems(Document rootDocument, int numberOfItems) {

		NodeList lineItemListElement = rootDocument.getElementsByTagName("LineItem");
		Node distributionOrder = rootDocument.getElementsByTagName("DistributionOrder").item(0);
		Element newOrderItem = null;
		for (int i = 1; i < numberOfItems; i++) {
			Element orderItemElement = (Element) lineItemListElement.item(0);
			newOrderItem = (Element) orderItemElement.cloneNode(true);

			distributionOrder.appendChild(newOrderItem);
		}

		return rootDocument;
	}

}
