package com.ucbos.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Class for generating different types of valueType
 */
@SuppressWarnings({"unused" })
public class DataGeneratorUtil {

    private static Logger LOGGER = Logger.getLogger("OrderDataGenerator.class.getName()");
    private static DecimalFormat decimalFormate = new DecimalFormat("#.##");

    /**
     * Method to generate random string
     */
    public static String generateRandomString() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    /**
     * Method to pick random item from list
     */
    public static String getRandomItemNumberFromtheList(List<String> list) {

        Random r = new Random();
        String randomNum = list.get(r.nextInt(list.size()));
        System.out.println("randomNum" + randomNum);
        return randomNum;

    }

    /**
     * Method to fetch random number from given range
     */
    public static long getRandomNumber(int startRanage, int endRange) {
        long randomNum = ThreadLocalRandom.current().nextLong(Integer.valueOf(startRanage),
            Integer.valueOf(endRange) + 1);

        return randomNum;
    }

    /**
     * Method to fetch random decimal number from given range
     */
    public static String getRandomDecimalNumber(int min, int max) {
        double randomDeciNum = Math.random() * (max - min + 1) + min;
        decimalFormate.setRoundingMode(RoundingMode.UP);

        return decimalFormate.format(randomDeciNum);
    }

    /**
     * Method to convert date formate
     */
    public static String getDateFormat(String dateFormat) {

        ZonedDateTime currentDate = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        return currentDate.format(formatter);
    }

    /**
     * Method to modify date
     */
    public static String getModifiedDaysDate(String format, int days, int minutes) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zoneDateTimeModified;

        if (days != 0) {
            zoneDateTimeModified = zonedDateTime.plusDays(days);
        } else {
            zoneDateTimeModified = zonedDateTime.plusMinutes(minutes);
        }
        return zoneDateTimeModified.format(formatter);
    }

    /**
     * Method to add minutes to date
     */
    public static String addMinutesToDate(String format, long numberOfMinutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zoneDateTimeAddMinutes = zonedDateTime.plusMinutes(numberOfMinutes);
        return zoneDateTimeAddMinutes.format(formatter);
    }

    /**
     * Method to fetch the epoch days
     */
    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
            .current()
            .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    /**
     * Method to fetch random boolean value from list
     */
    public static String getRandomBooleanFromtheList(List<String> list) {
        Random r = new Random();
        String randomBoolean = list.get(r.nextInt(list.size()));
        return randomBoolean;
    }

}
