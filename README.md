## Drones

### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Abilities
- registering a drone
- loading a drone with medication items
- checking loaded medication items for a given drone
- checking available drones for loading
- check drone battery level for a given drone
- change drone state for different stage of delivery

You can use Postman client for communication with application, please review ./postman file

### Usage
You can use postman collection for that
1. Register new drone with POST /drone/register
2. Check drone states with GET /drone/{{id}}, GET /drone/battery/{{id}}
3. Review available drones with GET /drone/available
4. Load drone with PUT /delivery/load/{{id}}
5. Send drone with PUT /delivery/send/{{id}}
6. Mark drone as delivered with PUT /delivery/delivered/{{id}}
7. Return drone with PUT /delivery/returning/{{id}}
8. Mark drone as returned with PUT /delivery/returned/{{id}}

### Requirements
- Docker
- Java 17, Maven (for local running )

### Running
#### Docker
```shell
docker build -t dh -f deployment/dockerfile --build-arg DATABASE_USERNAME=u --build-arg DATABASE_PASSWORD=p .
docker run --name dh -p 8080:8080 -d dh 
```

#### Local
```shell
mvn clean compile exec:java -Dexec.mainClass="com.az.dronehub.DroneHubApplication"
```
or
```shell
mvn clean install
java -jar ./target/DroneHub-0.0.1-SNAPSHOT.jar
```
