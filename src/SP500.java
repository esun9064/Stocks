import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

/*
 * Test file
 */
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


	  

		Date today = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(today);
		YStockQuote x = new YStockQuote("GOOG");
		try {
			x.find_historical_data(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    x.find_five_day_change(DayRange.five_day);
	    String[] change = x.get_five_day_change();
	    System.out.println(change[0] + " " + change[1] + " " + change[2]);
	    
	    x.find_one_month_change(DayRange.one_month);
	    change = x.get_one_month_change();
	    System.out.println(change[0] + " " + change[1] + " " + change[2]);
	    
		x.find_three_month_change(DayRange.three_month);
	    change = x.get_three_month_change();
	    System.out.println(change[0] + " " + change[1] + " " + change[2]);
		
	    x.find_six_month_change(DayRange.six_month);
	    change = x.get_six_month_change();
	    System.out.println(change[0] + " " + change[1] + " " + change[2]);
	    
		x.find_one_year_change(DayRange.one_year);
	    change = x.get_one_year_change();
	    System.out.println(change[0] + " " + change[1] + " " + change[2]);
	    
		x.find_five_year_change(DayRange.five_year);
	    change = x.get_five_year_change();
	    System.out.println(change[0] + " " + change[1] + " " + change[2]);
		
		x.find_ten_year_change(DayRange.ten_year);
	    change = x.get_ten_year_change();
	    System.out.println(change[0] + " " + change[1] + " " + change[2]);
	    
		x.find_max_change();
	    change = x.get_max_year_change();
	    System.out.println(change[0] + " " + change[1] + " " + change[2]);
		
		x.find_ytd_change(DayRange.ytd);
	    change = x.get_ytd_change();
	    System.out.println(change[0] + " " + change[1] + " " + change[2]);
		/*
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		System.out.println(x.historical_data.get(0));
		System.out.println(x.historical_data.get(1));
		System.out.println(x.historical_data.get(x.historical_data.size()-1));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String date =  df.format(calendar.getTime());
		System.out.println((date));

		System.out.println(x.find_data_by_date(date));
		*/

		
	    
	}
	  
	
	  
}
