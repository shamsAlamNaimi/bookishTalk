package com.bookishTalk.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bookishTalk.dao.UserDAO;
import com.bookishTalk.pojo.UserPojo;

public class UserValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserPojo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		ApplicationContext context =  new AnnotationConfigApplicationContext();
//		UserDAO userDao=(UserDAO)context.getBean("userdao");
		ValidationUtils.rejectIfEmpty(errors, "userName", "emptyName", "User Name can't be empty");
		ValidationUtils.rejectIfEmpty(errors, "userPassword", "emptyPassword", "Password can't be empty");
		ValidationUtils.rejectIfEmpty(errors, "verifyPassword", "emptyVerifyPassword", "Password can't be empty");
		UserPojo u= (UserPojo) target;
		//System.out.println("username "+userDao.toString());
		//if(!userDao.userPresent(u.getUserName())) errors.rejectValue("userName", "userPresert", "User Name already present");
		if(!u.getUserPassword().equals(u.getVerifyPassword())) errors.rejectValue("userPassword", "invalidpassword", "password doesn't match");
		
	}

}
