package com.ucbos.performance.models;

import java.util.List;

/**
 * Entity class for defining yaml document model
 *
 */
public class YamlDocumentModel {

    private BulkLoadConfig bulkloadconfig;
    private List<YmlNode> xmlnodes;

    public BulkLoadConfig getBulkloadconfig() {
        return bulkloadconfig;
    }

    public void setBulkloadconfig(BulkLoadConfig value) {
        this.bulkloadconfig = value;
    }

    public List<YmlNode> getXmlnodes() {
        return xmlnodes;
    }

    public void setXmlnodes(List<YmlNode> xmlnodes) {
        this.xmlnodes = xmlnodes;
    }
}