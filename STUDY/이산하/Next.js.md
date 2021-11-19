# Next.js

>  Universal 리액트 어플리케이션의 서버렌더링을 쉽게 구현 할 수 있게 도와주는 간단한 프레임워크.

## 특징

- 직관적인 [페이지 기반](https://nextjs.org/docs/basic-features/pages) 라우팅 시스템( [동적 경로](https://nextjs.org/docs/routing/dynamic-routes) 지원 포함 )

- [사전 렌더링](https://nextjs.org/docs/basic-features/pages#pre-rendering) , [정적 생성](https://nextjs.org/docs/basic-features/pages#static-generation-recommended) (SSG) 및 [서버 측 렌더링](https://nextjs.org/docs/basic-features/pages#server-side-rendering) (SSR) 모두 페이지 단위로 지원
- 빠른 페이지 로드를 위한 자동 코드 분할
- 최적화된 프리페칭을 통한 [클라이언트 측 라우팅](https://nextjs.org/docs/routing/introduction#linking-between-pages)
- [내장 CSS](https://nextjs.org/docs/basic-features/built-in-css-support) 및 [Sass 지원](https://nextjs.org/docs/basic-features/built-in-css-support#sass-support) 및 모든 [CSS-in-JS](https://nextjs.org/docs/basic-features/built-in-css-support#css-in-js) 라이브러리 지원
- [Fast Refresh를](https://nextjs.org/docs/basic-features/fast-refresh) 지원하는 개발 환경
- Serverless Functions로 API 엔드포인트를 빌드하기 위한 [API 경로](https://nextjs.org/docs/api-routes/introduction)
- extendable



### 코드 스플리팅

일반적인 리액트 싱글페이지에서는 초기 렌더링 때 모든 컴포넌트를 내려받는다. 하지만 규모가 커지고, 용량이 커지면 로딩속도가 지연될 수 있는 문제점이 있다. Next는 이러한 문제점을 개선해서 필요에 따라 파일을 불러올 수 있게 여러 개의 파일을 분리하는 코드 스플리팅을 사용한다. 폴더 구조를 보면 pages 폴더 안에 각 page 즉, 라우트들이 들어가며, Components 폴더에는 React 컴포넌트들이 들어가게 된다. 예를 들어, 브라우저가 실행되고, 사용자가 접속을 하게 되면, 첫 페이지인 index page만 불러오게 되고, 그 이후에 다른 페이지로 넘어갔을 때는 해당 페이지만 불러오게 된다.

### CSR 라우팅

사용방법은 Router와 Link를 모두 import 해서 사용할 수 있다. Link에서는 href와 as props가 있는데 이 href는 해당 페이지로 이동해 주는 역할을 하고, as는 href의 URL을 조금 더 직관적으로 만들어주는 역할을 해준다. Router는 링크와 동일하게 해당 페이지로 이동해주는 역할을 하지만 개발자에게 조금 더 제어권을 넘겨줘서 쉽게 Redirect도 가능하다.

### getInitialProps()

Next의 핵심기능인 getInitialProps 함수를 통해 데이터를 가져올 수 있다. React의 ComponentDidMount 는 렌더링이 두 번 되지만, Next에서는 데이터를 미리 갖고 오기 때문에 한 번에 렌더링이 가능하다.




## 환경설정

Next.js 는 Mac, Windows, Linux 에서 동일하게 작동한다. 즉, Node.js 만 있으면 된다.

```
mkdir hello-next
cd hello-next
yarn init -y
yarn add react react-dom next
mkdir pages
```

 

package.json 파일에 다음과 같은 스크립트를 추가

```json
{
  "name": "hello-next",
  "version": "1.0.0",
  "license": "MIT",
  "scripts": {
    "dev": "next"
  },
  "dependencies": {
    "next": "^2.1.0",
    "react": "^15.4.2",
    "react-dom": "^15.4.2"
  }
}
```

---

모든 것을 자동으로 설정해주는 `create-next-app` 을 사용하여 새 Next.js 앱을 만드는 것이 좋다.

프로젝트 실행 방법

```bash
npx create-next-app
# or
yarn create next-app
```

TypeScript 프로젝트로 시작하려면 다음 `--typescript`플래그를 사용.

```bash
npx create-next-app --typescript
# or
yarn create next-app --typescript
```

설치가 완료되면 지침에 따라 개발 서버를 시작하고 , 편집 후  `pages/index.js` 에서 결과 확인.