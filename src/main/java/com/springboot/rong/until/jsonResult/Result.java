package com.springboot.rong.until.jsonResult;


public class Result
{
  private String jwtToken;
  private String code;
  private String path;
  private String dateType;
  private String data;
  private String message;

  
  
  
  
public String getData() {
	return data;
}
public void setData(String data) {
	if(data==null){
		this.code="300";
	}else{
		this.code="200";
	}
	this.data = data;
}
public String getJwtToken() {
	return jwtToken;
}
public void setJwtToken(String jwtToken) {
	this.jwtToken = jwtToken;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	if(message==null){
		this.code="300";
	}else{
		this.code="200";
	}
	this.message = message;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getDateType() {
	return dateType;
}
public void setDateType(String dateType) {
	this.dateType = dateType;
}
  
  
  
}
