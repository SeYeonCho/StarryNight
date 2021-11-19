## HDFS

- HDFS는 Java로 작성된 구글의 GFS 기반의 파일 시스템

**HDFS의 file 저장 방식**

- File은 block 단위로 분할됩니다.(block은 기본 64MB 또는 128MB)
- 데이터가 로드 될 때 여러 머신에 저장된다.
  1. 같은 file의 다른 block들은 서로 다른 머신에 저장
  2. 이를 통해 MapReduce 처리 가능
- Block들은 여러 머신에 복제되어 Data node에 저장
  1. replication은 각 3개
- Name node로 불리는 master node는 어떤 block들이 file을 구성하고 있고, 어느 위치에 저장되어 있는지에 대한 정보를 meta data로 관리
- HDFS 사용법에서 hadoop fs -put명령어를 입력하면 해당 데이터가 3개의 PC로 분산되어 자동 저장될 것입니다. 이로 인해 시스템 운영중 fault가 생겨도 잘 해결할 수 있게 됩니다. 이때 네임 노드는 metadata를 저장하게 됩니다.
- metadata : 데이터에 대한 데이터를 칭한다. 여기서는 파일 블록 정보 (데이터가 어떤 컴퓨터에 있는지)에 대한 데이터가 된다.
- Block들은 Hadoop configuration에 설정된 디렉터리를 통해 저장된다. namenode의 metadata를 사용하지 않으면, HDFS에 접근할 수 있는 방법이 존재하지 않는다.

**클라이언트 애플리케이션이 file에 접근하는 경우**

- namenode와 통신하여 file을 구성하고 있는 block들의 정보와 datanode의 block의 위치 정보를 제공받습니다. 이후 데이터를 읽기 위해 datanode와 직접 통신을 하게 된다. 결과적으로 읽기 작업만 일어나는 namenode는 bottleneck이 되지 않는다.

**HDFS 접근 방법**

- HDFS는 shell 또는 JAVA API 그리고 EcoSystem 프로젝트를 사용해 접근할 수 있다.
- 대표적인 ecoSystem은 Flume(network source로 부터 데이터 수집), Sqoop(HDFS와 RDBMS 사이의 데이터 전송), Hue(Web 기반의 interface UI로 browse, upload, download, file view 등이 가능) 등이 존재합니다.

**HDFS 네임노드 가용성**

- namenode daemon은 반드시 항상 실행되어 있어야한다. 그래야 클러스터에 접근이 가능하다.
- 따라서 고가용성 모드 2개의 네임노드(Active , Standby)를 구성하기도 한다.
- 일반적인 Classic mode에서는 1개의 네임노드와 또다른 "helper" 노드는 SecondaryNameNode로 구성됩니다. 이 때, helper 노드는 백업 목적이 아니며, 네임노드를 복수할 수 있는 정보를 가지고 있는 PC 입니다. 따라서 장애 발생시 NameNode를 대신하는 것이 불가능 합니다.

**Hadoop의 구성요소**

- Client : namenode를 통해 정보를 받고 이후 직접적으로 data node와 통신합니다.
- Master node : 물리적으로 Master Node 역할 (Job Tracker , Name node)을 하는 노드로서 , slave node에 대한 정보와 실행을 할 Task에 대한 관리 담당
- Slave node : 물리적으로 Slave Node역할 (Data node , Task node)을 하는 노드로서 , 실제로 데이터를 분산되어 가지고 있으며 client에서 요청이 오면 데이터를 전달하는 역할 및 담당 task를 수행하는 역할을 한다.

**Data Anlytics 관점**

- **Job Tracker:** 노드에 Task를 할당하는 역할과 모든 Task를 모니터링하고 실패할 경우 Task를 재실행하는 역할.
- **Task Tracker:** Task는 Map Task와 Reduce Task로 나눠질 수 있으며, Task가 위치한 HDFS의 데이터를 사용하여 MapReduce를 수행.

**Data Storage 관점**

- **Name Node:** HDFS의 파일 및 디렉터리에 대한 메타 데이터를 유지. 클라이언트로 부터 데이터 위치 요청이 오면 전달, 장애 손상시 Secondary Node로 대체
- **Data Node:** 데이터를 HDFS의 Block 단위로 구성. Fault Recovery를 위해 default로 3 copy를 유지, Heartbeat를 통하여 지속적으로 파일 위치 전달.

## 하둡의 기본 개념

### 하둡이란?

- 대용향 자료를 처리할 수 있는 컴퓨터 클러스터에서 동작하는 분산 응용 프로그램을 지원하는 오픈소스 자바 프레임워크
- 분산 데이터 처리 기술 : 큰 용량의 단일 서버보다 여러 서버의 작은 용량을 묶은 컴퓨터 클러스터가 가성비가 더 좋다.

### 하둡의 목적

- 매우 큰 데이터를 저장할 수 있다. - HDFS
- 그 데이터를 이용해서 연산을 수행할 수 있다 - 맵리듀스

### 하트비트

- 데이터 노드는 네임노드에게 하트비트를 3초마다 보낸다. 하트비트에는 디스크 가용 공간정보, 데이터이동, 적재량 등의 정보가 들어있다.
- 핸드쉐이킹에 사용된다. 10초 이상 받지 못하면 사용하지 못한다고 인식

### 맵 리듀스

- 맵 리듀스 모델을 사용해서 애플리케이션을 작성할 경우, 하둡이 확장성과 관련된 모든 일을 알아서 처리
- Map은 데이터 원천을 Key와 Value의 형태로 연관성이 있는 데이터 분류로 묶는 작업
- Reduce는 Map 작업 중 중복데이터를 제거하고 원하는 데이터를 추출하는 작업
- 셔플링 : 처리된 결과를 여러 머신에 이리저리 재배치하는 모습