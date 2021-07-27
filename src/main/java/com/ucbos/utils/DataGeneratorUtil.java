package com.ucbos.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
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
        System.out.println("randomNum" + randomNum);
        return randomNum;

    }

    public static long getRandomNumber(String startRanage, String endRange) {
        long randomNum = ThreadLocalRandom.current().nextLong(Integer.valueOf(startRanage),
            Integer.valueOf(endRange) + 1);

        return randomNum;
    }

    public static String getRandomDecimalNumber(String min, String max) {
        double randomDeciNum = Math.random() * (Integer.valueOf(max) - Integer.valueOf(min) + 1) + Integer.valueOf(min);
        decimalFormate.setRoundingMode(RoundingMode.UP);

        return decimalFormate.format(randomDeciNum);
    }

    public static String getDateFormat(String dateFormat) {

        ZonedDateTime currentDate = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        return currentDate.format(formatter);
    }

    public static String getFutureDate(String format, int numberOfDaysAhead) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        ZonedDateTime zonedDateTime = ZonedDateTime.now().plusDays(numberOfDaysAhead);

        return zonedDateTime.format(formatter);
    }

    public static String getPreviousDate(String format, int numberOfDaysPrevious) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime previousDate = zonedDateTime.minus(Period.ofDays(numberOfDaysPrevious));
        return previousDate.format(formatter);
    }

    public static String addMinutesToDate(String format, long numberOfMinutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zoneDateTimeAddMinutes = zonedDateTime.plusMinutes(numberOfMinutes);
        return zoneDateTimeAddMinutes.format(formatter);
    }

    public static String minusMinutesToDate(String format, long numberOfMinutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTimeMinusMinutes = zonedDateTime.minusMinutes(numberOfMinutes);
        return zonedDateTimeMinusMinutes.format(formatter);
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
