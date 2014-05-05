import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.*;
/*
 * Class holds stock information
 */

public class YStockQuote {

    /*
     * Stock data
     */
    private String ticker;
  
	
	private String Symbol, Name, LastTradePriceOnly, LastTradeTime;
	private String LastTradeDate, Change, PercentChange, Volume;
	private String AverageDailyVolume, Bid, Ask, PreviousClose, Open, DaysRange;
	private String YearRange, EarningsShare, PERatio, DividendPayDate;
	private String DividendShare, DividendYield, MarketCapitalization;
	private String StockExchange, ShortRatio, OneyrTargetPrice, EPSEstimateCurrentYear;
	private String PEGRatio, PriceBook, PriceSales, EBITDA, FiftydayMovingAverage;    
	private String TwoHundreddayMovingAverage, AskRealtime, BidRealtime;		
	private String ChangeInPercentRealtime, LastTradeRealtimeWithTime, Revenue;    
	private String ChangeRealtime, DaysRangeRealtime, MarketCapRealtime;
	
    private String[] five_day, one_month, three_month, six_month;
    private String[] one_year, YTD, five_year, ten_year, max_year;
    public ArrayList<String> historical_data;

    public YStockQuote(String ticker) {
        this.ticker = ticker;
        this.update();
    }
	
