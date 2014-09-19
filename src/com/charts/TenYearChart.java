/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.charts;
import com.std.DayRange;
import com.std.Index;
import com.std.YStockQuote;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;

/**
 *
 * @author Eric
 */
public class TenYearChart {
	
	TimeSeries close = new TimeSeries("Closing Price");
	ChartPanel chartPanel;

	public TenYearChart(YStockQuote currentStock) throws ParseException
	{
		DateAxis    domainAxis       = new DateAxis("Date");
		NumberAxis  rangeAxis        = new NumberAxis("Price");
		CandlestickRenderer renderer = new CandlestickRenderer();
		XYDataset   dataset          = getDataSet(currentStock);
		
		XYPlot mainPlot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
		
		//Do some setting up, see the API Doc
		renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));

		renderer.setDrawVolume(false);
		rangeAxis.setAutoRangeIncludesZero(false);
		
		domainAxis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yy"));
		domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		
		
		//Now create the chart and chart panel
		JFreeChart chart = new JFreeChart(currentStock.get_name(), null, mainPlot, false);
		
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(900, 400));
		
		
		XYPlot plot = (XYPlot) chart.getPlot();
		LegendTitle legend = new LegendTitle(plot);
		chart.addLegend(legend);
		chart.getLegend().setVisible(true);
		chart.getLegend().setPosition(RectangleEdge.BOTTOM);

		
		
		ValueAxis yAxis = (ValueAxis) plot.getRangeAxis();
		DateAxis xAxis = (DateAxis) plot.getDomainAxis();		
		
		xAxis.setDateFormatOverride(new SimpleDateFormat("MMM y"));
		xAxis.setAutoTickUnitSelection(true);
		xAxis.setAutoRange(true);
		renderer.setAutoWidthFactor(0.5); 
		renderer.setUpPaint(Color.green); 
		renderer.setDownPaint(new Color(0xc0, 0x00, 0x00)); 
		renderer.setSeriesPaint(0,Color.BLACK); 		
		StandardXYItemRenderer renderer1 = new StandardXYItemRenderer();
		renderer1.setSeriesPaint(0,Color.BLUE); 		
		TimeSeries movingAverage30 = MovingAverage.createMovingAverage(close, "MA(30)", 30, 0);
		Double currMA30 = (Double) movingAverage30.getDataItem(movingAverage30.getItemCount() - 1).getValue();
		currMA30 = Math.round(currMA30 * 100.0) / 100.0;		
		movingAverage30.setKey("MA(30): " + currMA30);
        TimeSeriesCollection collection = new TimeSeriesCollection();
        collection.addSeries(movingAverage30);
        plot.setDataset(1, collection);
        plot.setRenderer(1, renderer1);
		
		
		chartPanel.revalidate();
		chartPanel.repaint();
		chartPanel.revalidate();
		chartPanel.repaint();
	}
	
	protected AbstractXYDataset getDataSet(YStockQuote currentStock) throws ParseException {
		//This is the dataset we are going to create
		DefaultOHLCDataset result = null;
		//This is the data needed for the dataset
		OHLCDataItem[] data;
		
		//This is where we go get the data, replace with your own data source
		data = getData(currentStock);
		
		//Create a dataset, an Open, High, Low, Close dataset
		result = new DefaultOHLCDataset(currentStock.get_symbol(), data);
		
		return result;
	}
	
	//This method uses yahoo finance to get the OHLC data
	protected OHLCDataItem[] getData(YStockQuote currentStock) throws ParseException {
		ArrayList<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
		DateFormat df;
		if (currentStock instanceof Index)
			df = new SimpleDateFormat("y-MM-dd");
		else
			df = new SimpleDateFormat("dd-MM-yy");
		String TenYearDate = df.format(DayRange.ten_year);
		ArrayList<String> historicalData = currentStock.get_historical_data();
		int startIndex = currentStock.find_historical_data_index(TenYearDate);
		while (startIndex > 0)
		{
			String[] data = historicalData.get(startIndex).split(",");
			
			Date date = df.parse(data[0]);
			double open = Double.parseDouble(data[1]);
			double high = Double.parseDouble(data[2]);
			double low = Double.parseDouble(data[3]);
			double close = Double.parseDouble(data[4]);
			this.close.addOrUpdate(new Day(date), close);
			double volume;
			try
			{
				volume = Double.parseDouble(data[5]);
			}
			catch(NumberFormatException nfe)
			{
				volume = 0;
			}  
			OHLCDataItem item = new OHLCDataItem(date, open, high, low, close, volume);
			dataItems.add(item);
			startIndex -= 3;
		}
		
		OHLCDataItem[] OHLCData = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
		return OHLCData;
	}
	
	public ChartPanel getChartPanel()
	{
		return this.chartPanel;
	}
}
