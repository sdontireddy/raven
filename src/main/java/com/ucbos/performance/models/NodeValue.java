package com.ucbos.performance.models;

/**
 * Entity Class for Node Value Details
 *
 */
public class NodeValue {

    private String prefix;
    private String suffix;
    private String valueType;
    private String format;
    private int startRange;
    private int endRange;
    private int days;
    private String list;
    private String booleanList;
    private String staticValue;
    private String stepType;
    private String stepValue;
    private String staticString;
    private int minutes;

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

    public String getValueType() {
        if (valueType == null)
            return "";
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getFormat() {
        if (valueType == null)
            return "";
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getStartRange() {
        return startRange;
    }

    public void setStartRange(int startRange) {
        this.startRange = startRange;
    }

    public int getEndRange() {
        return endRange;
    }

    public void setEndRange(int endRange) {
        this.endRange = endRange;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getList() {
        if (list == null)
            return "";
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getBooleanList() {
        if (booleanList == null)
            return "";
        return booleanList;
    }

    public void setBooleanList(String booleanList) {
        this.booleanList = booleanList;
    }

    public String getStaticValue() {
        if (staticValue == null)
            return "";
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
        if (staticString == null)
            return "";
        return staticString;
    }

    public void setStaticString(String staticString) {
        this.staticString = staticString;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "NodeValue [prefix=" + prefix + ", suffix=" + suffix + ", valueType=" + valueType + ", format=" + format
            + ", startRange=" + startRange + ", endRange=" + endRange + ", days=" + days + ", list=" + list
            + ", booleanList=" + booleanList + ", staticValue=" + staticValue + ", stepType=" + stepType
            + ", stepValue=" + stepValue + ", staticString=" + staticString + ", minutes=" + minutes + "]";
    }
}
