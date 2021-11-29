# Server : Spring Boot ( Java )
# Station ( `/api/stations/` )
## Request: `GET`
### Ex) `get: /api/stations/`
- PathVariable: `station_no`
- get: /api/stations/`{station_no}`
## Response
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

---
## Request: `POST`
### Ex) `post: /api/stations/`
```json
{
  "station_nm" : "아파트",
  "lat" : 37.660935,
  "lon" : 127.32249
}
``` 
## Response
```json
{
  "code": 200,
  "msg": "ok",
  "data": {
    "station_nm": "아파트",
    "lat": 37.660935,
    "lon": 127.32249
  }
}
```