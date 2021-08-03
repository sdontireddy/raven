package com.ucbos.performance.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;

import com.ucbos.performance.config.YmlConfigReader;

/**
 * Class to check given file present or not and count of file generated match the given number of files
 * 
 * @author Rudreshkumar
 *
 */
public class FileCreationCheck {

    @Value("${loadtesting.pathconstants.genericpath}")
    private String genericPath;

    public void filePresenceAndCountCheckTest(String filePath, String fileName) {

        assertTrue(new File(filePath).exists());

        int count = 0;
        File[] listOfFiles = (new File(genericPath)).listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            System.out.println(listOfFiles[i].getName());
            if (listOfFiles[i].getName().endsWith(fileName)) {
                count++;
            }
        }
        System.out.println(count);
        System.out.println(YmlConfigReader.getBulkLoadConfig().getNumberOfFiles());
        assertTrue(count == YmlConfigReader.getBulkLoadConfig().getNumberOfFiles());
    }
}