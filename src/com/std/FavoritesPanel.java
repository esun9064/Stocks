/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.std;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 *
 * @author Eric
 */
public class FavoritesPanel extends JTabbedPane{
    StockPanel[] favoriteStocks = new StockPanel[10];
    public FavoritesPanel()
    {
        if (System.getProperty("os.name").contains("Mac"))
            this.setTabPlacement(JTabbedPane.TOP);
        else
            this.setTabPlacement(JTabbedPane.LEFT);
        System.out.println(System.getProperty("os.name"));
        this.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
    }
    
    public void getFavoriteStocks(String filePath, YStockQuote sp500, DayRange b, JPopupMenu graphMenu) throws FileNotFoundException
    {
        File file = new File(filePath);
        Scanner input = new Scanner(file);
        for (int i = 0 ; i < 10; i++)
        {
            if (input.hasNext())
            {
                String symbol = input.next();
                YStockQuote stock = new YStockQuote(symbol);

                
                String message;
                String stats = stock.get_price() + "<br> " + stock.get_change() + " " + stock.get_percent_change();
                if (Double.parseDouble(stock.get_change()) < 0)
                    message = stock.get_symbol() + "<font color='red'> " + stats + "</font>";
                else
                    message = stock.get_symbol() + "<font color='green'> " + stats + "</font>";
                String title = "<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>" + message + "</body></html>";
                if (System.getProperty("os.name").contains("Mac"))
                    title = title.replaceAll("<br>", "");
                favoriteStocks[i] = new StockPanel(new YStockQuote(symbol), sp500, b, graphMenu);
                
                this.addTab(title, favoriteStocks[i]);
                this.addChangeListener(new ChangeListener()
                {
                    public void stateChanged(ChangeEvent e)
                    {
                        processTabChange();
                    }
                    
                    
                });
                
            }
            else
                break;
        }
        
    }
    
    private void processTabChange() {
        if (this.getSelectedComponent() instanceof StockPanel)
        {
            StockPanel pnl = (StockPanel) this.getSelectedComponent();
            if (pnl.getFinished() == false)
            {
                pnl.setHistoricalData();
            }
        }
    }
    
    public void addNewTab(String stock, YStockQuote sp500, DayRange b, JPopupMenu graphMenu)
    {
        YStockQuote newStock = new YStockQuote(stock);
        StockPanel newPnl = new StockPanel(newStock, sp500, b, graphMenu);
        String message;
        String stats = newStock.get_price() + "<br> " + newStock.get_change() + " " + newStock.get_percent_change();
        if (Double.parseDouble(newStock.get_change()) < 0)
            message = newStock.get_symbol() + "<font color='red'> " + stats + "</font>";
        else
            message = newStock.get_symbol() + "<font color='green'> " + stats + "</font>";
        String title = "<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>" + message + "</body></html>";
        
        this.addTab(title, newPnl);
    }
}
