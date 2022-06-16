package com.bustracker.entity;

import com.bustracker.status.BusStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_bus_log")
public class Bus {

	@Id
	@JsonProperty(value = "id", index = 1)
	private String id;

	@JsonProperty(value = "timeRowLogId", index = 2)
	private String timeRowLogId;

	@JsonProperty(value = "lat", index = 3)
	private double lat;

	@JsonProperty(value = "lon",index = 4)
	private double lon;

	@JsonProperty(value = "accuracy", index = 5)
	private double accuracy;

//	@JsonProperty(value = "status",index = 6)
//	private BusStatus status;

	@CreatedDate
	@JsonProperty(value = "inputDate", index = 6)
	private LocalDateTime inputDate;

}
