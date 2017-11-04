package com.cg.client;

import java.util.Scanner;

import com.cg.exception.AirlineException;

public class AirlineClient {

	public static void main(String[] args) {
	try {
			System.out.println("************Welcome To My Airlines*************");
			int exitCondition=1;
			do{
				System.out.println("Select your Identity:");
				System.out.println("1.Administrator\n2.Airline Executive\n3.Customer");
				Scanner sc = new Scanner(System.in);
				int choice = sc.nextInt();
				switch(choice){
				case 1:
					CustomerClient customerClient = new CustomerClient();
					customerClient.customerPortal();
					break;
					
				case 2:
					ExecutiveClient executiveClient = new ExecutiveClient();
					executiveClient.executivePortal();
					break;
				case 3:
					AdminClient adminClient = new AdminClient();
					adminClient.adminPortal();
					break;
				default:
					System.out.println("Invalid Choice, Enter 1 to continue or 0 to exit");
					exitCondition = sc.nextInt();
				}
			}while(exitCondition!=0);
		
		} catch (AirlineException e) {
			System.out.println(e.getMessage());
		}

	}

}
