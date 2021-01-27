package com.bookishTalk.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.bookishTalk.pojo.BookPojo;
import com.bookishTalk.pojo.UserPojo;

public class BookDAO extends DAO {
	public List<BookPojo> getBooks(){
		String queryString= "FROM BookPojo";
		Query query= getSession().createQuery(queryString);
		return query.list();
	}
	
	public BookPojo searchBook(int id) {
		String querySting="FROM BookPojo Where bookId= :id";
		Query query= getSession().createQuery(querySting);
		query.setParameter("id", id);
		return (BookPojo) query.uniqueResult();
	}
	
	public void saveObj(BookPojo book) {
		begin();
		getSession().save(book);
		commit();
		close();
	}
	
	
	public void updateObj(BookPojo book, int id) {
		begin();
		String updateString="update BookPojo b set b.bookName=:bn , b.bookAuthor= :ba, b.description= :de where bookId= :val";
		Query updatequery= getSession().createQuery(updateString);
		updatequery.setParameter("bn", book.getBookName());
		updatequery.setParameter("ba", book.getBookAuthor());
		updatequery.setParameter("de", book.getDescription());
		updatequery.setParameter("val",id);
		int i=updatequery.executeUpdate();
		commit();
		close();
	}
	
	public void delete(int id) {
		begin();
		String querySting="DELETE FROM BookPojo Where bookId= :id";
		Query query= getSession().createQuery(querySting);
		query.setParameter("id", id);
		int count2 = query.executeUpdate();
		commit();
		close();
	}
	
	public void incLike(int id) {
		String querySting="FROM BookPojo Where bookId= :id";
		Query query= getSession().createQuery(querySting);
		query.setParameter("id", id);
		BookPojo book=(BookPojo) query.uniqueResult();
		//book.setLikedBy(book.getLikedBy()+1);
		begin();
		String updateString="update BookPojo b set b.likedBy=:lb where bookId= :val";
		Query updatequery= getSession().createQuery(updateString);
		updatequery.setParameter("lb", book.getLikedBy()+1);
		updatequery.setParameter("val", id);
		int i=updatequery.executeUpdate();
		commit();
		close();
		
		
		
	}
	
	public void decLike(int id) {
		String querySting="FROM BookPojo Where bookId= :id";
		Query query= getSession().createQuery(querySting);
		query.setParameter("id", id);
		BookPojo book=(BookPojo) query.uniqueResult();
		//book.setLikedBy(book.getLikedBy()+1);
		begin();
		String updateString="update BookPojo b set b.unlikedBy=:lb where bookId= :val";
		Query updatequery= getSession().createQuery(updateString);
		updatequery.setParameter("lb", book.getUnlikedBy()+1);
		updatequery.setParameter("val", id);
		int i=updatequery.executeUpdate();
		commit();
		close();
		
		
		
	}
	
	
	public List<BookPojo> getMyBook(UserPojo user){
		String queryString="FROM BookPojo Where addedby= :ab";
		Query query= getSession().createQuery(queryString);
		query.setString("ab", user.getUserName());
		return query.list();
	}
}
