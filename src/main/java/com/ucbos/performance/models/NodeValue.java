package com.ucbos.performance.models;

/**
 * Entity Class for Node Value Details
 *
 */
public class NodeValue {

    private String prefix;
    private String suffix;
    private String valuetype;
    private String format;
    private String startrange;
    private String endrange;
    private int adddays;
    private String list;
    private String booleanlist;
    private int minusdays;
    private String staticValue;
    private String stepType;
    private String stepValue;
    private String staticString;
    private long addMinutes;
    private long minusMinutes;
    
    @Override
    public String toString() {
        return "NodeValue [prefix=" + prefix + ", suffix=" + suffix + ", valuetype=" + valuetype + ", format=" + format
            + ", startrange=" + startrange + ", endrange=" + endrange + ", adddays=" + adddays + ", list=" + list
            + ", booleanlist=" + booleanlist + ", minusdays=" + minusdays + ", staticValue=" + staticValue
            + ", stepType=" + stepType + ", stepValue=" + stepValue + ", staticString=" + staticString + ", addMinutes="
            + addMinutes + ", minusMinutes=" + minusMinutes + "]";
    }

    public String getPrefix() {
        if (prefix == null)
            return "";
        return prefix;
    }

    public void setPrefix(String value) {
        this.prefix = value;
    }

    public String getSuffix() {
        if (suffix == null)
            return "";
        return suffix;
    }

    public void setSuffix(String value) {
        this.suffix = value;
    }

    public String getValuetype() {
        if (valuetype == null)
            return "";
        return valuetype;
    }

    public void setValuetype(String valuetype) {
        this.valuetype = valuetype;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStartrange() {
        return startrange;
    }

    public void setStartrange(String startrange) {
        this.startrange = startrange;
    }

    public String getEndrange() {
        return endrange;
    }

    public void setEndrange(String endrange) {
        this.endrange = endrange;
    }

    public int getAdddays() {
        return adddays;
    }

    public void setAdddays(int adddays) {
        this.adddays = adddays;
    }

    public String getList() {
        if (list == null)
            return "";
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getBooleanlist() {
        if (booleanlist == null)
            return "";
        return booleanlist;
    }

    public void setBooleanlist(String booleanlist) {
        this.booleanlist = booleanlist;
    }

    public int getMinusdays() {
        return minusdays;
    }

    public void setMinusdays(int minusdays) {
        this.minusdays = minusdays;
    }

    public String getStaticValue() {
        return staticValue;
    }

    public void setStaticValue(String staticValue) {
        this.staticValue = staticValue;
    }

    public String getStepType() {
        return stepType;
    }

    public void setStepType(String stepType) {
        this.stepType = stepType;
    }

    public String getStepValue() {
        return stepValue;
    }

    public void setStepValue(String stepValue) {
        this.stepValue = stepValue;
    }

    public String getStaticString() {
        return staticString;
    }

    public void setStaticString(String staticString) {
        this.staticString = staticString;
    }

    public long getAddMinutes() {
        return addMinutes;
    }

    public void setAddMinutes(long addMinutes) {
        this.addMinutes = addMinutes;
    }

    public long getMinusMinutes() {
        return minusMinutes;
    }

    public void setMinusMinutes(long minusMinutes) {
        this.minusMinutes = minusMinutes;
    }
}
