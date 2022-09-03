package com.bustracker.entity;

import com.bustracker.enums.DayOffStatus;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document("c_day_off")
public class DayOff {

    @Id
    @JsonProperty(value = "id", index = 1)
    private String id;

    @JsonProperty(value = "facilityId", index = 2)
    private String facilityId;

    @JsonProperty(value = "dayOffStatus", index = 3)
    private DayOffStatus dayOffStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    @JsonProperty(value = "startDate", index = 4)
    private String startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    @JsonProperty(value = "endDate", index = 5)
    private String endDate;

    @JsonProperty(value = "message", index = 6)
    private String message;

    @CreatedDate
    @JsonProperty(value = "inputDate", index = 999)
    private LocalDateTime inputDate;

}
