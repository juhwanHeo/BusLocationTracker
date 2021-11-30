# Server : Spring Boot ( Java )
## Bus_Log ( `/api/bus-logs/` )
## Request: `GET`
###Ex) `get: /api/bus-logs/`
- PathVariable: `seq_no`
- get: /api/bus-logs/`{seq_no}`
## Response Ex)
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
---
###Ex) `get: /api/bus-logs/current`
- get: /api/bus-logs/`current`
## Response Ex)
```json
{
    "code": 200,
    "msg": "ok",
    "data": [
        {
            "seq_no": 4,
            "lat": "37.886273",
            "lon": "127.73564",
            "accuracy": 88.123554,
            "arrivaltime": "2021-11-22 16:10:45"
        }
    ]
}
```
---
---
## Request: `POST`
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
  "code": 200,
  "msg": "ok",
  "data": {
    "lat": 37.660935,
    "lon": 127.32249,
    "accuracy": 88.123554,
    "arrivaltime": "2021-11-22 16:10:45"
  }
}
```
