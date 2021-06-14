package com.ucbos.performance.models;

public class NodeValue {

    private String prefix;
    private String suffix;
    private String generatedvalue;

    public String getPrefix() { if(prefix==null) return "" ; return prefix; }
    public void setPrefix(String value) { this.prefix = value; }

    public String getSuffix() { if(suffix==null) return "";return suffix;}
    public void setSuffix(String value) { this.suffix = value; }


    public String getGeneratedvalue() {
        if(generatedvalue==null) return "";
        return generatedvalue;
    }

    public void setGeneratedvalue(String generatedvalue) {
        this.generatedvalue = generatedvalue;
    }
}
