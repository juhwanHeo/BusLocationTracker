package com.bustracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TimetableDTO {

    private int order;
    private String aptStart;
    private String maseokStationNo1;
    private String simseokJungGo;
    private String songraChoJung;
    private String maseokChoJung;
    private String maseokHighSchool;
    private String maseokStationNo2;
    private String aptEnd;

}
