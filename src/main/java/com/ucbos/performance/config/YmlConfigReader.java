package com.ucbos.performance.config;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.ucbos.performance.models.BulkLoadConfig;
import com.ucbos.performance.models.YamlDocumentModel;
import com.ucbos.performance.models.YmlNode;


public class YmlConfigReader {
    private static List<YmlNode> xmlNodesToUpdate;
    private static BulkLoadConfig bulkLoadConfig;

    public static void main(String[] args) throws Exception {
        readYamlConfigurationAssumingListinSameDocument("mapping.yaml");
    }

    public static List<YmlNode> readYamlConfigurationAssumingListinSameDocument(String mappingFile) throws Exception{
        System.out.println("Loading Mapping file configured " + mappingFile);
        Constructor constructor = new Constructor(YamlDocumentModel.class);
        TypeDescription configDesc = new TypeDescription(YamlDocumentModel.class);
        configDesc.putListPropertyType("xmlnodes", YmlNode.class);
        constructor.addTypeDescription(configDesc);
        Yaml yaml = new Yaml(constructor);
        InputStream inputStream = YmlConfigReader.class
                .getClassLoader()
                .getResourceAsStream(mappingFile);
        YamlDocumentModel xmlNodeconfig = (YamlDocumentModel) yaml.load(inputStream);
        System.out.println("Begin generating data files using below Configurations");
        System.out.println(bulkLoadConfig);
        int count = 0;
        for (YmlNode object : xmlNodeconfig.getXmlnodes()) {
            count++;
            System.out.println(object);
            assertTrue(object instanceof YmlNode);
        }
        xmlNodesToUpdate = xmlNodeconfig.getXmlnodes();
        bulkLoadConfig = xmlNodeconfig.getBulkloadconfig();

        return xmlNodeconfig.getXmlnodes();
    }

    public static void readYamlConfigurationAssumingMultipleDocuments() throws Exception{
            Yaml yaml = new Yaml(new Constructor(YmlNode.class));
            InputStream inputStream = YmlConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("mapping-multi-documents_tobe_deleted.yaml");
            int count = 0;
            for (Object object : yaml.loadAll(inputStream)) {
                count++;
                System.out.println(object);
                assertTrue(object instanceof YmlNode);
            }
        }

    public static List<YmlNode> getXmlNodesToUpdate() {
        return xmlNodesToUpdate;
    }

    public static void setXmlNodesToUpdate(List<YmlNode> xmlNodesToUpdate) {
        YmlConfigReader.xmlNodesToUpdate = xmlNodesToUpdate;
    }

    public static BulkLoadConfig getBulkLoadConfig() {
        return bulkLoadConfig;
    }

    public static void setBulkLoadConfig(BulkLoadConfig bulkLoadConfig) {
        YmlConfigReader.bulkLoadConfig = bulkLoadConfig;
    }
}