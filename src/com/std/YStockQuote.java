package com.std;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.jfree.data.general.DefaultPieDataset;
/*
* Class holds stock information
*/

public class YStockQuote {
	
	/*
	* Stock data
	*/
	protected String ticker;
	
	protected String Symbol, Name, LastTradePriceOnly, LastTradeTime;
	protected String LastTradeDate, Change, PercentChange, Volume;
	protected String AverageDailyVolume, Bid, Ask, PreviousClose, Open, DaysRange;
	protected String YearRange, EarningsShare, PERatio, DividendPayDate;
	protected String DividendShare, DividendYield, MarketCapitalization;
	protected String StockExchange, ShortRatio, OneyrTargetPrice, EPSEstimateCurrentYear;
	protected String EPSEstimateNextYear, EPSEstimateNextQuarter, Beta;
	protected String PEGRatio, PriceBook, PriceSales, EBITDA, FiftydayMovingAverage;
	protected String TwoHundreddayMovingAverage, AskRealtime, BidRealtime;
	protected String ChangeFromTwoHundreddayMovingAverage, PercentChangeFromTwoHundreddayMovingAverage;
	protected String ChangeFromFiftydayMovingAverage, PercentChangeFromFiftydayMovingAverage;
	protected String ChangeFromYearLow, ChangeFromYearHigh, PercentChangeFromYearLow, PercentChangeFromYearHigh;
	protected String YearLow, YearHigh, ChangeInPercentRealtime, LastTradeRealtimeWithTime, Revenue;
	protected String ChangeRealtime, DaysRangeRealtime, MarketCapRealtime;
	protected String PriceEPSEstimateCurrentYear, PriceEPSEstimateNextYear;
	
	
	protected String[] five_day, one_month, three_month, six_month;
	protected String[] one_year, YTD, five_year, ten_year, max_year;
	public ArrayList<String> historical_data;
	public double[] historical_rate_of_return;
	public ArrayList<String> intraday_1d;
	public ArrayList<String> intraday_5d;
	
