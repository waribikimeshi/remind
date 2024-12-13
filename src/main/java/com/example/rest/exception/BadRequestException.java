package com.example.rest.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 例外リクエスト不正
 * @author hanada
 *
 */
@Data
@EqualsAndHashCode()
public class BadRequestException  extends Exception {

	private static final long serialVersionUID = 1L;

	private List<String> messageList = new ArrayList<String>();

	public BadRequestException(BindingResult bindingResult) {
        for (FieldError error : bindingResult.getFieldErrors()) {
        	this.addMessage(error.getField() + ":" + error.getDefaultMessage());
        }

	}
	
	public void addMessage(String message) {
		messageList.add(message);
	}

}
