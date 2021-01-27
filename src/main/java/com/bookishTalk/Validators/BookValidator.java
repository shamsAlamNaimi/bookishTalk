package com.bookishTalk.Validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.bookishTalk.pojo.BookPojo;

public class BookValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BookPojo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "bookName", "emptyName", "Book Name can't be empty");
		ValidationUtils.rejectIfEmpty(errors, "bookAuthor", "emptyAuthor", "Author Name can't be empty");
		BookPojo book=(BookPojo) target;
		if(!isStringOnlyAlphabets(book.getBookName())) errors.rejectValue("bookName", "invalidName", "invalid book name");
		if(!isStringOnlyAlphabets(book.getBookAuthor())) errors.rejectValue("bookAuthor", "invalidAuthor", "invalid author name");
	}
	
	public boolean isStringOnlyAlphabets(String str) {
		return ((!str.equals("")) 
	            && (str != null) 
	            && (str.matches("^(?! )[A-Za-z\\s]*$"))); 
			} 
	}


