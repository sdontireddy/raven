	package com.ucbos.utils;

	import org.apache.commons.lang.RandomStringUtils;

	import java.time.LocalDate;
	import java.time.ZonedDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.List;
	import java.util.Random;
	import java.util.concurrent.ThreadLocalRandom;
	import java.util.logging.Logger;



	@SuppressWarnings({ "deprecation", "unused" })
	public class DataGeneratorUtil {

		private static Logger LOGGER = Logger.getLogger("OrderDataGenerator.class.getName()");





		public static String generateRandomString() {
			return RandomStringUtils.randomAlphanumeric(5);


		}






		public static String getRandomItemNumberFromtheList(List<String> list) {

			Random r = new Random();
			String randomNum = list.get(r.nextInt(list.size()));
			System.out.println("randomNum" + randomNum);
			return randomNum;

		}
		public static long getRandomNumberNumber(int startRanage , int endRange) {
			long randomNum =ThreadLocalRandom.current().nextLong(startRanage, endRange + 1);

			return randomNum;
		}


		public static String getDateFormat(String dateFormat) {

			ZonedDateTime currentDate = ZonedDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

			return  currentDate.format(formatter) ;
		}

		public static String getFutureDate(String format , int numberOfDaysAhead) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);


			ZonedDateTime start = ZonedDateTime.now();
			ZonedDateTime end = ZonedDateTime.now().plusDays(numberOfDaysAhead);

			return  end.format(formatter) ;
		}

		public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
			long startEpochDay = startInclusive.toEpochDay();
			long endEpochDay = endExclusive.toEpochDay();
			long randomDay = ThreadLocalRandom
			  .current()
			  .nextLong(startEpochDay, endEpochDay);

			return LocalDate.ofEpochDay(randomDay);
		}


	}
