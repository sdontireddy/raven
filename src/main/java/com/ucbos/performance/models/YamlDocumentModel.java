package com.ucbos.performance.models;

import java.util.List;

/**
 * Entity class for defining yaml document model
 *
 */
public class YamlDocumentModel {

    private BulkLoadConfig bulkloadconfig;
    private List<YmlNode> xmlnodes;

    /**
     * return bulkloadconfig
     */
    public BulkLoadConfig getBulkloadconfig() {
        return bulkloadconfig;
    }

    /**
     * return xmlnodes
     */
    public List<YmlNode> getXmlnodes() {
        return xmlnodes;
    }

    /**
     * @param value to be set
     */
    public void setBulkloadconfig(BulkLoadConfig value) {
        this.bulkloadconfig = value;
    }

    /**
     * @param xmlnodes to be set
     */
    public void setXmlnodes(List<YmlNode> xmlnodes) {
        this.xmlnodes = xmlnodes;
    }
}
