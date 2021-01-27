package com.bookishTalk.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class BookPojo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idbooks") 
	public int bookId;
	
	@Column(name="bookname") 
	public String bookName;
	
	@Column(name="authorname") 
	public String bookAuthor;
	
	@Column(name="addedby") 
	public String addedBy;
	
	@Column(name="likedby") 
	public int likedBy;
	
	@Column(name="unlikeby") 
	public int unlikedBy;
	
	@Column(name="description")
	public String description;
	
public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	//	@ManyToOne
//	@JoinColumn(name = "useradded", nullable = false)
//	public UserPojo userdata;
//	
//	
//	
//	public UserPojo getUserdata() {
//		return userdata;
//	}
//	public void setUserdata(UserPojo userdata) {
//		this.userdata = userdata;
//	}
	public BookPojo() {}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	public int getLikedBy() {
		return likedBy;
	}
	public void setLikedBy(int likedBy) {
		this.likedBy = likedBy;
	}
	public int getUnlikedBy() {
		return unlikedBy;
	}
	public void setUnlikedBy(int unlikedBy) {
		this.unlikedBy = unlikedBy;
	}
	
}
