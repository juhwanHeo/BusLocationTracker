package com.bustracker.entity;

import com.bustracker.dto.TimetableDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_time_table")
public class TimeTable {

	@Id
	@JsonProperty(value = "id", index = 0)
	private String id;

	@JsonProperty(value = "facilityId", index = 1)
	private String facilityId;

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "inputDate", index = 2)
	private LocalDateTime inputDate;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "updateDate", index = 3)
	private LocalDateTime updateDate;

	@Transient
	@JsonProperty(value = "timeRowList", index = 4)
	private List<TimeRow> timeRowList;


	@Transient
	public List<TimetableDTO> convertListDTO() {
		List<TimetableDTO> list = new ArrayList<>();

		for (TimeRow timeRow : timeRowList) {
			TimetableDTO timetableDTO = new TimetableDTO();
			timetableDTO.setOrder(timeRow.getOrder());
			for (Time time : timeRow.getTimeList()) {
				switch (time.getStation().getStationCode()) {
					case "S001":
						if (!StringUtils.hasText(timetableDTO.getAptStart())) timetableDTO.setAptStart(time.getTime());
						else timetableDTO.setAptEnd(time.getTime());
						break;
					case "S002":
						timetableDTO.setMaseokStationNo1(time.getTime());
						break;
					case "S003":
						timetableDTO.setMaseokStationNo2(time.getTime());
						break;
					case "S004":
						timetableDTO.setSimseokJungGo(time.getTime());
						break;
					case "S005":
						timetableDTO.setSongraChoJung(time.getTime());
						break;
					case "S006":
						timetableDTO.setMaseokChoJung(time.getTime());
						break;
					case "S007":
						timetableDTO.setMaseokHighSchool(time.getTime());
						break;
				}
			}

			validationDTO(timetableDTO);
			list.add(timetableDTO);
		}
		return list;
	}

	@Transient
	private void validationDTO(TimetableDTO dto) {
		if (!StringUtils.hasText(dto.getAptStart())) dto.setAptStart("-");
		if (!StringUtils.hasText(dto.getMaseokStationNo1())) dto.setMaseokStationNo1("-");
		if (!StringUtils.hasText(dto.getMaseokStationNo2())) dto.setMaseokStationNo2("-");
		if (!StringUtils.hasText(dto.getSimseokJungGo())) dto.setSimseokJungGo("-");
		if (!StringUtils.hasText(dto.getSongraChoJung())) dto.setSongraChoJung("-");
		if (!StringUtils.hasText(dto.getMaseokChoJung())) dto.setMaseokChoJung("-");
		if (!StringUtils.hasText(dto.getMaseokHighSchool())) dto.setMaseokHighSchool("-");
	}

}
