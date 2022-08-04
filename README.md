# BusLocationTracker
*****

## Spring Boot
- 2.7.0
- Java 1.8

## Database
- MongoDB
- docker-compose.yml
    ```yaml
    version: "3"
    services:
      mongodb:
        image: mongo
        restart: always  # 컨테이너 실행 시 재시작
        container_name: mongodb # 컨테이너 이름 설정
        ports:
          - "9017:27017"
        environment:
          - TZ=Asia/Seoul
          # MongoDB 계정 및 패스워드 설정 옵션
          - MONGO_INITDB_ROOT_USERNAME=mongo
          - MONGO_INITDB_ROOT_PASSWORD=mongo123
        volumes:
          - /Users/user/juhwan/docker-compose/mongodb/data/db:/data/db
          - /Users/user/juhwan/docker-compose/mongodb/timezone:/etc/timezone
      mongo-express:
        image: mongo-express
        restart: always
        ports:
          - "9018:8081"
        environment:
          - ME_CONFIG_MONGODB_URL=mongodb://mongo:mongo123@host.docker.internal:9017/
          - TZ="Asia/Seoul"
        depends_on:
          - mongodb
    ```

## License
- MIT License
