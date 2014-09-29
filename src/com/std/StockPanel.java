/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.std;

import com.charts.FiveDayChart;
import com.charts.FiveYearChart;
import com.charts.IntradayChart;
import com.charts.MaxChart;
import com.charts.OneMonthChart;
import com.charts.OneYearChart;
import com.charts.SixMonthChart;
import com.charts.TenYearChart;
import com.charts.ThreeMonthChart;
import com.charts.YTDChart;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author Eric
 */
public class StockPanel extends JPanel{
    
    
    JPanel main = new JPanel();
    
    JLabel nameLbl = new JLabel();
    JLabel priceChangePercentLbl = new JLabel();
    Object[][] data;
    JPanel nameAndChangePanel = new JPanel();
    JPanel dataAndGraph = new JPanel();
    StockTable remainingInfoTable = new StockTable();
    JTabbedPane chartPane = new JTabbedPane();
    Boolean finished;
    YStockQuote currentStock;
    YStockQuote sp500;
    DayRange b;
    JPopupMenu graphMenu;
    
    public StockPanel(YStockQuote currentStock, YStockQuote sp500, DayRange b, JPopupMenu graphMenu)
    {
        this.finished = false;
        this.currentStock = currentStock;
        this.sp500 = sp500;
        this.b = b;
        this.graphMenu = graphMenu;
        
        //input field and button
        
        
    }
    
    public void setHistoricalData()
    {
        this.setLayout(new BorderLayout());
        nameAndChangePanel.setLayout(new BoxLayout(nameAndChangePanel,BoxLayout.PAGE_AXIS));
        nameAndChangePanel.add(nameLbl);
        nameAndChangePanel.add(priceChangePercentLbl);
        
        data = new Object[30][2];
        String[] colnames = {	"1", "2"};
        
        GridBagConstraints c = new GridBagConstraints();
        dataAndGraph.setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,20);
        dataAndGraph.add(nameAndChangePanel, c);
        c.weightx = 0;
        c.weighty = 1;
        c.insets = new Insets(0,20,0,0);
        //c.ipady = 20;
        c.gridx = 0;
        c.gridy = 1;
        remainingInfoTable = new StockTable(data, colnames);
        remainingInfoTable.setShowGrid(false);
        remainingInfoTable.setTableHeader(null);
        remainingInfoTable.setBackground(this.getBackground());
        remainingInfoTable.setFocusable(false);
        remainingInfoTable.setColumnSelectionAllowed(false);
        remainingInfoTable.setRowSelectionAllowed(false);
        remainingInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataAndGraph.add(remainingInfoTable, c);
        this.add(dataAndGraph, BorderLayout.CENTER);
        
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 2;
        //c.ipady = 20;
        c.gridx = 1;
        c.gridy = 0;
        dataAndGraph.add(chartPane, c);
        chartPane.setVisible(false);
        this.add(dataAndGraph, BorderLayout.CENTER);
        
        
        Test.show_hist_data(currentStock, b);
        currentStock.calculate_beta(sp500, 1200);
        
