package com.ucbos.performance.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import com.ucbos.performance.config.YmlConfigReader;

/**
 * Class to check given file present or not and count of file generated match the given number of files
 * 
 * @author Rudreshkumar
 *
 */
public class FileCreationCheck {

    public void filePresenceAndCountCheckTest(String filePath, String genericPath, String fileName) {

        assertTrue(new File(filePath).exists());

        int count = 0;
        File[] listOfFiles = (new File(genericPath)).listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].getName().endsWith(fileName)) {
                count++;
            }
        }
        assertTrue(count == YmlConfigReader.getBulkLoadConfig().getNumberOfFiles());
    }

}
