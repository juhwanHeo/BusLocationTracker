package com.bustracker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_time_row")
public class TimeRow {

	@Id
	@JsonProperty(value = "id", index = 0)
	private String id;

	@JsonProperty(value = "timetableId", index = 1)
	private String timetableId;

	@JsonProperty(value = "order", index = 2)
	private int order;

	@JsonProperty(value = "startTime", index = 3)
	private String startTime;

	@JsonProperty(value = "startTimeMillis", index = 4)
	private long startTimeMillis;

	@JsonProperty(value = "endTime", index = 5)
	private String endTIme;

	@JsonProperty(value = "endTimeMillis", index = 6)
	private long endTimeMillis;

	@Transient
	@JsonProperty(value = "timeList", index = 8)
	private List<Time> timeList;


	@Transient
	public TimeRowLog convertLog() {
		return TimeRowLog.builder()
				.timetableId(timetableId)
				.order(order)
				.today(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
				.startTime(startTime)
				.startTimeMillis(startTimeMillis)
				.endTIme(endTIme)
				.endTimeMillis(endTimeMillis)
				.timeList(timeList)
				.build();
	}
}
