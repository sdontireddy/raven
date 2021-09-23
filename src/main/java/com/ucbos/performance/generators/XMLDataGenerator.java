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
 * Generates bulk of data files with given set of configurations defined in the corresponding yaml file
 *
 * @author sdontireddy
 */
public class XMLDataGenerator {

    private static Logger LOGGER = Logger.getLogger("XMLDataGenerator.class.getName()");
    private String previousId = "";

    /**
     * Method to generate files
     *
     * @return true if files generate else false
     * @throws Exception exception to be thrown on failure
     */
    public boolean generateOrderDataFiles() throws Exception {

        boolean returnFlag;
        String sampleFileName = YmlConfigReader.getBulkLoadConfig().getSampleFile();
        LOGGER.log(Level.INFO, "--------------Started----------------" + sampleFileName);
        long startTimeInMillis = System.currentTimeMillis();

        // Iterate the yml file so to produce given number of files with given nodes updated.
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

        long endTimeInMillis = System.currentTimeMillis();
        long totalTimeTaken = endTimeInMillis - startTimeInMillis;
        double elapsedSeconds = totalTimeTaken / 1000.0;
        returnFlag = true;
        LOGGER.log(Level.INFO, "--------------Completed : Time Elapsed----------------" + elapsedSeconds);

        return returnFlag;
    }

    /**
     * Based on the YAML configuration , given xpaths will be updated with the configured values
     */
    private Document fillTheMappedValuesYml(Document document, XMLUtil xmlUtil)
        throws Exception {

        LOGGER.log(Level.INFO,"Fill the Mapped Values");

        String childNode = YmlConfigReader.getBulkLoadConfig().getChildNode();
        childNode = childNode.substring(1, childNode.length()-1);
        List<String> childNodeList = Arrays.asList(childNode.split(","));

        // Update the document with given count of the child nodes.
        for (String childNodeDetail : childNodeList) {

            String parentNode = childNodeDetail.substring(0, childNodeDetail.indexOf('='));
            String childNodeName = childNodeDetail.substring(childNodeDetail.indexOf('=')+1,
                childNodeDetail.indexOf("=", childNodeDetail.indexOf('=')+1));
            String childNodeCount = childNodeDetail.substring(
                childNodeDetail.indexOf("=", childNodeDetail.indexOf('=')+1)+1, childNodeDetail.length());
            document = updateXMLDocwithGivenChildItems(document, childNodeName, Integer.parseInt(childNodeCount),
                parentNode);
            }

        // Update the all the xml nodes with given value-type
        Document updatedDocument = document;
        for (YmlNode xmlConfigNode : YmlConfigReader.getXmlNodesToUpdate()) {

            updatedDocument = this.updateNodesWithGeneratedValue(updatedDocument, xmlConfigNode,
                xmlUtil);
        }
        LOGGER.log(Level.INFO,"Filled the Mapped Values");

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
        LOGGER.log(Level.INFO,"Update nodes with generated value");

        if (xPathToBeUpdated.indexOf("$$$$") > -1) {

            String xPath = xPathToBeUpdated.substring(0, xPathToBeUpdated.indexOf("/["));
            String xPathToUpdate = xPathToBeUpdated.substring(0, xPathToBeUpdated.indexOf("/["));
            Map<String, Integer> hashMap = this.getNodeCountValue(xPathToBeUpdated);

            for (int i = 1; i <= hashMap.get("$$"); i++) {

                for (int j = 1; j <= hashMap.get("$$$"); j++) {

                    for (int k = 1; k <= hashMap.get("$$$$"); k++) {

                        value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate, value)
                            + nodeValueToGenerate.getSuffix();

                        xPath = xPath.replace("$$$$", String.valueOf(k));
                        xPath = xPath.replace("$$$", String.valueOf(j));
                        xPath = xPath.replace("$$", String.valueOf(i));

                        updatedDocument = xmlUtil.updateDocument(updatedDocument, xPath, value);
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

                for (int j = 1; j <= hashMap.get("$$$"); j++) {

                    value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate, value)
                        + nodeValueToGenerate.getSuffix();

                    xPath = xPath.replace("$$$", String.valueOf(j));
                    xPath = xPath.replace("$$", String.valueOf(i));

                    updatedDocument = xmlUtil.updateDocument(updatedDocument, xPath, value);
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

                updatedDocument = xmlUtil.updateDocument(updatedDocument, xPath, value);
                xPath = xPathToUpdate;
            }
        } else {

            value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate, value)
            + nodeValueToGenerate.getSuffix();

            updatedDocument = xmlUtil.updateDocument(updatedDocument, xPathToBeUpdated, value);
        }
        LOGGER.log(Level.INFO,"Updated nodes with generated value");

        return updatedDocument;
    }

