![Asset_6](/README.asset/Asset_6.png)

SSAFY 2학기 특화 프로젝트 - 별나린밤

## Quick Links 

<!-- ------ -->



- [PJT명](#프로젝트명)

- [프로젝트 설명](#프로젝트-설명)

- [기술스택](#기술-스택)

- [WIKI 문서](#wiki)

- [Architecture](#architecture)


<!-- ----- -->

# 시연영상
\>\>\> [Click here!!](https://drive.google.com/file/d/10fGj2RbCLhnS47UzKYTzb0A5VhDikS1b/view?usp=sharing) <<<

<!-- ----- -->

# 프로젝트 설명

<!-- ------ -->


- Youtube, Naver, Twitter 등의 SNS와 플랫폼들의 Open API를 통해 정보를 수집하고, 그것을 바탕으로 키워드에 대한 분석과 평가를 제공해주는 프로젝트


- 키워드에 대한 분석
    - 각 플랫폼에서 키워드가 가장 빠르게 언급되기 시작한 플랫폼과 현재 가장 많은 언급량을 가지고 있는 플랫폼 등을 비교하며 어떠한 플랫폼에서 키워드를 검색하는 것이 좋을지 추천
그 외에 키워드가 월간 검색되고 있는 숫자, 그 키워드로 발행되고 있는 컨텐츠의 발행량 등에 대한 정보를 제공
내부 분석을 통한 키워드의 컨텐츠 포화지수/트렌드 지수를 파악하여 제공



- 차별점
    - 저희가 관련 기타 상용 서비스들을 사용하면서 느꼈던 가장 큰 불편은 많은 좋은 정보를 제공해주지만, 그것을 쉽게 이해할 수 없고 또한 너무 많은 정보가 주어지기 때문에 오히려 얻어갈 수 있는 정보가 적다는 점이었습니다.
전문성 있는 많은 양의 데이터에 대한 니즈도 있겠지만, 보기 쉽게 정제된 적은 양의 데이터에 대한 니즈도 있을 것이라는 판단 하에 프로젝트의 방향성을 정하게 되었습니다.



# 기술 스택

---

![image](/README.asset/image.png)
특이사항: Apache Kafka가 아닌 Confluence Kafka를 사용하였음.

---
#### JAVA / SpringBoot / Redis / Gradle / Kafka / hadoop 

* Kafka를 통해 데이터 파이프라인 구축
* Hadoop을 통해 방대한 데이터를 백업

#### React.js / Next.js / Redux / Scss / SWR/ Typescript

* Next를 통해 SSR을 도입하여 서버에서 사용자에게 보여줄 페이지를 모두 구성
* Typescript를 통해 type의 장점을 사용하여 구현
* SWR를 통해 API 데이터를 효츌적으로 처리

#### Jenkins

* release branch에 대한 Push Event시 자동 Test / Build 진행(CI)
* release branch push시 원격 서버의 배포 스크립트 실행(CD)

#### NGiNX

* 특이사항 없음

<!-- # WIKI

------

프로젝트의 모든 정보는 [WIKI](https://lab.ssafy.com/s05-bigdata-dist/S05P21B103/-/wikis/Home)를 통해 참고하실 수 있습니다.
 -->


# Architecture

---

![Image_Pasted_at_2021-10-6_20-58](/README.asset/Image_Pasted_at_2021-10-6_20-58.png)



# Writer

- 0533030 임호정
- 0534451 김주현
- 0534698 조세연
- 0534587 이산하
- 0534325 최승재

# Meeting Logs

- [Notion](https://feline-ceramic-f5b.notion.site/021d6df882794c8eb1259bd7dbd1c504)

---

## 팀원 소개

| Name     | 김주현 | 이산하 | 임호정 | 조세연 | 최승재 |
| -------- | ------ | ------ | ------ | ------ | ------ |
| Position | BE | FE/UI,UX | FE/UI,UX |    BE  | BE |
| GitLab   | @juhyun7955 | @sana0803 | @39ghwjd |  @cnsy831 |@nodayst|

