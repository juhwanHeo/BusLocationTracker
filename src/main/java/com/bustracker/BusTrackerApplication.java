package com.bustracker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class BusTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusTrackerApplication.class, args);
    }

    @Value("${server.port}")
    private int serverPort;

    @Value("${log.path}")
    private String logPath;

    @Value("${log.level}")
    private String logLevel;

    @Value("${log.jdbc.level}")
    private String logJdbcLevel;

    @Value("${log.max.history}")
    private String logMaxHistory;

    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
            log.info("------------------------------- args --------------------------------");
            log.info("args length: {}", args.length);
            log.info("args: {}", Arrays.toString(args));
            log.info("---------------------------- Server Info ----------------------------");
            log.info("Server Start Time: {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            log.info("Server Port: {}", serverPort);
            log.info("Logback Log Path: {}", logPath);
            log.info("Logback Log Level: {}", logLevel);
            log.info("Logback Log Jdbc Level: {}",  logJdbcLevel);
            log.info("Logback Log 보관일: {} 일",  logMaxHistory);
            log.info("-------------------------------- end --------------------------------");
        };
    }
}
