package client;

import server.Hardware;
import server.Hws;
import server.License;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserFrame extends JFrame implements ActionListener, WindowListener {

    private JPanel panel;
    private Integer id;
    private String login;
    private String pass;
    private SignIn objSi;
    private JLabel inf;
    private JLabel userLa;
    private JTextField userText;
    private JScrollPane scrollLicense;
    private JScrollPane scrollHws;
    private JScrollPane scrollCom;
    private JTable licenseT;
    private JTable hwT;
    private JTable companyT;
    private HWSTableModel hwsm = new HWSTableModel();
    private LicenseTableModel ltm = new LicenseTableModel();
    private CompanyTableModel ctm = new CompanyTableModel();
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private JButton addLicense;
    private JButton editLicense;
    private JButton deleteLicense;
    private JButton addHws;
    private JButton editHws;
    private JButton deleteHws;
    private JButton editUser;
    private JButton addCompany;
    private JButton editCompany;
    private JButton deleteCompany;
    private JButton close;
    private JButton refresh;
    private JButton order;
    private ArrayList<Integer> idHws;
    private ArrayList<Integer> companyIdHw;
    private ArrayList<Integer> hwId;
    private ArrayList<Integer> amountHw;
    private ArrayList<String> dateStartHw;
    private ArrayList<String> dateEndHw;
    private ArrayList<Integer> idLicense;
    private ArrayList<Integer> companyIdSw;
    private ArrayList<Integer> swId;
    private ArrayList<String> dateEndSw;
    private ArrayList<Integer> companyId;
    private ArrayList<Integer> idUser;
    private ArrayList<String> name;
    private ArrayList<Integer> amount;
    private server.Hws hws;
    private server.License license;
    private server.Company com;
    private Integer amountHws;
    private Integer amountLicense;
    private Integer amountCom;
    private String idLicenseSelected;
    private String companyIdSwSelected;
    private String swIdSelected;
    private String dateEndSwSelected;
    private String idHwsSel;
    private String comIdHwsSel;
    private String hwIdSel;
    private String dateStartHwSel;
    private String dateEndHwSel;
    private String amountHwSel;
    private String comIdSel;
    private String nameComSel;
    private String amountComSel;
    private String idUserComSel;
    private server.Hardware hw;
    private server.Hardware hwTT;
    private server.Software sw;
    private server.Software swTT;
    private ArrayList<Integer> hwTtId = new ArrayList<Integer>();
    private ArrayList<String> hwTtName = new ArrayList<String>();
    private ArrayList<String> swTtName = new ArrayList<String>();
    private ArrayList<Integer> swTtId = new ArrayList<Integer>();
    private Integer amSw;
    private Integer amHw;
    private ArrayList<Integer> remember = new ArrayList<Integer>();
    private int j = 0;
    private int flag=0;

    public UserFrame(ObjectInputStream ois, ObjectOutputStream oos, SignIn si, Integer id, String log, String pas,
                     Hws hws, License license,server.Company com ,Integer amountHws, Integer amountLicense, Integer amountCom,
                     server.Hardware hw, Integer amHw, server.Software sw, Integer amSw) {
        this.amSw = amSw;
        this.amHw = amHw;
        this.swTT = sw;
        this.hwTT = hw;
        this.swTtId = sw.getId();
        this.swTtName = sw.getName();
        this.hwTtId = hw.getId();
        this.hwTtName = hw.getName();
        this.ois = ois;
        this.oos = oos;
        this.objSi = si;
        this.id = id; //id входящего клиента
        this.login = log; //логин входящего клиента
        this.pass = pas; // пароль входящего клиента
        this.hws = hws;
        this.license = license;
        this.com = com;

        //hwstatus
        this.idHws = hws.getId();
        this.companyIdHw = hws.getCompanyId();
        this.hwId = hws.getHwId();
        this.dateStartHw = hws.getDateStart();
        this.dateEndHw = hws.getDateEnd();
        this.amountHw = hws.getAmount();
        //hwstatus

        //license
        this.idLicense = license.getId();
        this.companyIdSw = license.getCompanyId();
        this.swId = license.getSwId();
        this.dateEndSw = license.getDateEnd();
        //license

        //Количество записей
        this.amountHws = amountHws;
        this.amountLicense = amountLicense;
        this.amountCom = amountCom;
        //Количество записей

        //Company
        this.companyId = com.getId();
        this.name = com.getName();
        this.amount = com.getAmount();
        this.idUser = com.getIdUser();
        //Company



        for (int i = 0; i < this.amountCom; i++){
            if (this.id.equals(idUser.get(i))) {
                String[] row = new String[4];
                row[0] = companyId.get(i).toString();
                row[1] = name.get(i);
                row[2] = amount.get(i).toString();
                row[3] = idUser.get(i).toString();
                ctm.addData(row);
                remember.add(i);
                j++;
            }
        }

        for (int i = 0; i < this.amountHws; i++){
            for (int j1 = 0; j1 < j; j1++) {
                if (companyId.get(remember.get(j1)).equals(companyIdHw.get(i))) {
                    String[] row = new String[8];
                    row[0] = idHws.get(i).toString();
                    row[1] = companyIdHw.get(i).toString();
                    for (int j = 0; j < companyId.size(); j++){
                        if (companyIdHw.get(i).toString().equals(companyId.get(j).toString())){
                            row[2] = name.get(j);
                        }
                    }
                    row[3] = hwId.get(i).toString();
                    for (int j = 0; j < hwTtId.size(); j++){
                        if (hwId.get(i).toString().equals(hwTtId.get(j).toString())){
                            row[4] = hwTtName.get(j);
                        }
                    }
                    row[5] = dateStartHw.get(i);
                    row[6] = dateEndHw.get(i);
                    row[7] = amountHw.get(i).toString();
                    hwsm.addData(row);
                }
            }
        }
        for (int i = 0; i < this.amountLicense; i++){
            for (int j1 = 0; j1 < j; j1++) {
                if (companyId.get(remember.get(j1)).equals(companyIdSw.get(i))) {
                    String[] row = new String[6];
                    row[0] = idLicense.get(i).toString();
                    row[1] = companyIdSw.get(i).toString();
                    for (int j = 0; j < companyId.size(); j++){
                        if (companyIdSw.get(i).toString().equals(companyId.get(j).toString())){
                            row[2] = name.get(j);
                        }
                    }
                    row[3] = swId.get(i).toString();
                    for (int j = 0; j < swTtId.size(); j++){
                        if (swId.get(i).toString().equals(swTtId.get(j).toString())){
                            row[4] = swTtName.get(j);
                        }
                    }
                    row[5] = dateEndSw.get(i);
                    ltm.addData(row);
                }
            }
        }

        setSize(1400,900);
        setTitle("Пользователь");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(100, 0);

        panel = new JPanel();
        panel.setLayout(null);

        companyT = new JTable(ctm);
        companyT.setSize(400,300);
        companyT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (companyT.getSelectedRowCount() > 0){
                    editCompany.setEnabled(true);
                    deleteCompany.setEnabled(true);
                    order.setEnabled(true);
                    addLicense.setEnabled(true);
                    addHws.setEnabled(true);
                    int row = companyT.getSelectedRow();
                    setComIdSel((String) companyT.getValueAt(row, 0));
                    setNameComSel((String) companyT.getValueAt(row, 1));
                    setAmountComSel((String) companyT.getValueAt(row, 2));
                    setIdUserComSel((String) companyT.getValueAt(row, 3));
                }
                else {
                    editCompany.setEnabled(false);
                    deleteCompany.setEnabled(false);
                    addLicense.setEnabled(false);
                    addHws.setEnabled(false);
                    order.setEnabled(false);
                }
            }
        });
        scrollCom = new JScrollPane(companyT);
        scrollCom.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCom.setSize(525,300);
        scrollCom.setLocation(25, 400);
        panel.add(scrollCom);

        hwT = new JTable(hwsm);
        hwT.setSize(600,300);
        hwT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (hwT.getSelectedRowCount() > 0){
                    editHws.setEnabled(true);
                    deleteHws.setEnabled(true);
                    int row = hwT.getSelectedRow();
                    setIdHwsSel((String) hwT.getValueAt(row, 0));
                    setComIdHwsSel((String) hwT.getValueAt(row, 1));
                    setHwIdSel((String) hwT.getValueAt(row, 3));
                    setDateStartHwSel((String) hwT.getValueAt(row, 5));
                    setDateEndHwSel((String)hwT.getValueAt(row,6));
                    setAmountHwSel((String)hwT.getValueAt(row,7));
                }
                else {
                    editHws.setEnabled(false);
                    deleteHws.setEnabled(false);
                }
            }
        });
        scrollHws = new JScrollPane(hwT);
        scrollHws.setSize(750,300);
        scrollHws.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollHws.setLocation(600, 400);
        panel.add(scrollHws);

        licenseT = new JTable(ltm);
        licenseT.setSize(525,300);
        licenseT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (licenseT.getSelectedRowCount() > 0){
                    editLicense.setEnabled(true);
                    deleteLicense.setEnabled(true);
                    int row = licenseT.getSelectedRow();
                    setIdLicenseSelected((String) licenseT.getValueAt(row, 0));
                    setCompanyIdSwSelected((String) licenseT.getValueAt(row, 1));
                    setSwIdSelected((String) licenseT.getValueAt(row, 3));
                    setDateEndSwSelected((String) licenseT.getValueAt(row, 4));
                }
                else {
                    editLicense.setEnabled(false);
                    deleteLicense.setEnabled(false);
                }
            }
        });
        scrollLicense = new JScrollPane(licenseT);
        scrollLicense.setSize(525,300);
        scrollLicense.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollLicense.setLocation(25,50);
        panel.add(scrollLicense);

        addLicense = new JButton("Внести лицензию");
        addLicense.setSize(190,40);
        addLicense.setLocation(610,50);
        addLicense.addActionListener(this::actionBLPerformed);
        addLicense.setVisible(true);
        addLicense.setEnabled(false);
        panel.add(addLicense);

        editLicense = new JButton("Продлить Лицензию");
        editLicense.setSize(190,40);
        editLicense.setLocation(805,50);
        editLicense.addActionListener(this::actionEditLiPerformed);
        editLicense.setVisible(true);
        editLicense.setEnabled(false);
        panel.add(editLicense);

        deleteLicense = new JButton("Отказаться от Лицензии");
        deleteLicense.setSize(190,40);
        deleteLicense.setLocation(1000,50);
        deleteLicense.addActionListener(this::actionDeleteLiPerformed);
        deleteLicense.setVisible(true);
        deleteLicense.setEnabled(false);
        panel.add(deleteLicense);

        order = new JButton("Заказы");
        order.setSize(190,40);
        order.setLocation(805,150);
        order.addActionListener(this::actionOrderLiPerformed);
        order.setVisible(true);
        order.setEnabled(false);
        panel.add(order);

        addHws = new JButton("Внести данные об АО");
        addHws.setSize(190,40);
        addHws.setLocation(610,350);
        addHws.addActionListener(this::actionAOPerformed);
        addHws.setVisible(true);
        addHws.setEnabled(false);
        panel.add(addHws);

        editHws = new JButton("Изменить данные об АО");
        editHws.setSize(190,40);
        editHws.setLocation(805,350);
        editHws.addActionListener(this::actionEditAOPerformed);
        editHws.setVisible(true);
        editHws.setEnabled(false);
        panel.add(editHws);

        deleteHws = new JButton("Удалить Данные об АО");
        deleteHws.setSize(190,40);
        deleteHws.setLocation(1000,350);
        deleteHws.addActionListener(this::actionDeleteAOPerformed);
        deleteHws.setVisible(true);
        deleteHws.setEnabled(false);
        panel.add(deleteHws);

        addCompany = new JButton("Добавить компанию");
        addCompany.setSize(190,20);
        addCompany.setLocation(25,715);
        addCompany.setVisible(true);
        addCompany.addActionListener(this::actionComPerformed);
        panel.add(addCompany);

        editCompany = new JButton("Изменить компанию");
        editCompany.setSize(190,20);
        editCompany.setLocation(220,715);
        editCompany.setVisible(true);
        editCompany.setEnabled(false);
        editCompany.addActionListener(this::actionComEditPerformed);
        panel.add(editCompany);

        deleteCompany = new JButton("Удалить компанию");
        deleteCompany.setSize(190,20);
        deleteCompany.setLocation(415,715);
        deleteCompany.setVisible(true);
        deleteCompany.setEnabled(false);
        deleteCompany.addActionListener(this::actionComDeletePerformed);
        panel.add(deleteCompany);

        close = new JButton("Выход");
        close.setSize(100,40);
        close.setLocation(1050, 715);
        close.addActionListener(this::actionClosePerformed);
        close.setVisible(true);
        panel.add(close);

        refresh = new JButton("Обновить таблицы");
        refresh.setSize(150,40);
        refresh.setLocation(895, 715);
        refresh.addActionListener(this::actionRefPerformed);
        refresh.setVisible(true);
        refresh.setEnabled(false);
        panel.add(refresh);

        userLa = new JLabel(this.id.toString());
        userLa.setSize(20,20);
        userLa.setLocation(120,375);
        userLa.setVisible(true);
        panel.add(userLa);
        inf = new JLabel("Вы вошли как");
        inf.setSize(90,20);
        inf.setLocation(25,375);
        inf.setVisible(true);
        panel.add(inf);
        userText = new JTextField(this.login);
        userText.setSize(100,20);
        userText.setLocation(150,375);
        userText.setEnabled(false);
        userText.setVisible(true);
        panel.add(userText);

        editUser = new JButton("Изменить пользователя");
        editUser.setSize(200,20);
        editUser.setLocation(255,375);
        editUser.setVisible(true);
        editUser.addActionListener(this::actionEditPerformed);
        panel.add(editUser);

        setContentPane(panel);
        objSi.setVisible(false);
    }

    private void actionOrderLiPerformed(ActionEvent actionEvent) {
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
            //
            num = 28;
            oos.writeUTF(num.toString());
            oos.flush();
            Integer amCom = 0;
            amCom = (Integer) ois.readObject();
            server.Company comp = (server.Company) ois.readObject();
            //
                    OrdersFrame of = new OrdersFrame(ois,oos,this,hw,or,number1,comIdSel, nameComSel,comp,amCom);
                    of.setVisible(true);
            this.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*Обновить таблицы*/private void actionRefPerformed(ActionEvent actionEvent) {
        if (flag==0){
            Integer numReg = 14;
            try {
                oos.writeUTF(numReg.toString());
                oos.flush();
                //license
                Integer num1 = 0;
                num1 = (Integer) ois.readObject();
                server.License license = (server.License) ois.readObject();
                this.idLicense = license.getId();
                this.companyIdSw = license.getCompanyId();
                this.swId = license.getSwId();
                this.dateEndSw = license.getDateEnd();
                ltm.deleteData();
                for (int i = 0; i < num1; i++){
                    for (int j1 = 0; j1 < j; j1++) {
                        if (companyId.get(remember.get(j1)).equals(companyIdSw.get(i))) {
                            String[] row = new String[6];
                            row[0] = idLicense.get(i).toString();
                            row[1] = companyIdSw.get(i).toString();
                            for (int j = 0; j < companyId.size(); j++){
                                if (companyIdSw.get(i).toString().equals(companyId.get(j).toString())){
                                    row[2] = name.get(j);
                                }
                            }
                            row[3] = swId.get(i).toString();
                            row[4] = "";
                            row[5] = dateEndSw.get(i);
                            ltm.addData(row);
                        }
                    }
                }
                licenseT.repaint();
                //license

                //hws
                hwsm.deleteData();
                Integer numberH = 0;
                numberH = (Integer) ois.readObject();
                server.Hws hws = (server.Hws) ois.readObject();
                this.idHws = hws.getId();
                this.companyIdHw = hws.getCompanyId();
                this.hwId = hws.getHwId();
                this.dateStartHw = hws.getDateStart();
                this.dateEndHw = hws.getDateEnd();
                this.amountHw = hws.getAmount();
                for (int i = 0; i < numberH; i++){
                    for (int j1 = 0; j1 < j; j1++) {
                        if (companyId.get(remember.get(j1)).equals(companyIdHw.get(i))) {
                            String[] row = new String[8];
                            row[0] = idHws.get(i).toString();
                            row[1] = companyIdHw.get(i).toString();
                            for (int j = 0; j < companyId.size(); j++){
                                if (companyIdHw.get(i).toString().equals(companyId.get(j).toString())){
                                    row[2] = name.get(j);
                                }
                            }
                            row[3] = hwId.get(i).toString();
                            row[4] = "";
                            row[5] = dateStartHw.get(i);
                            row[6] = dateEndHw.get(i);
                            row[7] = amountHw.get(i).toString();
                            hwsm.addData(row);
                        }
                    }
                }
                hwT.repaint();
                //hws

                //com
                ctm.deleteData();
                Integer numberC = 0;
                numberC = (Integer) ois.readObject();
                server.Company com = (server.Company) ois.readObject();
                this.companyId = com.getId();
                this.name = com.getName();
                this.amount = com.getAmount();
                this.idUser = com.getIdUser();
                for (int i = 0; i < numberC; i++){
                    if (this.id.equals(idUser.get(i))) {
                        String[] row = new String[4];
                        row[0] = companyId.get(i).toString();
                        row[1] = name.get(i);
                        row[2] = amount.get(i).toString();
                        row[3] = idUser.get(i).toString();
                        ctm.addData(row);
                    }
                }
                companyT.repaint();
                refresh.setEnabled(false);
                //com
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            flag++;
        }
    }

    /*Удалить компанию*/private void actionComDeletePerformed(ActionEvent actionEvent) {
        AddCompany ac = new AddCompany(null, true,ois,oos,23,Integer.parseInt(this.comIdSel),3);
        ac.setVisible(true);
        refresh.setEnabled(true);
        flag=0;
    }

    /*Изменить компанию*/private void actionComEditPerformed(ActionEvent actionEvent) {
        //comIdSel
        AddCompany ac = new AddCompany(null, true,ois,oos,22,Integer.parseInt(this.comIdSel),1);
        ac.setVisible(true);
        refresh.setEnabled(true);
        flag=0;
    }

    /*Добавить компанию*/private void actionComPerformed(ActionEvent actionEvent) {
        AddCompany ac = new AddCompany(null, true,ois,oos,15,this.id,0);
        ac.setVisible(true);
        refresh.setEnabled(true);
        flag=0;
    }

    /*Изменение пользователя*/private void actionEditPerformed(ActionEvent actionEvent) {
        DeleteDialog dg = new DeleteDialog(null, true,panel,ois,oos,id.toString(),login,pass,1);
        dg.setVisible(true);
    }

    /*Удалить АО*/private void actionDeleteAOPerformed(ActionEvent actionEvent) {
        Integer num = 6;
        try {
            oos.writeUTF(num.toString());
            oos.flush();
            Integer num1 = 0;
            num1 = (Integer) ois.readObject();
            server.Software sw = (server.Software) ois.readObject();
            num = 10;
            oos.writeUTF(num.toString());
            oos.flush();
            Integer hwAm=0;
            hwAm = (Integer) ois.readObject();
            server.Hardware hw = (server.Hardware) ois.readObject();
            AddDialog1 ad = new AddDialog1(null,true,ois,oos,21,hw,hwAm,idHwsSel,comIdHwsSel,
                    hwIdSel, dateStartHwSel, dateEndHwSel, amountHwSel,3);
            ad.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        refresh.setEnabled(true);
        flag=0;
    }

    /*Изменить АО*/private void actionEditAOPerformed(ActionEvent actionEvent) {
        Integer num = 6;
        try {
            oos.writeUTF(num.toString());
            oos.flush();
            Integer num1 = 0;
            num1 = (Integer) ois.readObject();
            server.Software sw = (server.Software) ois.readObject();
            num = 10;
            oos.writeUTF(num.toString());
            oos.flush();
            Integer hwAm=0;
            hwAm = (Integer) ois.readObject();
            server.Hardware hw = (server.Hardware) ois.readObject();
            AddDialog1 ad = new AddDialog1(null,true,ois,oos,20,hw,hwAm,idHwsSel,comIdHwsSel,
                    hwIdSel, dateStartHwSel, dateEndHwSel, amountHwSel,1);
            ad.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        refresh.setEnabled(true);
        flag=0;
    }

    /*Внести АО*/private void actionAOPerformed(ActionEvent actionEvent) {
        if (amountCom!=0){
            Integer num = 6;
            try {
                oos.writeUTF(num.toString());
                oos.flush();
                Integer num1 = 0;
                num1 = (Integer) ois.readObject();
                server.Software sw = (server.Software) ois.readObject();
                num = 10;
                oos.writeUTF(num.toString());
                oos.flush();
                Integer hwAm=0;
                hwAm = (Integer) ois.readObject();
                server.Hardware hw = (server.Hardware) ois.readObject();
                AddDialog1 ad = new AddDialog1(null,true,ois,oos,17,hw,hwAm,comIdSel,nameComSel,
                        amountComSel, idUserComSel, comIdSel, nameComSel,0);
                ad.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            refresh.setEnabled(true);
            flag=0;
        }
    }

    /*Отказаться от лицензии*/private void actionDeleteLiPerformed(ActionEvent actionEvent) {
        Integer num = 6;
        try {
            oos.writeUTF(num.toString());
            oos.flush();
            Integer num1 = 0;
            num1 = (Integer) ois.readObject();
            server.Software sw = (server.Software) ois.readObject();
            num = 10;
            oos.writeUTF(num.toString());
            oos.flush();
            Integer hwAm=0;
            hwAm = (Integer) ois.readObject();
            server.Hardware hw = (server.Hardware) ois.readObject();
            AddDialog ad = new AddDialog(null, true,ois,oos,19,sw,num1,idLicenseSelected,companyIdSwSelected,1);
            ad.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        refresh.setEnabled(true);
        flag=0;
    }

    /*Закрыть*/private void actionClosePerformed(ActionEvent actionEvent) {
        this.dispose();
        objSi.setVisible(true);
    }

    /*Продлить лицензию*/private void actionEditLiPerformed(ActionEvent actionEvent) {
        Integer num = 6;
        try {
            oos.writeUTF(num.toString());
            oos.flush();
            Integer num1 = 0;
            num1 = (Integer) ois.readObject();
            server.Software sw = (server.Software) ois.readObject();
            this.sw = sw;
            num = 10;
            oos.writeUTF(num.toString());
            oos.flush();
            Integer hwAm=0;
            hwAm = (Integer) ois.readObject();
            server.Hardware hw = (server.Hardware) ois.readObject();
            this.hw = hw;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        ProlongLicense pl = new ProlongLicense(null, true,ois,oos,18,idLicenseSelected, this.sw, this.hw, swIdSelected);
        pl.setVisible(true);
        refresh.setEnabled(true);
        flag=0;
    }

    /*Внести лицензию*/private void actionBLPerformed(ActionEvent actionEvent) {
        if (amountCom!=0){
            Integer num = 6;
            try {
                oos.writeUTF(num.toString());
                oos.flush();
                Integer num1 = 0;
                num1 = (Integer) ois.readObject();
                server.Software sw = (server.Software) ois.readObject();
                this.sw = sw;
                num = 10;
                oos.writeUTF(num.toString());
                oos.flush();
                Integer hwAm=0;
                hwAm = (Integer) ois.readObject();
                server.Hardware hw = (server.Hardware) ois.readObject();
                this.hw = hw;
                AddDialog ad = new AddDialog(null,true,ois,oos,16,sw,num1,comIdSel,nameComSel,0);
                ad.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            refresh.setEnabled(true);
            flag=0;
        }
    }

    public void setIdLicenseSelected(String idLicenseSelected) {
        this.idLicenseSelected = idLicenseSelected;
    }

    public void setCompanyIdSwSelected(String companyIdSwSelected) {
        this.companyIdSwSelected = companyIdSwSelected;
    }

    public void setSwIdSelected(String swIdSelected) {
        this.swIdSelected = swIdSelected;
    }

    public void setDateEndSwSelected(String dateEndSwSelected) {
        this.dateEndSwSelected = dateEndSwSelected;
    }

    public void setIdHwsSel(String idHwsSel) {
        this.idHwsSel = idHwsSel;
    }

    public void setComIdHwsSel(String comIdHwsSel) {
        this.comIdHwsSel = comIdHwsSel;
    }

    public void setHwIdSel(String hwIdSel) {
        this.hwIdSel = hwIdSel;
    }

    public void setDateStartHwSel(String dateStartHwSel) {
        this.dateStartHwSel = dateStartHwSel;
    }

    public void setDateEndHwSel(String dateEndHwSel) {
        this.dateEndHwSel = dateEndHwSel;
    }

    public void setAmountHwSel(String amountHwSel) {
        this.amountHwSel = amountHwSel;
    }

    public void setComIdSel(String comIdSel) {
        this.comIdSel = comIdSel;
    }

    public void setNameComSel(String nameComSel) {
        this.nameComSel = nameComSel;
    }

    public void setAmountComSel(String amountComSel) {
        this.amountComSel = amountComSel;
    }

    public void setIdUserComSel(String idUserComSel) {
        this.idUserComSel = idUserComSel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
