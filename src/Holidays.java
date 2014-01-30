import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//
public class Holidays {


    public static Date NewYearsDayObserved (int nYear) {
        Calendar cal = new GregorianCalendar(nYear, Calendar.JANUARY, 1);
        switch(cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SATURDAY :
                return (new GregorianCalendar(--nYear, Calendar.DECEMBER, 31)).getTime();
            case Calendar.SUNDAY :
                return (new GregorianCalendar(nYear, Calendar.JANUARY, 2)).getTime();
            case Calendar.MONDAY :
            case Calendar.TUESDAY :
            case Calendar.WEDNESDAY :
            case Calendar.THURSDAY :
            case Calendar.FRIDAY :
            default :
                return cal.getTime();
        }
    }

    public static Date MartinLutherKing (int nYear) {
        // Third Monday in January
        Calendar cal = new GregorianCalendar(nYear, Calendar.JANUARY, 1);
        switch(cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY :
                return (new GregorianCalendar(nYear, Calendar.JANUARY, 16)).getTime();
            case Calendar.MONDAY :
                return (new GregorianCalendar(nYear, Calendar.JANUARY, 15)).getTime();
            case Calendar.TUESDAY :
                return (new GregorianCalendar(nYear, Calendar.JANUARY, 21)).getTime();
            case Calendar.WEDNESDAY :
                return (new GregorianCalendar(nYear, Calendar.JANUARY, 20)).getTime();
            case Calendar.THURSDAY :
                return (new GregorianCalendar(nYear, Calendar.JANUARY, 19)).getTime();
            case Calendar.FRIDAY :
                return (new GregorianCalendar(nYear, Calendar.JANUARY, 18)).getTime();
            default : // Saturday
                return (new GregorianCalendar(nYear, Calendar.JANUARY, 17)).getTime();
        }
    }

    public static Date PresidentsDay (int nYear) {
        // Third Monday in February
        Calendar cal = new GregorianCalendar(nYear, Calendar.FEBRUARY, 1);
        switch(cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY :
                return (new GregorianCalendar(nYear, Calendar.FEBRUARY, 16)).getTime();
            case Calendar.MONDAY :
                return (new GregorianCalendar(nYear, Calendar.FEBRUARY, 15)).getTime();
            case Calendar.TUESDAY :
                return (new GregorianCalendar(nYear, Calendar.FEBRUARY, 21)).getTime();
            case Calendar.WEDNESDAY :
                return (new GregorianCalendar(nYear, Calendar.FEBRUARY, 20)).getTime();
            case Calendar.THURSDAY :
                return (new GregorianCalendar(nYear, Calendar.FEBRUARY, 19)).getTime();
            case Calendar.FRIDAY :
                return (new GregorianCalendar(nYear, Calendar.FEBRUARY, 18)).getTime();
            default : // Saturday
                return (new GregorianCalendar(nYear, Calendar.FEBRUARY, 17)).getTime();
        }
    }

    public static Date EasterSunday(int nYear) {
    	/*
    	 * Algorithm from US Naval Observatory
    	 */
        // Let y be the year (such as 1800 or 2001).
        int y = nYear;
        // Divide y by 19 and call the remainder a. Ignore the quotient.
        int a = y % 19;
        // Divide y by 100 to get a quotient b and a remainder c.
        int b = y / 100;
        int c = y % 100;
        // Divide b by 4 to get a quotient d and a remainder e.
        int d = b / 4;
        int e = b % 4;
        // Divide 8 * b + 13 by 25 to get a quotient g. Ignore the remainder.
        int g = (8 * b + 13) / 25;
        // Divide 19 * a + b - d - g + 15 by 30 to get a remainder h. Ignore the quotient.
        int h = (19 * a + b - d - g + 15) % 30;  // not sure wich ways the parenthesis go here
        // Divide c by 4 to get a quotient j and a remainder k.
        int j = c / 4;
        int k = c % 4;
        // Divide a + 11 * h by 319 to get a quotient m. Ignore the remainder.
        int m = (a + 11 * h) / 319;    // again which order for the operations needs parenthesis
        // Divide 2 * e + 2 * j - k - h + m + 32 by 7 to get a remainder r. Ignore the quotient.
        int r = (2 * e + 2 * j - k - h + m + 32) % 7;
        // Divide h - m + r + 90 by 25 to get a quotient n. Ignore the remainder.
        int n = (h - m + r + 90) / 25;
        // Divide h - m + r + n + 19 by 32 to get a remainder p. Ignore the quotient.
        int p = (h - m + r + n + 19) % 32;

        return new GregorianCalendar(y, n-1, p).getTime();
    }

    public static Date GoodFridayObserved(int nYear) {
        // Get Easter Sunday and subtract two days
        int nEasterMonth = 0;
        int nEasterDay = 0;
        int nGoodFridayMonth = 0;
        int nGoodFridayDay = 0;
        Date dEasterSunday;
        dEasterSunday = EasterSunday(nYear);
        Calendar cal = new GregorianCalendar();
        cal.setTime(dEasterSunday);
        nEasterMonth = cal.get(Calendar.MONTH);
        nEasterDay = cal.get(Calendar.DAY_OF_MONTH) ;
        cal.add(Calendar.DAY_OF_YEAR, -2);

        return cal.getTime();
    }

