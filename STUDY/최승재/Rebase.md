## Merge와 Rebase의 차이점



### Merge 



![img](https://suhwan.dev/images/git_rebase_example/git_two_branches.png)

### Rebase

"master와 dev 브랜치의 공통 조상 커밋부터 dev 브랜치까지의 모든 커밋의 base를 master브랜치의 위치로 바꾸어라" 라는 의미

master와 dev의 공통 조상 커밋인 C1부터 dev에만 있었던 c4,c5커밋들이 master 브랜치가 가리키고 있던 C3을 base로 하여 다시 적용된 것



![image](https://suhwan.dev/images/git_rebase_example/git_merging_with_git_merge.png)



# 'master'에  'issue3'를 reabse하는 과정

rebase는 보통 리모트 브랜치에 커밋을 깔끔하게 적용하고 싶을 때 사용한다. 

![img](https://backlog.com/git-tutorial/kr/img/post/stepup/capture_stepup2_8_1_1.png)

'issue3' 브랜치로 전환하여 'master' 브랜치에 rebase를 실행한다.

```
git checkout issue3

git rebase master
```



### 충돌 일어났을 때

rebase의 경우 충돌 부분을 수정한 후에 commit이 아니라 rebase 명령에 --continue옵션을 지정하여 실행한다.

만약 rebase 자체를 취소하려면 --abort옵션을 지정한다.

```
git add myfile.txt // 충돌난 파일 / 파일이 여러개인 경우 git add .

git rebase --continue // rebase 다시 진행
```



![img](https://backlog.com/git-tutorial/kr/img/post/stepup/capture_stepup2_8_1.png)

rebase만 실행한 경우에는 위의 그림처럼 'issue3'브랜치가 두 브랜치의 앞 쪽 위치가 옮겨졌을 뿐이다.

'master'브랜치는 아직 'issue3'의 변경사항을 적용되지 못한 상태이다.

'master'브랜치로 전환하여 'issue3' 브랜치의 변경 사항을 모두 병합할 차례이다.

(저희는 보통 여기서 git lab을 통해서 Merge Request를 보냈습니다.)

```
git checkout master

git merge issue3
```



![img](https://backlog.com/git-tutorial/kr/img/post/stepup/capture_stepup2_8_2.png)

### MergeRequest 주의사항

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbqSEi2%2FbtrdBqZcpEg%2F4SaYSGB7CJ5GwBNwaMXNuk%2Fimg.png)



Delete source brach when merge request is accepted 옵션을 잘 확인해주세요.

저희 저번 프로젝트 같은 경우 develop에 작업 브랜치를 rebase했을 때 리베이스한 작업 브랜치는 필요 없기때문에 해당 옵션을 체크해 원격 저장소에서 해당 브랜치를 지웠습니다.

하지만 develop에서 master나 release로 Merge Requset를 할 때 저 부분을 체크 해제하지 않아 develop을 지워버리는 경우가 있었어서 잘 확인해주시는게 좋을 것 같습니다. (사실 제가 날렸습니다.....)



