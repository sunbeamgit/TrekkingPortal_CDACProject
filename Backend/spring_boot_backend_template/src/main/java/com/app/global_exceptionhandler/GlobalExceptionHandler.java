package com.app.global_exceptionhandler;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(
			ResourceNotFoundException ex){
		return ResponseEntity.
				status(HttpStatus.NOT_FOUND).
				body(new ApiResponse(ex.getMessage()));
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> handleIOException(IOException e) {
	    System.out.println("Handling IOException");
	    e.printStackTrace(); 
	    return ResponseEntity
	            .status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ApiResponse("An IO error occurred: " + e.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		System.out.println("in catch-all");
		e.printStackTrace();//Added only for detailed debugging!
		return ResponseEntity.
				status(HttpStatus.INTERNAL_SERVER_ERROR).
				body(new ApiResponse(e.getMessage()));
	}
}
