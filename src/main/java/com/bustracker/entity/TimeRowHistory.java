package com.bustracker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_time_row_history")
public class TimeRowHistory {

	@Id
	@JsonProperty(value = "id", index = 0)
	private String id;

	@JsonProperty(value = "facilityId", index = 1)
	private String facilityId;

	@JsonProperty(value = "timetableId", index = 2)
	private String timetableId;

	@JsonProperty(value = "order", index = 3)
	private int order;

	@JsonProperty(value = "startTime", index = 4)
	private String startTime;

	@JsonProperty(value = "startTimeMillis", index = 5)
	private long startTimeMillis;

	@JsonProperty(value = "endTime", index = 6)
	private String endTIme;

	@JsonProperty(value = "endTimeMillis", index = 7)
	private long endTimeMillis;

	@JsonProperty(value = "timeList", index = 8)
	private List<Time> timeList;

}
