package View;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import javax.swing.table.AbstractTableModel;

public class MainFrame extends JFrame{

	private FlowLayout layout;
	private JTable table;
	private PriceTable priceTable;

	public MainFrame(){
		super();
		priceTable = new PriceTable();
		table = new JTable(priceTable);

		JScrollPane scrollPane = new JScrollPane(table);
		super.getContentPane().add(scrollPane);
		super.setSize(600, 500);
		super.setVisible(true);
		super.setLayout(layout);
		super.setVisible(true);
	}
	
	public void addTableValue(String price, int tick){
		this.priceTable.addRow(new Object[]{price,tick});
	}
}