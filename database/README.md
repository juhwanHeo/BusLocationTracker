# BusLocationTrackServer
## Database : PostgreSQL
### docker ( [참고](https://judo0179.tistory.com/96) )


```
  # 모든 컨테이너를 종료하고 삭제한다. 
  $ docker stop $(docker ps -a -q)
  $ docker rm $(docker ps -a -q)
  
  # 데이터를 저장할 볼륨을 생성한다. 
  $ docker volume create pgdata 
  pgdata
  
  # DB 시작
  $ docker run -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=1q2w3e4r -d -v pgdata:/var/lib/postgresql/data postgres
```

`docker` 에서 PostgreSQL 사용시 컨테이너에  
데이터를 변경했을 때 컨테이너를 `종료(docker stop postgres)`할 경우 해당 컨테이너 내부에 있는 데이터는 남아 있지만  
컨테이너 자체를 `삭제(docker rm postgres)`할 경우 내부 데이터가 남아 있지 않아 처음부터 다시 데이터를 생성해야한다.  
따라서 컨테이너 데이터를 종속시킬 `컨테이너 볼륨`이나 `로컬`을 만들어야 하며, `컨테이너 볼륨을 만들어 진행 했다.`

*****
