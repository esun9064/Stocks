import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class YStockQuote {

	private String name;
	private String symbol;
	private String price;
	private String change;
	private String volume;
	private String avg_daily_volume;
	private String stock_exchange;
	private String market_cap;
	private String book_value;
	private String ebita;
	private String dividend_per_share;
	private String dividend_yield;
	private String earnings_per_share;
	private String year_high;
	private String year_low;
	private String fiftyday_moving_avg;
	private String twohundredday_moving_avg;
	private String price_earnings_ratio;
	private String price_earnings_growth_ratio;
	private String price_sales_ratio;
	private String price_book_ratio;
	private String short_ratio;
	private String percent_change;
	private String revenue;
	private String eps_estimate_current_yr;
	private String eps_estimate_next_yr;
	private String eps_estimate_next_quarter;
	private String days_low;
	private String days_high;
	private String one_yr_target;
	private String last_trade_date;
	private String[] five_day;
	private String[] one_month;
	private String[] three_month;
	private String[] six_month;
	private String[] one_year;
	private String[] YTD;
	private String[] five_year;
	private String[] ten_year;
	private String shares_outstanding;

	
	
	
	
	public YStockQuote(String symbol)
	{
		String url = "http://finance.yahoo.com/d/quotes.csv?s=";
		
		url += symbol;
			
		url += "&f=" + "snl1c6va2xj1b4j4dyekjm3m4rr5p5p6s7p2s6e7e8e9ght8d1j2";
		InputStream input;
		try {
			input = new URL(url).openStream();
			Scanner s = new Scanner(input);
			s.useDelimiter("\\A");
			String csv = s.hasNext() ? s.next() : "";
			s.close();
			input.close();
			csv = csv.replace("\"", "");
			String[] data = csv.split(",");
			
			symbol = data[0];
			name = data[1];
			price = data[2];
			change = data[3];
			volume = data[4];
			avg_daily_volume = data[5];
			stock_exchange = data[6];
			market_cap = data[7];
			book_value = data[8];
			ebita = data[9];
			dividend_per_share = data[10];
			dividend_yield = data[11];
			earnings_per_share = data[12];
			year_high = data[13];
			year_low = data[14];
			fiftyday_moving_avg = data[15];
			twohundredday_moving_avg = data[16];
			price_earnings_ratio = data[17];
			price_earnings_growth_ratio = data[18];
			price_sales_ratio = data[19];
			price_book_ratio = data[20];
			short_ratio = data[21];
			percent_change = data[22];
			revenue = data[23];
			eps_estimate_current_yr = data[24];
			eps_estimate_next_yr = data[25];
			eps_estimate_next_quarter = data[26];
			days_low = data[27];
			days_high = data[28];
			one_yr_target = data[29];
			last_trade_date = data[30];
			int len = data.length;
			int i;
			shares_outstanding = data[31];
			for (i = 31; i < len; i++)
				shares_outstanding += data[i];
			shares_outstanding = shares_outstanding.replace("\r\n", "");
			shares_outstanding = shares_outstanding.replace(" ", "");

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public String get_symbol()
	{
		return this.symbol;
	}
	
	public String get_name()
	{
		return this.name;
	}

	public String get_price()
	{
		return this.price;
	}

	public String get_change()
	{
		return this.change;
	}

	public String get_volume()
	{
		return this.volume;
	}

	public String get_avg_daily_volume()
	{
		return this.avg_daily_volume;
	}

	public String get_stock_exchange()
	{
		return this.stock_exchange;
	}

	public String get_market_cap()
	{
		return this.market_cap;
	}

	public String get_book_value()
	{
		return this.book_value;
	}

	public String get_ebitda()
	{
		return this.ebita;
	}

	public String get_dividend_per_share()
	{
		return this.dividend_per_share;
	}
	
	public String get_dividend_yield()
	{
		return this.dividend_yield;
	}
	
	public String get_earnings_per_share()
	{
		return this.earnings_per_share;
	}
	
	public String get_year_high()
	{
		return this.year_high;
	}
	
	public String get_year_low()
	{
		return this.year_low;
	}
	
	public String get_fiftyday_moving_avg()
	{
		return this.fiftyday_moving_avg;
	}
	
	public String get_twohundredday_moving_avg()
	{
		return this.twohundredday_moving_avg;
	}
	
	public String get_price_earnings_ratio()
	{
		return this.price_earnings_ratio;
	}
	
	public String get_price_earnings_growth_ratio()
	{
		return this.price_earnings_growth_ratio;
	}
	
	public String get_price_sales_ratio()
	{
		return this.price_sales_ratio;
	}
	
	public String get_price_book_ratio()
	{
		return this.price_book_ratio;
	}
	
	public String get_short_ratio()
	{
		return this.short_ratio;
	}

	public String get_percent_change()
	{
		return this.percent_change;
	}
	
	public String get_revenue()
	{
		return this.revenue;
	}
	
	public String get_eps_estimate_current_yr()
	{
		return this.eps_estimate_current_yr;
	}
	
	public String get_eps_estimate_next_yr()
	{
		return this.eps_estimate_next_yr;
	}
	
	public String get_eps_estimate_next_quarter()
	{
		return this.eps_estimate_next_quarter;
	}
	
	public String get_days_low()
	{
		return this.days_low;
	}
	
	public String get_days_high()
	{
		return this.days_high;
	}
	
	public String get_one_yr_target()
	{
		return this.one_yr_target;
	}
	
	public String get_last_trade_date()
	{
		return this.last_trade_date;
	}
	
	public String get_shares_outstanding()
	{
		return this.shares_outstanding;
	}
	
	public void find_ten_year_change(String day, String month, String year) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String url = "http://ichart.finance.yahoo.com/table.csv?s=WU&a=";
		
		url += month + "&b=" + day + "&c=" + year + "&d=";
		url += month + "&e=" + day + "&f=" + year + "&g=d&ignore=.csv";
		InputStream input;
		input = new URL(url).openStream();
		Scanner s = new Scanner(input);
		s.useDelimiter("\\A");
		String csv = s.hasNext() ? s.next() : "";
		s.close();
		input.close();
		csv = csv.replace("\"", "");
		String data[] = csv.split(",");
		double ten_year_price = Double.parseDouble(data[4]);
		double current_price = Double.parseDouble(price);
		double change = current_price - ten_year_price;
		double percent = Math.round(change/current_price * 100 *100.0)/100.0;
		ten_year[0] = String.valueOf(change);
		ten_year[1] = String.valueOf(percent) + "%";

	}

	public void find_ytd_change(String day, String month, String year) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String url = "http://ichart.finance.yahoo.com/table.csv?s=WU&a=";
		
		url += month + "&b=" + day + "&c=" + year + "&d=";
		url += month + "&e=" + day + "&f=" + year + "&g=d&ignore=.csv";
		InputStream input;
		input = new URL(url).openStream();
		Scanner s = new Scanner(input);
		s.useDelimiter("\\A");
		String csv = s.hasNext() ? s.next() : "";
		s.close();
		input.close();
		csv = csv.replace("\"", "");
		String data[] = csv.split(",");
		double ytd_price = Double.parseDouble(data[4]);
		double current_price = Double.parseDouble(price);
		double change = current_price - ytd_price;
		double percent = Math.round(change/current_price * 100 *100.0)/100.0;
		YTD[0] = String.valueOf(change);
		YTD[1] = String.valueOf(percent) + "%";
	}

	public void find_five_year_change(String day, String month, String year) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String url = "http://ichart.finance.yahoo.com/table.csv?s=WU&a=";
		
		url += month + "&b=" + day + "&c=" + year + "&d=";
		url += month + "&e=" + day + "&f=" + year + "&g=d&ignore=.csv";
		InputStream input;
		input = new URL(url).openStream();
		Scanner s = new Scanner(input);
		s.useDelimiter("\\A");
		String csv = s.hasNext() ? s.next() : "";
		s.close();
		input.close();
		csv = csv.replace("\"", "");
		String data[] = csv.split(",");
		double five_year_price = Double.parseDouble(data[4]);
		double current_price = Double.parseDouble(price);
		double change = current_price - five_year_price;
		double percent = Math.round(change/current_price * 100 *100.0)/100.0;
		five_year[0] = String.valueOf(change);
		five_year[1] = String.valueOf(percent) + "%";
	}

	public void find_one_year_change(String day, String month, String year) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String url = "http://ichart.finance.yahoo.com/table.csv?s=WU&a=";
		
		url += month + "&b=" + day + "&c=" + year + "&d=";
		url += month + "&e=" + day + "&f=" + year + "&g=d&ignore=.csv";
		InputStream input;
		input = new URL(url).openStream();
		Scanner s = new Scanner(input);
		s.useDelimiter("\\A");
		String csv = s.hasNext() ? s.next() : "";
		s.close();
		input.close();
		csv = csv.replace("\"", "");
		String data[] = csv.split(",");
		double one_year_price = Double.parseDouble(data[4]);
		double current_price = Double.parseDouble(price);
		double change = current_price - one_year_price;
		double percent = Math.round(change/current_price * 100 *100.0)/100.0;
		one_year[0] = String.valueOf(change);
		one_year[1] = String.valueOf(percent) + "%";
	}

	public void find_six_month_change(String day, String month, String year) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String url = "http://ichart.finance.yahoo.com/table.csv?s=WU&a=";
		
		url += month + "&b=" + day + "&c=" + year + "&d=";
		url += month + "&e=" + day + "&f=" + year + "&g=d&ignore=.csv";
		InputStream input;
		input = new URL(url).openStream();
		Scanner s = new Scanner(input);
		s.useDelimiter("\\A");
		String csv = s.hasNext() ? s.next() : "";
		s.close();
		input.close();
		csv = csv.replace("\"", "");
		String data[] = csv.split(",");
		double six_month_price = Double.parseDouble(data[4]);
		double current_price = Double.parseDouble(price);
		double change = current_price - six_month_price;
		double percent = Math.round(change/current_price * 100 *100.0)/100.0;
		six_month[0] = String.valueOf(change);
		six_month[1] = String.valueOf(percent) + "%";
	}

	public void find_three_month_change(String day, String month, String year) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String url = "http://ichart.finance.yahoo.com/table.csv?s=WU&a=";
		
		url += month + "&b=" + day + "&c=" + year + "&d=";
		url += month + "&e=" + day + "&f=" + year + "&g=d&ignore=.csv";
		InputStream input;
		input = new URL(url).openStream();
		Scanner s = new Scanner(input);
		s.useDelimiter("\\A");
		String csv = s.hasNext() ? s.next() : "";
		s.close();
		input.close();
		csv = csv.replace("\"", "");
		String data[] = csv.split(",");
		double three_month_price = Double.parseDouble(data[4]);
		double current_price = Double.parseDouble(price);
		double change = current_price - three_month_price;
		double percent = Math.round(change/current_price * 100 *100.0)/100.0;
		three_month[0] = String.valueOf(change);
		three_month[1] = String.valueOf(percent) + "%";
	}

	public void find_one_month_change(String day, String month, String year) throws IOException {
		// TODO Auto-generated method stub
		String url = "http://ichart.finance.yahoo.com/table.csv?s=WU&a=";
		
		url += month + "&b=" + day + "&c=" + year + "&d=";
		url += month + "&e=" + day + "&f=" + year + "&g=d&ignore=.csv";
		InputStream input;
		input = new URL(url).openStream();
		Scanner s = new Scanner(input);
		s.useDelimiter("\\A");
		String csv = s.hasNext() ? s.next() : "";
		s.close();
		input.close();
		csv = csv.replace("\"", "");
		String data[] = csv.split(",");
		double one_month_price = Double.parseDouble(data[4]);
		double current_price = Double.parseDouble(price);
		double change = current_price - one_month_price;
		double percent = Math.round(change/current_price * 100 *100.0)/100.0;
		one_month[0] = String.valueOf(change);
		one_month[1] = String.valueOf(percent) + "%";
	}

	public void find_five_day_change(String day, String month, String year) throws MalformedURLException, IOException {
		String url = "http://ichart.finance.yahoo.com/table.csv?s=WU&a=";
		
		url += month + "&b=" + day + "&c=" + year + "&d=";
		url += month + "&e=" + day + "&f=" + year + "&g=d&ignore=.csv";
		InputStream input;
		input = new URL(url).openStream();
		Scanner s = new Scanner(input);
		s.useDelimiter("\\A");
		String csv = s.hasNext() ? s.next() : "";
		s.close();
		input.close();
		csv = csv.replace("\"", "");
		String data[] = csv.split(",");
		double five_day_price = Double.parseDouble(data[4]);
		double current_price = Double.parseDouble(price);
		double change = current_price - five_day_price;
		double percent = Math.round(change/current_price * 100 *100.0)/100.0;
		five_day[0] = String.valueOf(change);
		five_day[1] = String.valueOf(percent) + "%";
	}
	
	public String[] get_ten_year_change() {
		// TODO Auto-generated method stub
		return this.ten_year;
	}

	public String[] get_ytd_change() {
		// TODO Auto-generated method stub
		return this.YTD;
	}

	public String[] get_five_year_change() {
		// TODO Auto-generated method stub
		return this.five_year;
	}

	public String[] get_one_year_change() {
		// TODO Auto-generated method stub
		return this.one_year;
	}

	public String[] get_six_month_change() {
		// TODO Auto-generated method stub
		return this.six_month;
	}

	public String[] get_three_month_change() {
		// TODO Auto-generated method stub
		return this.three_month;
	}

	public String[] get_one_month_change() {
		// TODO Auto-generated method stub
		return this.one_month;
	}

	public String[] get_five_day_change() {
		return this.five_day;
	}
	
	
    
}
