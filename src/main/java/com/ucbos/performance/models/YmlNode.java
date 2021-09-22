package com.ucbos.performance.models;

/**
 * Entity class for Yml node
 *
 */
public class YmlNode {

    private String name;
    private String path;
    private NodeValue value;

    /**
     * retrun name
     */
    public String getName() {
        return name;
    }

    /**
     * return path
     */
    public String getPath() {
        return path;
    }

    /**
     * return value
     */
    public NodeValue getValue() {
        return value;
    }

    /**
     * @param value to be set to name
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * @param value to be set to path
     */
    public void setPath(String value) {
        this.path = value;
    }

    /**
     * @param value to be set
     */
    public void setValue(NodeValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\nName: " + name + "\npath: " + path;
    }
}
