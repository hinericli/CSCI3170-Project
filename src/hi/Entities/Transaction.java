package hi.Entities;

import java.util.Date;

public class Transaction {
    private int tID;
    private int pID;
    private int sID;
    private java.sql.Date tDate;

    public Transaction(int tID, int pID, int sID, java.sql.Date tDate) {
        this.tID = tID;
        this.pID = pID;
        this.sID = sID;
        this.tDate = tDate;
    }

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(java.sql.Date tDate) {
        this.tDate = tDate;
    }
}