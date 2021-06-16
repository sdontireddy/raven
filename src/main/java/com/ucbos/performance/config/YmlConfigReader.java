package com.ucbos.performance.config;

import java.io.InputStream;
import java.util.List;

import com.ucbos.performance.models.YamlDocumentModel;
import com.ucbos.performance.models.YmlNode;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class YmlConfigReader {

    public static void main(String[] args) throws Exception {
        readYamlConfigurationAssumingListinSameDocument();
    }

    public static List<YmlNode> readYamlConfigurationAssumingListinSameDocument() throws Exception{
        Constructor constructor = new Constructor(YamlDocumentModel.class);
        TypeDescription configDesc = new TypeDescription(YamlDocumentModel.class);
        configDesc.putListPropertyType("xmlnodes", YmlNode.class);
        constructor.addTypeDescription(configDesc);
        Yaml yaml = new Yaml(constructor);
        InputStream inputStream = YmlConfigReader.class
                .getClassLoader()
                .getResourceAsStream("mapping.yaml");
        YamlDocumentModel xmlNodeconfig = (YamlDocumentModel) yaml.load(inputStream);

        int count = 0;
        for (YmlNode object : xmlNodeconfig.getXmlnodes()) {
            count++;
            System.out.println(object);
            assertTrue(object instanceof YmlNode);
        }

        return xmlNodeconfig.getXmlnodes();

    }

    public static void readYamlConfigurationAssumingMultipleDocuments() throws Exception{
            Yaml yaml = new Yaml(new Constructor(YmlNode.class));
            InputStream inputStream = YmlConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("mapping-multi-documents.yaml");
            int count = 0;
            for (Object object : yaml.loadAll(inputStream)) {
                count++;
                System.out.println(object);
                assertTrue(object instanceof YmlNode);
            }

        }
    }



