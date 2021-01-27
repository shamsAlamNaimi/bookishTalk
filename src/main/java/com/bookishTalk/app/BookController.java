package com.bookishTalk.app;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.bookishTalk.Validators.BookValidator;
import com.bookishTalk.dao.BookDAO;
import com.bookishTalk.dao.UserDAO;
import com.bookishTalk.pojo.BookPojo;
import com.bookishTalk.pojo.UserPojo;
import com.bookishTalk.view.XlsView;

@Controller
public class BookController {
	@Autowired
	BookValidator validator;
	
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String getBook(HttpServletRequest request,BookDAO bookdao) {
		Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
		if(!userAtive) {
			System.out.println("inside false");
			return "home";
		}
		System.out.println("inside all book");
		List<BookPojo> list = bookdao.getBooks();
		Collections.sort(list,new Comparator<BookPojo>() {

			@Override
			public int compare(BookPojo o1, BookPojo o2) {
				// TODO Auto-generated method stub
				return o2.getLikedBy()-o1.getLikedBy();
			}
			
		});
		request.setAttribute("bookList", list);
		return "bookPage";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String showForm(ModelMap model,HttpServletRequest request,BookPojo book) {
		Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
		if(!userAtive) {
			System.out.println("inside false");
			return "home";
		}
		
		model.addAttribute("book", book);
		return "addBook";
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.GET)
	public String editForm(ModelMap model,HttpServletRequest request,BookDAO bookDao) {
		Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
		if(!userAtive) {
			System.out.println("inside false");
			return "home";
		}
		String var=request.getParameter("movie_id");
		BookPojo bk=bookDao.searchBook(Integer.parseInt(var));
		model.addAttribute("book", bk);
		return "editBook";
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	public String showedit(@ModelAttribute("book") BookPojo book,HttpServletRequest request,ModelMap model, BindingResult result,SessionStatus status,BookDAO bookdao) {
		try {
			Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
			if(!userAtive) {
				System.out.println("inside false");
				return "home";
			}
			validator.validate(book, result);
			
			if(result.hasErrors()) {
				//model.addAttribute("book", book);
				return "addBook";}
			UserPojo u=(UserPojo) request.getSession().getAttribute("userDetail");
			//book.setAddedBy(u.getUserName());
			String var=request.getParameter("movie_id");
			bookdao.updateObj(book,Integer.parseInt(var));
			status.setComplete();
			return this.getMyBook(request,bookdao);
		} catch (HibernateException hibernateEx ) {
			// TODO Auto-generated catch block
			return "error_ne";
		}
	}
	
	
	
	
	
	@RequestMapping(value="/likeincrease", method=RequestMethod.POST)
	public String incLike(BookDAO bookdao,HttpServletRequest request,UserDAO userDAO) {
		Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
		if(!userAtive) {
			System.out.println("inside false");
			return "home";
		}
		String var=request.getParameter("movie_id");
		System.out.println("movieId----------"+var);
		bookdao.incLike(Integer.parseInt(var));
		userDAO.addDataToSet(Integer.parseInt(var),request);
		List<BookPojo> list = bookdao.getBooks();
		List<BookPojo> l=userDAO.getUnrivedBook(list, request);
		request.setAttribute("bookList", l);
		return "unreviewdBook";
	}
	
	@RequestMapping(value="/deletebook", method=RequestMethod.POST)
	public String deltebook(BookDAO bookdao,HttpServletRequest request,UserDAO userDAO) {
		Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
		if(!userAtive) {
			System.out.println("inside false");
			return "home";
		}
		String var=request.getParameter("movie_id");
		System.out.println("movieId----------"+var);
		bookdao.delete(Integer.parseInt(var));
		List<BookPojo> list = bookdao.getMyBook((UserPojo) request.getSession().getAttribute("userDetail"));
		request.setAttribute("bookList", list);
		return "mybooks";
	}
	
	
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String showSuccess(@ModelAttribute("book") BookPojo book,HttpServletRequest request,ModelMap model, BindingResult result,SessionStatus status,BookDAO bookdao) {
		try {
			Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
			if(!userAtive) {
				System.out.println("inside false");
				return "home";
			}
			
			
			validator.validate(book, result);
			
			if(result.hasErrors()) {
				//model.addAttribute("book", book);
				return "addBook";}
			UserPojo u=(UserPojo) request.getSession().getAttribute("userDetail");
			book.setAddedBy(u.getUserName());
			book.setLikedBy(0);
			book.setUnlikedBy(0);
			bookdao.saveObj(book);
			status.setComplete();
			return this.getMyBook(request,bookdao);
		} catch (HibernateException hibernateEx ) {
			// TODO Auto-generated catch block
			return "error_ne";
		}
	}
	
	@RequestMapping(value="/unreviewd", method=RequestMethod.GET)
	public String getUnreviewdbook(HttpServletRequest request,BookDAO bookdao,UserDAO userDAO) {
		try {
			Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
			if(!userAtive) {
				System.out.println("inside false");
				return "home";
			}
			List<BookPojo> list = bookdao.getBooks();
			List<BookPojo> l=userDAO.getUnrivedBook(list, request);
			request.setAttribute("bookList", l);
			return "unreviewdBook";
		}catch (HibernateException hibernateEx ) {
			// TODO Auto-generated catch block
			return "error_ne";
		}
	}
	
	@RequestMapping(value = "/mybook", method = RequestMethod.GET)
	public String getMyBook(HttpServletRequest request,BookDAO bookdao) {
		try {
			Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
			if(!userAtive) {
				System.out.println("inside false");
				return "home";
			}
			System.out.println("inside all book");
			List<BookPojo> list = bookdao.getMyBook((UserPojo) request.getSession().getAttribute("userDetail"));
			request.setAttribute("bookList", list);
			return "mybooks";
		} catch (HibernateException hibernateEx ) {
			// TODO Auto-generated catch block
			return "error_ne";
		}
	}
	
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView getview(HttpServletRequest request,BookDAO bookdao) {
		Boolean userAtive=(Boolean) request.getSession().getAttribute("loogedIn");
		if(!userAtive) {
			System.out.println("inside false");
			return new ModelAndView("home");
		}
		List<BookPojo> list = bookdao.getBooks();
		Collections.sort(list,new Comparator<BookPojo>() {

			@Override
			public int compare(BookPojo o1, BookPojo o2) {
				// TODO Auto-generated method stub
				return o2.getLikedBy()-o1.getLikedBy();
			}
			
		});
		XlsView view = new XlsView();
		request.setAttribute("bookList", list);
		return new ModelAndView(view,"listBook",list) ;
	}
	
	
}
