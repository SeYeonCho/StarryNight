## 포팅 메뉴얼 

### Requirements

- Jenkins에서 배포를 진행 

### Jenkins 의존성 설정 

| 종류  | 버전  |   |   |   |
|---|---|---|---|---|
| Gradle  | 7.1.1  |   |   |   |
| NodeJS  | 16.10.0  |   |   |   |
| Docker  | 20.10.8  |   |   |   |

### BackEnd 의존성 설정 

| 종류  | 버전  |   |   |   |
|---|---|---|---|---|
| Spring Boot Framework  | 2.5.5  |   |   |   |
| Java openjdk | 11 |   |   |   |
| Gradle | 7.2 |   |   |   |
| confluent(Kafka, Kafka Streams, Kafka Connect, Hadoop 3.3.1) | 1.40.1 (java 8) |   |   |   |
| python | 3.8.10 |   |   |   |

### 배포 시 특이사항 

- container 3개를 미리 정리하고 나서, 빌드를 수행해야 함 

