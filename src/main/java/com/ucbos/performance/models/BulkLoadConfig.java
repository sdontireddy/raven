package com.ucbos.performance.models;

/**
 * Entity class for BulkLoadConfig
 *
 */
public class BulkLoadConfig {

    private String sampleFile;
    private int numberOfFiles;
    private String resultsFolderWithPath;
    private String childNode;
    private int numberOfChildNodes;
    private int numberOfSubChildNodes;
    private int numberOfMainNodes;

    public String getResultsFolderWithPath() {
        return resultsFolderWithPath;
    }

    public void setResultsFolderWithPath(String value) {
        this.resultsFolderWithPath = value;
    }

    public String getSampleFile() {
        return sampleFile;
    }

    public void setSampleFile(String value) {
        this.sampleFile = value;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int value) {
        this.numberOfFiles = value;
    }

    public String getChildNode() {
        return childNode;
    }

    public void setChildNode(String value) {
        this.childNode = value;
    }

    public int getNumberOfChildNodes() {
        return numberOfChildNodes;
    }

    public void setNumberOfChildNodes(int value) {
        this.numberOfChildNodes = value;
    }

    public int getNumberOfSubChildNodes() {
        return numberOfSubChildNodes;
    }

    public void setNumberOfSubChildNodes(int numberOfSubChildNodes) {
        this.numberOfSubChildNodes = numberOfSubChildNodes;
    }

    public int getNumberOfMainNodes() {
        return numberOfMainNodes;
    }

    public void setNumberOfMainNodes(int numberOfMainNodes) {
        this.numberOfMainNodes = numberOfMainNodes;
    }

    @Override
    public String toString() {
        return "BulkLoadConfig [sampleFile=" + sampleFile + ", numberOfFiles=" + numberOfFiles
            + ", resultsFolderWithPath=" + resultsFolderWithPath + ", childNode=" + childNode + ", numberOfChildNodes="
            + numberOfChildNodes + ", numberOfSubChildNodes=" + numberOfSubChildNodes + ", numberOfMainNodes="
            + numberOfMainNodes + "]";
    }
}
