package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.general.Series;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYDataset;

public class Chart extends JPanel {

	private XYPlot plot;
	private XYDataset[] datas = new XYDataset[9];
	private Series[] series = new Series[9];
	private RegularTimePeriod t = new Day();
	private final JFreeChart chart;
	
	public Chart(){
		super(new BorderLayout());
		
		chart = ChartFactory.createTimeSeriesChart("---", "Time (s)", "Price ($)", null, true, true, false);
		chart.setBackgroundPaint(Color.white);

		this.plot = chart.getXYPlot();
		this.plot.setBackgroundPaint(Color.lightGray);
		this.plot.setDomainGridlinePaint(Color.white);
		this.plot.setRangeGridlinePaint(Color.white);
		final ValueAxis axis = this.plot.getDomainAxis();
		axis.setAutoRange(true);

		final NumberAxis rangeAxis2 = new NumberAxis("Range Axis 2");
		rangeAxis2.setAutoRangeIncludesZero(false);

		final ChartPanel chartPanel = new ChartPanel(chart);
		super.add(chartPanel);

		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	}

	public void addLine(Line line){
		this.plot.setDataset(
				line.getIndex(),datas[line.getIndex()]
				);
		this.plot.setRenderer(line.getIndex(), new StandardXYItemRenderer());
	}
	
	public void add(Line line, float price, int tick ){
		System.out.println("ajout");
		this.plot.setDataset(line.getIndex(),datas[line.getIndex()]);
		if(series[line.getIndex()] == null)
		{
			addLine(line);
			series[line.getIndex()] = new Series();
		}
		series[line.getIndex()].
		chart.fireChartChanged();
	}
}