package com.bustracker.dao;

import com.bustracker.dto.Bus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BusDAO {
    List<Bus> get(Map<String, Object> map);

}
