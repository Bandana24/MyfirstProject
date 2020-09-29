package com.example.demo.exceptions;

import java.time.LocalDate;

public class ErrorDetails {

	private LocalDate Error_Date;
	private String Error_Desc;
	private String Error_Details;

	public String getError_Details() {
		return Error_Details;
	}

	public void setError_Details(String error_Details) {
		Error_Details = error_Details;
	}

	public LocalDate getError_Date() {
		return Error_Date;
	}

	public void setError_Date(LocalDate error_Date) {
		Error_Date = error_Date;
	}

	public String getError_Desc() {
		return Error_Desc;
	}

	public void setError_Desc(String error_Desc) {
		Error_Desc = error_Desc;
	}

	public ErrorDetails(LocalDate error_Date, String error_Desc, String error_Details) {
		super();
		Error_Date = error_Date;
		Error_Desc = error_Desc;
		Error_Details = error_Details;
	}
	
	public ErrorDetails()
	{}
		

}
