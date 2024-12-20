package hi.Entities;

public class Salesperson {
    private int sID;
    private String sName;
    private String sAddress;
    private int sPhoneNumber;
    private int sExperience;

    public Salesperson(int sID, String sName, String sAddress, int sPhoneNumber, int sExperience) {
        this.sID = sID;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sPhoneNumber = sPhoneNumber;
        this.sExperience = sExperience;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public int getsPhoneNumber() {
        return sPhoneNumber;
    }

    public void setsPhoneNumber(int sPhoneNumber) {
        this.sPhoneNumber = sPhoneNumber;
    }

    public int getsExperience() {
        return sExperience;
    }

    public void setsExperience(int sExperience) {
        this.sExperience = sExperience;
    }
}