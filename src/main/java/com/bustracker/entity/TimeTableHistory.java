package com.bustracker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_time_table")
public class TimeTableHistory {

	@Id
	private String id;

	@JsonProperty("timeRowIds")
	private List<String> timeRowIds;

	@CreatedDate
	@JsonProperty("inputDate")
	private String inputDate;

	@LastModifiedDate
	@JsonProperty("updateDate")
	private String updateDate;

}
