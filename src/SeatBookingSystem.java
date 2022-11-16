import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
public class SeatBookingSystem {
	private static Scanner console = new Scanner(System.in);
	private static final trainSeat[] totalSeats = new trainSeat[18];
	public static void main(String[] args) throws Exception {
		loadSeats();//loads seat data
		String option = "";
		do {
			System.out.println("---Seat Booking System---\n"
					+ "1-Reserve Seat\n"
					+ "2-Cancel Seat\n"
					+ "3-View Seat Reservation\n"
					+ "Q-Quit\n"
					+ "Pick:");

			option = console.next().toUpperCase();

			switch (option) {//switch case for menu selection
			case "1": {
				reserveSeat();
				break;
			}
			case "2": {
				cancelSeat();
				break;
			}
			case "3": {
				displaySeats();
				break;
			} 
			}
		}while (!(option.equalsIgnoreCase("Q")));// closes program
		System.out.println("--- Thank you for using our system ---");
	}
	public static void loadSeats () throws FileNotFoundException{ //loads seat data for multiple uses
		Scanner read = new Scanner(new FileReader("seats.txt"));
		int index=0;

		while (read.hasNext()) {
			String seatID = read.next();
			String seatType = read.next();
			boolean window = Boolean.parseBoolean(read.next());
			boolean aisle = Boolean.parseBoolean(read.next());
			boolean table = Boolean.parseBoolean(read.next());
			double price = read.nextDouble();
			String email = read.next();//reads all values within .txt file	
			for (int i=0; i<totalSeats.length; i++){
			}				
			totalSeats[index] = new trainSeat(seatID, seatType, window, aisle, table, price, email);
			index++;//adding new objects to the array in order to read to the 
		}
		read.close();
	}
	private static void updateSeatData() throws FileNotFoundException {//method used to update seat data
		PrintStream write = new PrintStream ("seats.txt");
		for (int i=0; i<totalSeats.length; i++)
			write.println(totalSeats[i].toString());
		write.close();
	}
	private static void displaySeats() {
		System.out.println("---Seats onboard train ---\n"
				+ "SeatNo.: Seat Class: window: Aisle: Table: Price: Reservation:"); // used to identify variables of the reservation list
		for (int i=0; i<totalSeats.length; i++) { 
			if (totalSeats[i].email.equals("free")){//used to hide reservations that are taken so users don't double book and protects user data
			System.out.println(totalSeats[i].seatID+"        "+totalSeats[i].seatType+"        "+totalSeats[i].window+"    "+totalSeats[i].aisle+"   "+totalSeats[i].window+"   "+totalSeats[i].price+"  "+totalSeats[i].email);
		}//displays seats in the console to showcase the known variable (e.g. seat type, window, aisle, ETC.
	}
	}	
	private static <totalSeats> void reserveSeat() throws IOException {	
		System.out.println("---Seat reservation menu---");
		displaySeats();//shows lists of seats that are available
		String seatInput;
		String eInput;
		boolean correct=false;
		while(!correct){
			System.out.print("Select seat you would like to reserve:");
			seatInput = console.next();
			for (int i=0; i<totalSeats.length; i++) {
				if (totalSeats[i].claimSeatNo().equalsIgnoreCase(seatInput)) {
					System.out.println("Seat Found\n"
							+ "Enter email:");
					eInput= console.next();
					while(!(eInput.contains("@"))) { //requires user to insert valid email
						System.out.println("email not entered, please enter valid email");
						eInput = console.next();
						correct=true;
						break;
					}
					totalSeats[i].claimUserEmail(eInput).equals(eInput);
					updateSeatData();
					System.out.println(totalSeats[i].toString());
					System.out.println("Seat has been reserved: " + seatInput + ", Seat reserved under: " + eInput);
					correct = true; //breaks loop
					break;
				}
			}
		}
	}
	private static <totalSeats> void cancelSeat() throws IOException{
		System.out.println("---Seat cancel menu---");
		String seatInput;
		String cInput= "free";
		boolean correct = false;
		while (!correct) {
			System.out.println("Select seat you would like to cancel:");
			seatInput = console.next();
			while (!(seatInput.contains(seatInput))) {//seat validation
				System.out.println("seat not found");
			}
			for (int i=0; i<totalSeats.length; i++)//for loop to access array 
				if (totalSeats[i].claimSeatNo().equalsIgnoreCase(seatInput)) {
					System.out.println("Seat Found");
					totalSeats[i].claimUserEmail(cInput).equals("free");
					updateSeatData();//allows method to update seat data
					System.out.println(totalSeats[i].toString());
					System.out.println("seat has been cancelled ");
					correct=true;
				}
		}
	}
}




