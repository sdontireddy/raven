package com.ucbos.performance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;



@SuppressWarnings({ "deprecation", "unused" })
public class OrderDataGenerator extends OrderConstants {

	private static Logger LOGGER = Logger.getLogger("OrderDataGenerator.class.getName()");


	public static String getSiteID() {
		return SiteID;
	}
	


	public static long getItemID() {
		long randomNum = ThreadLocalRandom.current().nextLong(startItemRange, endItemRange + 1);
		return randomNum;
	}
	
	public static String getItemIDFromPreConfiguredList() {
		String randomNum = getRandomItemNumberFromtheList(Arrays.asList(itemsList.split(",",-1)));
		return randomNum;
	}



	/**
	 * Randomly generate the quantity
	 * 
	 * @return
	 */
	public static long getQty() {
		long randomNum = ThreadLocalRandom.current().nextLong(startQtyRange, endQtyRange + 1);
		return randomNum;
	}

	public static String getRandomItemNumberFromtheList(List<String> list) {

		Random r = new Random();
		String randomNum = list.get(r.nextInt(list.size()));
		System.out.println("randomNum" + randomNum);
		return randomNum;

	}

	public static String getNextOrderID() {
		String randomNum =ThreadLocalRandom.current().nextLong(startQtyRange, endQtyRange + 1) + "-" + getSiteID() + "-"
				+ ThreadLocalRandom.current().nextLong(startQtyRange, endQtyRange + 1);
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyy");

		return "TEST-" + currentDate.format(formatter) + "-" + randomNum;
	}
	/*
	 * Just for fun : DOID in the format of AIML1125202SETMMDDYYRANDOMNUMBER
	 */
	public static String getNextDOID() {
		
		String randomNum =ThreadLocalRandom.current().nextLong(startQtyRange, endQtyRange + 1) + getSiteID() 
				+ ThreadLocalRandom.current().nextLong(startQtyRange, endQtyRange + 1);
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyy");

		return "AIML1125202SET" + currentDate.format(formatter) + randomNum;
	}
	
	public static String getFutureDate(int numberOfDaysAhead) {
		
		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().plusDays(numberOfDaysAhead);
		LocalDate random = between(start, end);
		
		return random.toString();
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
