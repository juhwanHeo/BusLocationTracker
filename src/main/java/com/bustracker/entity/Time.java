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

    @JsonProperty(value = "timeRowId", index = 1)
    private String timeRowId;

    @JsonProperty(value = "order", index = 2)
    private int order;

    @JsonProperty(value = "stationId", index = 3)
    private String stationId;

    @JsonProperty(value = "time", index = 4)
    private String time;

    @Transient
    @JsonProperty(value = "station", index = 5)
    private Station station;

}
