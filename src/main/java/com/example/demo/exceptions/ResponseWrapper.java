package com.example.demo.exceptions;

public class ResponseWrapper {

	
	Object Ret_object;
	String Msg_success;
	String Msg_failure;
	
	public Object getRet_object() {
		return Ret_object;
	}
	public void setRet_object(Object ret_object) {
		Ret_object = ret_object;
	}
	public String getMsg_success() {
		return Msg_success;
	}
	public void setMsg_success(String msg_success) {
		Msg_success = msg_success;
	}
	public String getMsg_failure() {
		return Msg_failure;
	}
	public void setMsg_failure(String msg_failure) {
		Msg_failure = msg_failure;
	}
	
	
	
}
