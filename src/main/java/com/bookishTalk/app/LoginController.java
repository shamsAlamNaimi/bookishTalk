package com.bookishTalk.app;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.bookishTalk.Validators.UserValidator;
import com.bookishTalk.dao.BookDAO;
import com.bookishTalk.dao.UserDAO;
import com.bookishTalk.pojo.BookPojo;
import com.bookishTalk.pojo.UserPojo;

@Controller
public class LoginController {
	@Autowired 
	UserValidator validator;
	
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request, UserDAO userDAO) {
		try {
			String un=request.getParameter("username");
			String ps=request.getParameter("password");
			UserPojo user=userDAO.checkLogin(un, ps);
			
			if(user==null) return "nouser";
			HttpSession session= request.getSession();
			session.setAttribute("userDetail", user);
			UserPojo u=(UserPojo)request.getSession().getAttribute("userDetail");
			System.out.println("------------"+u.getUserName());
			
			return "main_page";
		} catch (HibernateException hibernateEx ) {
			// TODO Auto-generated catch block
			return "error_ne";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session= request.getSession();
		session.removeAttribute("userDetail");
		return "home";
	}
	

	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showForm(ModelMap model,HttpServletRequest request,UserPojo user) {
		model.addAttribute("user", user);
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String showSuccess(@ModelAttribute("user") UserPojo user,HttpServletRequest request,ModelMap model, BindingResult result,SessionStatus status,UserDAO userDAO) {
	
			try {
				validator.validate(user, result);
				if(result.hasErrors()) return "signup";
				userDAO.saveObj(user);
				HttpSession session= request.getSession();
				session.setAttribute("userDetail", user);
				UserPojo u=(UserPojo)request.getSession().getAttribute("userDetail");
				return "main_page";
			} catch (HibernateException hibernateEx ) {
				// TODO Auto-generated catch block
				return "userpre";
			}
		
	}
}
