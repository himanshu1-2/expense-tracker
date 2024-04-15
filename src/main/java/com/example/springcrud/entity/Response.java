package com.example.springcrud.entity;

import java.util.List;

public class Response <T> {

	 private String message;
	 //Page<Expense>   
	 private List<T> data;

	    public Response(String message, List<T> data) {
	        this.message = message;
	        this.data = data;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public List<T> getData() {
	        return data;
	    }


}
