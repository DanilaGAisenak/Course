package client;

import server.Company;
import server.Hardware;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AddOrder extends JDialog implements ActionListener {

    private JPanel panel;
    private server.Hardware hw;
    private server.Company com;
    private JComboBox cbHw;
    private JButton sendReg;
    private JButton cancelReg;
    private JTextField amount;
    private JTextField comTf;
    private JLabel comLa;
    private JLabel hwLa;
    private JLabel amLa;


    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String comId;
    private OrdersFrame of;
    private ArrayList<String> name;
    private ArrayList<String> nameList;
    private ArrayList<Integer> id;
    private ArrayList<Integer> idList;
    private String comName;
    private String idOr;
    private String am;
    private int choice;
    private Integer num;//код операции

    public AddOrder(Frame owner, boolean modal, Hardware hw, Company com,
                    ObjectInputStream ois, ObjectOutputStream oos,
                    String comId, String comName, OrdersFrame of, int choice,Integer num, String idOr, String am) {
        super(owner, modal);
        if (choice==0) {
            this.am = am;
            this.num = num;
            this.hw = hw;
            this.ois = ois;
            this.oos = oos;
            this.comId = comId;
            this.comName = comName;
            this.idOr = idOr;
            this.of = of;
            this.choice = choice;
            this.name = com.getName();
            this.id = com.getId();
            this.nameList = hw.getName();
            this.idList = hw.getId();
            String[] items = new String[nameList.size()];
            for (int i = 0; i < nameList.size(); i++){
                items[i] = nameList.get(i);
            }

            setTitle("Добавление");
            setResizable(false);
            setSize(500, 400);
            setLocationRelativeTo(null);

            panel = new JPanel();
            panel.setLayout(null);

            comTf = new JTextField();
            comTf.setText(comName);
            comTf.setSize(200, 40);
            comTf.setLocation(150, 50);
            comTf.setVisible(true);
            comTf.setEnabled(false);
            panel.add(comTf);

            cbHw = new JComboBox<>(items);
            cbHw.setSize(200, 40);
            cbHw.setLocation(150, 130);
            cbHw.setVisible(true);
            panel.add(cbHw);

            amount = new JTextField();
            amount.setSize(200, 40);
            amount.setLocation(150, 210);
            amount.setVisible(true);
            panel.add(amount);

            comLa = new JLabel("Компания");
            comLa.setSize(100, 20);
            comLa.setLocation(150, 20);
            comLa.setVisible(true);
            panel.add(comLa);

            hwLa = new JLabel("АО");
            hwLa.setSize(100, 20);
            hwLa.setLocation(150, 100);
            hwLa.setVisible(true);
            panel.add(hwLa);

            amLa = new JLabel("Количество");
            amLa.setSize(100, 20);
            amLa.setLocation(150, 180);
            amLa.setVisible(true);
            panel.add(amLa);

            sendReg = new JButton("Отправить");
            sendReg.setSize(120, 40);
            sendReg.setLocation(190, 260);
            sendReg.addActionListener(this::actionPerformed);
            sendReg.setVisible(true);
            panel.add(sendReg);

            cancelReg = new JButton("Отмена");
            cancelReg.setSize(120, 40);
            cancelReg.setLocation(190, 310);
            cancelReg.setVisible(true);
            cancelReg.addActionListener(this::actionCancelPerformed);
            panel.add(cancelReg);

            setContentPane(panel);
        }
        else if (choice==1) {
            this.am = am;
            this.num = num;
            this.hw = hw;
            this.ois = ois;
            this.oos = oos;
            this.idOr = idOr;
            this.comId = comId;
            this.comName = comName;
            this.of = of;
            this.choice = choice;
            this.name = com.getName();
            this.id = com.getId();

            setTitle("Удаление");
            setResizable(false);
            setSize(500, 400);
            setLocationRelativeTo(null);

            panel = new JPanel();
            panel.setLayout(null);

            comTf = new JTextField();
            comTf.setText(idOr);
            comTf.setSize(200, 40);
            comTf.setLocation(150, 150);
            comTf.setVisible(true);
            comTf.setEnabled(false);
            panel.add(comTf);

            sendReg = new JButton("Отправить");
            sendReg.setSize(120, 40);
            sendReg.setLocation(190, 260);
            sendReg.addActionListener(this::actionPerformed);
            sendReg.setVisible(true);
            panel.add(sendReg);

            cancelReg = new JButton("Отмена");
            cancelReg.setSize(120, 40);
            cancelReg.setLocation(190, 310);
            cancelReg.setVisible(true);
            cancelReg.addActionListener(this::actionCancelPerformed);
            panel.add(cancelReg);

            setContentPane(panel);
        }
        else if (choice == 3) {
            this.am = am;
            this.num = num;
            this.hw = hw;
            this.ois = ois;
            this.oos = oos;
            this.idOr = idOr;
            this.comId = comId;
            this.comName = comName;
            this.of = of;
            this.choice = choice;
            this.name = com.getName();
            this.id = com.getId();
            this.nameList = hw.getName();
            this.idList = hw.getId();
            String[] items = new String[nameList.size()];
            for (int i = 0; i < nameList.size(); i++){
                items[i] = nameList.get(i);
            }

            setTitle("Изменение");
            setResizable(false);
            setSize(500, 400);
            setLocationRelativeTo(null);

            panel = new JPanel();
            panel.setLayout(null);

            comTf = new JTextField();
            comTf.setText(comName);
            comTf.setSize(200, 40);
            comTf.setLocation(150, 50);
            comTf.setVisible(true);
            comTf.setEnabled(false);
            panel.add(comTf);

            cbHw = new JComboBox<>(items);
            cbHw.setSize(200, 40);
            cbHw.setLocation(150, 130);
            cbHw.setVisible(true);
            cbHw.setEnabled(false);
            panel.add(cbHw);

            amount = new JTextField();
            amount.setSize(200, 40);
            amount.setLocation(150, 210);
            amount.setVisible(true);
            panel.add(amount);

            comLa = new JLabel("Компания");
            comLa.setSize(100, 20);
            comLa.setLocation(150, 20);
            comLa.setVisible(true);
            panel.add(comLa);

            hwLa = new JLabel("АО");
            hwLa.setSize(100, 20);
            hwLa.setLocation(150, 100);
            hwLa.setVisible(true);
            panel.add(hwLa);

            amLa = new JLabel("Количество");
            amLa.setSize(100, 20);
            amLa.setLocation(150, 180);
            amLa.setVisible(true);
            panel.add(amLa);

            sendReg = new JButton("Отправить");
            sendReg.setSize(120, 40);
            sendReg.setLocation(190, 260);
            sendReg.addActionListener(this::actionPerformed);
            sendReg.setVisible(true);
            panel.add(sendReg);

            cancelReg = new JButton("Отмена");
            cancelReg.setSize(120, 40);
            cancelReg.setLocation(190, 310);
            cancelReg.setVisible(true);
            cancelReg.addActionListener(this::actionCancelPerformed);
            panel.add(cancelReg);

            setContentPane(panel);
        }
        else if (choice==4) {
            this.am = am;
            this.num = num;
            this.hw = hw;
            this.ois = ois;
            this.oos = oos;
            this.idOr = idOr;
            this.comId = comId;
            this.comName = comName;
            this.of = of;
            this.choice = choice;
            this.name = com.getName();
            this.id = com.getId();
            this.nameList = hw.getName();
            this.idList = hw.getId();
            String[] items = new String[nameList.size()];
            for (int i = 0; i < nameList.size(); i++){
                items[i] = nameList.get(i);
            }

            setTitle("Изменение");
            setResizable(false);
            setSize(500, 400);
            setLocationRelativeTo(null);

            panel = new JPanel();
            panel.setLayout(null);

            comTf = new JTextField();
            comTf.setText(comName);
            comTf.setSize(200, 40);
            comTf.setLocation(150, 50);
            comTf.setVisible(true);
            comTf.setEnabled(false);
            panel.add(comTf);

            cbHw = new JComboBox<>(items);
            cbHw.setSize(200, 40);
            cbHw.setLocation(150, 130);
            cbHw.setVisible(true);
            cbHw.setEnabled(false);
            panel.add(cbHw);

            amount = new JTextField();
            amount.setSize(200, 40);
            amount.setText(this.am);
            amount.setLocation(150, 210);
            amount.setVisible(true);
            panel.add(amount);

            comLa = new JLabel("Компания");
            comLa.setSize(100, 20);
            comLa.setLocation(150, 20);
            comLa.setVisible(true);
            panel.add(comLa);

            hwLa = new JLabel("АО");
            hwLa.setSize(100, 20);
            hwLa.setLocation(150, 100);
            hwLa.setVisible(true);
            panel.add(hwLa);

            amLa = new JLabel("Количество");
            amLa.setSize(100, 20);
            amLa.setLocation(150, 180);
            amLa.setVisible(true);
            panel.add(amLa);

            sendReg = new JButton("Отправить");
            sendReg.setSize(120, 40);
            sendReg.setLocation(190, 260);
            sendReg.addActionListener(this::actionPerformed);
            sendReg.setVisible(true);
            panel.add(sendReg);

            cancelReg = new JButton("Отмена");
            cancelReg.setSize(120, 40);
            cancelReg.setLocation(190, 310);
            cancelReg.setVisible(true);
            cancelReg.addActionListener(this::actionCancelPerformed);
            panel.add(cancelReg);

            setContentPane(panel);
        }
    }

    private void actionCancelPerformed(ActionEvent actionEvent) {
        this.dispose();
        of.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (choice==0){
            Integer idPost = 0;
            String sel = (String) cbHw.getSelectedItem();
            for (int i = 0; i < nameList.size(); i++){
                if (sel.equals(nameList.get(i))){
                    idPost = idList.get(i);
                }
            }
            try {
                oos.writeUTF(num.toString());
                oos.flush();
                String res = comId+" "+idPost+" "+amount.getText();
                oos.writeUTF(res);
                oos.flush();
                String line = ois.readUTF();
                if (line.equals("Command proceeded")) {
                    WarningDialog wd = new WarningDialog(null, true, panel, "Успешно");
                    line = "";
                    this.dispose();
                } else {
                    WarningDialog wd = new WarningDialog(null, true, panel, "Ошибка");
                    line = "";
                    this.dispose();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        else if (choice==1) {
            try{
                oos.writeUTF(num.toString());
                oos.flush();
                oos.writeUTF(idOr+" "+idOr);
                oos.flush();
                String line = ois.readUTF();
                if (line.equals("Command proceeded")) {
                    WarningDialog wd = new WarningDialog(null, true, panel, "Успешно");
                    line = "";
                    this.dispose();
                } else {
                    WarningDialog wd = new WarningDialog(null, true, panel, "Ошибка");
                    line = "";
                    this.dispose();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (choice==3) {
            try{
                oos.writeUTF(num.toString());
                oos.flush();
                oos.writeUTF(amount.getText()+" "+idOr);
                oos.flush();
                String line = ois.readUTF();
                if (line.equals("Command proceeded")) {
                    WarningDialog wd = new WarningDialog(null, true, panel, "Успешно");
                    line = "";
                    this.dispose();
                } else {
                    WarningDialog wd = new WarningDialog(null, true, panel, "Ошибка");
                    line = "";
                    this.dispose();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (choice==4) {
            try{
                oos.writeUTF(num.toString());
                oos.flush();
                Integer permit = 1;
                oos.writeUTF(permit.toString()+" "+idOr);
                oos.flush();
                String line = ois.readUTF();
                if (line.equals("Command proceeded")) {
                    WarningDialog wd = new WarningDialog(null, true, panel, "Успешно");
                    line = "";
                    this.dispose();
                } else {
                    WarningDialog wd = new WarningDialog(null, true, panel, "Ошибка");
                    line = "";
                    this.dispose();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
