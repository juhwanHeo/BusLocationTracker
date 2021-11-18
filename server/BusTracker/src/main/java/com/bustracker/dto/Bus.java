package com.bustracker.dto;

import lombok.*;

import java.sql.Date;

@Getter
@AllArgsConstructor
@Builder
public class Bus {

	private int seq_no;
	private String lat;
	private String lon;
	private double accuracy;
	private Date arrivaltime;

	@Getter
	@Setter
	public static class Request {
		private String lat;
		private String lon;
		private double accuracy;
		private Date arrivaltime;
	}

	@Getter
	@AllArgsConstructor
	public static class Response<T> {
		private int code;
		private String msg;
		private T data;
	}

}
