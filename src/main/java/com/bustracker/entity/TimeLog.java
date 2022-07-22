package com.bustracker.entity;

import com.bustracker.status.TimeStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_time_log")
public class TimeLog {

    @Id
    @JsonProperty(value = "id", index = 0)
    private String id;


    @JsonProperty(value = "facilityId", index = 1)
    private String facilityId;

    @JsonProperty(value = "timeRowLogId", index = 2)
    private String timeRowLogId;

    @JsonProperty(value = "order", index = 3)
    private int order;

    @JsonProperty(value = "time", index = 4)
    private String time;

    @JsonProperty(value = "stationId", index = 5)
    private String stationId;

    @Transient
    @JsonProperty(value = "station", index = 6)
    private Station station;

    @JsonProperty(value = "status", index = 7)
    private TimeStatus status;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "inputDate", index = 8)
    private LocalDateTime inputDate;

}
