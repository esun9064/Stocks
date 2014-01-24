import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;


public class SP500 {

	public static String find_fiveDay()
	{
		return null;
	}
	
	public static String find_oneMonth()
	{
		return null;
	}
	
	public static String find_threeMonth()
	{
		return null;
	}
	
	public static String find_sixMonth()
	{
		return null;
	}
	
	public static String find_oneYear()
	{
		return null;
	}
	
	public static String find_YTD()
	{
		return null;
	}
	
	public static String find_fiveYear()
	{
		return null;
	}
	  
	public static String find_tenYear()
	{
		return null;
	}
	public static void main(String args[]) 
	
	{
		/*
	    	YStockQuote x = new YStockQuote("GOOG");
	    	
	    	Date current_date = new Date();
	    	Calendar calendar = GregorianCalendar.getInstance();
	    	calendar.setTime(current_date);
	    	
	    	Calendar[] holidays = new GregorianCalendar[9];
	    	int len = holidays.length;
	    	for (int i = 0; i < len; i++)
	    		holidays[i] = GregorianCalendar.getInstance();	    	
	    	holidays[0].setTime(Holidays.NewYearsDayObserved(calendar.get(Calendar.YEAR)));
	    	holidays[1].setTime(Holidays.MartinLutherKing(calendar.get(Calendar.YEAR)));
	    	holidays[2].setTime(Holidays.PresidentsDay(calendar.get(Calendar.YEAR)));
	    	holidays[3].setTime(Holidays.GoodFridayObserved(calendar.get(Calendar.YEAR)));
	    	holidays[4].setTime(Holidays.MemorialDay(calendar.get(Calendar.YEAR)));
	    	holidays[5].setTime(Holidays.IndependenceDayObserved(calendar.get(Calendar.YEAR)));
	    	holidays[6].setTime(Holidays.LaborDay(calendar.get(Calendar.YEAR)));
	    	holidays[7].setTime(Holidays.Thanksgiving(calendar.get(Calendar.YEAR)));
	    	holidays[8].setTime(Holidays.ChristmasDayObserved(calendar.get(Calendar.YEAR)));
	    	for (int i = 0; i < len; i++)
	    	{
	    		if (holidays[i].get(Calendar.DAY_OF_MONTH) == holidays[i].getActualMaximum(Calendar.DAY_OF_MONTH))
	    			holidays[i] = null;
	    	}

	    	String five_day = find_fiveDay();
	    	
	    	
	    	int count = 0;
	    	while (count < 5)
	    	{
		    	if (calendar.get(Calendar.DAY_OF_WEEK) > 1 && calendar.get(Calendar.DAY_OF_WEEK) < 7 )
		    	{
		    		count += 1;
		    	}
		    	else {
			    	for (int i = 0; i < len; i++)
			    	{
			    			if (holidays[i].equals(calendar))
			    			{
			    				count += 1;
			    				break;
			    			}		
			    	}
		    	}
		    	calendar.add(Calendar.DAY_OF_YEAR, -1);
	    	}
	    	calendar.add(Calendar.DAY_OF_YEAR, 5);
	    	calendar.add(Calendar.MONTH, -6);
	    	calendar.add(Calendar.HOUR_OF_DAY, -17);

	    	System.out.println(calendar.get(Calendar.MONTH) +1);
	    	System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

	    	System.out.println(calendar.get(Calendar.YEAR));
	    	DateFormat df = new SimpleDateFormat("M/d/y");
			System.out.println( df.format(calendar.getTime()));

	    	*/
		
	
		DayRange b = new DayRange();


	    System.out.printf("%s/%s/%s\n", DayRange.five_day[0], DayRange.five_day[1], DayRange.five_day[2]);
	    System.out.printf("%s/%s/%s\n", DayRange.one_month[0], DayRange.one_month[1], DayRange.one_month[2]);
	    System.out.printf("%s/%s/%s\n", DayRange.three_month[0], DayRange.three_month[1], DayRange.three_month[2]);
	    System.out.printf("%s/%s/%s\n", DayRange.six_month[0], DayRange.six_month[1], DayRange.six_month[2]);
	    System.out.printf("%s/%s/%s\n", DayRange.one_year[0], DayRange.one_year[1], DayRange.one_year[2]);
	    System.out.printf("%s/%s/%s\n", DayRange.five_year[0], DayRange.five_year[1], DayRange.five_year[2]);
	    System.out.printf("%s/%s/%s\n", DayRange.ten_year[0], DayRange.ten_year[1], DayRange.ten_year[2]);
	    System.out.printf("%s/%s/%s\n", DayRange.ytd[0], DayRange.ytd[1], DayRange.ytd[2]);


		
	    
	}
	  
	
	  
}
