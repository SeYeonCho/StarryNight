# FE

## FE 기술스택

- React, Redux
- Next.js
- typescript
- CRA (create react app)
  - 초보가 쓰기 쉽게 해주는 툴. 스켈레톤코드 같은것.
- react-spring
  - 애니메이션 만들어주는 애 (상호작용이 있는 데이터 변경 애니메이션같은것만 이거로 사용. 나머지 기본 UI/UX는 scss, css를 사용)
- css in js / styled-component
  - css 내부에 class를 사용해서 문법을 작성하면 다른 css 파일에서도 참조할 수 있게 도와주는 애
- **스타일 프레임워크** : MATERIAL-UI
- **비동기처리** : axios
- **패키지 매니저** : yarn
- **테스트**
  - jest : 함수 단위 테스트
  - storybook : 컴포넌트 테스트
  - cypress : 시나리오 테스트




## 프로젝트 빌딩

```bash
yarn create next-app --typescript

yarn add redux react-redux next-redux-wrapper
(상태관리도구)(리액트-리덕스)(뭐더라 마지막은 호환??)
	
yarn add --dev redux-devtools-extension

yarn add react-spring
	
yarn add styled-components		// scss

yarn add @material-ui/core

yarn add axios

npm install --save-dev jest babel-jest @testing-library/react @testing-library/jest-dom identity-obj-proxy react-test-renderer
(요게 jest)

npm install --save-dev cypress

yarn add --dev typescript @types/react @types/node

npx -p @storybook/cli sb init --type react
```

bash에

`--dev` <- 프로덕션 레벨에서 쓴다는 말

`--dev`가 없으면 개발단계에서만 쓴다는 말

---

https://ingg.dev/eslint/ -> eslint, prettier 설정