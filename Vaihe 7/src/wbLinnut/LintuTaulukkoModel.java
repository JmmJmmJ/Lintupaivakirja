package wbLinnut;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Linnut taulukon model
 * @author Jyrki M‰ki
 * @version 26.2.2021
 */
public class LintuTaulukkoModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "Laji", "Tieteellinen nimi", "Havaintoja" };
    private List<Object[]> data = new ArrayList<Object[]>();

    
    /**
     * Lis‰‰ rivin taulukkoon
     * @param o lis‰tt‰v‰ rivi
     */
    public void addRow(Object[] o) {
        data.add(o);
        // fireTableRowsInserted(0,getRowCount()-1); tulee virheit‰
        fireTableDataChanged();
    }
    

    /**
     * Tyhejnt‰‰ taulukon
     */
    public void clear() {
        data.clear();
    }
    

    @SuppressWarnings("rawtypes")
    Class[] types = { String.class, String.class, Integer.class };

    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.types[columnIndex];
    }
    

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    

    @Override
    public int getRowCount() {
        return data.size();
    }
    

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    
    @Override
    public Object getValueAt(int row, int col) {
        return data.get(row)[col];
    }

    
    @Override
    public void setValueAt(Object value, int row, int col) {
        data.get(row)[col] = value;
        fireTableCellUpdated(row, col);
    }
}