    public static Date MemorialDay (int nYear) {
        // Last Monday in May
        Calendar cal = new GregorianCalendar(nYear, Calendar.MAY, 1);
        switch(cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY :
                return (new GregorianCalendar(nYear, Calendar.MAY, 30)).getTime();
            case Calendar.MONDAY :
                return (new GregorianCalendar(nYear, Calendar.MAY, 29)).getTime();
            case Calendar.TUESDAY :
                return (new GregorianCalendar(nYear, Calendar.MAY, 28)).getTime();
            case Calendar.WEDNESDAY :
                return (new GregorianCalendar(nYear, Calendar.MAY, 27)).getTime();
            case Calendar.THURSDAY :
                return (new GregorianCalendar(nYear, Calendar.MAY, 26)).getTime();
            case Calendar.FRIDAY :
                return (new GregorianCalendar(nYear, Calendar.MAY, 25)).getTime();
            default : // Saturday
                return (new GregorianCalendar(nYear, Calendar.MAY, 31)).getTime();
        }
    }

    public static Date IndependenceDayObserved (int nYear) {
        Calendar cal = new GregorianCalendar(nYear, Calendar.JULY, 4);
        switch(cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SATURDAY:
                return (new GregorianCalendar(nYear, Calendar.JULY, 3)).getTime();
            case Calendar.SUNDAY :
                return (new GregorianCalendar(nYear, Calendar.JULY, 5)).getTime();
            case Calendar.MONDAY :
            case Calendar.TUESDAY :
            case Calendar.WEDNESDAY :
            case Calendar.THURSDAY :
            case Calendar.FRIDAY :
            default :
                return cal.getTime();
        }
    }

    public static Date LaborDay (int nYear) {
        // The first Monday in September
        Calendar cal = new GregorianCalendar(nYear, Calendar.SEPTEMBER, 1);
        switch(cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.TUESDAY :
                return (new GregorianCalendar(nYear, Calendar.SEPTEMBER, 7)).getTime();
            case Calendar.WEDNESDAY :
                return (new GregorianCalendar(nYear, Calendar.SEPTEMBER, 6)).getTime();
            case Calendar.THURSDAY :
                return (new GregorianCalendar(nYear, Calendar.SEPTEMBER, 5)).getTime();
            case Calendar.FRIDAY :
                return (new GregorianCalendar(nYear, Calendar.SEPTEMBER, 4)).getTime();
            case Calendar.SATURDAY :
                return (new GregorianCalendar(nYear, Calendar.SEPTEMBER, 3)).getTime();
            case Calendar.SUNDAY :
                return (new GregorianCalendar(nYear, Calendar.SEPTEMBER, 2)).getTime();
            case Calendar.MONDAY :
            default :
                return cal.getTime();
        }
    }



    public static Date Thanksgiving(int nYear) {
        Calendar cal = new GregorianCalendar(nYear, Calendar.NOVEMBER, 1);
        switch(cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY :
                return (new GregorianCalendar(nYear, Calendar.NOVEMBER, 26)).getTime();
            case Calendar.MONDAY :
                return (new GregorianCalendar(nYear, Calendar.NOVEMBER, 25)).getTime();
            case Calendar.TUESDAY :
                return (new GregorianCalendar(nYear, Calendar.NOVEMBER, 24)).getTime();
            case Calendar.WEDNESDAY :
                return (new GregorianCalendar(nYear, Calendar.NOVEMBER, 23)).getTime();
            case Calendar.THURSDAY :
                return (new GregorianCalendar(nYear, Calendar.NOVEMBER, 22)).getTime();
            case Calendar.FRIDAY :
                return (new GregorianCalendar(nYear, Calendar.NOVEMBER, 28)).getTime();
            default : // Saturday
                return (new GregorianCalendar(nYear, Calendar.NOVEMBER, 27)).getTime();
        }
    }

    public static Date ChristmasDayObserved (int nYear)	{
        Calendar cal = new GregorianCalendar(nYear, Calendar.DECEMBER, 25);
        switch(cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SATURDAY:
                return (new GregorianCalendar(nYear, Calendar.DECEMBER, 24)).getTime();
            case Calendar.SUNDAY :
                return (new GregorianCalendar(nYear, Calendar.DECEMBER, 26)).getTime();
            case Calendar.MONDAY :
            case Calendar.TUESDAY :
            case Calendar.WEDNESDAY :
            case Calendar.THURSDAY :
            case Calendar.FRIDAY :
            default :
                return cal.getTime();
        }
    }


    public static void main(String [ ] args) {

        SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM d");
        for (int nYear = 2013; nYear <= 2035; nYear++) {
            System.out.println("Federal Holidays for " + nYear + ":");
            System.out.printf("%-23s = %s%n", df.format(Holidays.NewYearsDayObserved(nYear)), "New Year's Day (observed)");
            System.out.printf("%-23s = %s%n", df.format(Holidays.MartinLutherKing(nYear)), "ML King Day");
            System.out.printf("%-23s = %s%n", df.format(Holidays.PresidentsDay(nYear)), "President's Day");
            System.out.printf("%-23s = %s%n", df.format(Holidays.GoodFridayObserved(nYear)), "Good Friday");
            System.out.printf("%-23s = %s%n", df.format(Holidays.EasterSunday(nYear)), "Easter Sunday");

            System.out.printf("%-23s = %s%n", df.format(Holidays.MemorialDay(nYear)), "Memorial Day (observed)");
            System.out.printf("%-23s = %s%n", df.format(Holidays.IndependenceDayObserved(nYear)), "Independence Day (observed)");
            System.out.printf("%-23s = %s%n", df.format(Holidays.LaborDay(nYear)), "Labor Day");
            System.out.printf("%-23s = %s%n", df.format(Holidays.Thanksgiving(nYear)), "Thanksgiving Day");
            System.out.printf("%-23s = %s%n", df.format(Holidays.ChristmasDayObserved(nYear)), "Christmas Day (observed)");
            System.out.println();
        }
    }
}
