package com.bookishTalk.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="userdata")
public class UserPojo {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="userId") 
		public int userId;
		
		@Column(name="username", unique= true) 
		public String userName;
		
		@Column(name="userpassword") 
		public String userPassword;
		
		@Column(name="verifypassword") 
		public String verifyPassword;
		
		
		@ElementCollection
		@JoinTable(name="reviewd")
		Set<Integer> bookMapping= new HashSet<Integer>();
	
		
		
		
		public Set<Integer> getBookMapping() {
			return bookMapping;
		}
		public void setBookMapping(Set<Integer> s) {
			this.bookMapping = s;
		}
		public String getVerifyPassword() {
			return verifyPassword;
		}
		public void setVerifyPassword(String verifyPassword) {
			this.verifyPassword = verifyPassword;
		}
		public UserPojo() {}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserPassword() {
			return userPassword;
		}
		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
		
}
