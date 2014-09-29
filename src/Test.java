import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        x.getIntraday();
		
        x.find_historical_data(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
         
        x.find_five_day_change(DayRange.five_day);
       
        x.find_one_month_change(DayRange.one_month);
        
        x.find_three_month_change(DayRange.three_month);
       
        x.find_six_month_change(DayRange.six_month);
      
        x.find_one_year_change(DayRange.one_year);
        
        x.find_five_year_change(DayRange.five_year);

        x.find_ten_year_change(DayRange.ten_year);

        x.find_max_change();

        x.find_ytd_change(DayRange.ytd);
        
    }
    
    public static void main(String args[]) 
    {
        Test sp = new Test();
        sp.init();
		
		YStockQuote fr = new YStockQuote("aapl");
		sp.show_hist_data(fr, sp.b);
		YStockQuote[] sp500 = new YStockQuote[500];  
       
        //sp.update_per_15(sp500);
      
       
        String[] colnames = {	"Name",
        						"Symbol",
        						"Price",
        						"Change",
        						"Percent Change",
								"Volume",
								"Market Cap",
								"Dividend Share",
								"Dividend Yield",
								"Short Ratio",
								"Five Day Change",
								"One Month Change",
								"Six Month Change",
								"One Year Change",
								"Five Year Change",
								"Ten Year Change",};
		Object[][] data = new Object[500][16];  
        JTable stocks = new JTable(data, colnames);
        stocks.setVisible(true);
        stocks.setFillsViewportHeight(true);
        JFrame frame = new JFrame();
        frame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,0));
        frame.setContentPane(panel);
        panel.add(new JScrollPane(stocks, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		stocks.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		frame.pack();
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Scanner input = new Scanner(System.in);
		try {
			File file = new File("sp500.txt");
			input = new Scanner(file);
			
			for (int i = 0; i < 500 ; i++)
			{
				sp500[i] = new YStockQuote(input.next());			
				data[i][0] = sp500[i].get_name();
				data[i][1] = sp500[i].get_symbol();
				data[i][2] = sp500[i].get_price();
				data[i][3] = sp500[i].get_change();
				data[i][4] = sp500[i].get_percent_change();
				
				data[i][5] = sp500[i].get_volume();
				data[i][6] = sp500[i].get_market_cap();
				data[i][7] = sp500[i].get_dividend_per_share();
				data[i][8] = sp500[i].get_dividend_yield();
				data[i][9] = sp500[i].get_short_ratio();
			}			
		}
		catch(Exception e)
		{
			System.out.println("File not found");
		}		
		for (int i = 0 ; i < 500; i++)
		{
			sp.show_hist_data(sp500[i], sp.b);
			String[] temp = sp500[i].get_five_day_change();
			data[i][10] = temp[1];
			temp = sp500[i].get_one_month_change();
			data[i][11] = temp[1];
			temp = sp500[i].get_six_month_change();
			data[i][12] = temp[1];
			temp = sp500[i].get_one_year_change();
			data[i][13] = temp[1];
			temp = sp500[i].get_five_year_change();
			data[i][14] = temp[1];
			temp = sp500[i].get_ten_year_change();
			data[i][15] = temp[1];
		}
		

	}

}
