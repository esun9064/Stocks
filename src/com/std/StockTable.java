/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.std;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Eric
 */
public class StockTable extends JXTable {
    
    StockTable()
    {
        super();
    }
    
    StockTable(Object[][] rowData, Object[] columnNames)
    {
        super(rowData, columnNames);
    }
    
    
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
    
    public void setRowHeights(int height)
    {
        try
        {
            for (int row = 0; row < this.getRowCount(); row++)
            {
                this.setRowHeight(row, height);
            }
        }
        catch(ClassCastException e) {}
    }
    
    public void sizeColumnsToFit() {
        sizeColumnsToFit(5);
    }
    
    public void sizeColumnsToFit(int columnMargin) {
        JTableHeader tableHeader = this.getTableHeader();
        
        if(tableHeader == null) {
            // can't auto size a table without a header
            return;
        }
        
        FontMetrics headerFontMetrics = tableHeader.getFontMetrics(tableHeader.getFont());
        
        int[] minWidths = new int[this.getColumnCount()];
        int[] maxWidths = new int[this.getColumnCount()];
        
        for(int columnIndex = 0; columnIndex < this.getColumnCount(); columnIndex++) {
            int headerWidth = headerFontMetrics.stringWidth(this.getColumnName(columnIndex));
            
            minWidths[columnIndex] = headerWidth + columnMargin;
            
            int maxWidth = getMaximalRequiredColumnWidth(columnIndex, headerWidth);
            
            maxWidths[columnIndex] = Math.max(maxWidth, minWidths[columnIndex]) + columnMargin;
        }
        
        adjustMaximumWidths(minWidths, maxWidths);
        
        for(int i = 0; i < minWidths.length; i++) {
            if(minWidths[i] > 0) {
                this.getColumnModel().getColumn(i).setMinWidth(minWidths[i]);
            }
            
            if(maxWidths[i] > 0) {
                this.getColumnModel().getColumn(i).setMaxWidth(maxWidths[i]);
                
                this.getColumnModel().getColumn(i).setWidth(maxWidths[i]);
            }
        }
    }
    
    private void adjustMaximumWidths(int[] minWidths, int[] maxWidths) {
        if(this.getWidth() > 0) {
            // to prevent infinite loops in exceptional situations
            int breaker = 0;
            
            // keep stealing one pixel of the maximum width of the highest column until we can fit in the width of the table
            while(sum(maxWidths) > this.getWidth() && breaker < 10000) {
                int highestWidthIndex = findLargestIndex(maxWidths);
                
                maxWidths[highestWidthIndex] -= 1;
                
                maxWidths[highestWidthIndex] = Math.max(maxWidths[highestWidthIndex], minWidths[highestWidthIndex]);
                
                breaker++;
            }
        }
    }
    
    private int getMaximalRequiredColumnWidth(int columnIndex, int headerWidth) {
        int maxWidth = headerWidth;
        
        TableColumn column = this.getColumnModel().getColumn(columnIndex);
        
        TableCellRenderer cellRenderer = column.getCellRenderer();
        
        if(cellRenderer == null) {
            cellRenderer = new DefaultTableCellRenderer();
        }
        
        for(int row = 0; row < this.getModel().getRowCount(); row++) {
            Component rendererComponent = cellRenderer.getTableCellRendererComponent(
                    this,
                    this.getModel().getValueAt(row, columnIndex),
                    false,
                    false,
                    row,
                    columnIndex);
            
            double valueWidth = rendererComponent.getPreferredSize().getWidth();
            
            maxWidth = (int) Math.max(maxWidth, valueWidth);
        }
        
        return maxWidth;
    }
    
    private int findLargestIndex(int[] widths) {
        int largestIndex = 0;
        int largestValue = 0;
        
        for(int i = 0; i < widths.length; i++) {
            if(widths[i] > largestValue) {
                largestIndex = i;
                largestValue = widths[i];
            }
        }
        
        return largestIndex;
    }
    
    private int sum(int[] widths) {
        int sum = 0;
        
        for(int width : widths) {
            sum += width;
        }
        
        return sum;
    }
}
