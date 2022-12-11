package server;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Orders implements Serializable {
    private ArrayList<Integer> orderId = new ArrayList<Integer>();
    private ArrayList<Integer> companyId = new ArrayList<Integer>();
    private ArrayList<Integer> amount = new ArrayList<Integer>();
    private ArrayList<Integer> hwId = new ArrayList<Integer>();
    private ArrayList<Integer> bool = new ArrayList<Integer>(); //0-false, 1-true

    public Orders(ResultSet rs) {
        try {
            while (rs.next()) {
                setOrderId(rs.getInt(1));
                setCompanyId(rs.getInt(2));
                setHwId(rs.getInt(3));
                setAm(rs.getInt(4));
                setBool(rs.getInt(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Integer> getOrderId() {
        return orderId;
    }

    public ArrayList<Integer> getCompanyId() {
        return companyId;
    }

    public ArrayList<Integer> getAm() {
        return amount;
    }

    public ArrayList<Integer> getHwId() {
        return hwId;
    }

    public ArrayList<Integer> getBool() {
        return bool;
    }


    public void setOrderId(Integer orderId) {
        this.orderId.add(orderId);
    }

    public void setCompanyId(Integer companyId) {
        this.companyId.add(companyId);
    }

    public void setAm(Integer swId) {
        this.amount.add(swId);
    }

    public void setHwId(Integer hwId) {
        this.hwId.add(hwId);
    }

    public void setBool(Integer bool) {
        this.bool.add(bool);
    }

}

