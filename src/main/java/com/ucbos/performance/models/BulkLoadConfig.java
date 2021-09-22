package com.ucbos.performance.models;

/**
 * Entity class for BulkLoadConfig
 */
public class BulkLoadConfig {

    private String sampleFile;
    private int numberOfFiles;
    private String resultsFolderWithPath;
    private String childNode;
    private int numberOfChildNodes;
    private int numberOfSubChildNodes;
    private int numberOfMainNodes;

    /**
     * return folderPath
     */
    public String getResultsFolderWithPath() {
        return resultsFolderWithPath;
    }

    /**
     * return samplefile name
     */
    public String getSampleFile() {
        return sampleFile;
    }

    /**
     * return number of files
     */
    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    /**
     * return childnode
     */
    public String getChildNode() {
        return childNode;
    }

    /**
     * return number of childnodes
     */
    public int getNumberOfChildNodes() {
        return numberOfChildNodes;
    }

    /**
     * return number of subchildnodes
     */
    public int getNumberOfSubChildNodes() {
        return numberOfSubChildNodes;
    }

    /**
     * return number of main nodes
     */
    public int getNumberOfMainNodes() {
        return numberOfMainNodes;
    }

    /**
     * @param value to be set for resultsFolderWithPath
     */
    public void setResultsFolderWithPath(String value) {
        this.resultsFolderWithPath = value;
    }

    /**
     * @param value to be set for sampleFile
     */
    public void setSampleFile(String value) {
        this.sampleFile = value;
    }

    /**
     * @param value to be set for numberOfFiles
     */
    public void setNumberOfFiles(int value) {
        this.numberOfFiles = value;
    }

    /**
     * @param value to be set for childNode
     */
    public void setChildNode(String value) {
        this.childNode = value;
    }

    /**
     * @param value to be set for numberOfChildNodes
     */
    public void setNumberOfChildNodes(int value) {
        this.numberOfChildNodes = value;
    }

    /**
     * @param value to be set for numberOfSubChildNodes
     */
    public void setNumberOfSubChildNodes(int numberOfSubChildNodes) {
        this.numberOfSubChildNodes = numberOfSubChildNodes;
    }

    /**
     * @param value to be set for numberOfMainNodes
     */
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
