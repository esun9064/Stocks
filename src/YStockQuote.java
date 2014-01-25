import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/*
 * Class holds stock information
 */

public class YStockQuote {

	/*
	 * Stock data
	 */
	private String name, symbol, price, change, volume;
	private String avg_daily_volume, stock_exchange, market_cap;
	private String book_value, ebita, dividend_per_share, dividend_yield;
	private String earnings_per_share, year_high, year_low, fiftyday_moving_avg;
	private String twohundredday_moving_avg, price_earnings_ratio;
	private String price_earnings_growth_ratio, price_sales_ratio;
	private String price_book_ratio, short_ratio, percent_change;
	private String revenue, eps_estimate_current_yr, eps_estimate_next_yr;
	private String eps_estimate_next_quarter, days_low, days_high;
	private String one_yr_target, last_trade_date;
	private String[] five_day, one_month, three_month, six_month;
	private String[] one_year, YTD, five_year, ten_year, max_year;
	private String shares_outstanding;
	public ArrayList<String> historical_data;
	
	public YStockQuote(String ticker) {
		String url = "http://finance.yahoo.com/d/quotes.csv?s=";
		url += ticker;		
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
			e.printStackTrace();
		}
	}
	/*
	 * Array list of comma separated values Date, Open, High, Low, Close, Volume, Adjusted Close
	 */
	public void find_historical_data(int day, int month, int year) throws MalformedURLException, IOException {
		try {
			String url = "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&a=0&b=1&c=1970&d=";
			url += month + "&e=" + day + "&f=" + year + "&g=d&ignore=.csv";
			InputStream input;
			input = new URL(url).openStream();
			Scanner s = new Scanner(input);
			s.useDelimiter("\\A");
			String csv = s.hasNext() ? s.next() : "";
			s.close();
			input.close();
			csv = csv.replace("\"", "");
			historical_data = new ArrayList<String>(Arrays.asList(csv.split("\n")));
		}
		catch(Exception ex) {
			System.out.println("Could not retrieve data");
		}
	}
	
	/*
	 * Uses binary search to find data from a particular date. 
	 * @param	Date	String format in yyyy/MM/dd
	 */
	public String find_data_by_date(String date) {
		int min = 0;
		int max = historical_data.size() -1;
		while (max >= min) {
			int mid = min + (max - min) /2;
			String y = historical_data.get(mid);
			if (historical_data.get(mid).contains(date)) {
				return historical_data.get(mid);
			}
			else if (historical_data.get(mid).compareTo(date) < 0) //lower half of array
				max = mid - 1;
			else //upper half
				min = mid + 1;
		}
		return "FAIL";
	}
	
	public void find_max_change() {
		int max = historical_data.size() - 1;
		String[] data = historical_data.get(max).split(",");
		double max_year_price = Double.parseDouble(data[data.length - 1]);
		double current_price = Double.parseDouble(price);
		double change = current_price - max_year_price;
		change = Math.round(change * 100.0) / 100.0;
		double percent = Math.round(change/current_price * 100 * 100.0) / 100.0;
		max_year = new String[3];
		max_year[0] = String.valueOf(change);
		max_year[1] = String.valueOf(percent);
		max_year[2] = data[0];
	}
	
	/*
	 * Historical price based on adjusted close on date
	 * @param	Date	date of close price
	 */
	public void find_ten_year_change(String date) {
		int max = historical_data.size() -1;
		if (historical_data.get(max).compareTo(date) > 0) {
			String[] data = historical_data.get(max).split(",");
			double ten_year_price = Double.parseDouble(data[data.length-1]);
			double current_price = Double.parseDouble(price);
			double change = current_price - ten_year_price;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/current_price * 100 *100.0)/100.0;
			ten_year = new String[3];
			ten_year[0] = String.valueOf(change);
			ten_year[1] = String.valueOf(percent);	
			ten_year[2] = data[0];
		}
		else {
			String entries = this.find_data_by_date(date);
			if (!entries.equals("FAIL")) {
				String[] data = entries.split(",");
				double ten_year_price = Double.parseDouble(data[data.length-1]);
				double current_price = Double.parseDouble(price);
				double change = current_price - ten_year_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/current_price * 100 *100.0)/100.0;
				ten_year = new String[3];
				ten_year[0] = String.valueOf(change);
				ten_year[1] = String.valueOf(percent);	
				ten_year[2] = data[0];
			}
		}
	}

	public void find_ytd_change(String date) {
		int max = historical_data.size() -1;
		if (historical_data.get(max).compareTo(date) > 0) {
			String[] data = historical_data.get(max).split(",");
			double ytd_price = Double.parseDouble(data[data.length-1]);
			double current_price = Double.parseDouble(price);
			double change = current_price - ytd_price;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/current_price * 100 *100.0)/100.0;
			YTD = new String[3];
			YTD[0] = String.valueOf(change);
			YTD[1] = String.valueOf(percent);
			YTD[2] = data[0];
		}
		else {
			String entries = this.find_data_by_date(date);
			if (!entries.equals("FAIL")) {
				String[] data = entries.split(",");	
				double ytd_price = Double.parseDouble(data[data.length-1]);
				double current_price = Double.parseDouble(price);
				double change = current_price - ytd_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/current_price * 100 *100.0)/100.0;
				YTD = new String[3];
				YTD[0] = String.valueOf(change);
				YTD[1] = String.valueOf(percent);
				YTD[2] = data[0];
			}
		}
	}

	public void find_five_year_change(String date) {
		int max = historical_data.size() -1;
		if (historical_data.get(max).compareTo(date) > 0) {
			String[] data = historical_data.get(max).split(",");
			double five_year_price = Double.parseDouble(data[data.length-1]);
			double current_price = Double.parseDouble(price);
			double change = current_price - five_year_price;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/current_price * 100 *100.0)/100.0;
			five_year = new String[3];
			five_year[0] = String.valueOf(change);
			five_year[1] = String.valueOf(percent);
			five_year[2] = data[0];
		}
		else {
			String entries = this.find_data_by_date(date);
			if (!entries.equals("FAIL")) {
				String[] data = entries.split(",");
				double five_year_price = Double.parseDouble(data[data.length-1]);
				double current_price = Double.parseDouble(price);
				double change = current_price - five_year_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/current_price * 100 *100.0)/100.0;
				five_year = new String[3];
				five_year[0] = String.valueOf(change);
				five_year[1] = String.valueOf(percent);
				five_year[2] = data[0];
			}
		}
	}

	public void find_one_year_change(String date) {
		int max = historical_data.size() -1;
		if (historical_data.get(max).compareTo(date) > 0) {
			String[] data = historical_data.get(max).split(",");
			double one_year_price = Double.parseDouble(data[data.length-1]);
			double current_price = Double.parseDouble(price);
			double change = current_price - one_year_price;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/current_price * 100 *100.0)/100.0;
			one_year = new String[3];
			one_year[0] = String.valueOf(change);
			one_year[1] = String.valueOf(percent);
			one_year[2] = data[0];
		}
		else {
			String entries = this.find_data_by_date(date);
			if (!entries.equals("FAIL")) {
				String[] data = entries.split(",");
				double one_year_price = Double.parseDouble(data[data.length-1]);
				double current_price = Double.parseDouble(price);
				double change = current_price - one_year_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/current_price * 100 *100.0)/100.0;
				one_year = new String[3];
				one_year[0] = String.valueOf(change);
				one_year[1] = String.valueOf(percent);
				one_year[2] = data[0];
			}
		}
	}

	public void find_six_month_change(String date) {
		int max = historical_data.size() -1;
		if (historical_data.get(max).compareTo(date) > 0) {
			String[] data = historical_data.get(max).split(",");
			double six_month_price = Double.parseDouble(data[data.length-1]);
			double current_price = Double.parseDouble(price);
			double change = current_price - six_month_price;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/current_price * 100 *100.0)/100.0;
			six_month = new String[3];
			six_month[0] = String.valueOf(change);
			six_month[1] = String.valueOf(percent);
			six_month[2] = data[0];
		}
		else {
			String entries = this.find_data_by_date(date);
			if (!entries.equals("FAIL")) {
				String[] data = entries.split(",");
				double six_month_price = Double.parseDouble(data[data.length-1]);
				double current_price = Double.parseDouble(price);
				double change = current_price - six_month_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/current_price * 100 *100.0)/100.0;
				six_month = new String[3];
				six_month[0] = String.valueOf(change);
				six_month[1] = String.valueOf(percent);
				six_month[2] = data[0];
			}
		}
	}

	public void find_three_month_change(String date) {
		int max = historical_data.size() -1;
		if (historical_data.get(max).compareTo(date) > 0) {
			String[] data = historical_data.get(max).split(",");
			double three_month_price = Double.parseDouble(data[data.length-1]);
			double current_price = Double.parseDouble(price);
			double change = current_price - three_month_price;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/current_price * 100 *100.0)/100.0;
			three_month = new String[3];
			three_month[0] = String.valueOf(change);
			three_month[1] = String.valueOf(percent);
			three_month[2] = data[0];
		}
		else {
			String entries = this.find_data_by_date(date);
			if (!entries.equals("FAIL")) {
				String[] data = entries.split(",");
				double three_month_price = Double.parseDouble(data[data.length-1]);
				double current_price = Double.parseDouble(price);
				double change = current_price - three_month_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/current_price * 100 *100.0)/100.0;
				three_month = new String[3];
				three_month[0] = String.valueOf(change);
				three_month[1] = String.valueOf(percent);
				three_month[2] = data[0];
			}
		}
	}

	public void find_one_month_change(String date) {
		int max = historical_data.size() -1;
		if (historical_data.get(max).compareTo(date) > 0) {
			String[] data = historical_data.get(max).split(",");
			double one_month_price = Double.parseDouble(data[data.length-1]);
			double current_price = Double.parseDouble(price);
			double change = current_price - one_month_price;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/current_price * 100 *100.0)/100.0;
			one_month = new String[3];
			one_month[0] = String.valueOf(change);
			one_month[1] = String.valueOf(percent);
			one_month[2] = data[0];
		}
		else {
			String entries = this.find_data_by_date(date);
			if (!entries.equals("FAIL")) {
				String[] data = entries.split(",");
				double one_month_price = Double.parseDouble(data[data.length-1]);
				double current_price = Double.parseDouble(price);
				double change = current_price - one_month_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/current_price * 100 *100.0)/100.0;
				one_month = new String[3];
				one_month[0] = String.valueOf(change);
				one_month[1] = String.valueOf(percent);
				one_month[2] = data[0];
			}
		}
	}

	public void find_five_day_change(String date) {
		int max = historical_data.size() -1;
		if (historical_data.get(max).compareTo(date) > 0) {
			String[] data = historical_data.get(max).split(",");
			double five_day_price = Double.parseDouble(data[data.length-1]);
			double current_price = Double.parseDouble(price);
			double change = current_price - five_day_price;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/current_price * 100 *100.0)/100.0;
			five_day = new String[3];
			five_day[0] = String.valueOf(change);
			five_day[1] = String.valueOf(percent);	
			five_day[2] = data[0];
		}
		else {
			String entries = this.find_data_by_date(date);
			if (!entries.equals("FAIL")) {
				String[] data = entries.split(",");
				double five_day_price = Double.parseDouble(data[data.length-1]);
				double current_price = Double.parseDouble(price);
				double change = current_price - five_day_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/current_price * 100 *100.0)/100.0;
				five_day = new String[3];
				five_day[0] = String.valueOf(change);
				five_day[1] = String.valueOf(percent);	
				five_day[2] = data[0];
			}
		}
	}
	
	public String[] get_max_year_change() {
		return this.max_year;
	}
	
	public String[] get_ten_year_change() {
		return this.ten_year;
	}

	public String[] get_ytd_change() {
		return this.YTD;
	}

	public String[] get_five_year_change() {
		return this.five_year;
	}

	public String[] get_one_year_change() {
		return this.one_year;
	}

	public String[] get_six_month_change() {
		return this.six_month;
	}

	public String[] get_three_month_change() {
		return this.three_month;
	}

	public String[] get_one_month_change() {
		return this.one_month;
	}

	public String[] get_five_day_change() {
		return this.five_day;
	}
	
	public String get_symbol() {
		return this.symbol;
	}
	
	public String get_name() {
		return this.name;
	}

	public String get_price() {
		return this.price;
	}

	public String get_change() {
		return this.change;
	}

	public String get_volume() {
		return this.volume;
	}

	public String get_avg_daily_volume() {
		return this.avg_daily_volume;
	}

	public String get_stock_exchange() {
		return this.stock_exchange;
	}

	public String get_market_cap() {
		return this.market_cap;
	}

	public String get_book_value() {
		return this.book_value;
	}

	public String get_ebitda() {
		return this.ebita;
	}

	public String get_dividend_per_share() {
		return this.dividend_per_share;
	}
	
	public String get_dividend_yield() {
		return this.dividend_yield;
	}
	
	public String get_earnings_per_share() {
		return this.earnings_per_share;
	}
	
	public String get_year_high() {
		return this.year_high;
	}
	
	public String get_year_low() {
		return this.year_low;
	}
	
	public String get_fiftyday_moving_avg() {
		return this.fiftyday_moving_avg;
	}
	
	public String get_twohundredday_moving_avg() {
		return this.twohundredday_moving_avg;
	}
	
	public String get_price_earnings_ratio() {
		return this.price_earnings_ratio;
	}
	
	public String get_price_earnings_growth_ratio() {
		return this.price_earnings_growth_ratio;
	}
	
	public String get_price_sales_ratio() {
		return this.price_sales_ratio;
	}
	
	public String get_price_book_ratio() {
		return this.price_book_ratio;
	}
	
	public String get_short_ratio() {
		return this.short_ratio;
	}

	public String get_percent_change() {
		return this.percent_change;
	}
	
	public String get_revenue() {
		return this.revenue;
	}
	
	public String get_eps_estimate_current_yr() {
		return this.eps_estimate_current_yr;
	}
	
	public String get_eps_estimate_next_yr() {
		return this.eps_estimate_next_yr;
	}
	
	public String get_eps_estimate_next_quarter() {
		return this.eps_estimate_next_quarter;
	}
	
	public String get_days_low() {
		return this.days_low;
	}
	
	public String get_days_high() {
		return this.days_high;
	}
	
	public String get_one_yr_target() {
		return this.one_yr_target;
	}
	
	public String get_last_trade_date() {
		return this.last_trade_date;
	}
	
	public String get_shares_outstanding() {
		return this.shares_outstanding;
	}
}
