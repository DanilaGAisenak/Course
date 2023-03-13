package client;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class OrdersTableModel extends AbstractTableModel {
    private int columnCount = 7;
    private ArrayList<String[]> data;

    public OrdersTableModel() {
        data = new ArrayList<String[]>();
        for(int i = 0; i < data.size(); i++){
            data.add(new String[getColumnCount()]);
        }
    }

    public void addData(String[] row ){
        String[] rowTable = new String[getColumnCount()];
        rowTable = row;
        data.add(rowTable);
        this.fireTableDataChanged();
    }

    public void deleteData(){
        data.clear();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = data.get(rowIndex);
        return row[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0: return "Номер_Заказа";
            case 1: return "Номер_Компании";
            case 2: return "Компания";
            case 3: return "Номер_АО";
            case 4: return "АО";
            case 5: return "Количество";
            case 6: return "Одобрено";
        }
        return "";
    }
}