	public YStockQuote()
	{
		
	}
	
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
			this.EPSEstimateNextYear = (String) quote.get("EPSEstimateNextYear");
			this.EPSEstimateNextQuarter = (String) quote.get("EPSEstimateNextQuarter");
			this.PEGRatio = (String) quote.get("PEGRatio");
			this.PriceBook = (String) quote.get("PriceBook");
			this.PriceSales = (String) quote.get("PriceSales");
			this.EBITDA = (String) quote.get("EBITDA");
			this.FiftydayMovingAverage = (String) quote.get("FiftydayMovingAverage");
			this.TwoHundreddayMovingAverage = (String) quote.get("TwoHundreddayMovingAverage");
			this.ChangeFromFiftydayMovingAverage = (String) quote.get("ChangeFromFiftydayMovingAverage");
			this.ChangeFromTwoHundreddayMovingAverage = (String) quote.get("ChangeFromTwoHundreddayMovingAverage");
			this.PercentChangeFromFiftydayMovingAverage = (String) quote.get("PercentChangeFromFiftydayMovingAverage");
			this.PercentChangeFromTwoHundreddayMovingAverage = (String) quote.get("PercentChangeFromTwoHundreddayMovingAverage");
			this.YearHigh = (String) quote.get("YearHigh");
			this.YearLow = (String) quote.get("YearLow");
			this.ChangeFromYearHigh = (String) quote.get("ChangeFromYearHigh");
			this.ChangeFromYearLow = (String) quote.get("ChangeFromYearLow");
			this.PercentChangeFromYearHigh = (String) quote.get("PercebtChangeFromYearHigh");		//json has typo
			this.PercentChangeFromYearLow = (String) quote.get("PercentChangeFromYearLow");
			this.AskRealtime = (String) quote.get("AskRealtime");
			this.BidRealtime = (String) quote.get("BidRealtime");
			this.ChangeInPercentRealtime = (String) quote.get("ChangeInPercentRealtime");
			this.LastTradeRealtimeWithTime = (String) quote.get("LastTradeRealtimeWithTime");
			this.LastTradeTime = (String) quote.get("LastTradeTime") + " ET";
			this.ChangeRealtime = (String) quote.get("ChangeRealtime");
			this.DaysRangeRealtime = (String) quote.get("DaysRangeRealtime");
			this.MarketCapRealtime = (String) quote.get("MarketCapRealtime");
			this.PriceEPSEstimateCurrentYear = (String) quote.get("PriceEPSEstimateCurrentYear");
			this.PriceEPSEstimateNextYear = (String) quote.get("PriceEPSEstimateNextYear");
		}
		
		catch (Exception e)
		{
			
		}
	}
	
	/*
	* Array list of comma separated values Date, Open, High, Low, Close, Volume, Adjusted Close
	*/
	public void find_historical_data(String month, String day, String year)
	{
		try {
			String ticker = this.ticker.replace("-", ".");	//for stock such as brk-b yahoo uses "-" while google uses "."
			String url = "http://www.google.com/finance/historical?q=" + this.StockExchange + "%3A" + this.ticker + "&startdate=Jan%201,%201970&enddate=" + month + "%20" + day + ",%20" + year + "&output=csv\n";
			InputStream input;
			input = new URL(url).openStream();
			Scanner s = new Scanner(input);
			s.useDelimiter("\\A");
			String csv = s.hasNext() ? s.next() : "";
			s.close();
			input.close();
			csv = csv.replace("\"", "");
			csv = csv.replace("Jan", "01");
			csv = csv.replace("Feb", "02");
			csv = csv.replace("Mar", "03");
			csv = csv.replace("Apr", "04");
			csv = csv.replace("May", "05");
			csv = csv.replace("Jun", "06");
			csv = csv.replace("Jul", "07");
			csv = csv.replace("Aug", "08");
			csv = csv.replace("Sep", "09");
			csv = csv.replace("Oct", "10");			
			csv = csv.replace("Nov", "11");
			csv = csv.replace("Dec", "12");
			historical_data = new ArrayList<String>(Arrays.asList(csv.split("\n")));
		}
		catch(Exception ex) {
			System.out.println("Could not retrieve historical data");
			System.out.println("Error with connection");
			
		}
	}
	/**
	 * 5d 1d
	 * @param range
	 */
	public void getIntraday()
	{
		try {
			String url = "http://chartapi.finance.yahoo.com/instrument/1.0/" + this.Symbol + "/chartdata;type=quote;range=1d/csv";
			InputStream input;
			input = new URL(url).openStream();
			Scanner s = new Scanner(input);
			s.useDelimiter("\\A");
			String csv = s.hasNext() ? s.next() : "";
			s.close();
			input.close();
			csv = csv.replace("\"", "");
			intraday_1d = new ArrayList<String>(Arrays.asList(csv.split("\n")));
			
			url = "http://chartapi.finance.yahoo.com/instrument/1.0/" + this.Symbol + "/chartdata;type=quote;range=5d/csv";
			input = new URL(url).openStream();
			s = new Scanner(input);
			s.useDelimiter("\\A");
			csv = s.hasNext() ? s.next() : "";
			s.close();
			input.close();
			csv = csv.replace("\"", "");
			intraday_5d = new ArrayList<String>(Arrays.asList(csv.split("\n")));
		}
		catch(Exception ex) {
			System.out.println("Could not retrieve intraday data");
			System.out.println("Error with connection");
			
		}
		
	}
	
	/*
	* Uses binary search to find data from a particular date.
	* @param	Date	String format in yyyy/MM/dd
	*/
	public String find_data_by_date(String date)
	{
		String[] inputDateArr = date.split("-");
		if (Integer.parseInt(inputDateArr[2]) < 70 )
			inputDateArr[2] = "20" + inputDateArr[2];
		else
			inputDateArr[2] = "19" + inputDateArr[2];
		String inputDate = inputDateArr[2] + "-" + inputDateArr[1] + "-"  + inputDateArr[0];
		int min = 1;
		int max = historical_data.size() -1;
		while (max >= min) {
			int mid = min + (max - min) /2;
			String y = historical_data.get(mid);
			if (historical_data.get(mid).contains(date)) {
				return historical_data.get(mid);
			}
			else 
			{
				String[] data = y.split(",");
				String[] checkDateArr = data[0].split("-");
				if (checkDateArr[0].length() == 1)
					checkDateArr[0] = "0" + checkDateArr[0];
				if (Integer.parseInt(checkDateArr[2]) < 70 )
					checkDateArr[2] = "20" + checkDateArr[2];
				else
					checkDateArr[2] = "19" + checkDateArr[2];				
				String checkDate = checkDateArr[2] + "-" + checkDateArr[1] + "-"  + checkDateArr[0];
				int compare = (checkDate).compareTo(inputDate);
				if (compare < 0) //lower half of array
					max = mid - 1;
				else //upper half
					min = mid + 1;
			}
		}
		return "FAIL";
	}
	
	public int find_historical_data_index(String date)
	{
		String[] inputDateArr = date.split("-");
		if (Integer.parseInt(inputDateArr[2]) < 70 )
			inputDateArr[2] = "20" + inputDateArr[2];
		else
			inputDateArr[2] = "19" + inputDateArr[2];
		String inputDate = inputDateArr[2] + "-" + inputDateArr[1] + "-"  + inputDateArr[0];
		int min = 1;
		int max = historical_data.size() -1;
		while (max >= min) {
			int mid = min + (max - min) /2;
			String y = historical_data.get(mid);
			if (historical_data.get(mid).contains(date)) {
				return mid;
			}
			else 
			{
				String[] data = y.split(",");
				String[] checkDateArr = data[0].split("-");
				if (checkDateArr[0].length() == 1)
					checkDateArr[0] = "0" + checkDateArr[0];
				if (Integer.parseInt(checkDateArr[2]) < 70 )
					checkDateArr[2] = "20" + checkDateArr[2];
				else
					checkDateArr[2] = "19" + checkDateArr[2];				
				String checkDate = checkDateArr[2] + "-" + checkDateArr[1] + "-"  + checkDateArr[0];
				int compare = (checkDate).compareTo(inputDate);
				if (compare < 0) //lower half of array
					max = mid - 1;
				else //upper half
					min = mid + 1;
			}
		}
		return historical_data.size() - 1;
	}
	
	/*
	* Five day change, will use the previous day close if possible
	*/
	public String find_data_by_date(String date, int check)
	{
		/*
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
		*/
		if (historical_data.size() == 1)
			return "FAIL";
		if (historical_data.size() < 5)
			return historical_data.get(historical_data.size() - 1);
		else
			return historical_data.get(4);
	}
	
	//change based on adjusted close, adjusted for stock splits and dividends
	public void find_max_change()
	{
		try
		{
			int max = historical_data.size() - 1;
			String[] data = historical_data.get(max).split(",");
			double max_year_price = Double.parseDouble(data[data.length - 2]);
			double current_price = Double.parseDouble(LastTradePriceOnly);
			double change = current_price - max_year_price;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/max_year_price * 100 * 100.0) / 100.0;
			max_year = new String[3];
			max_year[0] = String.valueOf(change);
			max_year[1] = String.valueOf(percent) + "%";
			max_year[2] = data[0];
		}
		catch(NullPointerException ex)
		{
			max_year = new String[3];
			max_year[0] = "";
			max_year[1] = "";
			max_year[2] = "";
		}
	}

	/**
	 * Historical price based on adjusted close on date
	 * @param	Date	date of close price
	 */
	public void find_ten_year_change(String date)
	{
		try
		{
			int max = historical_data.size() -1;
			
			String[] last = historical_data.get(max).split(",");
			String[] lasDateArr = last[0].split("-");
			String[] inputDateArr = date.split("-");
			if (lasDateArr[0].length() == 1)
				lasDateArr[0] = "0" + lasDateArr[0];
			if (Integer.parseInt(lasDateArr[2]) < 70 )
				lasDateArr[2] = "20" + lasDateArr[2];
			else
				lasDateArr[2] = "19" + lasDateArr[2];
			if (Integer.parseInt(inputDateArr[2]) < 70 )
				inputDateArr[2] = "20" + inputDateArr[2];	
			else
				inputDateArr[2] = "19" + inputDateArr[2];				
			String maxDate = lasDateArr[2] + "-" + lasDateArr[1] + "-"  + lasDateArr[0];
			String inputDate = inputDateArr[2] + "-" + inputDateArr[1] + "-"  + inputDateArr[0];
			
			
			if (maxDate.compareTo(inputDate) > 0)
			{
				String[] data = historical_data.get(max).split(",");
				double ten_year_price = Double.parseDouble(data[data.length-2]);
				double current_price = Double.parseDouble(LastTradePriceOnly);
				double change = current_price - ten_year_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/ten_year_price * 100 *100.0)/100.0;
				ten_year = new String[3];
				ten_year[0] = String.valueOf(change);
				ten_year[1] = String.valueOf(percent) + "%";
				ten_year[2] = data[0];
			}
			else {
				String entries = this.find_data_by_date(date);
				if (!entries.equals("FAIL"))
				{
					String[] data = entries.split(",");
					double ten_year_price = Double.parseDouble(data[data.length-2]);
					double current_price = Double.parseDouble(LastTradePriceOnly);
					double change = current_price - ten_year_price;
					change = Math.round(change * 100.0) / 100.0;
					double percent = Math.round(change/ten_year_price * 100 *100.0)/100.0;
					ten_year = new String[3];
					ten_year[0] = String.valueOf(change);
					ten_year[1] = String.valueOf(percent) + "%";
					ten_year[2] = data[0];
				}
				else
				{
					ten_year = new String[3];
					ten_year[0] = "N\\A";
					ten_year[1] = "N\\A";
					ten_year[2] = "N\\A";
				}
			}
		}
		catch(NullPointerException ex)
		{
			ten_year = new String[3];
			ten_year[0] = "";
			ten_year[1] = "";
			ten_year[2] = "";
		}
	}
	
	public void find_ytd_change(String date)
	{
		try
		{
			int max = historical_data.size() -1;
			
			String[] last = historical_data.get(max).split(",");
			String[] lasDateArr = last[0].split("-");
			String[] inputDateArr = date.split("-");
			if (lasDateArr[0].length() == 1)
				lasDateArr[0] = "0" + lasDateArr[0];
			if (Integer.parseInt(lasDateArr[2]) < 70 )
				lasDateArr[2] = "20" + lasDateArr[2];
			else
				lasDateArr[2] = "19" + lasDateArr[2];
			if (Integer.parseInt(inputDateArr[2]) < 70 )
				inputDateArr[2] = "20" + inputDateArr[2];	
			else
				inputDateArr[2] = "19" + inputDateArr[2];				
			String maxDate = lasDateArr[2] + "-" + lasDateArr[1] + "-"  + lasDateArr[0];
			String inputDate = inputDateArr[2] + "-" + inputDateArr[1] + "-"  + inputDateArr[0];
			
			
			if (maxDate.compareTo(inputDate) > 0)
			{
				String[] data = historical_data.get(max).split(",");
				double ytd_price = Double.parseDouble(data[data.length-2]);
				double current_price = Double.parseDouble(LastTradePriceOnly);
				double change = current_price - ytd_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/ytd_price * 100 *100.0)/100.0;
				YTD = new String[3];
				YTD[0] = String.valueOf(change);
				YTD[1] = String.valueOf(percent) + "%";
				YTD[2] = data[0];
			}
			else
			{
				String entries = this.find_data_by_date(date);
				if (!entries.equals("FAIL"))
				{
					String[] data = entries.split(",");
					double ytd_price = Double.parseDouble(data[data.length-2]);
					double current_price = Double.parseDouble(LastTradePriceOnly);
					double change = current_price - ytd_price;
					change = Math.round(change * 100.0) / 100.0;
					double percent = Math.round(change/ytd_price * 100 *100.0)/100.0;
					YTD = new String[3];
					YTD[0] = String.valueOf(change);
					YTD[1] = String.valueOf(percent) + "%";
					YTD[2] = data[0];
				}
				else
				{
					YTD = new String[3];
					YTD[0] = "N\\A";
					YTD[1] = "N\\A";
					YTD[2] = "N\\A";
				}
			}
		}
		catch(NullPointerException ex)
		{
			YTD = new String[3];
			YTD[0] = "";
			YTD[1] = "";
			YTD[2] = "";
		}
	}
	
	public void find_five_year_change(String date)
	{
		try
		{
			int max = historical_data.size() -1;
			
			String[] last = historical_data.get(max).split(",");
			String[] lasDateArr = last[0].split("-");
			String[] inputDateArr = date.split("-");
			if (lasDateArr[0].length() == 1)
				lasDateArr[0] = "0" + lasDateArr[0];
			if (Integer.parseInt(lasDateArr[2]) < 70 )
				lasDateArr[2] = "20" + lasDateArr[2];
			else
				lasDateArr[2] = "19" + lasDateArr[2];
			if (Integer.parseInt(inputDateArr[2]) < 70 )
				inputDateArr[2] = "20" + inputDateArr[2];	
			else
				inputDateArr[2] = "19" + inputDateArr[2];				
			String maxDate = lasDateArr[2] + "-" + lasDateArr[1] + "-"  + lasDateArr[0];
			String inputDate = inputDateArr[2] + "-" + inputDateArr[1] + "-"  + inputDateArr[0];
			
			
			if (maxDate.compareTo(inputDate) > 0)
			{
				String[] data = historical_data.get(max).split(",");
				double five_year_price = Double.parseDouble(data[data.length-2]);
				double current_price = Double.parseDouble(LastTradePriceOnly);
				double change = current_price - five_year_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/five_year_price * 100 *100.0)/100.0;
				five_year = new String[3];
				five_year[0] = String.valueOf(change);
				five_year[1] = String.valueOf(percent) + "%";
				five_year[2] = data[0];
			}
			else
			{
				String entries = this.find_data_by_date(date);
				if (!entries.equals("FAIL")) {
					String[] data = entries.split(",");
					double five_year_price = Double.parseDouble(data[data.length-2]);
					double current_price = Double.parseDouble(LastTradePriceOnly);
					double change = current_price - five_year_price;
					change = Math.round(change * 100.0) / 100.0;
					double percent = Math.round(change/five_year_price * 100 *100.0)/100.0;
					five_year = new String[3];
					five_year[0] = String.valueOf(change);
					five_year[1] = String.valueOf(percent) + "%";
					five_year[2] = data[0];
				}
				else
				{
					five_year = new String[3];
					five_year[0] = "N\\A";
					five_year[1] = "N\\A";
					five_year[2] = "N\\A";
				}
			}
		}
		catch(NullPointerException ex)
		{
			five_year = new String[3];
			five_year[0] = "";
			five_year[1] = "";
			five_year[2] = "";
		}
	}
	
	public void find_one_year_change(String date)
	{
		try
		{
			int max = historical_data.size() -1;
			
			String[] last = historical_data.get(max).split(",");
			String[] lasDateArr = last[0].split("-");
			String[] inputDateArr = date.split("-");
			if (lasDateArr[0].length() == 1)
				lasDateArr[0] = "0" + lasDateArr[0];
			if (Integer.parseInt(lasDateArr[2]) < 70 )
				lasDateArr[2] = "20" + lasDateArr[2];
			else
				lasDateArr[2] = "19" + lasDateArr[2];
			if (Integer.parseInt(inputDateArr[2]) < 70 )
				inputDateArr[2] = "20" + inputDateArr[2];	
			else
				inputDateArr[2] = "19" + inputDateArr[2];				
			String maxDate = lasDateArr[2] + "-" + lasDateArr[1] + "-"  + lasDateArr[0];
			String inputDate = inputDateArr[2] + "-" + inputDateArr[1] + "-"  + inputDateArr[0];
			
			
			if (maxDate.compareTo(inputDate) > 0)
			{
				String[] data = historical_data.get(max).split(",");
				double one_year_price = Double.parseDouble(data[data.length-2]);
				double current_price = Double.parseDouble(LastTradePriceOnly);
				double change = current_price - one_year_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/one_year_price * 100 *100.0)/100.0;
				one_year = new String[3];
				one_year[0] = String.valueOf(change);
				one_year[1] = String.valueOf(percent) + "%";
				one_year[2] = data[0];
			}
			else
			{
				String entries = this.find_data_by_date(date);
				if (!entries.equals("FAIL"))
				{
					String[] data = entries.split(",");
					double one_year_price = Double.parseDouble(data[data.length-2]);
					double current_price = Double.parseDouble(LastTradePriceOnly);
					double change = current_price - one_year_price;
					change = Math.round(change * 100.0) / 100.0;
					double percent = Math.round(change/one_year_price * 100 *100.0)/100.0;
					one_year = new String[3];
					one_year[0] = String.valueOf(change);
					one_year[1] = String.valueOf(percent) + "%";
					one_year[2] = data[0];
				}
				else
				{
					one_year = new String[3];
					one_year[0] = "N\\A";
					one_year[1] = "N\\A";
					one_year[2] = "N\\A";
				}
			}
		}
		catch(NullPointerException ex)
		{
			one_year = new String[3];
			one_year[0] = "";
			one_year[1] = "";
			one_year[2] = "";
		}
	}
	
	public void find_six_month_change(String date)
	{
		try
		{
			int max = historical_data.size() -1;
			
			String[] last = historical_data.get(max).split(",");
			String[] lasDateArr = last[0].split("-");
			String[] inputDateArr = date.split("-");
			if (lasDateArr[0].length() == 1)
				lasDateArr[0] = "0" + lasDateArr[0];
			if (Integer.parseInt(lasDateArr[2]) < 70 )
				lasDateArr[2] = "20" + lasDateArr[2];
			else
				lasDateArr[2] = "19" + lasDateArr[2];
			if (Integer.parseInt(inputDateArr[2]) < 70 )
				inputDateArr[2] = "20" + inputDateArr[2];	
			else
				inputDateArr[2] = "19" + inputDateArr[2];				
			String maxDate = lasDateArr[2] + "-" + lasDateArr[1] + "-"  + lasDateArr[0];
			String inputDate = inputDateArr[2] + "-" + inputDateArr[1] + "-"  + inputDateArr[0];
			
			
			if (maxDate.compareTo(inputDate) > 0)
			{
				String[] data = historical_data.get(max).split(",");
				double six_month_price = Double.parseDouble(data[data.length-2]);
				double current_price = Double.parseDouble(LastTradePriceOnly);
				double change = current_price - six_month_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/six_month_price * 100 *100.0)/100.0;
				six_month = new String[3];
				six_month[0] = String.valueOf(change);
				six_month[1] = String.valueOf(percent) + "%";
				six_month[2] = data[0];
			}
			else
			{
				String entries = this.find_data_by_date(date);
				if (!entries.equals("FAIL"))
				{
					String[] data = entries.split(",");
					double six_month_price = Double.parseDouble(data[data.length-2]);
					double current_price = Double.parseDouble(LastTradePriceOnly);
					double change = current_price - six_month_price;
					change = Math.round(change * 100.0) / 100.0;
					double percent = Math.round(change/six_month_price * 100 *100.0)/100.0;
					six_month = new String[3];
					six_month[0] = String.valueOf(change);
					six_month[1] = String.valueOf(percent) + "%";
					six_month[2] = data[0];
				}
				else
				{
					six_month = new String[3];
					six_month[0] = "N\\A";
					six_month[1] = "N\\A";
					six_month[2] = "N\\A";
				}
			}
		}
		catch(NullPointerException ex)
		{
			six_month = new String[3];
			six_month[0] = "";
			six_month[1] = "";
			six_month[2] = "";
		}
	}
	
	public void find_three_month_change(String date)
	{
		try
		{
			int max = historical_data.size() -1;
			
			String[] last = historical_data.get(max).split(",");
			String[] lasDateArr = last[0].split("-");
			String[] inputDateArr = date.split("-");
			if (lasDateArr[0].length() == 1)
				lasDateArr[0] = "0" + lasDateArr[0];
			if (Integer.parseInt(lasDateArr[2]) < 70 )
				lasDateArr[2] = "20" + lasDateArr[2];
			else
				lasDateArr[2] = "19" + lasDateArr[2];
			if (Integer.parseInt(inputDateArr[2]) < 70 )
				inputDateArr[2] = "20" + inputDateArr[2];	
			else
				inputDateArr[2] = "19" + inputDateArr[2];				
			String maxDate = lasDateArr[2] + "-" + lasDateArr[1] + "-"  + lasDateArr[0];
			String inputDate = inputDateArr[2] + "-" + inputDateArr[1] + "-"  + inputDateArr[0];
			
			
			if (maxDate.compareTo(inputDate) > 0)
			{
				String[] data = historical_data.get(max).split(",");
				double three_month_price = Double.parseDouble(data[data.length-2]);
				double current_price = Double.parseDouble(LastTradePriceOnly);
				double change = current_price - three_month_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/three_month_price * 100 *100.0)/100.0;
				three_month = new String[3];
				three_month[0] = String.valueOf(change);
				three_month[1] = String.valueOf(percent) + "%";
				three_month[2] = data[0];
			}
			else
			{
				String entries = this.find_data_by_date(date);
				if (!entries.equals("FAIL"))
				{
					String[] data = entries.split(",");
					double three_month_price = Double.parseDouble(data[data.length-2]);
					double current_price = Double.parseDouble(LastTradePriceOnly);
					double change = current_price - three_month_price;
					change = Math.round(change * 100.0) / 100.0;
					double percent = Math.round(change/three_month_price * 100 *100.0)/100.0;
					three_month = new String[3];
					three_month[0] = String.valueOf(change);
					three_month[1] = String.valueOf(percent) + "%";
					three_month[2] = data[0];
				}
				else
				{
					three_month = new String[3];
					three_month[0] = "N\\A";
					three_month[1] = "N\\A";
					three_month[2] = "N\\A";
				}
			}
		}
		catch(NullPointerException ex)
		{
			three_month = new String[3];
			three_month[0] = "";
			three_month[1] = "";
			three_month[2] = "";
		}
	}
	
	public void find_one_month_change(String date)
	{
		try
		{
			int max = historical_data.size() -1;
			
			String[] last = historical_data.get(max).split(",");
			String[] lasDateArr = last[0].split("-");
			String[] inputDateArr = date.split("-");
			if (lasDateArr[0].length() == 1)
				lasDateArr[0] = "0" + lasDateArr[0];
			if (Integer.parseInt(lasDateArr[2]) < 70 )
				lasDateArr[2] = "20" + lasDateArr[2];
			else
				lasDateArr[2] = "19" + lasDateArr[2];
			if (Integer.parseInt(inputDateArr[2]) < 70 )
				inputDateArr[2] = "20" + inputDateArr[2];	
			else
				inputDateArr[2] = "19" + inputDateArr[2];				
			String maxDate = lasDateArr[2] + "-" + lasDateArr[1] + "-"  + lasDateArr[0];
			String inputDate = inputDateArr[2] + "-" + inputDateArr[1] + "-"  + inputDateArr[0];
			
			
			if (maxDate.compareTo(inputDate) > 0)
			{
				String[] data = historical_data.get(max).split(",");
				double one_month_price = Double.parseDouble(data[data.length-2]);
				double current_price = Double.parseDouble(LastTradePriceOnly);
				double change = current_price - one_month_price;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/one_month_price * 100 *100.0)/100.0;
				one_month = new String[3];
				one_month[0] = String.valueOf(change);
				one_month[1] = String.valueOf(percent) + "%";
				one_month[2] = data[0];
			}
			else
			{
				String entries = this.find_data_by_date(date);
				if (!entries.equals("FAIL"))
				{
					String[] data = entries.split(",");
					double one_month_price = Double.parseDouble(data[data.length-2]);
					double current_price = Double.parseDouble(LastTradePriceOnly);
					double change = current_price - one_month_price;
					change = Math.round(change * 100.0) / 100.0;
					double percent = Math.round(change/one_month_price * 100 *100.0)/100.0;
					one_month = new String[3];
					one_month[0] = String.valueOf(change);
					one_month[1] = String.valueOf(percent) + "%";
					one_month[2] = data[0];
				}
				else
				{
					one_month = new String[3];
					one_month[0] = "N\\A";
					one_month[1] = "N\\A";
					one_month[2] = "N\\A";
				}
			}
		}
		catch(NullPointerException ex)
		{
			one_month = new String[3];
			one_month[0] = "";
			one_month[1] = "";
			one_month[2] = "";
		}
	}
	
	public void find_five_day_change(String date)
	{
		try
		{
			int lastIndex = intraday_5d.size() - 1;
			String[] open = intraday_5d.get(22).split(",");
			String[] close = intraday_5d.get(lastIndex).split(",");
			Double openPrice = Double.parseDouble(open[1]);
			Double closePrice = Double.parseDouble(close[1]);
			Double change = closePrice - openPrice;
			change = Math.round(change * 100.0) / 100.0;
			double percent = Math.round(change/openPrice * 100 *100.0)/100.0;
			five_day = new String[3];
			five_day[0] = String.valueOf(change);
			five_day[1] = String.valueOf(percent) + "%";
			Date openDate = new Date((long) Double.parseDouble(open[0]) * 1000);
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			
			five_day[2] = df.format(openDate);
			
		}
		catch(Exception ex)
		{
			five_day = new String[3];
			five_day[0] = "";
			five_day[1] = "";
			five_day[2] = "";
		}
	}
	
	public void calculate_beta(YStockQuote sp500, int timeFrame)
	{
		double[] sp500Col = Arrays.copyOfRange(sp500.get_historical_rate_of_return(), 0, timeFrame);
		calculate_historical_rate_of_return(timeFrame);
		sp500Col = Arrays.copyOfRange(sp500.get_historical_rate_of_return(), 0, this.historical_rate_of_return.length);
		Covariance covarianceObj = new Covariance();
		Variance varianceObj = new Variance(false);
		double covariance = covarianceObj.covariance(historical_rate_of_return, sp500Col);
		double variance = varianceObj.evaluate(sp500Col);
		
		this.Beta = String.valueOf(Math.round((covariance/variance) *100.0)/100.0);
	}
	
	//timeframe in days - 0 is max
	public void calculate_historical_rate_of_return(int timeFrame)
	{
		int numIterations;
		if (timeFrame <= 0 || timeFrame >= historical_data.size())
		{
			numIterations = historical_data.size();
			timeFrame = historical_data.size() -1;	//first row of historical_data is not neccessary as it is column header
		}
		else
			numIterations = timeFrame + 1;
		historical_rate_of_return = new double[timeFrame];
		for (int i = 1; i < numIterations; i++)
		{
			if (i + 1 < historical_data.size())
			{
				String[] data = historical_data.get(i).split(",");
				String[] dayBeforeData = historical_data.get(i + 1).split(",");
				double historicalPrice = Double.parseDouble(data[data.length - 2]);
				double dayBeforePrice = Double.parseDouble(dayBeforeData[dayBeforeData.length -2]);
				double change = historicalPrice - dayBeforePrice;
				change = Math.round(change * 100.0) / 100.0;
				double percent = Math.round(change/dayBeforePrice * 100 *100.0)/100.0;
				historical_rate_of_return[i-1] = percent;
			}
			else
			{
				historical_rate_of_return = Arrays.copyOfRange(historical_rate_of_return, 0, i);
				break;
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
	
	public String get_ask()
	{
		return this.AskRealtime;
	}
	
	public String get_bid()
	{
		return this.BidRealtime;
	}
	
	public String get_beta()
	{
		return this.Beta;
	}
	
	public double[] get_historical_rate_of_return()
	{
		return this.historical_rate_of_return;
	}
	
	public ArrayList<String> get_historical_data()
	{
		return this.historical_data;
	}
	
	public String get_market_cap()
	{
		return this.MarketCapitalization;
	}
	
	public String get_ebitda()
	{
		return this.EBITDA;
	}
	
	public String get_days_range()
	{
		return this.DaysRange;
	}
	
	public String get_dividend_per_share()
	{
		return this.DividendShare;
	}
	
	public String get_dividend_yield()
	{
		return this.DividendYield;
	}
	
	public String get_earnings_estimate_current_year()
	{
		return this.EPSEstimateCurrentYear;
	}
	
	public String get_earnings_estimate_next_year()
	{
		return this.EPSEstimateNextYear;
	}
	
	public String get_earnings_estimate_next_quarter()
	{
		return this.EPSEstimateNextQuarter;
	}
	
	public String get_earnings_per_share()
	{
		return this.EarningsShare;
	}
	
	public ArrayList<String> get_one_day_data()
	{
		return this.intraday_1d;
	}
	
	public ArrayList<String> get_five_day_data()
	{
		return this.intraday_5d;
	}
	
	public String get_fiftyday_moving_avg()
	{
		return this.FiftydayMovingAverage;
	}
	
	public String get_twohundredday_moving_avg()
	{
		return this.TwoHundreddayMovingAverage;
	}
	
	public String get_change_from_fiftyday_moving_avg()
	{
		return this.ChangeFromFiftydayMovingAverage;
	}
	
	public String get_change_from_twohundredday_moving_avg()
	{
		return this.ChangeFromTwoHundreddayMovingAverage;
	}
	
	public String get_percent_change_from_fiftyday_moving_avg()
	{
		return this.PercentChangeFromFiftydayMovingAverage;
	}
	
	public String get_percent_change_from_twohundredday_moving_avg()
	{
		return this.PercentChangeFromTwoHundreddayMovingAverage;
	}
	
	public String get_year_low()
	{
		return this.YearLow;
	}
	
	public String get_year_high()
	{
		return this.YearHigh;
	}
	
	public String get_change_from_year_low()
	{
		return this.ChangeFromYearLow;
	}
	
	public String get_change_from_year_high()
	{
		return this.ChangeFromYearHigh;
	}
	
	public String get_percent_change_from_year_low()
	{
		return this.PercentChangeFromYearLow;
	}
	
	public String get_percent_change_from_year_high()
	{
		return this.PercentChangeFromYearHigh;
	}
	
	public String get_open_price()
	{
		return this.Open;
	}
	
	public String get_prev_close()
	{
		return this.PreviousClose;
	}
	
	public String get_price_earnings_ratio()
	{
		return this.PERatio;
	}
	
	public String get_price_book_ratio()
	{
		return this.PriceBook;
	}
	
	public String get_short_ratio()
	{
		return this.ShortRatio;
	}
	
	public String get_peg_ratio()
	{
		return this.PEGRatio;
	}
	
	public String get_pe()
	{
		return this.PERatio;
	}
	
	public String get_percent_change()
	{
		return this.PercentChange;
	}
	
	public String get_revenue()
	{
		return this.Revenue;
	}
	
	public String get_one_year_target()
	{
		return this.OneyrTargetPrice;
	}
	
	public String get_last_trade_date()
	{
		return this.LastTradeDate;
	}
	
	public String get_year_range()
	{
		return this.YearRange;
	}
	
	public String get_price_eps_estimate_current_year()
	{
		return this.PriceEPSEstimateCurrentYear;
	}
	
	public String get_price_eps_estimate_next_year()
	{
		return this.PriceEPSEstimateNextYear;
	}
	
}
