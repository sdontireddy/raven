package com.ucbos.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Utility to process and update the XML documents
 * 
 * @author sdontireddy
 *
 */
public class XMLUtil {

    private static Logger LOGGER = Logger.getLogger("XMLUtil.class.getName()");

    private DocumentBuilderFactory builderFactory;
    private DocumentBuilder builder;
    private Document document;

    public Document getDocument() {
        return document;
    }

    public Document getDocument(String filePath) throws SAXException, IOException {
        document = builder.parse(new File(filePath));

        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public static XMLUtil init(String filePath) throws ParserConfigurationException, SAXException, IOException {

        return new XMLUtil(filePath);
    }

    public XMLUtil() throws ParserConfigurationException {
        builderFactory = DocumentBuilderFactory.newInstance();
        builder = builderFactory.newDocumentBuilder();
    }

    public XMLUtil(String filePath) throws ParserConfigurationException, SAXException, IOException {
        builderFactory = DocumentBuilderFactory.newInstance();
        builder = builderFactory.newDocumentBuilder();
        URL url = this.getClass().getResource(filePath);

        File file = new File(filePath);

        document = builder.parse(new File(filePath));
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public XMLUtil(InputStream stream) throws ParserConfigurationException, SAXException, IOException {
        builderFactory = DocumentBuilderFactory.newInstance();
        builder = builderFactory.newDocumentBuilder();

        document = builder.parse(stream);
    }

    public Document updateDocument(Document document, String expression, String newValue) throws Exception {

        try {

            XPath xPath = XPathFactory.newInstance().newXPath();
            // String expression = "/OrderRequest/WebOrder/SiteID";
            Node node = (Node) xPath.evaluate(expression, document, XPathConstants.NODE);

            node.setTextContent(newValue);

        } catch (Exception e) {
            LOGGER.severe("Exception Updating the Document " + e.getMessage());
            throw e;
        }
        return document;
    }

    public Document updateDocument(Document document, String expression, long newValue) throws Exception {

        try {

            XPath xPath = XPathFactory.newInstance().newXPath();
            Node node = (Node) xPath.evaluate(expression, document, XPathConstants.NODE);

            node.setTextContent(String.valueOf(newValue));

        } catch (Exception e) {
            LOGGER.severe("Exception Updating the Document " + e.getMessage());
            throw e;
        }
        return document;
    }

    public void createXMLFile(String filePath, Document document)
        throws TransformerFactoryConfigurationError, TransformerException {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new File(filePath)));
        } catch (Exception e) {
            LOGGER.severe("Exception creating the XML file  " + filePath);
            throw e;
        }
    }

    public String readValue(Document document, String expression) throws XPathExpressionException {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        XPathExpression expr = xpath.compile(expression);
        return expr.evaluate(document).toString();
    }

    public static Object unmarshal(String fileName, Class className) throws JAXBException {

        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(className);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object obj = (Object) jaxbUnmarshaller.unmarshal(file);
            System.out.println(obj);

            return obj;
        } catch (JAXBException e) {
            throw e;
        }
    }

    public static boolean marshall(Class className, Object obj, String fileName) throws JAXBException {
        JAXBContext jaxbContext;
        File file = new File(fileName);
        jaxbContext = JAXBContext.newInstance(className);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(obj, file);
        return true;
    }

    public String convertDocumentToString(Document doc) throws Exception {

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        String output = "";
        try {
            transformer = tf.newTransformer();

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            output = writer.getBuffer().toString().replaceAll("\n|\r", "");
        } catch (TransformerException e) {
            throw new Exception();
        }
        return output;
    }

    public Document appendNewNode(Document rootdoc) {
        NodeList nodes = rootdoc.getElementsByTagName("OrderItemList");

        Element orderListElement = (Element) nodes.item(0);
        NodeList oderList = orderListElement.getElementsByTagName("OrderItem");
        Element newOrderItem = null;
        for (int i = 0; i < oderList.getLength(); i++) {
            Element orderItemElement = (Element) oderList.item(i);
            newOrderItem = (Element) orderItemElement.cloneNode(true);
        }
        orderListElement.appendChild(newOrderItem);
        oderList = orderListElement.getElementsByTagName("OrderItem");

        try {
            System.out.println(convertDocumentToString(rootdoc));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}