package com.bustracker.controller;

import com.bustracker.entity.Bus;
import com.bustracker.entity.Facility;
import com.bustracker.repository.FacilityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BusControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private FacilityRepository facilityRepository;

    @Test
    public void saveTest() throws Exception {
        Facility facility = facilityRepository.findAll().get(0);
        Bus bus = Bus.builder()
                .facilityId(facility.getId())
                .latitude(37.660935) //37.660935, 127.32249
                .longitude(127.32249)
                .accuracy(90.0)
                .build();

        String value = mapper.writeValueAsString(bus);

        log.info("value: {}", value);
        RequestBuilder reqBuilder = MockMvcRequestBuilders
                .post("/api/bus-logs")
                .content(value)
                .contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mvc.perform(reqBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultStr = result.getResponse().getContentAsString();
        log.info("{}", resultStr);
    }

    @Test
    public void saveTest2() throws Exception {
        Facility facility = facilityRepository.findAll().get(0);
        Bus bus = Bus.builder()
                .facilityId(facility.getId())
                .latitude(37.660900) //37.660935, 127.32249
                .longitude(127.32200)
                .accuracy(90.0)
                .build();

        String value = mapper.writeValueAsString(bus);

        log.info("value: {}", value);
        RequestBuilder reqBuilder = MockMvcRequestBuilders
                .post("/api/bus-logs")
                .content(value)
                .contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mvc.perform(reqBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultStr = result.getResponse().getContentAsString();
        log.info("{}", resultStr);
    }


    @Test
    public void findLastTest() throws Exception {
        RequestBuilder reqBuilder = MockMvcRequestBuilders
                .get("/api/bus-logs")
                .contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mvc.perform(reqBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultStr = result.getResponse().getContentAsString();
        log.info("{}", resultStr);
    }

    @Test
    public void runningTest() throws Exception {

        RequestBuilder reqBuilder = MockMvcRequestBuilders
                .get("/api/bus-logs/running")
                .contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mvc.perform(reqBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultStr = result.getResponse().getContentAsString();
        log.info("{}", resultStr);
    }
}