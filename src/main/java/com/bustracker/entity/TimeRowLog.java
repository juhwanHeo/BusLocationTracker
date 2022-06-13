package com.bustracker.entity;

import com.bustracker.status.TimeRowStatus;
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

import java.time.LocalDate;
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

	@JsonProperty(value = "timetableId", index = 1)
	private String timetableId;

	@JsonProperty(value = "today", index = 2)
	private String today;

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

	@JsonProperty(value = "status", index = 8)
	private TimeRowStatus status;

	@Transient
	@JsonProperty(value = "timeList", index = 9)
	private List<Time> timeList;

	@CreatedDate
	@JsonProperty(value = "inputDate", index = 10)
	private LocalDateTime inputDate;

	@LastModifiedDate
	@JsonProperty(value = "updateDate", index = 11)
	private LocalDateTime updateDate;

}
