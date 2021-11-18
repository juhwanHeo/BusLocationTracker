package com.bustracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@AllArgsConstructor
@Builder
public class Station {
	private int station_no;
	private String station_nm;
	private String lat;
	private String lon;

	@Getter
	@Setter
	public static class Request {
		private String station_nm;
		private String lat;
		private String lon;
	}

	@Getter
	@AllArgsConstructor
	public static class Response<T> {
		private int code;
		private String msg;
		private T data;
	}
}
