package com.ucbos.performance.generators;

import com.ucbos.performance.config.YmlConfigReader;
import com.ucbos.performance.models.NodeValue;
import com.ucbos.performance.models.YmlNode;
import com.ucbos.utils.DataGeneratorUtil;
import com.ucbos.utils.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

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
        String sampleFileName = YmlConfigReader.getBulkLoadConfig().getSamplefile();
        int nummberOfLineItems = YmlConfigReader.getBulkLoadConfig().getNumberofchildnodes();

        LOGGER.log(Level.INFO, "--------------Started----------------" + sampleFileName);
        System.out.println("sampleFilePath" + sampleFileName);
        long tStart = System.currentTimeMillis();
        IntStream.range(0, YmlConfigReader.getBulkLoadConfig().getNumberoffiles()).forEach(counter -> {
            try {
                String newFilePath = YmlConfigReader.getBulkLoadConfig().getResultsfolderwithpath() + (counter + 1) + sampleFileName;

                InputStream stream = this.getClass().getClassLoader().getResourceAsStream(sampleFileName);
                XMLUtil xmlUtil = new XMLUtil(stream);
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
     * Based on the YAML configuration , given xpaths will be updated with the configured values
     */
    private Document fillTheMappedValuesYml(Document document, XMLUtil xmlUtil, int numberOfChildLineItems)
            throws Exception {
        System.out.println("Fill the Mapped Values");

        String expression = "";

        document = updateXMLDocwithGivenChildItems(document, "LineItem", numberOfChildLineItems);
        Document updatedDocument = document;
        for (int i = 1; i <= numberOfChildLineItems; i++) {
            System.out.println("i ************" + i);

            for (YmlNode xmlConfigNode : YmlConfigReader.getXmlNodesToUpdate()) {
                System.out.println(xmlConfigNode);
                //For each of the node , we will have Name , Path , Value obj
                //First generate/calculate the with given values
                NodeValue nodeValueToGenerate = xmlConfigNode.getValue();
                System.out.println("Geneating value " + nodeValueToGenerate.getPrefix() + nodeValueToGenerate.getGeneratedvalue() + nodeValueToGenerate.getSuffix());

                String value = nodeValueToGenerate.getPrefix() + generateValue(nodeValueToGenerate) + nodeValueToGenerate.getSuffix();

                //we got the value we wanted to be updated with
                //just update the
                System.out.println("**********************Update" + xmlConfigNode.getPath() + "Value " + value);
                String xPath = xmlConfigNode.getPath();
                if (xPath.indexOf("$$") > -1) {
                    xPath = xPath.replace("$$", String.valueOf(i));
                }
                updatedDocument = xmlUtil.updateDocument(updatedDocument, xPath, value);
            }


        }

        return updatedDocument;

    }

    private String generateValue(NodeValue nodeValueToGenerate) {
        //private String generateValue(String generateValuesBasedonString,String format,int startRange , int endRanage) {
        String criteria = nodeValueToGenerate.getGeneratedvalue();
        String format = nodeValueToGenerate.getFormat();
        int startRange = nodeValueToGenerate.getStartrange();
        int endRanage = nodeValueToGenerate.getEndrange();
        int adddays = nodeValueToGenerate.getAdddays();
        String returnGeneratedStr = "0";
        switch (criteria) {
            case "randomnumber": {
                returnGeneratedStr = String.valueOf(DataGeneratorUtil.getRandomNumberNumber(1000, 9999));
                break;
            }
            case "randomstring": {
                returnGeneratedStr = DataGeneratorUtil.generateRandomString();
                break;
            }
            case "number": {
                returnGeneratedStr = String.valueOf(DataGeneratorUtil.getRandomNumberNumber(startRange, endRanage));
                break;
            }
            case "date": {
                if (adddays >= 0) {
                    returnGeneratedStr = DataGeneratorUtil.getFutureDate(format, adddays);
                } else {
                    returnGeneratedStr = DataGeneratorUtil.getDateFormat(format);
                }
                break;
            }

            case "randomfromlist": {
                returnGeneratedStr = DataGeneratorUtil.getRandomItemNumberFromtheList(Arrays.asList(nodeValueToGenerate.getList().split(",", -1)));
                break;
            }
        }
        return returnGeneratedStr;
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


    private Document updateXMLDocwithGivenChildItems(Document rootDocument, String nameOftheChildItem, int numberOfItems) {

        NodeList lineItemListElement = rootDocument.getElementsByTagName(nameOftheChildItem);
        Node parentNode = lineItemListElement.item(0).getParentNode();
        //Node distributionOrder = rootDocument.getElementsByTagName("DistributionOrder").item(0);
        Element newOrderItem = null;
        for (int i = 1; i < numberOfItems; i++) {
            Element orderItemElement = (Element) lineItemListElement.item(0);
            //Just append the Lineitems
            newOrderItem = (Element) orderItemElement.cloneNode(true);
            parentNode.appendChild(newOrderItem);
        }

        return rootDocument;
    }

}
