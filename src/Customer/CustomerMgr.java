package Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.lang.String;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileReader;

public class CustomerMgr {

	private static ArrayList<Customer> ListOfCustomer;
	
	public CustomerMgr() {
		ListOfCustomer = new ArrayList<Customer>();
		ListOfCustomer = loadCustomerList("customerList.txt");
	}

	public void init(){
		CustomerUI customerui = new CustomerUI();
				
		int choice;
		do {
			choice = customerui.mainMenu();
			switch (choice) {
			case 1:
				displayCustomerList();
				break;
			case 2:
				// add customer
				int customerId = getLastCustomerId() + 1;
				System.out.println("Customer Id to insert:" + customerId);
				
				// launch customerUI to get customer info
				String customerName = customerui.getCustomerName();
				String customerPhone = customerui.getCustomerPhone();
				String customrMember = customerui.getCustomerMember();

				// create new customer
				addCustomer(customerId, customerName, customerPhone, customrMember);
				System.out.println("\nCustomer added..");
				displayCustomerList();
				break;
			case 3:
				// remove customer object from the ArrayList of customer
				int customerIdToRemove = customerui.getRemoveId();
				removeCustomer(customerIdToRemove);
				break;
			case 4:
				// removeCustomer();
				saveCustomerList("customerList.txt");
				break;
			case 5:
				// saveCustomer();
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
		} while (choice != 5);

		
	}

	// load file name and return an ArrayList of Customer objects
	public ArrayList<Customer> loadCustomerList(String fileName) {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			//to store a line
			String line = "";
			
			// to consume the header row of csv
			String headerLine = "";
			headerLine = br.readLine();
			
			// keeps instanciating customer object until no more rows
			while((line = br.readLine()) != null) {			
				// split a line of string into an array
				// [0] is id, [1] is name etc...
				String[] values = line.split(",");
				
				// takes the corresponding array[0], fit into customer object
				Customer customer = new Customer();
				int i = Integer.parseInt(values[0]);
				customer.setCustomerId(i);
				customer.setName(values[1]);
				customer.setPhone(values[2]);
				
				// if value[3] is y, set the boolean to true
				if(values[3].equals("y")) {
					customer.setMember(true);
				}
				else {
					customer.setMember(false);
				}
				
				// append this customer object into the the array list of customer
				customerList.add(customer);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerList;
	}

	// pass in an ArrayList<Customer> and display all customers in the list
	public void displayCustomerList() {
		System.out.printf("CustId\tName\tPhone\t\tMember\n");
		for (Customer customer : ListOfCustomer) {
			String member = "";
			if (customer.isMember()) {
				member = "y";
			} else {
				member = "n";
			}
			System.out.println(customer.getCustomerId() + "\t" + customer.getName() + "\t" + customer.getPhone() + "\t" + customer.isMember());
		}
	}
	
	// function to add customer object to list of customer
	public void addCustomer(int customerId, String name, String phone, String member) {
		Customer newCustomer = new Customer();
		newCustomer.setCustomerId(customerId);
		newCustomer.setName(name);
		newCustomer.setPhone(phone);
		if(member.equals("y")) {
			newCustomer.setMember(true);
		}
		else {
			newCustomer.setMember(false);
		}
		ListOfCustomer.add(newCustomer);
	}

	// returns the last customer id in the list
	public int getLastCustomerId() {
		int lastCustomerId = 0;
		for (Customer customer : ListOfCustomer) {
			if (customer.getCustomerId() > lastCustomerId) {
				lastCustomerId = customer.getCustomerId();
			}
		}
		return lastCustomerId;
	}

	// remove customer object from the list of customer, store into a temp ArrayList if customer id is not CustomerIdemoval
	public void removeCustomer(int customerIdToRemove) {
		ArrayList<Customer> tempList = new ArrayList<Customer>();
		for (Customer customer : ListOfCustomer) {
			if (customer.getCustomerId() != customerIdToRemove) {
				tempList.add(customer);
			}
		}
		ListOfCustomer.clear();
		ListOfCustomer.addAll(tempList);

		System.out.println("Customer removed..");
		displayCustomerList();
	}


	// save customer list to csv file
	public void saveCustomerList(String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(fileName));
			pw.print("");
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			// write header row
			bw.write("CustomerId,Name,Phone,Member");
			bw.newLine();
			
			// write each customer object into the file
			for (Customer customer : ListOfCustomer) {
				String member = "";
				if (customer.isMember()) {
					member = "y";
				} else {
					member = "n";
				}
				bw.write(customer.getCustomerId() + "," + customer.getName() + "," + customer.getPhone() + "," + member);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// pass in an CustomerId integer and return the customer object
	public Customer getCustomerObj(int customerId) {
		Customer customer = new Customer();
		for (Customer c : ListOfCustomer) {
			if (c.getCustomerId() == customerId) {
				customer = c;
			}
		}
		return customer;
	}

}
