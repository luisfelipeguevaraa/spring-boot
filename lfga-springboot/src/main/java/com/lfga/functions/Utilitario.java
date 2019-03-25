package com.lfga.functions;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utilitario {

	public static int promedio(int[] arreglo) {
		int promedio = 0; 
		for( int i=0; i<arreglo.length; i++ ){ 
		promedio += arreglo[i]; 
		} 
		promedio = promedio / arreglo.length;
		return promedio;
	}
	
	public static double desviacion( int[] arreglo ) {
	    int prom, sum = 0; int i, n = arreglo.length;
	    prom = promedio(arreglo);

	    for ( i = 0; i < n; i++ ) 
	      sum += Math.pow ( arreglo[i] - prom, 2 );

	    return Math.sqrt ( sum / ( double ) n );
	  }
	
	public static Date fechaProbMuerte() {
		LocalDate randomDate = createRandomDate(2019, 2060);
		return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
	
	 public static int createRandomIntBetween(int start, int end) {
	        return start + (int) Math.round(Math.random() * (end - start));
	 }

	 public static LocalDate createRandomDate(int startYear, int endYear) {
	        int day = createRandomIntBetween(1, 28);
	        int month = createRandomIntBetween(1, 12);
	        int year = createRandomIntBetween(startYear, endYear);
	        return LocalDate.of(year, month, day);
	    }
	
}
