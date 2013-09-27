package com.jopss.topics.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ValidatingException extends Exception{

	private static final long serialVersionUID = 4942813749631064010L;

	private Map<String,String[]> messages = new HashMap<String,String[]>();
	
        public ValidatingException(String message){
            super(message);
        }
        
	public ValidatingException(ConstraintViolationException ex){
		
		Set<ConstraintViolation<?>> list = ex.getConstraintViolations();
		for(ConstraintViolation<?> constraint : list){
			String invalidValue = constraint.getInvalidValue() != null ? constraint.getInvalidValue().toString() : "";
			String invalidMessage = constraint.getMessageTemplate();
			String field = constraint.getPropertyPath().toString();
			
			invalidMessage = invalidMessage.substring(1,invalidMessage.length()-1); //retirando chaves.
			
			messages.put(field, new String[]{invalidValue, invalidMessage});
		}
		
	}
	
	/**
	 * Efetua a internacionalizacao da mensagem padrao do Hibernate Validator, ou da validacao inserida
	 * no messages.properties, no caso de usar alguma anotacao customizada ou especifica.
	 */
	public String internationalize(HttpServletRequest request){
		Locale locale = (Locale) Config.get(request.getSession(), Config.FMT_LOCALE);
		
		ResourceBundle reBundle = ResourceBundle.getBundle("messages", locale);
		ResourceBundle resValidation = ResourceBundle.getBundle("ValidationMessages", locale);
		
		StringBuilder str = new StringBuilder();
		
		for(String field : messages.keySet()){
			String[] details = messages.get(field);
			String invalidValue = details[0];
			String invalidMessage = details[1];
			
			str.append(reBundle.getString(field)+": ");
			str.append("'"+invalidValue+"'. ");
			
			try{
				str.append(resValidation.getString(invalidMessage));
			}catch(java.util.MissingResourceException ex){
				str.append(reBundle.getString(invalidMessage));
			}
			
			str.append("<br/>");
		}
		
		return str.toString();
	}
	
}
