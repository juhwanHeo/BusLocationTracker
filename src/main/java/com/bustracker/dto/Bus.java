package com.bustracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class Bus {

	private int seq_no;
	private double lat;
	private double lon;
	private double accuracy;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date arrivaltime;

	@Getter
	@Setter
	@ToString
	public static class Request {
		private double lat;
		private double lon;
		private double accuracy;

		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
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