        nameLbl.setText(currentStock.get_name() + " (" + currentStock.get_symbol() + ")");
        nameLbl.setFont(new Font(nameLbl.getName(), Font.BOLD, 24));
        priceChangePercentLbl.setText(currentStock.get_price() + " ");
        String message = currentStock.get_change() + " " + currentStock.get_percent_change();
        if (Double.parseDouble(currentStock.get_change()) < 0)
            priceChangePercentLbl.setText(String.format("<html>%s<font color='red'>%s</font></html>", priceChangePercentLbl.getText(), message));
        else
            priceChangePercentLbl.setText(String.format("<html>%s<font color='green'>%s</font></html>", priceChangePercentLbl.getText(), message));
        priceChangePercentLbl.setFont(new Font(priceChangePercentLbl.getName(), Font.PLAIN, 15));
        
        
        data[0][0] = "Prev Close: " + currentStock.get_prev_close();
        data[1][0] = "Open: " + currentStock.get_open_price();
        data[2][0] = "Bid: " + currentStock.get_bid();
        data[3][0] = "Ask: " + currentStock.get_ask();
        data[4][0] = "One Year Target: " + currentStock.get_one_year_target();
        data[5][0] = "Ebita: " + currentStock.get_ebitda();
        data[0][1] = "Day Range: " + currentStock.get_days_range();
        data[1][1] = "52 Week Range: " + currentStock.get_year_range();
        data[2][1] = "Volume: " + currentStock.get_volume();
        data[3][1] = "Average Daily Volume: " + currentStock.get_avg_daily_volume();
        data[4][1] = "Market Cap: " + currentStock.get_market_cap();
        data[5][1] = "P/E: "  + currentStock.get_pe();
        data[6][1] = "EPS: " + currentStock.get_earnings_per_share();
        data[7][1] = "Dividend (Yield): " + currentStock.get_dividend_per_share() + "(" + currentStock.get_dividend_yield() + ")";
        data[6][0] = "Reveune:" + currentStock.get_revenue();
        data[7][0] = "Earnings Estimate: " + currentStock.get_earnings_estimate_current_year();
        data[8][0] = "Beta: " + currentStock.get_beta();
        data[8][1] = "PEG Ratio: " + currentStock.get_peg_ratio();
        data[9][0] = "Short Ratio: " + currentStock.get_short_ratio();
        
