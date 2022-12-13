package client;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class AdminFrame extends JFrame implements ActionListener, WindowListener {
    private JPanel panel;
    private UsersTableModel utm = new UsersTableModel();
    private JScrollPane scroll;
    private JPanel ePanel;
    private JTable admTable;
    private JButton sort;
    private JButton edit;
    private JButton delete;
    private JButton register;
    private JButton software;
    private JButton hardware;
    private JButton licences;
    private JButton company;
    private JButton close;
    private JButton refresh;
    private JButton order;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private server.User user;
    private ArrayList<Integer> id;
    private ArrayList<String> login;
    private ArrayList<String> pass;
    private Integer number;
    private SignIn objSi;
    private String admId;
    private String admLog = "";
    private String admPas = "";
    private int flag = 0;
    public AdminFrame(JPanel epanel, ObjectOutputStream oos, ObjectInputStream ois,server.User user, Integer num, SignIn si){
        this.ePanel = epanel;
        this.oos = oos;
        this.ois = ois;
        this.id = user.getId();
        this.login = user.getLogin();
        this.pass = user.getPassword();
        this.number = num;
        this.objSi = si;

        setTitle("�������������");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(200, 0);
        setSize(1200, 800);

        panel = new JPanel();
        panel.setLayout(null);

        for (int i = 0; i < number; i++){
            String[] row = new String[3];
            row[0] = id.get(i).toString();
            row[1] = login.get(i);
            row[2] = pass.get(i);
            utm.addData(row);
        }
        admTable = new JTable(utm);
        //
        RowSorter<UsersTableModel>sorter = new TableRowSorter<UsersTableModel>(utm);
        admTable.setRowSorter(sorter);
        //
        admTable.setSize(600, 600);
        admTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (admTable.getSelectedRowCount() > 0){
                    edit.setEnabled(true);
                    delete.setEnabled(true);
                    int row = admTable.getSelectedRow();
                    setId((String) admTable.getValueAt(row, 0));
                    setLog((String) admTable.getValueAt(row, 1));
                    setPas((String) admTable.getValueAt(row, 2));
                }
                else {
                    edit.setEnabled(false);
                    delete.setEnabled(false);
                }
            }
        });
        scroll = new JScrollPane(admTable);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setLocation(550,80);
        scroll.setSize(600,600);
        panel.add(scroll);

        register = new JButton("�������� ������������");
        register.setSize(190,40);
        register.setLocation(550,25);
        register.addActionListener(this::actionPerformed);
        panel.add(register);

        delete = new JButton("������� ������������");
        delete.setSize(190,40);
        delete.setLocation(745,25);
        delete.addActionListener(this::actionDeletePerformed);
        delete.setVisible(true);
        delete.setEnabled(false);
        panel.add(delete);

        edit = new JButton("�������� ������������");
        edit.setSize(190,40);
        edit.setLocation(940,25);
        edit.addActionListener(this::actionEditPerformed);
        edit.setVisible(true);
        edit.setEnabled(false);
        panel.add(edit);

        close = new JButton("�����");
        close.setSize(100,40);
        close.setLocation(1050, 700);
        close.addActionListener(this::actionClosePerformed);
        close.setVisible(true);
        panel.add(close);

        refresh = new JButton("�������� �������");
        refresh.setSize(150,40);
        refresh.setLocation(895, 700);
        refresh.addActionListener(this::actionRefPerformed);
        refresh.setVisible(true);
        refresh.setEnabled(false);
        panel.add(refresh);

        software = new JButton("����������� �����������");
        software.addActionListener(this::actionSoftwarePerformed);
        software.setSize(250,50);
        software.setLocation(25,150);
        software.setVisible(true);
        panel.add(software);

        hardware = new JButton("���������� �����������");
        hardware.addActionListener(this::actionHardwarePerformed);
        hardware.setSize(250,50);
        hardware.setLocation(280,150);
        hardware.setVisible(true);
        panel.add(hardware);

        licences = new JButton("��������");
        licences.addActionListener(this::actionLicensesPerformed);
        licences.setSize(250,50);
        licences.setLocation(25,205);
        licences.setVisible(true);
        panel.add(licences);

        company = new JButton("��������");
        company.addActionListener(this::actionComPerformed);
        company.setSize(250,50);
        company.setLocation(280,205);
        company.setVisible(true);
        panel.add(company);

        order = new JButton("������");
        order.addActionListener(this::actionOrdPerformed);
        order.setSize(250,50);
        order.setLocation(150,260);
        order.setVisible(true);
        panel.add(order);


        setContentPane(panel);
        panel.setVisible(true);
    }

    private void actionOrdPerformed(ActionEvent actionEvent) {
        Integer num = 10;
        try {
            oos.writeUTF(num.toString());
            oos.flush();
            Integer number = 0;
            number = (Integer) ois.readObject();
            server.Hardware hw = (server.Hardware) ois.readObject();
            num = 24;
            oos.writeUTF(num.toString());
            oos.flush();
            Integer number1 = 0;
            number1 = (Integer) ois.readObject();
            server.Orders or = (server.Orders) ois.readObject();
            OrdersFrameA of = new OrdersFrameA(ois,oos,this,hw,or,number1,"", "");
            of.setVisible(true);
            this.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void actionComPerformed(ActionEvent actionEvent) {
        String line = null;
        try {
            Integer num = 14;
            oos.writeUTF(num.toString());
            oos.flush();
            //Integer numberL = 0;
            Integer num1 = 0;
            num1 = (Integer) ois.readObject();
            server.License license = (server.License) ois.readObject();
            Integer numberH = 0;
            numberH = (Integer) ois.readObject();
            server.Hws hws = (server.Hws) ois.readObject();
            Integer numberC = 0;
            numberC = (Integer) ois.readObject();
            server.Company com = (server.Company) ois.readObject();
            CompanyFrame cf = new CompanyFrame(panel,ois,oos,this,num1,com);
            this.setVisible(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void setId(String regId) {
        this.admId = regId;
    }

    public void setLog(String regLog) {
        this.admLog = regLog;
    }

    public void setPas(String regPas) {
        this.admPas = regPas;
    }

    public void actionEditPerformed(ActionEvent e){
        DeleteDialog dg = new DeleteDialog(null, true, panel,  ois, oos, admId, admLog, admPas, 1);
        dg.setVisible(true);
        refresh.setEnabled(true);
        delete.setEnabled(false);
        edit.setEnabled(false);
        flag=2;
    }
    public void actionDeletePerformed(ActionEvent e){
        DeleteDialog dg = new DeleteDialog(null, true, panel,  ois, oos, admId, admLog, admPas, 0);
        dg.setVisible(true);
        refresh.setEnabled(true);
        delete.setEnabled(false);
        edit.setEnabled(false);
        flag=2;
    }
    public void actionSoftwarePerformed(ActionEvent e){
        Integer num = 6;
        try {
            oos.writeUTF(num.toString());
            oos.flush();
            Integer num1 = 0;
            num1 = (Integer) ois.readObject();
            server.Software sw = (server.Software) ois.readObject();
            SoftWareFrame swf = new SoftWareFrame(panel, ois, oos, this, num1, sw);
            swf.setVisible(true);
            setVisible(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void actionHardwarePerformed(ActionEvent e){
        Integer num = 10;
        try {
            oos.writeUTF(num.toString());
            oos.flush();
            Integer num1 = 0;
            num1 = (Integer) ois.readObject();
            server.Hardware hw = (server.Hardware) ois.readObject();
            HardwareFrame hwf = new HardwareFrame(panel, ois, oos, this, num1, hw);
            hwf.setVisible(true);
            setVisible(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void actionLicensesPerformed(ActionEvent e){
        String line = null;
        try {
            Integer num = 14;
            oos.writeUTF(num.toString());
            oos.flush();
            //Integer numberL = 0;
            Integer num1 = 0;
            num1 = (Integer) ois.readObject();
            server.License license = (server.License) ois.readObject();
            Integer numberH = 0;
            numberH = (Integer) ois.readObject();
            server.Hws hws = (server.Hws) ois.readObject();
            Integer numberC = 0;
            numberC = (Integer) ois.readObject();
            server.Company com = (server.Company) ois.readObject();
            LicensesFrame lf = new LicensesFrame(panel,ois,oos,this,num1,license);
            this.setVisible(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void actionClosePerformed(ActionEvent e){
        this.dispose();
        objSi.setVisible(true);
    }
    public void actionRefPerformed(ActionEvent e){
            Integer numReg = 3;
            try {
                utm.deleteData();
                oos.writeUTF(numReg.toString());
                oos.flush();
                Integer num1 = 0;
                num1 = (Integer) ois.readObject();
                    this.user = (server.User) ois.readObject();
                    this.id = this.user.getId();
                    this.login = this.user.getLogin();
                    this.pass = this.user.getPassword();
                    for (int i = 0; i < num1; i++) {
                        String[] row = new String[3];
                        row[0] = id.get(i).toString();
                        row[1] = login.get(i);
                        row[2] = pass.get(i);
                        utm.addData(row);
                    }
                    admTable.repaint();
                    refresh.setEnabled(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Register rd = new Register(null, true, panel, ois, oos);
        rd.setVisible(true);
        refresh.setEnabled(true);
        flag=0;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
