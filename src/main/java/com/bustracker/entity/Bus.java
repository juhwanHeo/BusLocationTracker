package com.bustracker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_bus")
public class Bus {

	@Id
	private String id;

	@JsonProperty("timeTableId")
	private String timeTableId;

	@JsonProperty("timeRowId")
	private String timeRowId;

	@JsonProperty("lat")
	private double lat;

	@JsonProperty("lon")
	private double lon;

	@JsonProperty("accuracy")
	private double accuracy;


	public enum BusStatus {

		// 대기
		STAND_BY,

		// 운행 중
		IN_PROGRESS,

		// 운행 종료
		COMPLETE,

		// 지연
		DELAY
	}

}
