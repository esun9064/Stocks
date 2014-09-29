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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartTransferable;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;


/*
* Test file
*/
public class Test extends JFrame implements ActionListener, KeyListener {
    DayRange b;
    Date today;
    static Calendar calendar;
    YStockQuote currentStock;
    Index sp500 = new Index("%5EGSPC");
    //YStockQuote sp500 = new YStockQuote("INDEXSP");
    
    
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu toolsMenu = new JMenu("Tools");
    JMenuItem betaMenuItem = new JMenuItem("Change Beta Time Period");
    JMenuItem compareMenuItem = new JMenuItem("Compare Stocks");
    
    JPopupMenu graphMenu = new JPopupMenu();
    JMenuItem copyMenuItem = new JMenuItem("Copy");
    JMenu saveMenu = new JMenu("Save As");
    JMenuItem pngMenuItem = new JMenuItem("PNG");
    JMenuItem printMenuItem = new JMenuItem("Print...");
    JMenuItem sepWindowMenuItem = new JMenuItem("Show Charts in Separate Window");
    
    JPanel main = new JPanel();
    JPanel getquotePanel = new JPanel();
    JPanel stockInfoPanel = new JPanel();
    JTextField stockInputField = new JTextField(10);
    JButton getQuote = new JButton("Get Quote");
    JButton addToFavorites = new JButton("Add to Favorites");
    
    
    JLabel nameLbl = new JLabel();
    JLabel priceChangePercentLbl = new JLabel();
    Object[][] data;
    JPanel nameAndChangePanel = new JPanel();
    JPanel dataAndGraph = new JPanel();
    StockTable remainingInfoTable = new StockTable();
    JTabbedPane chartPane = new JTabbedPane();
    FavoritesPanel favPanel = new FavoritesPanel();
    
    
    //compare window
    JFrame compareContentFrame = new JFrame();
    JPanel compareContent = new JPanel();
    YStockQuote stock1;
    YStockQuote stock2;
    YStockQuote stock3;
    YStockQuote stock4;
    JPanel stock1Pnl;
    JPanel stock2Pnl;
    JPanel stock3Pnl;
    JPanel stock4Pnl;
    
    //compare stock 1
    JPanel getquotePanel1 = new JPanel();
    JPanel stockInfoPanel1 = new JPanel();
    JTextField stockInputField1 = new JTextField(10);
    JButton getQuote1 = new JButton("Get Quote");
    
    JLabel nameLbl1 = new JLabel();
    JLabel priceChangePercentLbl1 = new JLabel();
    Object[][] data1;
    JPanel nameAndChangePanel1 = new JPanel();
    JPanel dataAndGraph1 = new JPanel();
    StockTable remainingInfoTable1 = new StockTable();
    JTabbedPane chartPane1 = new JTabbedPane();
    JTabbedPane comparePane1 = new JTabbedPane();
    //compare stock 2
    JPanel getquotePanel2 = new JPanel();
    JPanel stockInfoPanel2 = new JPanel();
    JTextField stockInputField2 = new JTextField(10);
    JButton getQuote2 = new JButton("Get Quote");
    
    JLabel nameLbl2 = new JLabel();
    JLabel priceChangePercentLbl2 = new JLabel();
    Object[][] data2;
    JPanel nameAndChangePanel2 = new JPanel();
    JPanel dataAndGraph2 = new JPanel();
    StockTable remainingInfoTable2 = new StockTable();
    JTabbedPane chartPane2 = new JTabbedPane();
    JTabbedPane comparePane2 = new JTabbedPane();
    
    //compare stock 3
    JPanel getquotePanel3 = new JPanel();
    JPanel stockInfoPanel3 = new JPanel();
    JTextField stockInputField3 = new JTextField(10);
    JButton getQuote3 = new JButton("Get Quote");
    
    JLabel nameLbl3 = new JLabel();
    JLabel priceChangePercentLbl3 = new JLabel();
    Object[][] data3;
    JPanel nameAndChangePanel3 = new JPanel();
    JPanel dataAndGraph3 = new JPanel();
    StockTable remainingInfoTable3 = new StockTable();
    JTabbedPane chartPane3 = new JTabbedPane();
    JTabbedPane comparePane3 = new JTabbedPane();
    
    //compare stock 4
    JPanel getquotePanel4 = new JPanel();
    JPanel stockInfoPanel4 = new JPanel();
    JTextField stockInputField4 = new JTextField(10);
    JButton getQuote4 = new JButton("Get Quote");
    
    JLabel nameLbl4 = new JLabel();
    JLabel priceChangePercentLbl4 = new JLabel();
    Object[][] data4;
    JPanel nameAndChangePanel4 = new JPanel();
    JPanel dataAndGraph4 = new JPanel();
    StockTable remainingInfoTable4 = new StockTable();
    JTabbedPane chartPane4 = new JTabbedPane();
    JTabbedPane comparePane4 = new JTabbedPane();
    
