public class trainSeat {
	public String seatID;
	public String seatType;
	public boolean window;
	public boolean aisle;
	public boolean table;
	public double price;
	public String email;
	public trainSeat( String seatID, String seatType, boolean window, boolean aisle, boolean table, double price, String email) {
		this.seatID=seatID;
		this.seatType=seatType;
		this.window=window;
		this.aisle=aisle;
		this.table=table;
		this.price=price;
		this.email=email;
	}
	public String toString() {
		String result = seatID + " " + seatType + " " + window + " " + aisle + " " + table + " " + price + " " + email;
		for (int i=0; i<seatID.length(); i++) {
		}
		return result;

	}
	public String claimSeatNo() {
		return this.seatID; 
	}
	public String claimUserEmail(String email) {
		return this.email=email;
	}
}

