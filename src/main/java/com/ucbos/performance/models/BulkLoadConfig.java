package com.ucbos.performance.models;

import java.util.Objects;

public class BulkLoadConfig {

    private String samplefile;

    private int numberoffiles;
    private String resultsfolderwithpath;

    private String childnode;
    private int numberofchildnodes;

    public String getResultsfolderwithpath() { return resultsfolderwithpath; }
    public void setResultsfolderwithpath(String value) { this.resultsfolderwithpath = value; }

    public String getSamplefile() { return samplefile; }
    public void setSamplefile(String value) { this.samplefile = value; }

    public int getNumberoffiles() { return numberoffiles; }
    public void setNumberoffiles(int value) { this.numberoffiles = value; }

    public String getChildnode() { return childnode; }
    public void setChildnode(String value) { this.childnode = value; }

    public int getNumberofchildnodes() { return numberofchildnodes; }
    public void setNumberofchildnodes(int value) { this.numberofchildnodes = value; }


    @Override
    public String toString() {
        return "BulkLoadConfig{" +
                "samplefile='" + samplefile + '\'' +
                ", numberoffiles=" + numberoffiles +
                ", resultsfolderwithpath='" + resultsfolderwithpath + '\'' +
                ", childnode='" + childnode + '\'' +
                ", numberofchildnodes=" + numberofchildnodes +
                '}';
    }
}
