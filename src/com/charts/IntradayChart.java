/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.charts;

import com.std.YStockQuote;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import com.std.YStockQuote;
/**
 *
 * @author Eric
 */
public class IntradayChart {
    
    ChartPanel chartPanel;
    public IntradayChart(YStockQuote currentStock)
    {
        
        TimeSeries series = new TimeSeries(currentStock.get_name());
        ArrayList<String> fiveDayData = currentStock.get_one_day_data();
        int length = fiveDayData.size();
        for (int i = 17; i < length; i++)
        {
            String[] data = fiveDayData.get(i).split(",");
            Date time = new Date((long) Integer.parseInt(data[0]) * 1000);
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy-h-m");
            series.addOrUpdate(new Minute(time), Double.parseDouble(data[1]));
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                currentStock.get_name() + "(" + currentStock.get_symbol() + ")" + " Intraday",
                "Date",
                "Price",
                dataset,
                true,
                true,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        ValueAxis yAxis = (ValueAxis) plot.getRangeAxis();
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());
        xAxis.setDateFormatOverride(new SimpleDateFormat("h:m a"));
        xAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        //xAxis.setVerticalTickLabels(true);
        chartPanel = new ChartPanel(chart);
        chart.setBackgroundPaint(chartPanel.getBackground());
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        chartPanel.setVisible(true);
        chartPanel.revalidate();
        chartPanel.repaint();
    }
    
    public ChartPanel getChartPanel()
    {
        return this.chartPanel;
    }
}
