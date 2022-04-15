# Bus_Log ( `/api/bus-logs/` )
## Request Method: `POST`
### Ex) `post: /api/bus-logs/`
```json
{
    "lat" : 37.660935,
    "lon" : 127.32249,
    "accuracy" : 88.123554,
    "arrivaltime" : "2021-11-22 16:10:45"
}
``` 
## Response
```json
{
  "code": 201,
  "msg": "ok",
  "data": {
    "lat": 37.660935,
    "lon": 127.32249,
    "accuracy": 88.123554,
    "arrivaltime": "2021-11-22 16:10:45"
  }
}
```