    /*
    * Initialize calendar and methods for updating stock information
    */
    public final void init() {
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
    
    public static final void show_hist_data(YStockQuote x, DayRange b) {
        x.getIntraday();
        if (x instanceof Index)
        {
            DateFormat df = new SimpleDateFormat("y-MM-dd");
            String[] date = df.format(calendar.getTime()).split("-");
            
            ((Index) x).find_historical_data(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR));
            
            ((Index) x).find_five_day_change(df.format(DayRange.five_day));
            
            ((Index) x).find_one_month_change(df.format(DayRange.one_month));
            
            ((Index) x).find_three_month_change(df.format(DayRange.three_month));
            
            ((Index) x).find_six_month_change(df.format(DayRange.six_month));
            
            ((Index) x).find_one_year_change(df.format(DayRange.one_year));
            
            ((Index) x).find_five_year_change(df.format(DayRange.five_year));
            
            ((Index) x).find_ten_year_change(df.format(DayRange.ten_year));
            
            ((Index) x).find_max_change();
            
            ((Index) x).find_ytd_change(df.format(DayRange.ytd));
            
        }
        else
        {
            DateFormat df = new SimpleDateFormat("d-MM-yy");
            String[] date = df.format(calendar.getTime()).split("-");
            
            ((YStockQuote) x).find_historical_data(date[0], date[1], date[2]);
            x.find_five_day_change(df.format(DayRange.five_day));
            
            x.find_one_month_change(df.format(DayRange.one_month));
            
            x.find_three_month_change(df.format(DayRange.three_month));
            
            x.find_six_month_change(df.format(DayRange.six_month));
            
            x.find_one_year_change(df.format(DayRange.one_year));
            
            x.find_five_year_change(df.format(DayRange.five_year));
            
            x.find_ten_year_change(df.format(DayRange.ten_year));
            
            x.find_max_change();
            
            x.find_ytd_change(df.format(DayRange.ytd));
        }
        
    }
    
    public Test()
    {
        init();
        show_hist_data(sp500, this.b);
        
        sp500.calculate_historical_rate_of_return(0);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        setVisible(true);
        setSize(1280, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        graphMenu.add(sepWindowMenuItem);
        
        main.setLayout(new BorderLayout());
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(toolsMenu);
        toolsMenu.add(betaMenuItem);
        toolsMenu.add(compareMenuItem);
        compareMenuItem.addActionListener(this);
        main.add(mainMenuBar, BorderLayout.NORTH);
        add(main);
        
        graphMenu.add(copyMenuItem);
        copyMenuItem.setActionCommand("COPY");
        copyMenuItem.addActionListener(this);
        graphMenu.add(saveMenu);
        saveMenu.add(pngMenuItem);
        pngMenuItem.setActionCommand("PNG");
        pngMenuItem.addActionListener(this);
        graphMenu.add(printMenuItem);
        printMenuItem.setActionCommand("PRINT");
        printMenuItem.addActionListener(this);
        graphMenu.add(sepWindowMenuItem);
        sepWindowMenuItem.setActionCommand("SEP");
        sepWindowMenuItem.addActionListener(this);
        
        
        //input field and button
        getquotePanel.setLayout(new  FlowLayout(FlowLayout.LEFT));
        getQuote.addActionListener(this);
        getquotePanel.add(stockInputField);
        getquotePanel.add(getQuote);
        
        
        stockInfoPanel.setLayout(new BorderLayout());
        nameAndChangePanel.setLayout(new BoxLayout(nameAndChangePanel,BoxLayout.PAGE_AXIS));
        nameAndChangePanel.add(nameLbl);
        nameAndChangePanel.add(priceChangePercentLbl);
        //stockInfoPanel.add(nameAndChangePanel, BorderLayout.NORTH);
        
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
        remainingInfoTable.setBackground(stockInfoPanel.getBackground());
        remainingInfoTable.setFocusable(false);
        remainingInfoTable.setColumnSelectionAllowed(false);
        remainingInfoTable.setRowSelectionAllowed(false);
        remainingInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataAndGraph.add(remainingInfoTable, c);
        stockInfoPanel.add(getquotePanel, BorderLayout.NORTH);
        stockInfoPanel.add(dataAndGraph, BorderLayout.CENTER);
        addToFavorites.addActionListener(this);
        stockInputField.requestFocus();
        stockInputField.addKeyListener(this);
        
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 2;
        //c.ipady = 20;
        c.gridx = 1;
        c.gridy = 0;
        dataAndGraph.add(chartPane, c);
        
        //add favorits button to grid layout
        /*
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = .1;
        c.weighty = .1;
        c.gridwidth = 1;
        c.gridheight = 0;
        dataAndGraph.add(addToFavorites, c);
        */
        chartPane.setVisible(false);
        
        stockInputField.setText("%5EGSPC");
        this.getNewStock();
        
        String title = "<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Get Quote<br></body></html>";
        
        favPanel.addTab(title, stockInfoPanel);
        try {
            favPanel.getFavoriteStocks("favorites.txt", sp500, b, graphMenu);
        }
        catch(FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(main, "A basic JOptionPane message dialog");		}
        main.add(favPanel, BorderLayout.CENTER);
        
        repaint();
        revalidate();
        
    }
    
    public static void main(String args[])
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }
        Test sp = new Test();
        
        YStockQuote[] sp500 = new YStockQuote[500];
        
        //sp.update_per_15(sp500);
        
