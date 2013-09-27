package com.jopss.topics.exception;


public class TopicsException extends Exception{

	private static final long serialVersionUID = 1112813749631064010L;

	public TopicsException(){
		super();
	}
	
	public TopicsException(String s){
		super(s);
	}
	
	public TopicsException(Exception e){
		super(e);
	}
}
