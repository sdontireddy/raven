package com.ucbos.performance.generators;

import java.util.HashMap;
import java.util.Map;

public class Test {
   
    
    public static void main(String args[]) {
        Integer primaryKeyCount = 0;
        String replaceValue=""; 
        String StepValue = "1";
        for(int i=0;i<5;i++) {
        if(primaryKeyCount != 0) {

            Integer count = ++primaryKeyCount;
            replaceValue = count.toString();
        }
        else {
            replaceValue = StepValue;
            primaryKeyCount = Integer.valueOf(replaceValue);
        }
        System.out.println("primaryKeyCount=========="+primaryKeyCount);
        System.out.println("replaceValue=========="+replaceValue);
        }
            
    }
}
