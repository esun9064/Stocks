import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DayRange {
	
	private static Date current_date;
	private static Calendar calendar;	
	private static Calendar[] holidays;
	public static int len;

	
	public static String[] five_day;
	public static String[] one_month;
	public static String[] three_month;
	public static String[] six_month;
	public static String[] one_year;
	public static String[] ytd;
	public static String[] five_year;
	public static String[] ten_year;

	public DayRange()
	{
		current_date = new Date();
		calendar = GregorianCalendar.getInstance();	
		holidays = new GregorianCalendar[9];
		len = holidays.length;
		calendar.setTime(current_date);
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
    	calculate();
    	
	}
	
	public static void calculate()
	{
		

		if (calendar.get(Calendar.HOUR_OF_DAY) < 9)
			calendar.add(Calendar.HOUR_OF_DAY, -16);
		else if (calendar.get(Calendar.HOUR_OF_DAY) == 9 && calendar.get(Calendar.MINUTE) < 30)
		{
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 12);
		}

		current_date = calendar.getTime();

		five_day = find_fiveDay().split("/");
		one_month = find_oneMonth().split("/");
		three_month = find_threeMonth().split("/");
		six_month = find_sixMonth().split("/");
		one_year = find_oneYear().split("/");
		ytd = find_YTD().split("/");
		five_year = find_fiveYear().split("/");
		ten_year = find_tenYear().split("/");
	
		

		
	}
	
	
	private static String find_fiveDay()
	{
		calendar.setTime(current_date);

		int count = 0;

		do
		{
    		boolean holiday = false;
	    	for (int i = 0; i < len; i++)
	    	{
    			if (holidays[i] != null && holidays[i].get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR))
    			{
    				holiday = true;
    				break;
    			}		
	    	}
	    	if (holiday == false)
	    	{
	    		if (calendar.get(Calendar.DAY_OF_WEEK) > 1 && calendar.get(Calendar.DAY_OF_WEEK) < 7 )
	    		{
	    			count += 1;
	    		}
	    	}
	    	else if (holiday == true)
	    	{
	    		holiday = false;
	    	}
	    	calendar.add(Calendar.DAY_OF_YEAR, -1);
    	}
		while (count < 4);
		DateFormat df = new SimpleDateFormat("M/d/y");
		return df.format(calendar.getTime());
	}
	
	private static String find_oneMonth()
	{
		calendar.setTime(current_date);
		calendar.add(Calendar.MONTH, -1);

		switch(calendar.get(Calendar.DAY_OF_WEEK))
		{
		case 1:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case 7:
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			break;	
		default:	
			break;
		}
		for (int i = 0; i < len; i++)
    	{
    			if (holidays[i] != null && holidays[i].equals(calendar))
    			{
    				if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
    				{
        				calendar.add(Calendar.DAY_OF_YEAR, 3);
        				break;
    				}
    				else {
    					calendar.add(Calendar.DAY_OF_YEAR, 1);
    					break;
    				}
    			}		
    	}
		DateFormat df = new SimpleDateFormat("M/d/y");
		return df.format(calendar.getTime());
	}
	
	private static String find_threeMonth()
	{
		calendar.setTime(current_date);
		calendar.add(Calendar.MONTH, -3);
		switch(calendar.get(Calendar.DAY_OF_WEEK))
		{
		case 1:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case 7:
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			break;	
		default:	
			break;
		}
		for (int i = 0; i < len; i++)
    	{
    			if (holidays[i] != null && holidays[i].equals(calendar))
    			{
    				if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
    				{
        				calendar.add(Calendar.DAY_OF_YEAR, 3);
    				}
    				else
    					calendar.add(Calendar.DAY_OF_YEAR, 1);
    			}		
    	}
		DateFormat df = new SimpleDateFormat("M/d/y");
		return df.format(calendar.getTime());	}
	
	private static String find_sixMonth()
	{
		calendar.setTime(current_date);
		calendar.add(Calendar.MONTH, -6);
		switch(calendar.get(Calendar.DAY_OF_WEEK))
		{
		case 1:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case 7:
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			break;	
		default:	
			break;
		}
		for (int i = 0; i < len; i++)
    	{
    			if (holidays[i] != null && holidays[i].equals(calendar))
    			{
    				if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
    				{
        				calendar.add(Calendar.DAY_OF_YEAR, 3);
    				}
    				else
    					calendar.add(Calendar.DAY_OF_YEAR, 1);
    			}		
    	}
		DateFormat df = new SimpleDateFormat("M/d/y");
		return df.format(calendar.getTime());	}
	
	private static String find_oneYear()
	{
		calendar.setTime(current_date);
		calendar.add(Calendar.YEAR, -1);
		switch(calendar.get(Calendar.DAY_OF_WEEK))
		{
		case 1:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case 7:
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			break;	
		default:	
			break;
		}
		for (int i = 0; i < len; i++)
    	{
    			if (holidays[i] != null && holidays[i].equals(calendar))
    			{
    				if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
    				{
        				calendar.add(Calendar.DAY_OF_YEAR, 3);
    				}
    				else
    					calendar.add(Calendar.DAY_OF_YEAR, 1);
    			}		
    	}
		DateFormat df = new SimpleDateFormat("M/d/y");
		return df.format(calendar.getTime());	}
	
	private static String find_YTD()
	{
		calendar.setTime(current_date);
		calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 02);

		switch(calendar.get(Calendar.DAY_OF_WEEK))
		{
		case 1:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case 7:
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			break;	
		default:	
			break;
		}
		for (int i = 0; i < len; i++)
    	{
    			if (holidays[i] != null && holidays[i].equals(calendar))
    			{
    				if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
    				{
        				calendar.add(Calendar.DAY_OF_YEAR, 3);
    				}
    				else
    					calendar.add(Calendar.DAY_OF_YEAR, 1);
    			}		
    	}
		DateFormat df = new SimpleDateFormat("M/d/y");
		return df.format(calendar.getTime());	
	}
	
	private static String find_fiveYear()
	{
		calendar.setTime(current_date);
		calendar.add(Calendar.YEAR, -5);
		switch(calendar.get(Calendar.DAY_OF_WEEK))
		{
		case 1:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case 7:
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			break;	
		default:	
			break;
		}
		for (int i = 0; i < len; i++)
    	{
    			if (holidays[i] != null && holidays[i].equals(calendar))
    			{
    				if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
    				{
        				calendar.add(Calendar.DAY_OF_YEAR, 3);
    				}
    				else
    					calendar.add(Calendar.DAY_OF_YEAR, 1);
    			}		
    	}
		DateFormat df = new SimpleDateFormat("M/d/y");
		return df.format(calendar.getTime());	}
	  
	private static String find_tenYear()
	{
		calendar.setTime(current_date);
		calendar.add(Calendar.YEAR, -10);
		switch(calendar.get(Calendar.DAY_OF_WEEK))
		{
		case 1:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case 7:
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			break;	
		default:	
			break;
		}
		for (int i = 0; i < len; i++)
    	{
    			if (holidays[i] != null && holidays[i].equals(calendar))
    			{
    				if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
    				{
        				calendar.add(Calendar.DAY_OF_YEAR, 3);
    				}
    				else
    					calendar.add(Calendar.DAY_OF_YEAR, 1);
    			}		
    	}
		DateFormat df = new SimpleDateFormat("M/d/y");
		return df.format(calendar.getTime());	}
}
