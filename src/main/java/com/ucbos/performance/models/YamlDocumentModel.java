package com.ucbos.performance.models;

import java.util.List;

public class YamlDocumentModel {


    private BulkLoadConfig bulkloadconfig;

    public BulkLoadConfig getBulkloadconfig() { return bulkloadconfig; }
    public void setBulkloadconfig(BulkLoadConfig value) { this.bulkloadconfig = value; }

    private List<YmlNode> xmlnodes;


    public List<YmlNode> getXmlnodes() {
        return xmlnodes;
    }

    public void setXmlnodes(List<YmlNode> xmlnodes) {
        this.xmlnodes = xmlnodes;
    }
}