        data[11][0] = "50 Day MA: " + currentStock.get_fiftyday_moving_avg();
        data[12][0] = "200 Day MA: " + currentStock.get_twohundredday_moving_avg();
        if (currentStock.get_change_from_fiftyday_moving_avg() != null && currentStock.get_change_from_fiftyday_moving_avg().contains("-"))
        {
            data[13][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Change 50 Day MA: ", currentStock.get_change_from_fiftyday_moving_avg());
            data[14][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Percent 50 Day MA: ", currentStock.get_percent_change_from_fiftyday_moving_avg());
            
        }
        else
        {
            data[13][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Change 50 Day MA: ", currentStock.get_change_from_fiftyday_moving_avg());
            data[14][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Percent 50 Day MA: ", currentStock.get_percent_change_from_fiftyday_moving_avg());
        }
        if (currentStock.get_change_from_twohundredday_moving_avg() != null && currentStock.get_change_from_twohundredday_moving_avg().contains("-"))
        {
            data[15][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Change 200 Day MA: ", currentStock.get_change_from_twohundredday_moving_avg());
            data[16][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Percent 200 Day MA: ", currentStock.get_percent_change_from_twohundredday_moving_avg());
        }
        else
        {
            data[15][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Change 200 Day MA: ", currentStock.get_change_from_twohundredday_moving_avg());
            data[16][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Percent 200 Day MA: ", currentStock.get_percent_change_from_twohundredday_moving_avg());
        }
        data[17][0] = "Year High: " + currentStock.get_year_high();
        data[18][0] = "Year Low: " + currentStock.get_year_low();
        if (currentStock.get_change_from_year_high() != null && currentStock.get_change_from_year_high().contains("-"))
        {
            data[19][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Year High Change: ", currentStock.get_change_from_year_high());
            data[20][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Year High Percent: ", currentStock.get_percent_change_from_year_high());
        }
        else
        {
            data[19][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Year High Change: ", currentStock.get_change_from_year_high());
            data[20][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Year High Percent: ", currentStock.get_percent_change_from_year_high());
        }
        if (currentStock.get_change_from_year_low() != null && currentStock.get_change_from_year_low().contains("-"))
        {
            data[21][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Year Low Change: ", currentStock.get_change_from_year_low());
            data[22][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Year Low Percent:  ", currentStock.get_percent_change_from_year_low());
        }
        else
        {
            data[21][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Year Low Change: ", currentStock.get_change_from_year_low());
            data[22][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Year Low Percent:  ", currentStock.get_percent_change_from_year_low());
        }
        if (currentStock.get_five_day_change()[0] != null && currentStock.get_five_day_change()[0].contains("-"))
            data[11][1] = String.format("<html>%s<font color='red'>%s</font></html>", "5D Change: ", currentStock.get_five_day_change()[0] + " (" + currentStock.get_five_day_change()[1] + ")");
        else
            data[11][1] = String.format("<html>%s<font color='green'>%s</font></html>", "5D Change: ", currentStock.get_five_day_change()[0] + " (" + currentStock.get_five_day_change()[1] + ")");
        if (currentStock.get_one_month_change()[0] != null && currentStock.get_one_month_change()[0].contains("-"))
            data[12][1] = String.format("<html>%s<font color='red'>%s</font></html>", "1M Change: ", currentStock.get_one_month_change()[0] + " (" + currentStock.get_one_month_change()[1] + ")");
        else
            data[12][1] = String.format("<html>%s<font color='green'>%s</font></html>", "1M Change: ", currentStock.get_one_month_change()[0] + " (" + currentStock.get_one_month_change()[1] + ")");
        if (currentStock.get_three_month_change()[0] != null && currentStock.get_three_month_change()[0].contains("-"))
            data[13][1] = String.format("<html>%s<font color='red'>%s</font></html>", "3M Change: ", currentStock.get_three_month_change()[0] + " (" + currentStock.get_three_month_change()[1] + ")");
        else
            data[13][1] = String.format("<html>%s<font color='green'>%s</font></html>", "3M Change: ", currentStock.get_three_month_change()[0] + " (" + currentStock.get_three_month_change()[1] + ")");
        if (currentStock.get_six_month_change()[0] != null && currentStock.get_six_month_change()[0].contains("-"))
            data[14][1] = String.format("<html>%s<font color='red'>%s</font></html>", "6M Change: ", currentStock.get_six_month_change()[0] + " (" + currentStock.get_six_month_change()[1] + ")");
        else
            data[14][1] = String.format("<html>%s<font color='green'>%s</font></html>", "6M Change: ", currentStock.get_six_month_change()[0] + " (" + currentStock.get_six_month_change()[1] + ")");
        if (currentStock.get_ytd_change()[0] != null && currentStock.get_ytd_change()[0].contains("-"))
            data[15][1] = String.format("<html>%s<font color='red'>%s</font></html>", "YTD Change: ", currentStock.get_ytd_change()[0] + " (" + currentStock.get_ytd_change()[1] + ")");
        else
            data[15][1] = String.format("<html>%s<font color='green'>%s</font></html>", "YTD Change: ", currentStock.get_ytd_change()[0] + " (" + currentStock.get_ytd_change()[1] + ")");
        if (currentStock.get_one_year_change()[0] != null && currentStock.get_one_year_change()[0].contains("-"))
            data[16][1] = String.format("<html>%s<font color='red'>%s</font></html>", "1Y Change: ", currentStock.get_one_year_change()[0] + " (" + currentStock.get_one_year_change()[1] + ")");
        else
            data[16][1] = String.format("<html>%s<font color='green'>%s</font></html>", "1Y Change: ", currentStock.get_one_year_change()[0] + " (" + currentStock.get_one_year_change()[1] + ")");
        if (currentStock.get_five_year_change()[0] != null && currentStock.get_five_year_change()[0].contains("-"))
            data[17][1] = String.format("<html>%s<font color='red'>%s</font></html>", "5Y Change: ", currentStock.get_five_year_change()[0] + " (" + currentStock.get_five_year_change()[1] + ")");
        else
            data[17][1] = String.format("<html>%s<font color='green'>%s</font></html>", "5Y Change: ", currentStock.get_five_year_change()[0] + " (" + currentStock.get_five_year_change()[1] + ")");
        if (currentStock.get_ten_year_change()[0] != null && currentStock.get_ten_year_change()[0].contains("-"))
            data[18][1] = String.format("<html>%s<font color='red'>%s</font></html>", "10Y Change: ", currentStock.get_ten_year_change()[0] + " (" + currentStock.get_ten_year_change()[1] + ")");
        else
            data[18][1] = String.format("<html>%s<font color='green'>%s</font></html>", "10Y Change: ", currentStock.get_ten_year_change()[0] + " (" + currentStock.get_ten_year_change()[1] + ")");
        if (currentStock.get_max_year_change()[0] != null && currentStock.get_max_year_change()[0].contains("-"))
            data[19][1] = String.format("<html>%s<font color='red'>%s</font></html>", "Max Change: ", currentStock.get_max_year_change()[0] + " (" + currentStock.get_max_year_change()[1] + ")");
        else
            data[19][1] = String.format("<html>%s<font color='green'>%s</font></html>", "Max Change: ", currentStock.get_max_year_change()[0] + " (" + currentStock.get_max_year_change()[1] + ")");
        
        data[24][0] = "Earnings Est Next Quarter: " + currentStock.get_earnings_estimate_next_quarter();
        data[25][0] = "Earnings Est Current Year: " + currentStock.get_earnings_estimate_current_year();
        data[26][0] = "Earnings Est Next Year: " + currentStock.get_earnings_estimate_next_year();
        data[24][1] = "P/E Est Current Year: " + currentStock.get_price_eps_estimate_current_year();
        data[25][1] = "P/E Est Next Year: " + currentStock.get_price_eps_estimate_next_year();
        remainingInfoTable.setFont(new Font(remainingInfoTable.getName(), Font.PLAIN, 15));
        remainingInfoTable.packColumn(0, 4);
        remainingInfoTable.packColumn(1, 4);
        
        try {
            dataAndGraph.remove(chartPane);
            chartPane = new JTabbedPane();
            chartPane.setVisible(true);
            ChartPanel intradayChart = new IntradayChart(currentStock).getChartPanel();
            intradayChart.setPopupMenu(graphMenu);
            chartPane.addTab("1d", intradayChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_1);
            
            ChartPanel fivedayChart = new FiveDayChart(currentStock).getChartPanel();
            fivedayChart.setPopupMenu(graphMenu);
            chartPane.addTab("5d", fivedayChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_2);
            
            ChartPanel onemonthChart = new OneMonthChart(currentStock).getChartPanel();
            onemonthChart.setPopupMenu(graphMenu);
            chartPane.addTab("1m", onemonthChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_3);
            
            ChartPanel threemonthChart = new ThreeMonthChart(currentStock).getChartPanel();
            threemonthChart.setPopupMenu(graphMenu);
            chartPane.addTab("3m", threemonthChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_4);
            
            ChartPanel sixmonthChart = new SixMonthChart(currentStock).getChartPanel();
            sixmonthChart.setPopupMenu(graphMenu);
            chartPane.addTab("6m", sixmonthChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_5);
            
            ChartPanel ytdChart = new YTDChart(currentStock).getChartPanel();
            ytdChart.setPopupMenu(graphMenu);
            chartPane.addTab("ytd", ytdChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_6);
            
            ChartPanel oneyearChart = new OneYearChart(currentStock).getChartPanel();
            oneyearChart.setPopupMenu(graphMenu);
            chartPane.addTab("1y", oneyearChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_7);
            
            ChartPanel fiveyearChart = new FiveYearChart(currentStock).getChartPanel();
            fiveyearChart.setPopupMenu(graphMenu);
            chartPane.addTab("5y", fiveyearChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_8);
            
            ChartPanel tenyearChart = new TenYearChart(currentStock).getChartPanel();
            tenyearChart.setPopupMenu(graphMenu);
            chartPane.addTab("10y", tenyearChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_9);
            
            ChartPanel maxChart = new MaxChart(currentStock).getChartPanel();
            maxChart.setPopupMenu(graphMenu);
            chartPane.addTab("max", maxChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_0);
            
            c = new GridBagConstraints();
            c.anchor = GridBagConstraints.NORTHWEST;
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 0;
            c.gridheight = 2;
            //c.ipady = 20;
            c.gridx = 1;
            c.gridy = 0;
            dataAndGraph.add(chartPane, c);
            
        } catch (ParseException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        chartPane.revalidate();
        chartPane.repaint();
        revalidate();
        repaint();
        finished = true;
    }
    
    public boolean getFinished()
    {
        return this.finished;
    }
    
    public YStockQuote getYStockQuote()
    {
        return this.currentStock;
    }
    
    
}
