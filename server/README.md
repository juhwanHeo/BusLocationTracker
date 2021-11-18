# BusLocationTrackServer
## Server : Spring Boot ( Java )

`get: /api/bus-logs/`  
PathVariable: `seq_no`
요청 EX)
```json
{
    "code": 200,
    "msg": "ok",
    "data": [
        {
            "seq_no": 1,
            "lat": "37.886273",
            "lon": "127.73564",
            "accuracy": 99.0,
            "arrivaltime": "2021-11-18"
        },
        {
            "..."
        }
    ]
}
```

*****
`get: /api/stations/`  
요청 EX)
PathVariable: `station_no`
```json
{
    "code": 200,
    "msg": "ok",
    "data": [
        {
            "station_no": 1,
            "station_nm": "서희아파트",
            "lat": "37.660935",
            "lon": "127.32249"
        },
        {
            "station_no": 2,
            "station_nm": "마석역 1번 출구",
            "lat": "37.652059",
            "lon": "127.311561"
        },
        {
            "station_no": 3,
            "station_nm": "마석역 2번출구",
            "lat": "37.652995",
            "lon": "127.311207"
        },
        {
            "..."
        }
        
    ]
}

```

*****