	public static String readUrl(String urlString) throws Exception
	{
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuilder buffer = new StringBuilder();		
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			return buffer.toString();
		}
		catch(Exception ex)
		{
			return "FAIL";
		}
	}
	
	public void update() 
	{
		String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22" +
				this.ticker + "%22)%0A%09%09&" +
				"format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=";
		try {
			String jsonString = readUrl(url);
			
			
			
			JSONObject obj =(JSONObject)(new JSONParser().parse(jsonString));
			JSONObject query = (JSONObject) obj.get("query");
			JSONObject results = (JSONObject) query.get("results");
			JSONObject quote = (JSONObject) results.get("quote");
			
			
			this.Symbol = (String) quote.get("Symbol");
			this.Name = (String) quote.get("Name");
			this.LastTradePriceOnly = (String) quote.get("LastTradePriceOnly");
			this.LastTradeTime = (String) quote.get("LastTradeTime");
			this.LastTradeDate = (String) quote.get("LastTradeDate");
			this.Change = (String) quote.get("Change");
			this.PercentChange = (String) quote.get("PercentChange");
			this.Volume = (String) quote.get("Volume");
			this.AverageDailyVolume = (String) quote.get("AverageDailyVolume");
			this.Bid = (String) quote.get("Bid");
			this.Ask = (String) quote.get("Ask");
			this.PreviousClose = (String) quote.get("PreviousClose");
			this.Open = (String) quote.get("Open");
			this.DaysRange = (String) quote.get("DaysRange");
			this.YearRange = (String) quote.get("YearRange");
			this.EarningsShare = (String) quote.get("EarningsShare");
			this.PERatio = (String) quote.get("PERatio");				
			this.DividendPayDate = (String) quote.get("DividendPayDate");
			this.DividendShare = (String) quote.get("DividendShare");		
			this.DividendYield = (String) quote.get("DividendYield");
			this.MarketCapitalization = (String) quote.get("MarketCapitalization");
			this.StockExchange = (String) quote.get("StockExchange");
			this.Revenue = (String) quote.get("Revenue");
			this.ShortRatio = (String) quote.get("ShortRatio");
			this.OneyrTargetPrice = (String) quote.get("OneyrTargetPrice");			
			this.EPSEstimateCurrentYear = (String) quote.get("EPSEstimateCurrentYear");
			this.PEGRatio = (String) quote.get("PEGRatio");
			this.PriceBook = (String) quote.get("PriceBook");    
			this.PriceSales = (String) quote.get("PriceSales");		
			this.EBITDA = (String) quote.get("EBITDA");		
			this.FiftydayMovingAverage = (String) quote.get("FiftydayMovingAverage");    
			this.TwoHundreddayMovingAverage = (String) quote.get("TwoHundreddayMovingAverage");
			this.AskRealtime = (String) quote.get("AskRealtime");
			this.BidRealtime = (String) quote.get("BidRealtime");		
			this.ChangeInPercentRealtime = (String) quote.get("ChangeInPercentRealtime");    
			this.LastTradeRealtimeWithTime = (String) quote.get("LastTradeRealtimeWithTime");    
			this.ChangeRealtime = (String) quote.get("ChangeRealtime");    
			this.DaysRangeRealtime = (String) quote.get("DaysRangeRealtime");
			this.MarketCapRealtime = (String) quote.get("MarketCapRealtime");		
		}
		
		catch (Exception e)
		{
			
		}
	}
    
    /*
     * Array list of comma separated values Date, Open, High, Low, Close, Volume, Adjusted Close
     */
    public void find_historical_data(int day, int month, int year) 
	{
        try {
            String url = "http://ichart.finance.yahoo.com/table.csv?s=" + this.Symbol + "&a=0&b=1&c=1970&d=";
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
            System.out.println("Error with connection");

        }
    }

    /*
     * Uses binary search to find data from a particular date. 
     * @param	Date	String format in yyyy/MM/dd
     */
    public String find_data_by_date(String date) 
	{
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
    /*
     * Five day change, will use the previous day close if possible
     */
    public String find_data_by_date(String date, int check) 
	{
        int min = 0;
        int max = historical_data.size() -1;
        while (max >= min) {
            int mid = min + (max - min) /2;
            String y = historical_data.get(mid);
            if (historical_data.get(mid).contains(date)) {
            	if ((mid + 1) <= (historical_data.size() - 1))
            		return historical_data.get(mid+1) + ",previous close";
            	else
            		return historical_data.get(mid) + ",current open";
            }
            else if (historical_data.get(mid).compareTo(date) < 0) //lower half of array
                max = mid - 1;
            else //upper half
                min = mid + 1;
        }
        return "FAIL";
    }
    

    public void find_max_change() 
	{
        int max = historical_data.size() - 1;
        String[] data = historical_data.get(max).split(",");
        double max_year_price = Double.parseDouble(data[data.length - 1]);
        double current_price = Double.parseDouble(LastTradePriceOnly);
        double change = current_price - max_year_price;
        change = Math.round(change * 100.0) / 100.0;
        double percent = Math.round(change/max_year_price * 100 * 100.0) / 100.0;
        max_year = new String[3];
        max_year[0] = String.valueOf(change);
        max_year[1] = String.valueOf(percent);
        max_year[2] = data[0];
    }

    /**
     * Historical price based on adjusted close on date
     * @param	Date	date of close price
     */
    public void find_ten_year_change(String date) 
	{
        int max = historical_data.size() -1;
        if (historical_data.get(max).compareTo(date) > 0) 
		{
            String[] data = historical_data.get(max).split(",");
            double ten_year_price = Double.parseDouble(data[data.length-1]);
            double current_price = Double.parseDouble(LastTradePriceOnly);
            double change = current_price - ten_year_price;
            change = Math.round(change * 100.0) / 100.0;
            double percent = Math.round(change/ten_year_price * 100 *100.0)/100.0;
            ten_year = new String[3];
            ten_year[0] = String.valueOf(change);
            ten_year[1] = String.valueOf(percent);	
            ten_year[2] = data[0];
        }
        else {
            String entries = this.find_data_by_date(date);
            if (!entries.equals("FAIL")) 
			{
                String[] data = entries.split(",");
                double ten_year_price = Double.parseDouble(data[data.length-1]);
                double current_price = Double.parseDouble(LastTradePriceOnly);
                double change = current_price - ten_year_price;
                change = Math.round(change * 100.0) / 100.0;
                double percent = Math.round(change/ten_year_price * 100 *100.0)/100.0;
                ten_year = new String[3];
                ten_year[0] = String.valueOf(change);
                ten_year[1] = String.valueOf(percent);	
                ten_year[2] = data[0];
            }
        }
    }

    public void find_ytd_change(String date) 
	{
        int max = historical_data.size() -1;
        if (historical_data.get(max).compareTo(date) > 0) 
		{
            String[] data = historical_data.get(max).split(",");
            double ytd_price = Double.parseDouble(data[data.length-1]);
            double current_price = Double.parseDouble(LastTradePriceOnly);
            double change = current_price - ytd_price;
            change = Math.round(change * 100.0) / 100.0;
            double percent = Math.round(change/ytd_price * 100 *100.0)/100.0;
            YTD = new String[3];
            YTD[0] = String.valueOf(change);
            YTD[1] = String.valueOf(percent);
            YTD[2] = data[0];
        }
        else 
		{
            String entries = this.find_data_by_date(date);
            if (!entries.equals("FAIL")) 
			{
                String[] data = entries.split(",");	
                double ytd_price = Double.parseDouble(data[data.length-1]);
                double current_price = Double.parseDouble(LastTradePriceOnly);
                double change = current_price - ytd_price;
                change = Math.round(change * 100.0) / 100.0;
                double percent = Math.round(change/ytd_price * 100 *100.0)/100.0;
                YTD = new String[3];
                YTD[0] = String.valueOf(change);
                YTD[1] = String.valueOf(percent);
                YTD[2] = data[0];
            }
        }
    }

    public void find_five_year_change(String date) 
	{
        int max = historical_data.size() -1;
        if (historical_data.get(max).compareTo(date) > 0) 
		{
            String[] data = historical_data.get(max).split(",");
            double five_year_price = Double.parseDouble(data[data.length-1]);
            double current_price = Double.parseDouble(LastTradePriceOnly);
            double change = current_price - five_year_price;
            change = Math.round(change * 100.0) / 100.0;
            double percent = Math.round(change/five_year_price * 100 *100.0)/100.0;
            five_year = new String[3];
            five_year[0] = String.valueOf(change);
            five_year[1] = String.valueOf(percent);
            five_year[2] = data[0];
        }
        else 
		{
            String entries = this.find_data_by_date(date);
            if (!entries.equals("FAIL")) {
                String[] data = entries.split(",");
                double five_year_price = Double.parseDouble(data[data.length-1]);
                double current_price = Double.parseDouble(LastTradePriceOnly);
                double change = current_price - five_year_price;
                change = Math.round(change * 100.0) / 100.0;
                double percent = Math.round(change/five_year_price * 100 *100.0)/100.0;
                five_year = new String[3];
                five_year[0] = String.valueOf(change);
                five_year[1] = String.valueOf(percent);
                five_year[2] = data[0];
            }
        }
    }

    public void find_one_year_change(String date) 
	{
        int max = historical_data.size() -1;
        if (historical_data.get(max).compareTo(date) > 0) 
		{
            String[] data = historical_data.get(max).split(",");
            double one_year_price = Double.parseDouble(data[data.length-1]);
            double current_price = Double.parseDouble(LastTradePriceOnly);
            double change = current_price - one_year_price;
            change = Math.round(change * 100.0) / 100.0;
            double percent = Math.round(change/one_year_price * 100 *100.0)/100.0;
            one_year = new String[3];
            one_year[0] = String.valueOf(change);
            one_year[1] = String.valueOf(percent);
            one_year[2] = data[0];
        }
        else 
		{
            String entries = this.find_data_by_date(date);
            if (!entries.equals("FAIL")) 
			{
                String[] data = entries.split(",");
                double one_year_price = Double.parseDouble(data[data.length-1]);
                double current_price = Double.parseDouble(LastTradePriceOnly);
                double change = current_price - one_year_price;
                change = Math.round(change * 100.0) / 100.0;
                double percent = Math.round(change/one_year_price * 100 *100.0)/100.0;
                one_year = new String[3];
                one_year[0] = String.valueOf(change);
                one_year[1] = String.valueOf(percent);
                one_year[2] = data[0];
            }
        }
    }

    public void find_six_month_change(String date) 
	{
        int max = historical_data.size() -1;
        if (historical_data.get(max).compareTo(date) > 0) 
		{
            String[] data = historical_data.get(max).split(",");
            double six_month_price = Double.parseDouble(data[data.length-1]);
            double current_price = Double.parseDouble(LastTradePriceOnly);
            double change = current_price - six_month_price;
            change = Math.round(change * 100.0) / 100.0;
            double percent = Math.round(change/six_month_price * 100 *100.0)/100.0;
            six_month = new String[3];
            six_month[0] = String.valueOf(change);
            six_month[1] = String.valueOf(percent);
            six_month[2] = data[0];
        }
        else 
		{
            String entries = this.find_data_by_date(date);
            if (!entries.equals("FAIL")) 
			{
                String[] data = entries.split(",");
                double six_month_price = Double.parseDouble(data[data.length-1]);
                double current_price = Double.parseDouble(LastTradePriceOnly);
                double change = current_price - six_month_price;
                change = Math.round(change * 100.0) / 100.0;
                double percent = Math.round(change/six_month_price * 100 *100.0)/100.0;
                six_month = new String[3];
                six_month[0] = String.valueOf(change);
                six_month[1] = String.valueOf(percent);
                six_month[2] = data[0];
            }
        }
    }

    public void find_three_month_change(String date)
	{
        int max = historical_data.size() -1;
        if (historical_data.get(max).compareTo(date) > 0)
		{
            String[] data = historical_data.get(max).split(",");
            double three_month_price = Double.parseDouble(data[data.length-1]);
            double current_price = Double.parseDouble(LastTradePriceOnly);
            double change = current_price - three_month_price;
            change = Math.round(change * 100.0) / 100.0;
            double percent = Math.round(change/three_month_price * 100 *100.0)/100.0;
            three_month = new String[3];
            three_month[0] = String.valueOf(change);
            three_month[1] = String.valueOf(percent);
            three_month[2] = data[0];
        }
        else 
		{
            String entries = this.find_data_by_date(date);
            if (!entries.equals("FAIL"))
			{
                String[] data = entries.split(",");
                double three_month_price = Double.parseDouble(data[data.length-1]);
                double current_price = Double.parseDouble(LastTradePriceOnly);
                double change = current_price - three_month_price;
                change = Math.round(change * 100.0) / 100.0;
                double percent = Math.round(change/three_month_price * 100 *100.0)/100.0;
                three_month = new String[3];
                three_month[0] = String.valueOf(change);
                three_month[1] = String.valueOf(percent);
                three_month[2] = data[0];
            }
        }
    }

    public void find_one_month_change(String date)
	{
        int max = historical_data.size() -1;
        if (historical_data.get(max).compareTo(date) > 0)
		{
            String[] data = historical_data.get(max).split(",");
            double one_month_price = Double.parseDouble(data[data.length-1]);
            double current_price = Double.parseDouble(LastTradePriceOnly);
            double change = current_price - one_month_price;
            change = Math.round(change * 100.0) / 100.0;
            double percent = Math.round(change/one_month_price * 100 *100.0)/100.0;
            one_month = new String[3];
            one_month[0] = String.valueOf(change);
            one_month[1] = String.valueOf(percent);
            one_month[2] = data[0];
        }
        else 
		{
            String entries = this.find_data_by_date(date);
            if (!entries.equals("FAIL")) 
			{
                String[] data = entries.split(",");
                double one_month_price = Double.parseDouble(data[data.length-1]);
                double current_price = Double.parseDouble(LastTradePriceOnly);
                double change = current_price - one_month_price;
                change = Math.round(change * 100.0) / 100.0;
                double percent = Math.round(change/one_month_price * 100 *100.0)/100.0;
                one_month = new String[3];
                one_month[0] = String.valueOf(change);
                one_month[1] = String.valueOf(percent);
                one_month[2] = data[0];
            }
        }
    }

    public void find_five_day_change(String date)
	{
        
        int max = historical_data.size() -1;
        if (historical_data.get(max).compareTo(date) > 0)
		{
            String[] data = historical_data.get(max).split(",");
            double five_day_price = Double.parseDouble(data[data.length - 1]);
            double current_price = Double.parseDouble(LastTradePriceOnly);
            double change = current_price - five_day_price;
            change = Math.round(change * 100.0) / 100.0;
            double percent = Math.round(change/five_day_price * 100 *100.0)/100.0;
            five_day = new String[3];
            five_day[0] = String.valueOf(change);
            five_day[1] = String.valueOf(percent);	
            five_day[2] = data[0];
        }
        else 
		{
            String entries = this.find_data_by_date(date, 0);
            if (!entries.equals("FAIL")) 
			{
                String[] data = entries.split(",");
                double five_day_price;
                if (data[data.length - 1].equals("previous close"))
                	five_day_price = Double.parseDouble(data[data.length -2]);
                else 
                	five_day_price = Double.parseDouble(data[1]);
                double current_price = Double.parseDouble(LastTradePriceOnly);
                double change = current_price - five_day_price;
                change = Math.round(change * 100.0) / 100.0;
                double percent = Math.round(change/five_day_price * 100 *100.0)/100.0;
                five_day = new String[3];
                five_day[0] = String.valueOf(change);
                five_day[1] = String.valueOf(percent);	
                five_day[2] = data[0];
            }
        }

    }
    
    public String[] get_max_year_change() 
	{
        return this.max_year;
    }

    public String[] get_ten_year_change() 
	{
        return this.ten_year;
    }

    public String[] get_ytd_change() 
	{
        return this.YTD;
    }

    public String[] get_five_year_change() 
	{
        return this.five_year;
    }

    public String[] get_one_year_change() 
	{
        return this.one_year;
    }

    public String[] get_six_month_change() 
	{
        return this.six_month;
    }

    public String[] get_three_month_change() 
	{
        return this.three_month;
    }

    public String[] get_one_month_change() 
	{
        return this.one_month;
    }

    public String[] get_five_day_change() 
	{
        return this.five_day;
    }

    public String get_symbol() 
	{
        return this.Symbol;
    }

    public String get_name()
	{
        return this.Name;
    }

    public String get_price() 
	{
        return this.LastTradePriceOnly;
    }

    public String get_change() 
	{
        return this.Change;
    }

    public String get_volume() 
	{
        return this.Volume;
    }

    public String get_avg_daily_volume() 
	{
        return this.AverageDailyVolume;
    }

    public String get_stock_exchange() 
	{
        return this.StockExchange;
    }

    public String get_market_cap() 
	{
        return this.MarketCapitalization;
    }

    public String get_ebitda() 
	{
        return this.EBITDA;
    }

    public String get_dividend_per_share() 
	{
        return this.DividendShare;
    }

    public String get_dividend_yield() 
	{
        return this.DividendYield;
    }

    public String get_earnings_per_share() 
	{
        return this.EarningsShare;
    }

    public String get_fiftyday_moving_avg() 
	{
        return this.FiftydayMovingAverage;
    }

    public String get_twohundredday_moving_avg() 
	{
        return this.TwoHundreddayMovingAverage;
    }

    public String get_price_earnings_ratio() 
	{
        return this.PERatio;
    }

    public String get_price_sales_ratio() 
	{
        return this.PriceSales;
    }

    public String get_price_book_ratio()
	{
        return this.PriceBook;
    }

    public String get_short_ratio() 
	{
        return this.ShortRatio;
	}
    public String get_percent_change()
	{
        return this.PercentChange;
    }

    public String get_revenue() 
	{
        return this.Revenue;
    }

    public String get_last_trade_date() 
	{
        return this.LastTradeDate;
    }


}
