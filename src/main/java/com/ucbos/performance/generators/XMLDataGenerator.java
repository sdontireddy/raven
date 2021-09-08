package com.ucbos.performance.generators;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * Method to generate files
     *
     * @return true if files generate else false
     * @throws Exception
     */
    public boolean generateOrderDataFiles() throws Exception {
        boolean returnFlag = false;
        String sampleFileName = YmlConfigReader.getBulkLoadConfig().getSampleFile();

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
                Document updatedDocument = fillTheMappedValuesYml(xmlUtil.getDocument(), xmlUtil);

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

    /**
     * Based on the YAML configuration , given xpaths will be updated with the configured values
     */
    private Document fillTheMappedValuesYml(Document document, XMLUtil xmlUtil)
        throws Exception {
        System.out.println("Fill the Mapped Values");

        String childNode = YmlConfigReader.getBulkLoadConfig().getChildNode();
        childNode = childNode.substring(1, childNode.length()-1);
        List<String> childNodeList = Arrays.asList(childNode.split(","));
        System.out.println("childNodeList======================="+childNodeList);

        for (String childNodeDetail : childNodeList) {

            String parentNode = childNodeDetail.substring(0, childNodeDetail.indexOf('='));
            String childNodeName = childNodeDetail.substring(childNodeDetail.indexOf('=')+1,
                childNodeDetail.indexOf("=", childNodeDetail.indexOf('=')+1));
            String childNodeCount = childNodeDetail.substring(
                childNodeDetail.indexOf("=", childNodeDetail.indexOf('=')+1)+1, childNodeDetail.length());
            document = updateXMLDocwithGivenChildItems(document, childNodeName, Integer.parseInt(childNodeCount),
                parentNode);
            }

        Document updatedDocument = document;

        for (YmlNode xmlConfigNode : YmlConfigReader.getXmlNodesToUpdate()) {

            updatedDocument = this.updateNodesWithGeneratedValue(updatedDocument, xmlConfigNode,
                xmlUtil);
        }

        return updatedDocument;
    }

    /**
     * Method to update xmlnodes
     *
     * @param updatedDocument
     * @param xmlConfigNode
     * @param xmlUtil
     * @return updatedDocument
     * @throws Exception
     */
    private Document updateNodesWithGeneratedValue(Document updatedDocument, YmlNode xmlConfigNode, XMLUtil xmlUtil) 
        throws Exception {

        String value = "";
        NodeValue nodeValueToGenerate = xmlConfigNode.getValue();
        String xPathToBeUpdated = xmlConfigNode.getPath();

        if (xPathToBeUpdated.indexOf("$$$$") > -1) {
            System.out.println("*****************5***************************");
            String xPath = xPathToBeUpdated.substring(0, xPathToBeUpdated.indexOf("/["));
            String xPathToUpdate = xPathToBeUpdated.substring(0, xPathToBeUpdated.indexOf("/["));

            Map<String, Integer> hashMap = this.getNodeCountValue(xPathToBeUpdated);

            for (int i = 1; i <= hashMap.get("$$"); i++) {
                System.out.println("i ************" + i);

                for (int j = 1; j <= hashMap.get("$$$"); j++) {
                    System.out.println("j ************" + j);

                    for (int k = 1; k <= hashMap.get("$$$$"); k++) {

                        value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate, value)
                            + nodeValueToGenerate.getSuffix();

                        xPath = xPath.replace("$$$$", String.valueOf(k));
                        xPath = xPath.replace("$$$", String.valueOf(j));
                        xPath = xPath.replace("$$", String.valueOf(i));

                        updatedDocument = xmlUtil.updateDocument(updatedDocument, xPath, value);
                        System.out.println("expression=====================================" + xPath);
                        System.out.println("newValue=====================================" + value);
                        xPath = xPathToUpdate;
                    }
                    value = "";
                }
            }
        } else if (xPathToBeUpdated.indexOf("$$$") > -1) {

            String xPath = xPathToBeUpdated.substring(0, xPathToBeUpdated.indexOf("/["));
            String xPathToUpdate = xPathToBeUpdated.substring(0, xPathToBeUpdated.indexOf("/["));
            Map<String, Integer> hashMap = this.getNodeCountValue(xPathToBeUpdated);

            for (int i = 1; i <= hashMap.get("$$"); i++) {
                System.out.println("i ************" + i);

                for (int j = 1; j <= hashMap.get("$$$"); j++) {
                    System.out.println("j ************" + j);

                    value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate, value)
                        + nodeValueToGenerate.getSuffix();

                    xPath = xPath.replace("$$$", String.valueOf(j));
                    xPath = xPath.replace("$$", String.valueOf(i));

                    updatedDocument = xmlUtil.updateDocument(updatedDocument, xPath, value);
                    System.out.println("expression=====================================" + xPath);
                    System.out.println("newValue=====================================" + value);
                    xPath = xPathToUpdate;
                }
                value = "";
            }
        } else if (xPathToBeUpdated.indexOf("$$") > -1) {

            String xPath = xPathToBeUpdated.substring(0, xPathToBeUpdated.indexOf("/["));
            String xPathToUpdate = xPathToBeUpdated.substring(0, xPathToBeUpdated.indexOf("/["));
            Map<String, Integer> hashMap = this.getNodeCountValue(xPathToBeUpdated);

            for (int i = 1; i <= hashMap.get("$$"); i++) {

                value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate, value)
                    + nodeValueToGenerate.getSuffix();

                xPath = xPath.replace("$$", String.valueOf(i));

                System.out.println("expression=====================================" + xPath);
                System.out.println("newValue=====================================" + value);
                updatedDocument = xmlUtil.updateDocument(updatedDocument, xPath, value);
                xPath = xPathToUpdate;
            }
        } else {

            value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate, value)
            + nodeValueToGenerate.getSuffix();

            updatedDocument = xmlUtil.updateDocument(updatedDocument, xPathToBeUpdated, value);
        }

        return updatedDocument;
    }

    /**
     * Method to generate the value of different types
     *
     * @param nodeValueToGenerate
     * @param value
     * @return replaceValue
     */
    private String generateValue(NodeValue nodeValueToGenerate, String value) {

        String criteria = nodeValueToGenerate.getValueType();
        String format = nodeValueToGenerate.getFormat();
        int startRange = nodeValueToGenerate.getStartRange();
        int endRanage = nodeValueToGenerate.getEndRange();
        int days = nodeValueToGenerate.getDays();
        int minutes = nodeValueToGenerate.getMinutes();

        System.out.println("nodeValueToGenerate=======" + nodeValueToGenerate.toString());

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

                    StringBuilder numbersBuilder = this.stringSeperate(value);
                    Integer counter = Integer.valueOf(numbersBuilder.toString());
                    replaceValue = nodeValueToGenerate.getStaticString() + (++counter);
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
     * @return rootDocument
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

    /**
     * Method to update the document using the given node of different count
     *
     * @param rootDocument to be updated
     * @param nameOftheChildItem to be populated
     * @param numberOfItems to be created
     * @param nameOftheParentNode to which nodes to be appended
     * @return rootDocument
     */
    private Document updateXMLDocwithGivenChildItems(Document rootDocument, String nameOftheChildItem,
        int numberOfItems, String nameOftheParentNode) {

        NodeList lineItemChildListElement = rootDocument.getElementsByTagName(nameOftheChildItem);
        NodeList lineItemParentListElement = rootDocument.getElementsByTagName(nameOftheParentNode);
        Element newOrderItem = null;

        for(int i = 0; i < lineItemParentListElement.getLength(); i++) {

            for (int j = 1; j <= numberOfItems; j++) {
                Element orderItemElement = (Element) lineItemChildListElement.item(0);
                // Just append the Lineitems
                newOrderItem = (Element) orderItemElement.cloneNode(true);
                lineItemParentListElement.item(i).appendChild(newOrderItem);
            }
        }
        return rootDocument;
    }

    /**
     * Method to get key-value pair for node count
     *
     * @param path from where node count needs to be fetched
     * @return hashMap
     */
    public Map<String, Integer> getNodeCountValue(String path) {

        String subString = path.substring(path.indexOf("/[")+1, path.length());

        String nodeCount = subString.substring(1, subString.length() - 1);
        String[] keyValuePairs = nodeCount.split(",");
        Map<String, Integer> hashMap = new HashMap<>();

        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            hashMap.put(entry[0].trim(), Integer.valueOf(entry[1].trim()));
        }
        return hashMap;
    }

    /**
     * Method to fetch number out of given string
     *
     * @param value string
     * @return numbersBuilder
     */
    private StringBuilder stringSeperate(String value) {

        StringBuilder numbersBuilder = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (Character.isDigit(ch)) {
                numbersBuilder.append(ch);
            }
        }
        return numbersBuilder;
    }

}
