/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.std;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

/**
 *
 * @author Eric
 */
public class Index extends YStockQuote{
    
    public Index(String ticker)
    {
        super(ticker);
    }
    
    /*
    * Array list of comma separated values Date, Open, High, Low, Close, Volume, Adjusted Close
    */
    public void find_historical_data(int month, int day, int year)
    {
        try {
            String url = "http://real-chart.finance.yahoo.com/table.csv?s=" + this.ticker + "&d=" + month + "&e=" + day + "&f=" + year + "&g=d&a=0&b=1&c=1970&ignore=.csv";
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
            System.out.println("Could not retrieve historical data");
            System.out.println("Error with connection");
            
        }
    }
    
    @Override
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
    
    public int find_historical_data_index(String date)
    {
        int min = 0;
        int max = historical_data.size() -1;
        while (max >= min) {
            int mid = min + (max - min) /2;
            String y = historical_data.get(mid);
            if (historical_data.get(mid).contains(date)) {
                return mid;
            }
            else if (historical_data.get(mid).compareTo(date) < 0) //lower half of array
                max = mid - 1;
            else //upper half
                min = mid + 1;
        }
        return historical_data.size();
    }
    
    
    //change based on adjusted close, adjusted for stock splits and dividends
    @Override
    public void find_max_change()
    {
        try
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
    @Override
    public void find_ten_year_change(String date)
    {
        try
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
                ten_year[1] = String.valueOf(percent) + "%";
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
    
    @Override
    public void find_ytd_change(String date)
    {
        try
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
                YTD[1] = String.valueOf(percent) + "%";
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
    
    @Override
    public void find_five_year_change(String date)
    {
        try
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
                five_year[1] = String.valueOf(percent) + "%";
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
    
    @Override
    public void find_one_year_change(String date)
    {
        try
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
                one_year[1] = String.valueOf(percent) + "%";
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
    
    @Override
    public void find_six_month_change(String date)
    {
        try
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
                six_month[1] = String.valueOf(percent) + "%";
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
    
    @Override
    public void find_three_month_change(String date)
    {
        try
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
                three_month[1] = String.valueOf(percent) + "%";
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
    
    @Override
    public void find_one_month_change(String date)
    {
        try
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
                one_month[1] = String.valueOf(percent) + "%";
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
    
    @Override
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
    @Override
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
                double historicalPrice = Double.parseDouble(data[data.length - 1]);
                double dayBeforePrice = Double.parseDouble(dayBeforeData[dayBeforeData.length -1]);
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
}
