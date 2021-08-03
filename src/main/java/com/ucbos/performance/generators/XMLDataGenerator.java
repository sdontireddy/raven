package com.ucbos.performance.generators;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ucbos.performance.config.YmlConfigReader;
import com.ucbos.performance.jmeter.LoadTestConstants;
import com.ucbos.performance.models.NodeValue;
import com.ucbos.performance.models.YmlNode;
import com.ucbos.utils.DataGeneratorUtil;
import com.ucbos.utils.XMLUtil;

/**
 * Generates bulk of data files with given set of configurations defined in the mapping.yaml
 * <p>
 * Use init method to pass required Products information to be use
 *
 * @author sdontireddy
 */
public class XMLDataGenerator {

    private static Logger LOGGER = Logger.getLogger("XMLDataGenerator.class.getName()");

    public boolean generateOrderDataFiles() throws Exception {
        boolean returnFlag = false;
        String sampleFileName = YmlConfigReader.getBulkLoadConfig().getSampleFile();
        int nummberOfLineItems = YmlConfigReader.getBulkLoadConfig().getNumberOfChildNodes();
        int nummberOfSubLineItems = YmlConfigReader.getBulkLoadConfig().getNumberOfSubChildNodes();

        LOGGER.log(Level.INFO, "--------------Started----------------" + sampleFileName);
        System.out.println("sampleFilePath" + sampleFileName);
        long tStart = System.currentTimeMillis();
        IntStream.range(0, YmlConfigReader.getBulkLoadConfig().getNumberOfFiles()).forEach(counter -> {
            try {
                String newFilePath =
                    YmlConfigReader.getBulkLoadConfig().getResultsFolderWithPath() + (counter + 1) + sampleFileName;
                System.out.println("New File Path" + newFilePath);
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream(sampleFileName);
                XMLUtil xmlUtil = new XMLUtil(stream);
                Document updatedDocument = fillTheMappedValuesYml(xmlUtil.getDocument(), xmlUtil, nummberOfLineItems,
                    nummberOfSubLineItems);

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
     * Based on the YAML configuration , given xpaths will be updated with the configured values
     */
    private Document fillTheMappedValuesYml(Document document, XMLUtil xmlUtil, int numberOfChildLineItems,
        int numberOfSubLineItems)
        throws Exception {
        System.out.println("Fill the Mapped Values");

        String value = "";
        int numberofmainnodes = YmlConfigReader.getBulkLoadConfig().getNumberOfMainNodes();

        List<String> childNodeList = Arrays.asList(YmlConfigReader.getBulkLoadConfig().getChildNode().split(","));

        for (String childNode : childNodeList) {

            document = updateXMLDocwithGivenChildItems(document, childNode, numberOfChildLineItems);
        }
        Document updatedDocument = document;
        for (int i = 1; i <= (numberOfChildLineItems + numberofmainnodes); i++) {
            System.out.println("i ************" + i);

            for (YmlNode xmlConfigNode : YmlConfigReader.getXmlNodesToUpdate()) {
                System.out.println(xmlConfigNode);
                // For each of the node , we will have Name , Path , Value obj
                // First generate/calculate the with given values
                NodeValue nodeValueToGenerate = xmlConfigNode.getValue();
                System.out.println("Geneating value " + nodeValueToGenerate.getPrefix()
                    + nodeValueToGenerate.getValueType() + nodeValueToGenerate.getSuffix());

                numberOfSubLineItems =
                    xmlConfigNode.getPath().contains("$$$") ? numberOfSubLineItems : numberofmainnodes;

                for (int j = 1; j <= numberOfSubLineItems; j++) {

                    value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate, value)
                        + nodeValueToGenerate.getSuffix();
                    System.out.println("*****UpdatePath*****" + xmlConfigNode.getPath() + " " + value);

                    String xPath = xmlConfigNode.getPath();
                    if (xPath.indexOf("$$") > -1 && xPath.indexOf("$$$") > -1) {
                        xPath = xPath.replace("$$$", String.valueOf(j));
                        xPath = xPath.replace("$$", String.valueOf(i));
                    } else if (xPath.indexOf("$$") > -1) {

                        xPath = xPath.replace("$$", String.valueOf(i));
                    }
                    System.out.println("expression=====================================" + xPath);
                    System.out.println("newValue=====================================" + value);
                    updatedDocument = xmlUtil.updateDocument(updatedDocument, xPath, value);
                }
                value = "";
            }
        }

        return updatedDocument;
    }

    private String generateValue(NodeValue nodeValueToGenerate, String value) {

        String criteria = nodeValueToGenerate.getValueType();
        String format = nodeValueToGenerate.getFormat();
        int startRange = nodeValueToGenerate.getStartRange();
        int endRanage = nodeValueToGenerate.getEndRange();
        int days = nodeValueToGenerate.getDays();
        int minutes = nodeValueToGenerate.getMinutes();

        String replaceValue = "0";
        switch (criteria) {
            case LoadTestConstants.RANDOM_NUMBER:
                replaceValue = String.valueOf(DataGeneratorUtil.getRandomNumber(1000,
                    9999));
                break;
            case LoadTestConstants.RANDOM_STRING:
                replaceValue = DataGeneratorUtil.generateRandomString();
                break;
            case LoadTestConstants.RANDOM_INTEGER_NUMBER:
                replaceValue = String.valueOf(DataGeneratorUtil.getRandomNumber(startRange, endRanage));
                break;
            case LoadTestConstants.RANDOM_DECIMAL_NUMBER:
                replaceValue = DataGeneratorUtil.getRandomDecimalNumber(startRange, endRanage);
                break;
            case LoadTestConstants.DATE_TIME:
                if (days != 0 || minutes != 0) {
                    replaceValue = DataGeneratorUtil.getModifiedDaysDate(format, days, minutes);
                } else {
                    replaceValue = DataGeneratorUtil.getDateFormat(format);
                }
                break;
            case LoadTestConstants.RANDOM_FROM_LIST:
                replaceValue = DataGeneratorUtil
                    .getRandomItemNumberFromtheList(Arrays.asList(nodeValueToGenerate.getList().split(",")));
                break;
            case LoadTestConstants.BOOLEAN:
                replaceValue = DataGeneratorUtil
                    .getRandomBooleanFromtheList(Arrays.asList(nodeValueToGenerate.getBooleanList().split(",")));
                break;
            case LoadTestConstants.STATIC:
                replaceValue = nodeValueToGenerate.getStaticValue();
                break;
            case LoadTestConstants.INTEGER:
                if (nodeValueToGenerate.getStepType().equalsIgnoreCase(LoadTestConstants.INCREMENT)) {

                    if (!value.isEmpty()) {
                        replaceValue = String
                            .valueOf(Integer.valueOf(value) + Integer.valueOf(nodeValueToGenerate.getStepValue()));
                    } else {

                        replaceValue = String.valueOf(nodeValueToGenerate.getStartRange());
                    }
                } else {
                    if (!value.isEmpty()) {
                        replaceValue = String
                            .valueOf(Integer.valueOf(value) - Integer.valueOf(nodeValueToGenerate.getStepValue()));
                    } else {
                        replaceValue = String.valueOf(nodeValueToGenerate.getEndRange());
                    }
                }
            case LoadTestConstants.DECIMAL:
                if (nodeValueToGenerate.getStepType().equalsIgnoreCase(LoadTestConstants.INCREMENT)) {
                    if (!value.isEmpty()) {
                        replaceValue = String
                            .valueOf(new BigDecimal(value).add(new BigDecimal(nodeValueToGenerate.getStepValue())));
                    } else {
                        replaceValue = String.valueOf(new BigDecimal(nodeValueToGenerate.getStartRange()));
                    }
                } else {
                    if (!value.isEmpty()) {
                        replaceValue = String.valueOf(
                            new BigDecimal(value).subtract(new BigDecimal(nodeValueToGenerate.getStepValue())));
                    } else {
                        replaceValue = String.valueOf(new BigDecimal(nodeValueToGenerate.getEndRange()));
                    }
                }
            case LoadTestConstants.STRING_COUNTER:
                if (!value.isEmpty()) {
                    StringBuffer sb = new StringBuffer(value);
                    sb.deleteCharAt(sb.length() - 1);
                    int counter = Integer.valueOf(nodeValueToGenerate.getStepValue());
                    replaceValue = sb.toString() + counter++;
                } else {
                    replaceValue = nodeValueToGenerate.getStaticString() + nodeValueToGenerate.getStepValue();
                }
        }
        return replaceValue;
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

    private Document updateXMLDocwithGivenChildItems(Document rootDocument, String nameOftheChildItem,
        int numberOfItems) {

        NodeList lineItemListElement = rootDocument.getElementsByTagName(nameOftheChildItem);
        Node parentNode = lineItemListElement.item(0).getParentNode();
        // Node distributionOrder = rootDocument.getElementsByTagName("DistributionOrder").item(0);
        Element newOrderItem = null;
        for (int i = 1; i <= numberOfItems; i++) {
            Element orderItemElement = (Element) lineItemListElement.item(0);
            // Just append the Lineitems
            newOrderItem = (Element) orderItemElement.cloneNode(true);
            parentNode.appendChild(newOrderItem);
        }
        return rootDocument;
    }
}
