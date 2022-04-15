CREATE TABLE bus_log (
	bus_log_id int4 not null primary key,
	lat float8 not null,
	lon float8 not null,
	accuracy float8 not null,
	arrivaltime timestamp(0) not null
);

INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (1,37.660935, 127.32249,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (2,37.66161630296504, 127.32222359195114,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (3,37.66161630296504, 127.32170934709713,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (4,37.660487860564, 127.32149282294804,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (5,37.65922818309797, 127.32076069710565,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (6,37.65814255831361, 127.31963296715716,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (7,37.657371183701116, 127.31885708895635,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (8,37.655735557300055, 127.31906459126206,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (9,37.65393624222625, 127.31754987125257,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (10,37.653052842276765, 127.31576888444233,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (11,37.652747047549006, 127.31448142414915,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (12,37.652050510412785, 127.31433122044828,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (13,37.65198255522043, 127.31207816493526,99.0,'2021-12-03 15:33:48');
INSERT INTO bus_log (bus_log_id,lat,lon,accuracy,arrivaltime) VALUES (14,37.652059,127.311561,99.0,'2021-12-03 15:33:48');

---
CREATE TABLE station (
	station_id int4 NOT null primary key,
	station_nm varchar(300) NOT NULL,
	lat float8 NOT NULL,
	lon float8 NOT NULL
);

INSERT INTO station (station_id,station_nm,lat,lon) VALUES (1,'서희아파트',37.660935,127.32249);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (2,'마석역 1번 출구',37.652059,127.311561);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (3,'마석역 2번출구',37.652995,127.311207);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (4,'다리및',37.652982,127.306714);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (5,'심석중고등',37.656376,127.305985);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (6,'송라초중',37.655009,127.302257);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (7,'화도읍사무소',37.657519,127.301633);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (8,'마석초중 정문',37.648726,127.305006);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (9,'마석초중 후문',37.649853,127.30493);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (10,'마석고',37.644891,127.300135);
INSERT INTO station (station_id,station_nm,lat,lon) VALUES (11,'서희아파트',37.660935,127.32249);

---
CREATE TABLE time_table (
	time_table_id int4 not null primary key,
	use_yn char(1) not null,
	input_id varchar,
	input_date timestamp(0) not null,
	update_id varchar,
	update_date timestamp(0) not null
);

INSERT INTO time_table (time_table_id, use_yn, input_id, input_date, update_id, update_date) 
VALUES (1, 'Y', 'test1','2022-04-15 15:33:48', 'test1','2022-04-15 15:33:48');

---
CREATE TABLE "time" (
	time_id int4 not null,
	station_id int4 not null, 
	time_table_id int4 not null,
	start_time char(5) not null,
	input_id varchar,
	input_date timestamp(0) not null,
	update_id varchar,
	update_date timestamp(0) not null,
	CONSTRAINT fk_time_station 
	FOREIGN KEY(station_id) 
	REFERENCES station(station_id) 
	ON DELETE CASCADE ON UPDATE cascade,
	CONSTRAINT fk_time_timetable
	FOREIGN KEY(time_table_id) 
	REFERENCES time_table(time_table_id) 
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE UNIQUE INDEX XPK_TIME ON "time"
(
	time_id, station_id, time_table_id
);

ALTER TABLE "time" *
 ADD CONSTRAINT TIME_PK PRIMARY KEY 
 USING INDEX XPK_TIME;
commit;

INSERT INTO "time" (time_id, station_id ,time_table_id, start_time, input_id, input_date, update_id, update_date) 
VALUES (1, 1, 1, '06:40', 'test1','2022-04-15 15:33:48', 'test1', '2022-04-15 15:33:48');
INSERT INTO "time" (time_id, station_id ,time_table_id, start_time, input_id,input_date, update_id, update_date) 
VALUES (1, 2, 1, '06:45', 'test1','2022-04-15 15:33:48', 'test1', '2022-04-15 15:33:48');
INSERT INTO "time" (time_id ,station_id ,time_table_id, start_time, input_id, input_date, update_id, update_date) 
VALUES (1, 3, 1, '06:50', 'test1','2022-04-15 15:33:48', 'test1', '2022-04-15 15:33:48');
INSERT INTO "time" (time_id, station_id ,time_table_id, start_time, input_id, input_date, update_id, update_date) 
VALUES (1, 11, 1, '06:55', 'test1','2022-04-15 15:33:48', 'test1', '2022-04-15 15:33:48');
commit;


