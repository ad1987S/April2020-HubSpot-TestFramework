package com.qa.hubspot.util;

public class Credentials {

	    String username;
		
		String Password;
		
		
		public Credentials (String U,String P) {
			
			this.username =U;
			this.Password=P;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getPassword() {
			return Password;
		}


		public void setPassword(String password) {
			Password = password;
		}
		
		
}
