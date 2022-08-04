-- bus_log
CREATE TABLE bus_log (
	seq_no int4 NOT NULL,
	lat float8 NOT NULL,
	lon float8 NOT NULL,
	accuracy float8 NOT NULL,
	arrivaltime timestamp(0) NOT NULL
);


CREATE UNIQUE INDEX XPK_BUS_LOG ON bus_log 
(
	seq_no
);

-- station
CREATE TABLE station (
	station_no int4 NOT NULL,
	station_nm varchar(300) NOT null,
	lat float8 NOT NULL,
	lon float8 NOT NULL
);

CREATE UNIQUE INDEX XPK_STATION ON station 
(
	station_no
);