package View;

import java.awt.FlowLayout;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import javax.swing.table.AbstractTableModel;
import java.io.*;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartUtilities; 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class MainFrame extends JFrame{

	private FlowLayout layout;
	private JTable table;
	private PriceTable priceTable;
	private Chart chart;
	
	public MainFrame(){
		super();

		chart = new Chart();
		layout = new FlowLayout();
		priceTable = new PriceTable();
		table = new JTable(priceTable);

		JScrollPane scrollPane = new JScrollPane(table);
		super.setSize(600, 500);
		super.setPreferredSize(new Dimension(600,500));
		super.setVisible(true);
		super.setLayout(layout);
		super.add(table);
		super.add(new Chart());

		super.pack();
		super.setVisible(true);
	}

	public void addTableValue(String price, int tick){
		this.priceTable.addRow(new Object[]{price,tick});
	}

	public void add(Line line, float price, int tick ){
		chart.add(line,price,tick);
		/*
		 * 		ChartPanel chartPanel;
		JFreeChart chart = ChartFactory.createLineChart("Stocks","Time (second)","Price ($)",(CategoryDataset) datasets,PlotOrientation.VERTICAL,true,true,false);
		chart.setBackgroundPaint(Color.yellow);
		chartPanel = new ChartPanel(chart);
		super.add(chartPanel);

		 */
		//lines[line.getIndex()].add(price,tick);
	}
}