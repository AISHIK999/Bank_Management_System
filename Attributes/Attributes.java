package Attributes;

public class Attributes {
    // Initialize Attributes
    private int accountNumber;
    private String accountName;
    private String aadhaarNumber;
    private String mobileNumber;
    private String accountBalance;


    // Apply attributes
    public Attributes(int accountId, String accountName, String aadhaarNumber, String mobileNumber, String accountBalance) {
        this.accountNumber = accountId;
        this.accountName = accountName;
        this.aadhaarNumber = aadhaarNumber;
        this.mobileNumber = mobileNumber;
        this.accountBalance = accountBalance;
    }

    // Operate on attributes

    // Account Name
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getAccountName() {
        return accountName;
    }

    // Aadhaar Number
    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }
    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

	// Mobile Number
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}

	// Account Balance
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountBalance() {
		return accountBalance;
	}

	// toString
	@Override
	public String toString() {
		return accountNumber + "," + accountName + "," + aadhaarNumber + "," + mobileNumber + "," + accountBalance;
	}
}