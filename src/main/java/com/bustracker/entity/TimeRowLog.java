package com.bustracker.entity;

import com.bustracker.enums.TimeRowStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_time_row_log")
public class TimeRowLog {

	@Id
	@JsonProperty(value = "id", index = 0)
	private String id;

	@JsonProperty(value = "facilityId", index = 1)
	private String facilityId;

	@JsonProperty(value = "timetableId", index = 2)
	private String timetableId;

	@JsonProperty(value = "runningDate", index = 3)
	private String runningDate;

	@JsonProperty(value = "order", index = 4)
	private int order;

	@JsonProperty(value = "startTime", index = 5)
	private String startTime;

	@JsonProperty(value = "startTimeMillis", index = 6)
	private long startTimeMillis;

	@JsonProperty(value = "endTime", index = 7)
	private String endTIme;

	@JsonProperty(value = "endTimeMillis", index = 8)
	private long endTimeMillis;

	@JsonProperty(value = "status", index = 9)
	private TimeRowStatus status;

	@Transient
	@JsonProperty(value = "timeList", index = 10)
	private List<Time> timeList;

	@CreatedDate
	@JsonProperty(value = "inputDate", index = 11)
	private LocalDateTime inputDate;

	@LastModifiedDate
	@JsonProperty(value = "updateDate", index = 12)
	private LocalDateTime updateDate;

}
