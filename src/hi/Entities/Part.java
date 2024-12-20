package hi.Entities;

public class Part {
    private int pID;
    private String pName;
    private int pPrice;
    private int mID;
    private int cID;
    private int pWarrantyPeriod;
    private int pAvailableQuantity;

    public Part(int pID, String pName, int pPrice, int mID, int cID, int pWarrantyPeriod, int pAvailableQuantity) {
        this.pID = pID;
        this.pName = pName;
        this.pPrice = pPrice;
        this.mID = mID;
        this.cID = cID;
        this.pWarrantyPeriod = pWarrantyPeriod;
        this.pAvailableQuantity = pAvailableQuantity;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getpWarrantyPeriod() {
        return pWarrantyPeriod;
    }

    public void setpWarrantyPeriod(int pWarrantyPeriod) {
        this.pWarrantyPeriod = pWarrantyPeriod;
    }

    public int getpAvailableQuantity() {
        return pAvailableQuantity;
    }

    public void setpAvailableQuantity(int pAvailableQuantity) {
        this.pAvailableQuantity = pAvailableQuantity;
    }
}