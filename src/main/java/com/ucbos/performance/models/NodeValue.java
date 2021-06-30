package com.ucbos.performance.models;



public class NodeValue {

    private String prefix;
    private String suffix;
    private String generatedvalue;
    private String format;
    private int startrange;
    private int endrange;
    private int adddays;


    public String getList() {
        if(list==null) return  "" ;
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    private String list ;

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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getEndrange() {
        if(endrange == 0) return 9999;
        return endrange;
    }

    public void setEndrange(int endrange) {
        this.endrange = endrange;
    }

    public int getStartrange() {
        return startrange;
    }

    public void setStartrange(int startrange) {
        this.startrange = startrange;
    }

    public int getAdddays() {
        return adddays;
    }

    public void setAdddays(int adddays) {
        this.adddays = adddays;
    }
}
