# Bus_Log ( `/api/bus-logs/` )
## Request Method: `GET`
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
