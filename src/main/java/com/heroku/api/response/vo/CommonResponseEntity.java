package com.heroku.api.response.vo;

import lombok.Data;

@Data
public class CommonResponseEntity {

	private String message;
	
	private Object data;
	
	private Boolean isException;
	
}
