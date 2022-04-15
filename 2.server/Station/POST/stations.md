# Stations ( `/api/stations/` )
## Request Method: `POST`
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
  "code": 201,
  "msg": "ok",
  "data": {
    "station_nm": "아파트",
    "lat": 37.660935,
    "lon": 127.32249
  }
}
```
