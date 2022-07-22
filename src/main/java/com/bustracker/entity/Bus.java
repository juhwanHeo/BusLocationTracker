package com.bustracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	@JsonProperty(value = "facilityId", index = 2)
	private String facilityId;

	@JsonProperty(value = "latitude", index = 3)
	private double latitude;

	@JsonProperty(value = "longitude",index = 4)
	private double longitude;

	@JsonProperty(value = "accuracy", index = 5)
	private double accuracy;

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "inputDate", index = 6)
	private LocalDateTime inputDate;

}
