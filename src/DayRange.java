import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DayRange {

    private static Date current_date;
    private static Calendar calendar;	
    private static Calendar[][] holidays;
    public static int len;


    public static String five_day;
    public static String one_month;
    public static String three_month;
    public static String six_month;
    public static String one_year;
    public static String ytd;
    public static String five_year;
    public static String ten_year;

    public DayRange() {
        this.update();
    }
    public boolean is_old() {
        boolean old = false;
        Date temp = new Date();
        Calendar temp_cal = new GregorianCalendar();
        temp_cal.setTime(temp);
        if (temp_cal.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)
                && temp_cal.get(Calendar.YEAR) == calendar.get(Calendar.DAY_OF_YEAR))
            old = true;
        
        return old;
    }
    
    public void update() {
        current_date = new Date();
        calendar = GregorianCalendar.getInstance(); 
        holidays = new GregorianCalendar[3][9];
        len = holidays[0].length;
        calendar.setTime(current_date);
        for (int i = 0; i < len; i++)
		{
            holidays[0][i] = GregorianCalendar.getInstance();  
			holidays[1][i] = GregorianCalendar.getInstance();  
            holidays[2][i] = GregorianCalendar.getInstance();  
		}
		//one year
        holidays[0][0].setTime(Holidays.NewYearsDayObserved(calendar.get(Calendar.YEAR)));
        holidays[0][1].setTime(Holidays.MartinLutherKing(calendar.get(Calendar.YEAR)));
        holidays[0][2].setTime(Holidays.PresidentsDay(calendar.get(Calendar.YEAR)));
        holidays[0][3].setTime(Holidays.GoodFridayObserved(calendar.get(Calendar.YEAR)));
        holidays[0][4].setTime(Holidays.MemorialDay(calendar.get(Calendar.YEAR)));
        holidays[0][5].setTime(Holidays.IndependenceDayObserved(calendar.get(Calendar.YEAR)));
        holidays[0][6].setTime(Holidays.LaborDay(calendar.get(Calendar.YEAR)));
        holidays[0][7].setTime(Holidays.Thanksgiving(calendar.get(Calendar.YEAR)));
        holidays[0][8].setTime(Holidays.ChristmasDayObserved(calendar.get(Calendar.YEAR)));
		//5 year
		calendar.setTime(current_date);
        calendar.add(Calendar.YEAR, -5);
		holidays[1][0].setTime(Holidays.NewYearsDayObserved(calendar.get(Calendar.YEAR)));
        holidays[1][1].setTime(Holidays.MartinLutherKing(calendar.get(Calendar.YEAR)));
        holidays[1][2].setTime(Holidays.PresidentsDay(calendar.get(Calendar.YEAR)));
        holidays[1][3].setTime(Holidays.GoodFridayObserved(calendar.get(Calendar.YEAR)));
        holidays[1][4].setTime(Holidays.MemorialDay(calendar.get(Calendar.YEAR)));
        holidays[1][5].setTime(Holidays.IndependenceDayObserved(calendar.get(Calendar.YEAR)));
        holidays[1][6].setTime(Holidays.LaborDay(calendar.get(Calendar.YEAR)));
        holidays[1][7].setTime(Holidays.Thanksgiving(calendar.get(Calendar.YEAR)));
        holidays[1][8].setTime(Holidays.ChristmasDayObserved(calendar.get(Calendar.YEAR)));
		//10 year
		calendar.setTime(current_date);
        calendar.add(Calendar.YEAR, -10);
		holidays[2][0].setTime(Holidays.NewYearsDayObserved(calendar.get(Calendar.YEAR)));
        holidays[2][1].setTime(Holidays.MartinLutherKing(calendar.get(Calendar.YEAR)));
        holidays[2][2].setTime(Holidays.PresidentsDay(calendar.get(Calendar.YEAR)));
        holidays[2][3].setTime(Holidays.GoodFridayObserved(calendar.get(Calendar.YEAR)));
        holidays[2][4].setTime(Holidays.MemorialDay(calendar.get(Calendar.YEAR)));
        holidays[2][5].setTime(Holidays.IndependenceDayObserved(calendar.get(Calendar.YEAR)));
        holidays[2][6].setTime(Holidays.LaborDay(calendar.get(Calendar.YEAR)));
        holidays[2][7].setTime(Holidays.Thanksgiving(calendar.get(Calendar.YEAR)));
        holidays[2][8].setTime(Holidays.ChristmasDayObserved(calendar.get(Calendar.YEAR)));
        this.calculate();
    }
    
    
    public void calculate() {
        if (calendar.get(Calendar.HOUR_OF_DAY) < 9)
            calendar.add(Calendar.HOUR_OF_DAY, -16);
        else if (calendar.get(Calendar.HOUR_OF_DAY) == 9 && calendar.get(Calendar.MINUTE) < 30) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 12);
        }


        five_day = find_fiveDay();
        one_month = find_oneMonth();
        three_month = find_threeMonth();
        six_month = find_sixMonth();
        one_year = find_oneYear();
        ytd = find_YTD();
        five_year = find_fiveYear();
        ten_year = find_tenYear();

    }


    private static String find_fiveDay() {
        calendar.setTime(current_date);
        //System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        int count = 0;

        while (count <= 4) {
            boolean holiday = false;
            for (int i = 0; i < len; i++) {
                if (holidays[0][i] != null && holidays[0][i].get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                    holiday = true;
                    break;
                }		
            }
            if (holiday == false) {
                if (calendar.get(Calendar.DAY_OF_WEEK) > 1 && calendar.get(Calendar.DAY_OF_WEEK) < 7 ) {
                    count += 1;
                    if (count > 4)
                        break;
                }
            }
            else if (holiday == true) {
                holiday = false;
            }
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            //System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    private static String find_oneMonth() {
        calendar.setTime(current_date);
        calendar.add(Calendar.MONTH, -1);
        //System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        switch(calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                break;
            case 7:
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                break;	
            default:	
                break;
        }
        for (int i = 0; i < len; i++) {
            if (holidays[0][i] != null && holidays[0][i].get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                    calendar.add(Calendar.DAY_OF_YEAR, 3);
                    break;
                }
                else {
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                    break;
                }
            }		
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    private static String find_threeMonth() {
        calendar.setTime(current_date);
        calendar.add(Calendar.MONTH, -3);
        switch(calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                break;
            case 7:
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                break;	
            default:	
                break;
        }
        for (int i = 0; i < len; i++) {
            if (holidays[0][i] != null && holidays[0][i].get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                    calendar.add(Calendar.DAY_OF_YEAR, 3);
                }
                else
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
            }		
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());	
    }

    private static String find_sixMonth() {
        calendar.setTime(current_date);
        calendar.add(Calendar.MONTH, -6);
        switch(calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                break;
            case 7:
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                break;	
            default:	
                break;
        }
        for (int i = 0; i < len; i++) {
            if (holidays[0][i] != null && holidays[0][i].get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                    calendar.add(Calendar.DAY_OF_YEAR, 3);
                }
                else
                    calendar.add(Calendar.DAY_OF_YEAR, 1);	
            }		
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());	}

        private static String find_oneYear() {
            calendar.setTime(current_date);
            calendar.add(Calendar.YEAR, -1);
            switch(calendar.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                    break;
                case 7:
                    calendar.add(Calendar.DAY_OF_YEAR, -1);
                    break;	
                default:	
                    break;
            }
            for (int i = 0; i < len; i++) {
                if (holidays[0][i] != null && holidays[0][i].get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                    if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                        calendar.add(Calendar.DAY_OF_YEAR, 3);
                    }
                    else
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                }		
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(calendar.getTime());	}

            private static String find_YTD() {
                calendar.setTime(current_date);
                calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 02);

                switch(calendar.get(Calendar.DAY_OF_WEEK)) {
                    case 1:
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                        break;
                    case 7:
                        calendar.add(Calendar.DAY_OF_YEAR, -1);
                        break;	
                    default:	
                        break;
                }
                for (int i = 0; i < len; i++) {
                    if (holidays[0][i] != null && holidays[0][i].get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                        if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                            calendar.add(Calendar.DAY_OF_YEAR, 3);
                        }
                        else
                            calendar.add(Calendar.DAY_OF_YEAR, 1);
                    }		
                }
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                return df.format(calendar.getTime());	
            }

            private static String find_fiveYear() {
                calendar.setTime(current_date);
                calendar.add(Calendar.YEAR, -5);
                switch(calendar.get(Calendar.DAY_OF_WEEK)) {
                    case 1:
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                        break;
                    case 7:
                        calendar.add(Calendar.DAY_OF_YEAR, -1);
                        break;	
                    default:	
                        break;
                }
                for (int i = 0; i < len; i++) {
                    if (holidays[1][i] != null && holidays[1][i].get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                        if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                            calendar.add(Calendar.DAY_OF_YEAR, 3);
                        }
                        else
                            calendar.add(Calendar.DAY_OF_YEAR, 1);
                    }		
                }
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                return df.format(calendar.getTime());	}

                private static String find_tenYear() {
                    calendar.setTime(current_date);
                    calendar.add(Calendar.YEAR, -10);
                    switch(calendar.get(Calendar.DAY_OF_WEEK)) {
                        case 1:
                            calendar.add(Calendar.DAY_OF_YEAR, 1);
                            break;
                        case 7:
                            calendar.add(Calendar.DAY_OF_YEAR, -1);
                            break;	
                        default:	
                            break;
                    }
                    for (int i = 0; i < len; i++) {
                        if (holidays[2][i] != null && holidays[2][i].get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                            if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                                calendar.add(Calendar.DAY_OF_YEAR, 3);
                            }
                            else
                                calendar.add(Calendar.DAY_OF_YEAR, 1);
                        }		
                    }
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    return df.format(calendar.getTime());		
                }
}
