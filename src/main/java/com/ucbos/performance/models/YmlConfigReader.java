package com.ucbos.performance.models;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class YmlConfigReader {

    public static void main(String[] args) throws Exception {
        readYamlConfigurationAssumingListinSameDocument();
    }

    public static List<YmlConfigModel> readYamlConfigurationAssumingListinSameDocument() throws Exception{
        Constructor constructor = new Constructor(YamlModel.class);
        TypeDescription configDesc = new TypeDescription(YamlModel.class);
        configDesc.putListPropertyType("xmlnodes", YmlConfigModel.class);
        constructor.addTypeDescription(configDesc);
        Yaml yaml = new Yaml(constructor);
        InputStream inputStream = YmlConfigReader.class
                .getClassLoader()
                .getResourceAsStream("mapping.yaml");
        YamlModel xmlNodeconfig = (YamlModel) yaml.load(inputStream);

        int count = 0;
        for (YmlConfigModel object : xmlNodeconfig.getXmlnodes()) {
            count++;
            System.out.println(object);
            assertTrue(object instanceof YmlConfigModel);
        }

        return xmlNodeconfig.getXmlnodes();

    }

    public static void readYamlConfigurationAssumingMultipleDocuments() throws Exception{
            Yaml yaml = new Yaml(new Constructor(YmlConfigModel.class));
            InputStream inputStream = YmlConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("mapping-multi-documents.yaml");
            int count = 0;
            for (Object object : yaml.loadAll(inputStream)) {
                count++;
                System.out.println(object);
                assertTrue(object instanceof YmlConfigModel);
            }

        }
    }



