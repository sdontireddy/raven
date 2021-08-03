package com.ucbos.performance.models;

/**
 * Entity class for Yml node
 *
 */
public class YmlNode {

    private String name;
    private String path;
    private NodeValue value;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String value) {
        this.path = value;
    }

    public NodeValue getValue() {
        return value;
    }

    public void setValue(NodeValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "YmlNode [name=" + name + ", path=" + path + ", value=" + value + "]";
    }
}
