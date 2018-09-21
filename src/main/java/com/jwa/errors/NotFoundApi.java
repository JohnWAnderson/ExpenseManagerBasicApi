package com.jwa.errors;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public class NotFoundApi extends RuntimeException {

	public NotFoundApi(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NotFoundApi(String arg0) {
		super(arg0);
	}

	public NotFoundApi(Throwable arg0) {
		super(arg0);
	}

	
}
