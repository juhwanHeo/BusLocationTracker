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
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_time_table")
public class TimeTable {

	@Id
	@JsonProperty(value = "id", index = 1)
	private String id;

//	@JsonProperty("timeRowIds")
//	private List<String> timeRowIds;

	@CreatedDate
	@JsonProperty(value = "inputDate", index = 2)
	private String inputDate;

	@LastModifiedDate
	@JsonProperty(value = "updateDate", index = 3)
	private String updateDate;

	@Transient
	@JsonProperty(value = "timeRowList", index = 4)
	private List<TimeRow> timeRowList;

}
