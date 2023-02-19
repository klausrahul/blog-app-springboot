package com.rahul.blog.app.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
	
	
	String resourceName;
	String fieldname;
	long fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldname, long fieldValue) {
		super(String.format("%s not found with %s : %l",resourceName,fieldname,fieldValue));
		this.resourceName = resourceName;
		this.fieldname = fieldname;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException() {
		super("Resource Not Found on Server!!!");
	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
