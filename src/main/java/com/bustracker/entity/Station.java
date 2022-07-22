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
@Document(collection = "c_station")
public class Station {

	@Id
	private String id;

	@JsonProperty("facilityId")
	private String facilityId;

	@JsonProperty("stationCode")
	private String stationCode;

	@JsonProperty("stationName")
	private String stationName;

	@JsonProperty("lat")
	private double lat;

	@JsonProperty("lon")
	private double lon;

}
