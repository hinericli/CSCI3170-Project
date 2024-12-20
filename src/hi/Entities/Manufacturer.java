package hi.Entities;

public class Manufacturer {
    private int mID;
    private String mName;
    private String mAddress;
    private int mPhoneNumber;

    public Manufacturer(int mID, String mName, String mAddress, int mPhoneNumber) {
        this.mID = mID;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhoneNumber = mPhoneNumber;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public int getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(int mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }
}
