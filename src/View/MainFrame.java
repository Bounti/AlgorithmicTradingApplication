package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.JFreeChart; 
import java.awt.Color;
import org.jfree.chart.ChartPanel;

public class MainFrame extends JFrame{

	private FlowLayout layout;
	private JTable table;
	private PriceTable priceTable;

	private DefaultCategoryDataset[] lines = new DefaultCategoryDataset[9];
	private JFreeChart[] charts = new JFreeChart[9];
	public MainFrame(){
		super();

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setTitle("Algorithmic Trading (EOE)");
		
		ChartPanel chartPanel;
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		
		for(int i = 0; i<lines.length;i++)
		{
			JPanel onglet = new JPanel();
			lines[i] = new DefaultCategoryDataset();
			charts[i] = ChartFactory.createLineChart("Algorithmic Trading (EOE)","Time (second)","Price ($)",lines[i],PlotOrientation.VERTICAL,true,true,false);
			charts[i].setBackgroundPaint(Color.gray);
			chartPanel = new ChartPanel(charts[i]);
			onglet.add(chartPanel);
			onglets.addTab(Line.values()[i].getName(), onglet);
		}

		priceTable = new PriceTable();
		table = new JTable(priceTable);

		JScrollPane scrollPane = new JScrollPane(table);

		this.getContentPane().add(onglets,BorderLayout.WEST);
		this.getContentPane().add(scrollPane,BorderLayout.EAST);

		super.setSize(800, 600);
		super.pack();
		super.setVisible(true);
	}

	public void addTableValue(float tradePrice, float serverPrice){
		this.priceTable.addRow(new Object[]{tradePrice,serverPrice});
	}

	public void add(Line line, float price, int tick ){
		lines[line.getIndex()].addValue(price,line.getName(),String.valueOf(tick));
		charts[line.getIndex()].fireChartChanged();
	}
}