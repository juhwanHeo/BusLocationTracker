package com.bustracker.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class Station {
	private int station_no;
	private String station_nm;
	private double lat;
	private double lon;

	@Getter
	@Setter
	@ToString
	public static class Request {
		private String station_nm;
		private double lat;
		private double lon;
	}

	@Getter
	@AllArgsConstructor
	public static class Response<T> {
		private int code;
		private String msg;
		private T data;
	}
}
