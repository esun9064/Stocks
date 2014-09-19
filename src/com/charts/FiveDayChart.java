/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.charts;
import com.std.DayRange;
import com.std.YStockQuote;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.date.MonthConstants;
import org.jfree.date.SerialDate;

/**
 *
 * @author Eric
 */
public class FiveDayChart {
	
	ChartPanel chartPanel;
	
	public FiveDayChart(YStockQuote currentStock)
	{
		TimeSeries series = new TimeSeries(currentStock.get_name());
		ArrayList<String> fiveDayData = currentStock.get_five_day_data();
		int length = fiveDayData.size();
		for (int i = 22; i < length; i+= 5)
		{
			String[] data = fiveDayData.get(i).split(",");
			Date time = new Date((long) Integer.parseInt(data[0]) * 1000);
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy-h-m");
			series.addOrUpdate(new Minute(time), Double.parseDouble(data[1]));
		}
		String[] data = fiveDayData.get(length - 1).split(",");
		Date time = new Date((long) Integer.parseInt(data[0]) * 1000);
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy-h-m");
		series.addOrUpdate(new Minute(time), Double.parseDouble(data[1]));
		
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				currentStock.get_name() + "(" + currentStock.get_symbol() + ")" + " Five Day", 
				"Date", 
				"Price", 
				dataset, 
				true, 
				true, 
				false
		);
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);
		ValueAxis yAxis = (ValueAxis) plot.getRangeAxis();
		
		DateAxis xAxis = (DateAxis) plot.getDomainAxis();
		Date now = new Date();
		SegmentedTimeline segmentedTimeline = SegmentedTimeline.newFifteenMinuteTimeline();
		segmentedTimeline.addBaseTimelineExclusions(segmentedTimeline.getStartTime(), now.getTime());
		Calendar[][] holidays = DayRange.getHolidayDates();
		for (int i = 0 ; i < holidays[0].length; i++)
		{
			Calendar day = Calendar.getInstance();
			day.set(Calendar.YEAR, holidays[0][i].get(Calendar.YEAR));
			day.set(Calendar.MONTH, holidays[0][i].get(Calendar.MONTH));
			day.set(Calendar.DAY_OF_MONTH, holidays[0][i].get(Calendar.DAY_OF_MONTH));
			day.set(Calendar.HOUR_OF_DAY, 9);
			segmentedTimeline.addException(day.getTimeInMillis(), day.getTimeInMillis() + 21600000);
		}
		xAxis.setTimeline(segmentedTimeline);
		xAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
			 //xAxis.setVerticalTickLabels(true);
		xAxis.setDateFormatOverride(new SimpleDateFormat("MM-dd"));
		xAxis.setAutoTickUnitSelection(false);
		xAxis.setAutoRange(false);
		
		StandardXYItemRenderer renderer1 = new StandardXYItemRenderer();
		renderer1.setSeriesPaint(0,Color.BLUE); 		
		TimeSeries movingAverage5 = MovingAverage.createMovingAverage(series, "MA(5)", 30, 0);
		Double currMA5 = (Double) movingAverage5.getDataItem(movingAverage5.getItemCount() - 1).getValue();
		currMA5 = Math.round(currMA5 * 100.0) / 100.0;		
		movingAverage5.setKey("MA(5): " + currMA5);
        TimeSeriesCollection collection = new TimeSeriesCollection();
        collection.addSeries(movingAverage5);
        plot.setDataset(1, collection);
        plot.setRenderer(1, renderer1);	
		
		plot.setBackgroundPaint(Color.WHITE);
		
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