    /**
     * Method to generate the value of different types
     *
     * @param nodeValueToGenerate
     * @param value old value
     * @return replaceValue which needs to be updated
     */
    private String generateValue(NodeValue nodeValueToGenerate, String value) {

        String criteria = nodeValueToGenerate.getValueType();
        String format = nodeValueToGenerate.getFormat();
        int startRange = nodeValueToGenerate.getStartRange();
        int endRanage = nodeValueToGenerate.getEndRange();
        int days = nodeValueToGenerate.getDays();
        int minutes = nodeValueToGenerate.getMinutes();
        String stringLength = nodeValueToGenerate.getStringLength();
        String staticString = nodeValueToGenerate.getStaticString();
        String replaceValue = "";

        LOGGER.log(Level.INFO,"Generate value for given value-type");

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
                break;
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
                break;
            case LoadTestConstants.STRING_COUNTER:
                if (!value.isEmpty()) {

                    StringBuilder numbersBuilder = this.stringSeperate(value);
                    Integer counter = Integer.valueOf(numbersBuilder.toString());
                    replaceValue = nodeValueToGenerate.getStaticString() + (++counter);
                } else {
                    replaceValue = nodeValueToGenerate.getStaticString() + nodeValueToGenerate.getStepValue();
                }
                break;
            case LoadTestConstants.SEQUENCE_COUNTER:
                if (!staticString.isEmpty()) {
                    String stringFormat = "%0" + stringLength + "d";
                    if (!previousId.isEmpty()) {
                        int counter = Integer.parseInt(previousId.substring(previousId.indexOf("_") + 1,
                            previousId.length()));
                        previousId = staticString +
                            String.format(stringFormat, (++counter));
                        replaceValue = previousId;
                    } else {
                        previousId = staticString +
                            String.format(stringFormat, startRange);
                        replaceValue = previousId;
                    }
                } else {
                    if (!previousId.isEmpty()) {
                        int counter = Integer.valueOf(previousId);
                        replaceValue = String.valueOf((++counter));
                    } else {
                        previousId = String.valueOf(startRange);
                        replaceValue = previousId;
                    }
                }
                break;
        }

        LOGGER.log(Level.INFO,"Generated value for given value-type");
        return replaceValue;
    }

    /**
     * Create number of OrderItems
     *
     * @param rootDocument to be updated
     * @param numberOfItems to be updated
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

        LOGGER.log(Level.INFO,"Update document with given child nodes with given count");

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
        LOGGER.log(Level.INFO,"Updated document with given child nodes with given count");
        return rootDocument;
    }

    /**
     * Method to get key-value pair for node count
     *
     * @param path from where node count needs to be fetched
     * @return hashMap
     */
    public Map<String, Integer> getNodeCountValue(String path) {

        LOGGER.log(Level.INFO,"Get node-name and count to be updated");

        String subString = path.substring(path.indexOf("/[")+1, path.length());

        String nodeCount = subString.substring(1, subString.length() - 1);
        String[] keyValuePairs = nodeCount.split(",");
        Map<String, Integer> hashMap = new HashMap<>();

        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            hashMap.put(entry[0].trim(), Integer.valueOf(entry[1].trim()));
        }
        LOGGER.log(Level.INFO,"Get node-name and count to be updated");
        return hashMap;
    }

    /**
     * Method to fetch number out of given string
     *
     * @param value string
     * @return numbersBuilder
     */
    private StringBuilder stringSeperate(String value) {

        LOGGER.log(Level.INFO,"Seperate int value from string");

        StringBuilder numbersBuilder = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (Character.isDigit(ch)) {
                numbersBuilder.append(ch);
            }
        }
        LOGGER.log(Level.INFO,"Seperated int value from string");
        return numbersBuilder;
    }

}
