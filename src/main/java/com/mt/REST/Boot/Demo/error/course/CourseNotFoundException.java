package com.mt.REST.Boot.Demo.error.course;

public class CourseNotFoundException extends RuntimeException {

	public CourseNotFoundException() {
	}

	public CourseNotFoundException(String message) {
		super(message);
	}

	public CourseNotFoundException(Throwable cause) {
		super(cause);
	}

	public CourseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CourseNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
