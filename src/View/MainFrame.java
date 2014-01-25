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
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class MainFrame extends JFrame{

	private FlowLayout layout;
	private JTable table;
	private PriceTable priceTable;

	private DefaultCategoryDataset[] lines;

	public MainFrame(){
		super();

		lines = new DefaultCategoryDataset[9];
		ChartPanel chartPanel;

		for(int i = 9; i<lines.length;i++)
		{
			JFreeChart lineChartObject = ChartFactory.createLineChart("Stocks","Time (second)","Price ($)",lines[i],PlotOrientation.VERTICAL,true,true,false);
			lineChartObject.setBackgroundPaint(Color.yellow);
			chartPanel = new ChartPanel(lineChartObject);
			setContentPane(chartPanel);
		}

		priceTable = new PriceTable();
		table = new JTable(priceTable);

		JScrollPane scrollPane = new JScrollPane(table);

		super.setSize(600, 500);
		super.setVisible(true);
		super.setLayout(layout);
		super.setVisible(true);
	}

	public void addTableValue(String price, int tick){
		this.priceTable.addRow(new Object[]{price,tick});
	}

	public void add(Line line, float price, int tick ){
		lines[line.getIndex()].addValue(price,line.getName(),String.valueOf(tick));
	}
}