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

@SuppressWarnings({"unused" })
public class DataGeneratorUtil {

    private static Logger LOGGER = Logger.getLogger("OrderDataGenerator.class.getName()");
    private static DecimalFormat decimalFormate = new DecimalFormat("#.##");

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    public static String getRandomItemNumberFromtheList(List<String> list) {

        Random r = new Random();
        String randomNum = list.get(r.nextInt(list.size()));

        return randomNum;
    }

    public static long getRandomNumber(int startRanage, int endRange) {
        long randomNum = ThreadLocalRandom.current().nextLong(Integer.valueOf(startRanage),
            Integer.valueOf(endRange) + 1);

        return randomNum;
    }

    public static String getRandomDecimalNumber(int min, int max) {
        double randomDeciNum = Math.random() * (max - min + 1) + min;
        decimalFormate.setRoundingMode(RoundingMode.UP);

        return decimalFormate.format(randomDeciNum);
    }

    public static String getDateFormat(String dateFormat) {

        ZonedDateTime currentDate = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        return currentDate.format(formatter);
    }

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

    public static String addMinutesToDate(String format, long numberOfMinutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zoneDateTimeAddMinutes = zonedDateTime.plusMinutes(numberOfMinutes);

        return zoneDateTimeAddMinutes.format(formatter);
    }

    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
            .current()
            .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public static String getRandomBooleanFromtheList(List<String> list) {
        Random r = new Random();
        String randomBoolean = list.get(r.nextInt(list.size()));

        return randomBoolean;
    }
}