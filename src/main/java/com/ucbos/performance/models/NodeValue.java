package com.ucbos.performance.models;

/**
 * Entity Class for NodeValue
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

    /**
     * return prefix
     */
    public String getPrefix() {
        if (prefix == null)
            return "";
        return prefix;
    }

    /**
     * return suffix
     */
    public String getSuffix() {
        if (suffix == null)
            return "";
        return suffix;
    }

    /**
     * return valuetype
     */
    public String getValueType() {
        if (valueType == null)
            return "";
        return valueType;
    }

    /**
     * return format
     */
    public String getFormat() {
        if (valueType == null)
            return "";
        return format;
    }

    /**
     * return startrange
     */
    public int getStartRange() {
        return startRange;
    }

    /**
     * return endrange
     */
    public int getEndRange() {
        return endRange;
    }

    /**
     * return days
     */
    public int getDays() {
        return days;
    }

    /**
     * return list
     */
    public String getList() {
        if (list == null)
            return "";
        return list;
    }

    /**
     * return boolean list
     */
    public String getBooleanList() {
        if (booleanList == null)
            return "";
        return booleanList;
    }

    /**
     * return static value
     */
    public String getStaticValue() {
        if (staticValue == null)
            return "";
        return staticValue;
    }

    /**
     * return steptype
     */
    public String getStepType() {
        return stepType;
    }

    /**
     * return stepvalue
     */
    public String getStepValue() {
        return stepValue;
    }

    /**
     * return static string
     */
    public String getStaticString() {
        if (staticString == null)
            return "";
        return staticString;
    }

    /**
     * return minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @param value to be set for prefix
     */
    public void setPrefix(String value) {
        this.prefix = value;
    }

    /**
     * @param value to be set for suffix
     */
    public void setSuffix(String value) {
        this.suffix = value;
    }

    /**
     * @param valueType to be set
     */
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    /**
     * @param format to be set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @param startRange to be set
     */
    public void setStartRange(int startRange) {
        this.startRange = startRange;
    }

    /**
     * @param endRange to be set
     */
    public void setEndRange(int endRange) {
        this.endRange = endRange;
    }

    /**
     * @param days to be set
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * @param list to be set
     */
    public void setList(String list) {
        this.list = list;
    }

    /**
     * @param booleanList to be set
     */
    public void setBooleanList(String booleanList) {
        this.booleanList = booleanList;
    }

    /**
     * @param staticValue to be set
     */
    public void setStaticValue(String staticValue) {
        this.staticValue = staticValue;
    }

    /**
     * @param stepType to be set
     */
    public void setStepType(String stepType) {
        this.stepType = stepType;
    }

    /**
     * @param stepValue to be set
     */
    public void setStepValue(String stepValue) {
        this.stepValue = stepValue;
    }

    /**
     * @param staticString to be set
     */
    public void setStaticString(String staticString) {
        this.staticString = staticString;
    }

    /**
     * @param minutes to be set
     */
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
