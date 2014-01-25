package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class PriceTable extends AbstractTableModel{

	private List<Object[]> rows;
	private Object columnNames[] = { "Trade Price", "Server Price"};

	public PriceTable() {
		rows = new ArrayList<>();
	}
	
	public String getColumnName(int column) {
		return columnNames[column].toString();
	}

	public int getRowCount() {
		return rows.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int row, int col) {
		return rows.get(row)[col];
	}

	public void addRow(Object[] row){
		rows.add(row);
		fireTableDataChanged();
	}
}