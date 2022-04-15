# Bus_Log ( `/api/bus-logs/` )
## Request Method: `GET`
- PathVariable: `seq_no`
##Ex) `get: /api/bus-logs/`
- get: /api/bus-logs/
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
##Ex) `get: /api/bus-logs/{seq_no}`
- get: /api/bus-logs/`1`
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
        }
    ]
}