        /*
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
        */
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getQuote)
        {
            this.getNewStock();
        }
        if (e.getSource() == getQuote1)
        {
            this.getNewStock(stock1, stockInputField1, nameLbl1, priceChangePercentLbl1, data1, remainingInfoTable1, dataAndGraph1, chartPane1, comparePane1);
            if (stock2Pnl != null)
                stock2Pnl.setVisible(true);
            
        }
        if (e.getSource() == getQuote2)
        {
            this.getNewStock(stock2, stockInputField2, nameLbl2, priceChangePercentLbl2, data2, remainingInfoTable2, dataAndGraph2, chartPane2, comparePane2);
            if (stock3Pnl != null)
                stock3Pnl.setVisible(true);
        }
        if (e.getSource() == getQuote3)
        {
            if (stock4Pnl != null)
                stock4Pnl.setVisible(true);
            this.getNewStock(stock3, stockInputField3, nameLbl3, priceChangePercentLbl3, data3, remainingInfoTable3, dataAndGraph3, chartPane3, comparePane3);
        }
        if (e.getSource() == getQuote4)
        {
            this.getNewStock(stock4, stockInputField4, nameLbl4, priceChangePercentLbl4, data4, remainingInfoTable4, dataAndGraph4, chartPane4, comparePane4);
        }
        if (e.getSource() == copyMenuItem)
        {
            doCopy((ChartPanel) chartPane.getSelectedComponent());
        }
        if (e.getSource() == printMenuItem)
        {
            createChartPrintJob((ChartPanel) chartPane.getSelectedComponent());
        }
        if (e.getSource() == pngMenuItem)
        {
            
            try {
                doSaveAs((ChartPanel) chartPane.getSelectedComponent());
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "I/O error occurred.",
                        "Save As PNG", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == sepWindowMenuItem)
        {
            showSepWindow();
        }
        if (e.getSource() == compareMenuItem)
        {
            compareMenuItem();
        }
        if (e.getSource() == addToFavorites)
        {
            addStockToFavoritesList();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == stockInputField)
        {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                this.getNewStock();
            }
        }
    }
    
    public void addStockToFavoritesList()
    {
        try {
            File file = new File("favorites.txt");
            Scanner input = new Scanner(file);
            int i = 0;
            while (input.hasNext())
            {
                input.next();
                i++;
            }
            if (i < 10)
            {
                String symbol = currentStock.get_symbol();
                PrintWriter writer = new PrintWriter(file);
                writer.append(symbol + "\n");
                favPanel.addNewTab(symbol, this.sp500, this.b, graphMenu);
                this.revalidate();
                this.repaint();
            }
        }
        catch(FileNotFoundException ex)
        {
            
        }
    }
    
    public void getNewStock()
    {
        if (stockInputField.getText().equals("%5EGSPC"))
        {
            currentStock = new Index("%5EGSPC");
        }
        else
            currentStock = new YStockQuote(stockInputField.getText());
        show_hist_data(currentStock, this.b);
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
            data[16][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Percent 200 Day MA: " ,  currentStock.get_percent_change_from_twohundredday_moving_avg());
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
            
            GridBagConstraints c = new GridBagConstraints();
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
    }
    
    /**
     * Copies the current chart to the system clipboard.
     *
     * @since 1.0.13
     */
    public void doCopy(ChartPanel chartToCopy) {
        Clipboard systemClipboard
                = Toolkit.getDefaultToolkit().getSystemClipboard();
        Insets insets = getInsets();
        int w = getWidth() - insets.left - insets.right;
        int h = getHeight() - insets.top - insets.bottom;
        ChartTransferable selection = new ChartTransferable(chartToCopy.getChart(), w, h,
                chartToCopy.getMinimumDrawWidth(), chartToCopy.getMinimumDrawHeight(),
                chartToCopy.getMaximumDrawWidth(), chartToCopy.getMaximumDrawHeight(), true);
        systemClipboard.setContents(selection, null);
    }
    
    /**
     * Opens a file chooser and gives the user an opportunity to save the chart
     * in PNG format.
     *
     * @throws IOException if there is an I/O error.
     */
    public void doSaveAs(ChartPanel chartToSave) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(chartToSave.getDefaultDirectoryForSaveAs());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Image Files", "png");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File(chartToSave.getChart().getTitle().getText()));
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().getPath();
            if (chartToSave.isEnforceFileExtensions()) {
                if (!filename.endsWith(".png")) {
                    filename = filename + ".png";
                }
            }
            ChartUtilities.saveChartAsPNG(new File(filename), chartToSave.getChart(),
                    getWidth(), getHeight());
        }
        
    }
    
    /**
     * Creates a print job for the chart.
     */
    public void createChartPrintJob(ChartPanel chartToPrint) {
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pf = job.defaultPage();
        PageFormat pf2 = job.pageDialog(pf);
        if (pf2 != pf) {
            job.setPrintable(chartToPrint, pf2);
            if (job.printDialog()) {
                try {
                    job.print();
                }
                catch (PrinterException e) {
                    JOptionPane.showMessageDialog(this, e);
                }
            }
        }
    }
    
    /**
     * Prints the chart on a single page.
     *
     * @param g  the graphics context.
     * @param pf  the page format to use.
     * @param pageIndex  the index of the page. If not <code>0</code>, nothing
     *                   gets print.
     *
     * @return The result of printing.
     */
    public int print(Graphics g, PageFormat pf, int pageIndex, ChartPanel chartToPrint) {
        
        if (pageIndex != 0) {
            return 0;
        }
        Graphics2D g2 = (Graphics2D) g;
        double x = pf.getImageableX();
        double y = pf.getImageableY();
        double w = pf.getImageableWidth();
        double h = pf.getImageableHeight();
        chartToPrint.getChart().draw(g2, new Rectangle2D.Double(x, y, w, h), chartToPrint.getAnchor(),
                null);
        return 1;
        
    }
    
    private void showSepWindow() {
        try {
            JFrame chartFrame = new JFrame();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            chartFrame.setVisible(true);
            chartFrame.setSize(screenSize);
            JTabbedPane tempChartPane = new JTabbedPane();
            ChartPanel intradayChart = new IntradayChart(currentStock).getChartPanel();
            intradayChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("1d", intradayChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_1);
            
            ChartPanel fivedayChart = new FiveDayChart(currentStock).getChartPanel();
            fivedayChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("5d", fivedayChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_2);
            
            ChartPanel onemonthChart = new OneMonthChart(currentStock).getChartPanel();
            onemonthChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("1m", onemonthChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_3);
            
            ChartPanel threemonthChart = new ThreeMonthChart(currentStock).getChartPanel();
            threemonthChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("3m", threemonthChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_4);
            
            ChartPanel sixmonthChart = new SixMonthChart(currentStock).getChartPanel();
            sixmonthChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("6m", sixmonthChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_5);
            
            ChartPanel ytdChart = new YTDChart(currentStock).getChartPanel();
            ytdChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("ytd", ytdChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_6);
            
            ChartPanel oneyearChart = new OneYearChart(currentStock).getChartPanel();
            oneyearChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("1y", oneyearChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_7);
            
            ChartPanel fiveyearChart = new FiveYearChart(currentStock).getChartPanel();
            fiveyearChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("5y", fiveyearChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_8);
            
            ChartPanel tenyearChart = new TenYearChart(currentStock).getChartPanel();
            tenyearChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("10y", tenyearChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_9);
            
            ChartPanel maxChart = new MaxChart(currentStock).getChartPanel();
            maxChart.setPopupMenu(graphMenu);
            tempChartPane.addTab("max", maxChart);
            tempChartPane.setMnemonicAt(0, KeyEvent.VK_0);
            
            chartFrame.add(tempChartPane);
            
        }
        catch (ParseException e)
        {
            
        }
    }
    
    public void compareMenuItem()
    {
        compareContentFrame = new JFrame();
        compareContentFrame.setVisible(true);
        compareContentFrame.setSize(1280, 720);
        getquotePanel1 = new JPanel();
        stockInfoPanel1 = new JPanel();
        stockInputField1 = new JTextField(10);
        getQuote1 = new JButton("Get Quote");
        nameLbl1 = new JLabel();
        priceChangePercentLbl1 = new JLabel();
        nameAndChangePanel1 = new JPanel();
        dataAndGraph1 = new JPanel();
        remainingInfoTable1 = new StockTable();
        chartPane1 = new JTabbedPane();
        comparePane1 = new JTabbedPane();
        getquotePanel2 = new JPanel();
        stockInfoPanel2 = new JPanel();
        stockInputField2 = new JTextField(10);
        getQuote2 = new JButton("Get Quote");
        nameLbl2 = new JLabel();
        priceChangePercentLbl2 = new JLabel();
        nameAndChangePanel2 = new JPanel();
        dataAndGraph2 = new JPanel();
        remainingInfoTable2 = new StockTable();
        chartPane2 = new JTabbedPane();
        comparePane2 = new JTabbedPane();
        getquotePanel3 = new JPanel();
        stockInfoPanel3 = new JPanel();
        stockInputField3 = new JTextField(10);
        getQuote3 = new JButton("Get Quote");
        nameLbl3 = new JLabel();
        priceChangePercentLbl3 = new JLabel();
        nameAndChangePanel3 = new JPanel();
        dataAndGraph3 = new JPanel();
        remainingInfoTable3 = new StockTable();
        chartPane3 = new JTabbedPane();
        comparePane3 = new JTabbedPane();
        getquotePanel4 = new JPanel();
        stockInfoPanel4 = new JPanel();
        stockInputField4 = new JTextField(10);
        getQuote4 = new JButton("Get Quote");
        nameLbl4 = new JLabel();
        priceChangePercentLbl4 = new JLabel();
        nameAndChangePanel4 = new JPanel();
        dataAndGraph4 = new JPanel();
        remainingInfoTable4 = new StockTable();
        chartPane4 = new JTabbedPane();
        comparePane4 = new JTabbedPane();
        
        compareContent = new JPanel();
        compareContent.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        
        GridBagConstraints c = new GridBagConstraints();
        
        stock1Pnl = new JPanel();
        stock1Pnl.setLayout(new BorderLayout());
        getquotePanel1.setLayout(new  FlowLayout(FlowLayout.LEFT));
        getQuote1.addActionListener(this);
        getquotePanel1.add(stockInputField1);
        getquotePanel1.add(getQuote1);
        stock1Pnl.add(getquotePanel1, BorderLayout.NORTH);
        stock1Pnl.add(comparePane1, BorderLayout.CENTER);
        comparePane1.addTab("Quote", dataAndGraph1);
        comparePane1.addTab("Charts", chartPane1);
        
        nameAndChangePanel1.setLayout(new BoxLayout(nameAndChangePanel1,BoxLayout.PAGE_AXIS));
        nameAndChangePanel1.add(nameLbl1);
        nameAndChangePanel1.add(priceChangePercentLbl1);
        //stockInfoPanel.add(nameAndChangePanel, BorderLayout.NORTH);
        
        data1 = new Object[30][2];
        String[] colnames1 = {	"1", "2"};
        
        dataAndGraph1.setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,20);
        dataAndGraph1.add(nameAndChangePanel1, c);
        c.weightx = 0;
        c.weighty = 1;
        c.insets = new Insets(0,20,0,0);
        //c.ipady = 20;
        c.gridx = 0;
        c.gridy = 1;
        remainingInfoTable1 = new StockTable(data1, colnames1);
        remainingInfoTable1.setShowGrid(false);
        remainingInfoTable1.setTableHeader(null);
        remainingInfoTable1.setFocusable(false);
        remainingInfoTable1.setColumnSelectionAllowed(false);
        remainingInfoTable1.setRowSelectionAllowed(false);
        remainingInfoTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataAndGraph1.add(new JScrollPane(remainingInfoTable1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), c);
        stockInputField1.requestFocus();
        stockInputField1.addKeyListener(this);
        
        chartPane1.setVisible(false);
        repaint();
        revalidate();
        compareContent.add(stock1Pnl, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        stock2Pnl = new JPanel();
        stock2Pnl.setLayout(new BorderLayout());
        getquotePanel2.setLayout(new  FlowLayout(FlowLayout.LEFT));
        getQuote2.addActionListener(this);
        getquotePanel2.add(stockInputField2);
        getquotePanel2.add(getQuote2);
        stock2Pnl.add(getquotePanel2, BorderLayout.NORTH);
        stock2Pnl.add(comparePane2, BorderLayout.CENTER);
        comparePane2.addTab("Quote", dataAndGraph2);
        comparePane2.addTab("Charts", chartPane2);
        
        nameAndChangePanel2.setLayout(new BoxLayout(nameAndChangePanel2,BoxLayout.PAGE_AXIS));
        nameAndChangePanel2.add(nameLbl2);
        nameAndChangePanel2.add(priceChangePercentLbl2);
        //stockInfoPanel.add(nameAndChangePanel, BorderLayout.NORTH);
        
        data2 = new Object[30][2];
        String[] colnames2 = {	"1", "2"};
        
        dataAndGraph2.setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.NORTHEAST;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,20);
        c.gridx = 0;
        c.gridy = 0;
        dataAndGraph2.add(nameAndChangePanel2, c);
        c.weightx = 0;
        c.weighty = 1;
        c.insets = new Insets(0,20,0,0);
        //c.ipady = 20;
        c.gridx = 0;
        c.gridy = 1;
        remainingInfoTable2 = new StockTable(data2, colnames2);
        remainingInfoTable2.setShowGrid(false);
        remainingInfoTable2.setTableHeader(null);
        remainingInfoTable2.setFocusable(false);
        remainingInfoTable2.setColumnSelectionAllowed(false);
        remainingInfoTable2.setRowSelectionAllowed(false);
        remainingInfoTable2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataAndGraph2.add(new JScrollPane(remainingInfoTable2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), c);
        stockInputField2.requestFocus();
        stockInputField2.addKeyListener(this);
        
        chartPane2.setVisible(false);
        repaint();
        revalidate();
        compareContent.add(stock2Pnl, gc);
        
        gc.gridx = 0;
        gc.gridy = 1;
        stock3Pnl = new JPanel();
        stock3Pnl.setLayout(new BorderLayout());
        getquotePanel3.setLayout(new  FlowLayout(FlowLayout.LEFT));
        getQuote3.addActionListener(this);
        getquotePanel3.add(stockInputField3);
        getquotePanel3.add(getQuote3);
        stock3Pnl.add(getquotePanel3, BorderLayout.NORTH);
        stock3Pnl.add(comparePane3, BorderLayout.CENTER);
        comparePane3.addTab("Quote", dataAndGraph3);
        comparePane3.addTab("Charts", chartPane3);
        
        nameAndChangePanel3.setLayout(new BoxLayout(nameAndChangePanel3,BoxLayout.PAGE_AXIS));
        nameAndChangePanel3.add(nameLbl3);
        nameAndChangePanel3.add(priceChangePercentLbl3);
        //stockInfoPanel.add(nameAndChangePanel, BorderLayout.NORTH);
        
        data3 = new Object[30][2];
        String[] colnames3 = {	"1", "2"};
        
        dataAndGraph3.setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.SOUTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,20);
        c.gridx = 0;
        c.gridy = 0;
        dataAndGraph3.add(nameAndChangePanel3, c);
        c.weightx = 0;
        c.weighty = 1;
        c.insets = new Insets(0,20,0,0);
        //c.ipady = 20;
        c.gridx = 0;
        c.gridy = 1;
        remainingInfoTable3 = new StockTable(data3, colnames3);
        remainingInfoTable3.setShowGrid(false);
        remainingInfoTable3.setTableHeader(null);
        remainingInfoTable3.setFocusable(false);
        remainingInfoTable3.setColumnSelectionAllowed(false);
        remainingInfoTable3.setRowSelectionAllowed(false);
        remainingInfoTable3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataAndGraph3.add(new JScrollPane(remainingInfoTable3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), c);
        stockInputField3.requestFocus();
        stockInputField3.addKeyListener(this);
        
        chartPane3.setVisible(false);
        repaint();
        revalidate();
        compareContent.add(stock3Pnl, gc);
        
        
        gc.gridx = 1;
        gc.gridy = 1;
        stock4Pnl = new JPanel();
        stock4Pnl.setLayout(new BorderLayout());
        getquotePanel4.setLayout(new  FlowLayout(FlowLayout.LEFT));
        getQuote4.addActionListener(this);
        getquotePanel4.add(stockInputField4);
        getquotePanel4.add(getQuote4);
        stock4Pnl.add(getquotePanel4, BorderLayout.NORTH);
        stock4Pnl.add(comparePane4, BorderLayout.CENTER);
        comparePane4.addTab("Quote", dataAndGraph4);
        comparePane4.addTab("Charts", chartPane4);
        
        nameAndChangePanel4.setLayout(new BoxLayout(nameAndChangePanel4,BoxLayout.PAGE_AXIS));
        nameAndChangePanel4.add(nameLbl4);
        nameAndChangePanel4.add(priceChangePercentLbl4);
        
        data4 = new Object[30][2];
        String[] colnames4 = {	"1", "2"};
        
        dataAndGraph4.setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,20);
        c.gridx = 0;
        c.gridy = 0;
        dataAndGraph4.add(nameAndChangePanel4, c);
        c.weightx = 0;
        c.weighty = 1;
        c.insets = new Insets(0,20,0,0);
        //c.ipady = 20;
        c.gridx = 0;
        c.gridy = 1;
        remainingInfoTable4 = new StockTable(data4, colnames4);
        remainingInfoTable4.setShowGrid(false);
        remainingInfoTable4.setTableHeader(null);
        remainingInfoTable4.setFocusable(false);
        remainingInfoTable4.setColumnSelectionAllowed(false);
        remainingInfoTable4.setRowSelectionAllowed(false);
        remainingInfoTable4.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataAndGraph4.add(new JScrollPane(remainingInfoTable4, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), c);
        stockInputField4.requestFocus();
        stockInputField4.addKeyListener(this);
        
        chartPane4.setVisible(false);
        repaint();
        revalidate();
        comparePane1.setVisible(false);
        comparePane2.setVisible(false);
        comparePane3.setVisible(false);
        comparePane4.setVisible(false);
        stock3Pnl.setVisible(false);
        stock4Pnl.setVisible(false);
        stock2Pnl.setVisible(false);
        
        compareContent.add(stock4Pnl, gc);
        compareContentFrame.add(compareContent);
        compareContentFrame.repaint();
        compareContentFrame.revalidate();
        
    }
    
    public void getNewStock(YStockQuote stock, JTextField stockInputField, JLabel nameLbl,
            JLabel priceChangePercentLbl, Object[][] data, StockTable remainingInfoTable,
            JPanel dataAndGraph, JTabbedPane chartPane, JTabbedPane comparePane)
    {
        comparePane.setVisible(true);
        remainingInfoTable.setBackground(this.remainingInfoTable.getBackground());
        comparePane.setBackground(this.chartPane.getBackground());
        chartPane.setBackground(this.chartPane.getBackground());
        if (stockInputField.getText().equals("%5EGSPC"))
        {
            stock = new Index("%5EGSPC");
        }
        else
            stock = new YStockQuote(stockInputField.getText());
        show_hist_data(stock, this.b);
        stock.calculate_beta(sp500, 1200);
        
        nameLbl.setText(stock.get_name() + " (" + stock.get_symbol() + ")");
        nameLbl.setFont(new Font(nameLbl.getName(), Font.BOLD, 22));
        priceChangePercentLbl.setText(stock.get_price() + " ");
        String message = stock.get_change() + " " + stock.get_percent_change();
        if (Double.parseDouble(stock.get_change()) < 0)
            priceChangePercentLbl.setText(String.format("<html>%s<font color='red'>%s</font></html>", priceChangePercentLbl.getText(), message));
        else
            priceChangePercentLbl.setText(String.format("<html>%s<font color='green'>%s</font></html>", priceChangePercentLbl.getText(), message));
        priceChangePercentLbl.setFont(new Font(priceChangePercentLbl.getName(), Font.PLAIN, 15));
        
        
        data[0][0] = "Prev Close: " + stock.get_prev_close();
        data[1][0] = "Open: " + stock.get_open_price();
        data[2][0] = "Bid: " + stock.get_bid();
        data[3][0] = "Ask: " + stock.get_ask();
        data[4][0] = "One Year Target: " + stock.get_one_year_target();
        data[5][0] = "Ebita: " + stock.get_ebitda();
        data[0][1] = "Day Range: " + stock.get_days_range();
        data[1][1] = "52 Week Range: " + stock.get_year_range();
        data[2][1] = "Volume: " + stock.get_volume();
        data[3][1] = "Average Daily Volume: " + stock.get_avg_daily_volume();
        data[4][1] = "Market Cap: " + stock.get_market_cap();
        data[5][1] = "P/E: "  + stock.get_pe();
        data[6][1] = "EPS: " + stock.get_earnings_per_share();
        data[7][1] = "Dividend (Yield): " + stock.get_dividend_per_share() + "(" + stock.get_dividend_yield() + ")";
        data[6][0] = "Reveune:" + stock.get_revenue();
        data[7][0] = "Earnings Estimate: " + stock.get_earnings_estimate_current_year();
        data[8][0] = "Beta: " + stock.get_beta();
        data[8][1] = "PEG Ratio: " + stock.get_peg_ratio();
        data[9][0] = "Short Ratio: " + stock.get_short_ratio();
        
        data[11][0] = "50 Day MA: " + stock.get_fiftyday_moving_avg();
        data[12][0] = "200 Day MA: " + stock.get_twohundredday_moving_avg();
        if (stock.get_change_from_fiftyday_moving_avg() != null && stock.get_change_from_fiftyday_moving_avg().contains("-"))
        {
            data[13][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Change 50 Day MA: ", stock.get_change_from_fiftyday_moving_avg());
            data[14][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Percent 50 Day MA: ", stock.get_percent_change_from_fiftyday_moving_avg());
            
        }
        else
        {
            data[13][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Change 50 Day MA: ", stock.get_change_from_fiftyday_moving_avg());
            data[14][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Percent 50 Day MA: ", stock.get_percent_change_from_fiftyday_moving_avg());
        }
        if (stock.get_change_from_twohundredday_moving_avg() != null && stock.get_change_from_twohundredday_moving_avg().contains("-"))
        {
            data[15][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Change 200 Day MA: ", stock.get_change_from_twohundredday_moving_avg());
            data[16][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Percent 200 Day MA: ", stock.get_percent_change_from_twohundredday_moving_avg());
        }
        else
        {
            data[15][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Change 200 Day MA: ", stock.get_change_from_twohundredday_moving_avg());
            data[16][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Percent 200 Day MA: ", stock.get_percent_change_from_twohundredday_moving_avg());
        }
        data[17][0] = "Year High: " + stock.get_year_high();
        data[18][0] = "Year Low: " + stock.get_year_low();
        if (stock.get_change_from_year_high() != null && stock.get_change_from_year_high().contains("-"))
        {
            data[19][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Year High Change: ", stock.get_change_from_year_high());
            data[20][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Year High Percent: ", stock.get_percent_change_from_year_high());
        }
        else
        {
            data[19][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Year High Change: ", stock.get_change_from_year_high());
            data[20][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Year High Percent: ", stock.get_percent_change_from_year_high());
        }
        if (stock.get_change_from_year_low() != null && stock.get_change_from_year_low().contains("-"))
        {
            data[21][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Year Low Change: ", stock.get_change_from_year_low());
            data[22][0] = String.format("<html>%s<font color='red'>%s</font></html>", "Year Low Percent:  ", stock.get_percent_change_from_year_low());
        }
        else
        {
            data[21][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Year Low Change: ", stock.get_change_from_year_low());
            data[22][0] = String.format("<html>%s<font color='green'>%s</font></html>", "Year Low Percent:  ", stock.get_percent_change_from_year_low());
        }
        if (stock.get_five_day_change()[0] != null && stock.get_five_day_change()[0].contains("-"))
            data[11][1] = String.format("<html>%s<font color='red'>%s</font></html>", "5D Change: ", stock.get_five_day_change()[0] + " (" + stock.get_five_day_change()[1] + ")");
        else
            data[11][1] = String.format("<html>%s<font color='green'>%s</font></html>", "5D Change: ", stock.get_five_day_change()[0] + " (" + stock.get_five_day_change()[1] + ")");
        if (stock.get_one_month_change()[0] != null && stock.get_one_month_change()[0].contains("-"))
            data[12][1] = String.format("<html>%s<font color='red'>%s</font></html>", "1M Change: ", stock.get_one_month_change()[0] + " (" + stock.get_one_month_change()[1] + ")");
        else
            data[12][1] = String.format("<html>%s<font color='green'>%s</font></html>", "1M Change: ", stock.get_one_month_change()[0] + " (" + stock.get_one_month_change()[1] + ")");
        if (stock.get_three_month_change()[0] != null && stock.get_three_month_change()[0].contains("-"))
            data[13][1] = String.format("<html>%s<font color='red'>%s</font></html>", "3M Change: ", stock.get_three_month_change()[0] + " (" + stock.get_three_month_change()[1] + ")");
        else
            data[13][1] = String.format("<html>%s<font color='green'>%s</font></html>", "3M Change: ", stock.get_three_month_change()[0] + " (" + stock.get_three_month_change()[1] + ")");
        if (stock.get_six_month_change()[0] != null && stock.get_six_month_change()[0].contains("-"))
            data[14][1] = String.format("<html>%s<font color='red'>%s</font></html>", "6M Change: ", stock.get_six_month_change()[0] + " (" + stock.get_six_month_change()[1] + ")");
        else
            data[14][1] = String.format("<html>%s<font color='green'>%s</font></html>", "6M Change: ", stock.get_six_month_change()[0] + " (" + stock.get_six_month_change()[1] + ")");
        if (stock.get_ytd_change()[0] != null && stock.get_ytd_change()[0].contains("-"))
            data[15][1] = String.format("<html>%s<font color='red'>%s</font></html>", "YTD Change: ", stock.get_ytd_change()[0] + " (" + stock.get_ytd_change()[1] + ")");
        else
            data[15][1] = String.format("<html>%s<font color='green'>%s</font></html>", "YTD Change: ", stock.get_ytd_change()[0] + " (" + stock.get_ytd_change()[1] + ")");
        if (stock.get_one_year_change()[0] != null && stock.get_one_year_change()[0].contains("-"))
            data[16][1] = String.format("<html>%s<font color='red'>%s</font></html>", "1Y Change: ", stock.get_one_year_change()[0] + " (" + stock.get_one_year_change()[1] + ")");
        else
            data[16][1] = String.format("<html>%s<font color='green'>%s</font></html>", "1Y Change: ", stock.get_one_year_change()[0] + " (" + stock.get_one_year_change()[1] + ")");
        if (stock.get_five_year_change()[0] != null && stock.get_five_year_change()[0].contains("-"))
            data[17][1] = String.format("<html>%s<font color='red'>%s</font></html>", "5Y Change: ", stock.get_five_year_change()[0] + " (" + stock.get_five_year_change()[1] + ")");
        else
            data[17][1] = String.format("<html>%s<font color='green'>%s</font></html>", "5Y Change: ", stock.get_five_year_change()[0] + " (" + stock.get_five_year_change()[1] + ")");
        if (stock.get_ten_year_change()[0] != null && stock.get_ten_year_change()[0].contains("-"))
            data[18][1] = String.format("<html>%s<font color='red'>%s</font></html>", "10Y Change: ", stock.get_ten_year_change()[0] + " (" + stock.get_ten_year_change()[1] + ")");
        else
            data[18][1] = String.format("<html>%s<font color='green'>%s</font></html>", "10Y Change: ", stock.get_ten_year_change()[0] + " (" + stock.get_ten_year_change()[1] + ")");
        if (stock.get_max_year_change()[0] != null && stock.get_max_year_change()[0].contains("-"))
            data[19][1] = String.format("<html>%s<font color='red'>%s</font></html>", "Max Change: ", stock.get_max_year_change()[0] + " (" + stock.get_max_year_change()[1] + ")");
        else
            data[19][1] = String.format("<html>%s<font color='green'>%s</font></html>", "Max Change: ", stock.get_max_year_change()[0] + " (" + stock.get_max_year_change()[1] + ")");
        
        data[24][0] = "Earnings Est Next Quarter: " + stock.get_earnings_estimate_next_quarter();
        data[25][0] = "Earnings Est Current Year: " + stock.get_earnings_estimate_current_year();
        data[26][0] = "Earnings Est Next Year: " + stock.get_earnings_estimate_next_year();
        data[24][1] = "P/E Est Current Year: " + stock.get_price_eps_estimate_current_year();
        data[25][1] = "P/E Est Next Year: " + stock.get_price_eps_estimate_next_year();
        remainingInfoTable.setFont(new Font(remainingInfoTable.getName(), Font.PLAIN, 15));
        remainingInfoTable.packColumn(0, 4);
        remainingInfoTable.packColumn(1, 4);
        try {
            chartPane = new JTabbedPane();
            chartPane.setVisible(true);
            ChartPanel intradayChart = new IntradayChart(stock).getChartPanel();
            intradayChart.setPopupMenu(graphMenu);
            chartPane.addTab("1d", intradayChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_1);
            
            ChartPanel fivedayChart = new FiveDayChart(stock).getChartPanel();
            fivedayChart.setPopupMenu(graphMenu);
            chartPane.addTab("5d", fivedayChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_2);
            
            ChartPanel onemonthChart = new OneMonthChart(stock).getChartPanel();
            onemonthChart.setPopupMenu(graphMenu);
            chartPane.addTab("1m", onemonthChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_3);
            
            ChartPanel threemonthChart = new ThreeMonthChart(stock).getChartPanel();
            threemonthChart.setPopupMenu(graphMenu);
            chartPane.addTab("3m", threemonthChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_4);
            
            ChartPanel sixmonthChart = new SixMonthChart(stock).getChartPanel();
            sixmonthChart.setPopupMenu(graphMenu);
            chartPane.addTab("6m", sixmonthChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_5);
            
            ChartPanel ytdChart = new YTDChart(stock).getChartPanel();
            ytdChart.setPopupMenu(graphMenu);
            chartPane.addTab("ytd", ytdChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_6);
            
            ChartPanel oneyearChart = new OneYearChart(stock).getChartPanel();
            oneyearChart.setPopupMenu(graphMenu);
            chartPane.addTab("1y", oneyearChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_7);
            
            ChartPanel fiveyearChart = new FiveYearChart(stock).getChartPanel();
            fiveyearChart.setPopupMenu(graphMenu);
            chartPane.addTab("5y", fiveyearChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_8);
            
            ChartPanel tenyearChart = new TenYearChart(stock).getChartPanel();
            tenyearChart.setPopupMenu(graphMenu);
            chartPane.addTab("10y", tenyearChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_9);
            
            ChartPanel maxChart = new MaxChart(stock).getChartPanel();
            maxChart.setPopupMenu(graphMenu);
            chartPane.addTab("max", maxChart);
            chartPane.setMnemonicAt(0, KeyEvent.VK_0);
            comparePane.removeAll();
            comparePane.addTab("Quote", dataAndGraph);
            comparePane.addTab("Charts", chartPane);
            
            
        } catch (ParseException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        chartPane.revalidate();
        chartPane.repaint();
        revalidate();
        repaint();
    }
}
