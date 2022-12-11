package client;

import server.Hardware;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class OrdersFrameA extends JFrame implements ActionListener {

    private JPanel panel;
    private OrdersTableModel otm = new OrdersTableModel();
    private JScrollPane scroll;
    private JPanel ePanel;
    private JTable orTable;
    private JButton edit;
    private JButton delete;
    private JButton register;
    private JButton close;
    private JButton refresh;
    private JComboBox hwCb;
    private JComboBox swCb;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private ArrayList<Integer> orderId;
    private ArrayList<Integer> companyId;
    private ArrayList<Integer> am;
    private ArrayList<Integer> hwId;
    private ArrayList<Integer> bool; //0-false, 1-true
    private ArrayList<Integer> hardwareId;
    private ArrayList<Integer> hardwarePrice;
    private ArrayList<String> hardwareName;
    private ArrayList<String> hardwareManufacturer;
    private String idSel;
    private String idComSel;
    private String amount;
    private String idHwSel;
    private String permit;
    private AdminFrame uf;
    private server.Hardware hardware;
    private server.Software software;
    private server.Orders orders;
    private int num; //кол-во строк в таблице
    private String comId;
    private String comName;
    private int flag=0;

    public OrdersFrameA(ObjectInputStream ois, ObjectOutputStream oos, AdminFrame adf,
                       Hardware hardware, server.Orders orders, int num, String idColSel, String comName) {
        this.ois = ois;
        this.oos = oos;
        this.uf = adf;
        this.comName = comName;
        this.hardware = hardware;
        this.orders = orders;
        this.num = num;
        this.comId = idColSel;
        this.orderId = orders.getOrderId();
        this.companyId = orders.getCompanyId();
        this.am = orders.getAm();
        this.hwId = orders.getHwId();
        this.bool = orders.getBool();
        this.hardwareId = hardware.getId();
        this.hardwareName = hardware.getName();
        this.hardwarePrice = hardware.getPrice();
        this.hardwareManufacturer = hardware.getManufacturer();

        setTitle("Заказы");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(200, 0);
        setSize(1200, 800);

        panel = new JPanel();
        panel.setLayout(null);

        for (int i = 0; i < this.num; i++){
            String[] row = new String[6];
            row[0] = orderId.get(i).toString();
            row[1] = companyId.get(i).toString();
            row[2] = hardwareId.get(i).toString();
            row[3] = am.get(i).toString();
            row[4] = bool.get(i).toString();
            otm.addData(row);
        }

        orTable = new JTable(otm);
        orTable.setSize(700,600);
        orTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (orTable.getSelectedRowCount() > 0){
                    edit.setEnabled(true);
                    delete.setEnabled(true);
                    int row = orTable.getSelectedRow();
                    setIdSel((String) orTable.getValueAt(row, 0));
                    setIdComSel((String) orTable.getValueAt(row, 1));
                    setIdHwSel((String) orTable.getValueAt(row, 2));
                    setAmount((String) orTable.getValueAt(row,3));
                    setPermit((String) orTable.getValueAt(row, 4));
                }
                else {
                    edit.setEnabled(false);
                    delete.setEnabled(false);
                }
            }
        });
        scroll = new JScrollPane(orTable);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setLocation(450,80);
        scroll.setSize(700,600);
        panel.add(scroll);

        delete = new JButton("Удалить Заказ");
        delete.setSize(190,40);
        delete.setLocation(745,25);
        delete.addActionListener(this::actionDeletePerformed);
        delete.setVisible(true);
        delete.setEnabled(false);
        panel.add(delete);

        edit = new JButton("Изменить Заказ");
        edit.setSize(190,40);
        edit.setLocation(940,25);
        edit.addActionListener(this::actionEditPerformed);
        edit.setVisible(true);
        edit.setEnabled(false);
        panel.add(edit);

        close = new JButton("Выход");
        close.setSize(100,40);
        close.setLocation(1050, 700);
        close.addActionListener(this::actionClosePerformed);
        close.setVisible(true);
        panel.add(close);

        refresh = new JButton("Обновить таблицу");
        refresh.setSize(150,40);
        refresh.setLocation(895, 700);
        refresh.addActionListener(this::actionRefPerformed);
        refresh.setVisible(true);
        refresh.setEnabled(false);
        panel.add(refresh);

        setContentPane(panel);
        panel.setVisible(true);
    }

    private void actionDeletePerformed(ActionEvent actionEvent) {
        Integer number = 28;
        try {
            oos.writeUTF(number.toString());
            oos.flush();
            Integer number1 = 0;
            number1 = (Integer) ois.readObject();
            server.Company com = (server.Company) ois.readObject();
            AddOrder1 ao = new AddOrder1(null,true,hardware,com,ois,oos,comId,comName,this,1,26, idSel, amount);
            ao.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        flag=0;
        refresh.setEnabled(true);
    }

    private void actionEditPerformed(ActionEvent actionEvent) {
        Integer number = 28;
        try {
            oos.writeUTF(number.toString());
            oos.flush();
            Integer number1 = 0;
            number1 = (Integer) ois.readObject();
            server.Company com = (server.Company) ois.readObject();
            AddOrder1 ao = new AddOrder1(null,true,hardware,com,ois,oos,comId,comName,this,4,29, idSel, amount);
            ao.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        flag=0;
        refresh.setEnabled(true);
    }

    private void actionRefPerformed(ActionEvent actionEvent) {
        if (flag==0){
            Integer num = 24;
            try {
                otm.deleteData();
                oos.writeUTF(num.toString());
                oos.flush();
                Integer number1 = 0;
                number1 = (Integer) ois.readObject();
                server.Orders or = (server.Orders) ois.readObject();
                this.orderId = or.getOrderId();
                this.companyId = or.getCompanyId();
                this.hwId = or.getHwId();
                this.am = or.getAm();
                this.bool = or.getBool();
                for (int i = 0; i < number1; i++){
                    String[] row = new String[6];
                    row[0] = orderId.get(i).toString();
                    row[1] = companyId.get(i).toString();
                    row[2] = hwId.get(i).toString();
                    row[3] = am.get(i).toString();
                    row[4] = bool.get(i).toString();
                    otm.addData(row);
                }
                orTable.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            flag++;
        }
    }

    private void actionClosePerformed(ActionEvent actionEvent) {
        this.dispose();
        uf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setIdSel(String idSel) {
        this.idSel = idSel;
    }

    public void setIdComSel(String idComSel) {
        this.idComSel = idComSel;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public void setIdHwSel(String idHwSel) {
        this.idHwSel = idHwSel;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }
}
