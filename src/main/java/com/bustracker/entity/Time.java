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

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "c_time")
public class Time {

    @Id
    @JsonProperty(value = "id", index = 0)
    private String id;

    @JsonProperty(value = "facilityId", index = 1)
    private String facilityId;

    @JsonProperty(value = "timeRowId", index = 2)
    private String timeRowId;

    @JsonProperty(value = "order", index = 3)
    private int order;

    @JsonProperty(value = "time", index = 4)
    private String time;

    @JsonProperty(value = "stationId", index = 5)
    private String stationId;

    @Transient
    @JsonProperty(value = "station", index = 6)
    private Station station;

    @Transient
    public TimeLog convertLog() {
        return TimeLog.builder()
                .facilityId(facilityId)
                .order(order)
                .time(time)
                .stationId(stationId)
                .build();
    }
}
