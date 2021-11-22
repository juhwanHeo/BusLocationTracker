package com.bustracker.dao;

import com.bustracker.dto.Station;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StationDAO {
    List<Station> get(Map<String, Object> map);
    void add(Station.Request station);

}
