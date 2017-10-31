package com.cg.client;

import java.util.Scanner;

import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;


public class SanjayClient {

	public static void main(String[] args) throws AirlineException {
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		String username = "";
		String password = "";
		LoginMaster login = new LoginMaster();
		AirlineServiceImpl service = new AirlineServiceImpl();
		String user = null;
		while(true){
			if(username.isEmpty())
			{
				System.out.println("Hi user! What do you want to do today");
				choice =0;
				System.out.println("1.LogIn\n2.SignUp\n3.Flight Search");
			}
			else
			{
				System.out.println("Hi "+username+"! What do you want to do today");
				choice=2;
				System.out.println("1.Flight Search\n2.Logout");
			}

			choice += sc.nextInt();

			switch(choice){
			case 3:
				System.out.println("Sorry!! Server busy");
				break;
			case 1:
				System.out.print("Username :");
				//username = sc.next();
				login.setUsername(sc.next());
				sc.nextLine();
				System.out.print("Password :");
				//password = sc.nextLine();
				login.setPassword(sc.nextLine());
				login.setRole("customer");
				if(service.validLogin(login) !=0)
				{
					username = login.getUsername();
				}
				break;

			case 2:
				System.out.println("Hello new User! Please provide Some information to create your account");
				sc.nextLine();
				do{
					System.out.print("Username :");
					user = sc.nextLine();
					login.setUsername(user);
					if(user.contains(" "))
					{
						System.out.println("Username will not contain any Spaces");
					}
					else if(service.usernameIsAvail(user) == 1){
						System.out.println("Username Already Exist !! ");
					}
				}while(user.contains(" ") ||(service.usernameIsAvail(user) ==1) );
				
				long mobile = 0;
				do{
					System.out.print("mobile :");
					mobile = sc.nextLong();
					if(service.mobileIsAvail(mobile) ==1)
					{
						System.out.println("Mobile Number is already exist");
					}
				}while(service.mobileIsAvail(mobile) ==1);
				login.setMobile(mobile);
				
                sc.nextLine();
				System.out.print("Password :");
				login.setPassword(sc.nextLine());
				
				login.setRole("customer");

				service.signUp(login);
				System.out.println("SignUp Success");

				break;
			case 4:
				if(!username.isEmpty()){
					username="";
				System.out.println("you are Successfully log out");
				}
				else
					System.out.println("Please provide valid input");
				break;
			default:
				System.out.println("Please provide valid input");

			}
		}	

	}

}
