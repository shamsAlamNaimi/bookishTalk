package com.bookishTalk.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.query.Query;

import com.bookishTalk.pojo.BookPojo;
import com.bookishTalk.pojo.UserPojo;

public class UserDAO extends DAO {
		
	public UserPojo checkLogin(String un,String up) {
		
		String queryString= "FROM UserPojo WHERE userPassword= :up AND userName=:un";
		Query query= getSession().createQuery(queryString);
		query.setString("un", un);
		query.setString("up",up);
		System.out.println("var names"+un+" "+up);
		query.setComment("::::::Login  HQL in userDAO:::::");
		return (UserPojo) query.uniqueResult();
		}
	
	public boolean userPresent(String s) {
		String queryString= "FROM UserPojo WHERE userName=:un";
		Query query= getSession().createQuery(queryString);
		query.setString("un", s);
		UserPojo u=(UserPojo) query.uniqueResult();
		System.out.print("user present"+u.toString());
		return (u==null);
		
	}
	
	public void saveObj(UserPojo  user) {
		begin();
		getSession().save(user);
		commit();
		close();
	}
	
	public void userTest() {
		UserPojo u= new UserPojo();
		u.setUserName("test1");
		u.setUserPassword("test");
		Set<Integer> s= new HashSet<Integer>();
		s.add(1);
		s.add(2);
		s.add(5);
		u.setBookMapping(s);
		begin();
		getSession().save(u);
		commit();
		close();
		
		
	}
	public List<BookPojo> getUnrivedBook(List<BookPojo> l,HttpServletRequest request){
		List<BookPojo> ans= new ArrayList<BookPojo>();
		String queryString= "FROM UserPojo WHERE userName=:un";
		Query query= getSession().createQuery(queryString);
		UserPojo u=(UserPojo) request.getSession().getAttribute("userDetail");
		query.setParameter("un", u.getUserName());
		UserPojo user=(UserPojo) query.uniqueResult();
		Set<Integer> set=user.getBookMapping();
		for(BookPojo b:l) {
			if(!set.contains((int)b.getBookId())){
				ans.add(b);
				}
		}
		
		return ans;
	}
	
	public void addDataToSet(int id,HttpServletRequest request) {
		String queryString= "FROM UserPojo WHERE userName=:un";
		Query query= getSession().createQuery(queryString);
		UserPojo u=(UserPojo) request.getSession().getAttribute("userDetail");
		query.setParameter("un", u.getUserName());
		UserPojo user=(UserPojo) query.uniqueResult();
		Set<Integer> set=user.getBookMapping();
		set.add(id);
		begin();
		getSession().save(user);
		commit();
		close();
	}
}
