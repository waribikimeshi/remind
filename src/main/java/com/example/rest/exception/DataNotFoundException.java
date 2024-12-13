package com.example.rest.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 例外対象データなし
 * @author hanada
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class DataNotFoundException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> messageList = new ArrayList<String>();

	public void addMessage(String message) {
		messageList.add(message);
	}

}
