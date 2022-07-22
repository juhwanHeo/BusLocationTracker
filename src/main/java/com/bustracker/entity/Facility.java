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
@Document(collection = "c_facility")
public class Facility {

	@Id
	@JsonProperty(value = "id", index = 1)
	private String id;

	@JsonProperty(value = "facilityName", index = 2)
	private String facilityName;

	@JsonProperty(value = "facilityNameEng", index = 2)
	private String facilityNameEng;

	@JsonProperty(value = "contactNumber", index = 3)
	private String contactNumber;

	@JsonProperty(value = "email", index = 4)
	private String email;

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "inputDate", index = 5)
	private LocalDateTime inputDate;

}
