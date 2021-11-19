# Git Rebase

## Merge vs Rebase

- Merge는 브랜치를 통합하는 것
- Rebase는 브랜치의 base를 옮기는 것

따라서, Merge or Rebase가 아니라 Merge or ( Merge & Rebase ) 가 있다고 봐야합니다.



- Rebase의 예시

  ![img](https://blog.kakaocdn.net/dn/cofZo0/btqBkOJybm6/RshCv0OqsydpuNCRPcIxu1/img.png)





## 소스트리 기준 Rebase Merge 방법

### git Rebase

1.   Rebase를 할 대상 브랜치로 체크아웃 

   ![img](https://blog.kakaocdn.net/dn/x1cpA/btqCGQm8EP2/z2VInv2HKkQ0oUxguJBTvk/img.png)

2.  브랜치를 떼서 붙이고 싶은 부모 커밋에 우클릭 - 재배치 선택

   ![img](https://blog.kakaocdn.net/dn/n1e0N/btqCKgrzQek/XtnttXIr7L1abuNJnFM8Z1/img.png)



### Rebase 가 끝나면 이제  Merge 합니다

(Merge Request를 하시는 분들은 이 단계에서 gitlab 가셔서 Merge Request 하시면 됩니다.)

1. 병합하려는 브랜치의 최신 커밋으로 체크아웃

   1-1. 빠른 병합이 가능해도 새 커밋 생성 클릭(이 옵션이 없으면 새로운 merge 커밋이 발생하지 않습니다.)

   1-2. 이후 merge

   ![img](https://blog.kakaocdn.net/dn/bjrAnK/btqCHxnipmc/JjxYV9p1aHULLsWxqLna41/img.png)



### 주의점

- Rebase는 새로 발생한 feature 브랜치 / Merge는 기존의 브랜치(우리의 경우에는 develop이 됩니다.)에 체크아웃한 상태에서 진행해야합니다.