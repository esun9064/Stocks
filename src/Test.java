import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 * Test file
 */
public class Test {
    DayRange b;
    Date today;
    Calendar calendar;
    
    /*
     * Initialize calendar and methods for updating stock information
     */
    public void init() {
        b = new DayRange();
        b.calculate();
        today = new Date();
        calendar = new GregorianCalendar();
        calendar.setTime(today);
    }

    public void update_per_15(YStockQuote x) {
        this.update_historical();
        x.update();
    }
    
    public void update_historical() {
        if (b.is_old() == true)
            b.update();
    }
    
    public void show_hist_data(YStockQuote x, DayRange b) {
        
        

        
        x.find_historical_data(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
         
        x.find_five_day_change(DayRange.five_day);
        String[] change = x.get_five_day_change();
        System.out.println("Five day change: " +  change[0] + " " + change[1] + " " + change[2]);

        x.find_one_month_change(DayRange.one_month);
        change = x.get_one_month_change();
        System.out.println("One month change: " + change[0] + " " + change[1] + " " + change[2]);

        x.find_three_month_change(DayRange.three_month);
        change = x.get_three_month_change();
        System.out.println("Three month change: " +change[0] + " " + change[1] + " " + change[2]);

        x.find_six_month_change(DayRange.six_month);
        change = x.get_six_month_change();
        System.out.println("Six month change: " +change[0] + " " + change[1] + " " + change[2]);

        x.find_one_year_change(DayRange.one_year);
        change = x.get_one_year_change();
        System.out.println("One Year change: " +change[0] + " " + change[1] + " " + change[2]);

        x.find_five_year_change(DayRange.five_year);
        change = x.get_five_year_change();
        System.out.println("Five Year change: " +change[0] + " " + change[1] + " " + change[2]);

        x.find_ten_year_change(DayRange.ten_year);
        change = x.get_ten_year_change();
        System.out.println("Ten Year change: " +change[0] + " " + change[1] + " " + change[2]);

        x.find_max_change();
        change = x.get_max_year_change();
        System.out.println("Max change: " +change[0] + " " + change[1] + " " + change[2]);

        x.find_ytd_change(DayRange.ytd);
        change = x.get_ytd_change();
        System.out.println("YTD change: " +change[0] + " " + change[1] + " " + change[2]);
        System.out.println("\n");
        
    }
    
    public static void main(String args[]) 
    {
		
		
        

        Test sp = new Test();
        sp.init();
		YStockQuote[] sp500 = new YStockQuote[500];        
		try {
			File file = new File("sp500.txt");
			Scanner input = new Scanner(file);
			
			for (int i = 0; i < 500 ; i++)
			{
				sp500[i] = new YStockQuote(input.next());
			}			
		}
		catch(Exception e)
		{
			System.out.println("File not found");
		}
       
        //sp.update_per_15(sp500);
      
       
        String[] colnames = {	"Name",
        						"Symbol",
        						"Price",
        						"Change",
        						"Percent Change"};
		Object[][] data = new Object[500][5];
		for (int i = 0; i < 500 ; i ++)
		{
			data[i][0] = sp500[i].get_name();
			data[i][1] = sp500[i].get_symbol();
			data[i][2] = sp500[i].get_price();
			data[i][3] = sp500[i].get_change();
			data[i][4] = sp500[i].get_percent_change();
		}
  
        JTable stocks = new JTable(data, colnames);
        stocks.setVisible(true);
        stocks.setFillsViewportHeight(true);
        
        JFrame frame = new JFrame();
        frame.pack();
        frame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,0));
        frame.setContentPane(panel);
        panel.add(new JScrollPane(stocks));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
