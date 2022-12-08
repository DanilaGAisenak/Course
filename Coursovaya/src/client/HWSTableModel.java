package client;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HWSTableModel extends AbstractTableModel {
    private int columnCount = 6;
    private ArrayList<String[]> data;

    public HWSTableModel() {
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
            case 0: return "id";
            case 1: return "id_��������";
            case 2: return "id_��";
            case 3: return "����_�������_��";
            case 4: return "����_���������_�����_������������";
            case 5: return "����������";
        }
        return "";
    }
}
