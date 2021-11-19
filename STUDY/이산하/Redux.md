# Redux

React 프로젝트의 규모가 커질때마다 자식으로 넘겨주어야 하는 props의 깊이도 점점 깊어진다. 따라서, 어디에서든 내가 원하는 state를 사용할 수 있는 라이브러리 [Redux](https://redux.js.org/)가 나타났다.

Redux만 사용하여 React에서 사용할 수 있지만, 더 편하게 사용하기 위해 [React-Redux](https://react-redux.js.org/)가 나왔다.	



## React에 리덕스(Redux)가 필요한 이유?

리액트로 프로젝트를 진행하게 되면, Component는 local state를 가지게 되고, 어플리케이션은 global state를 가지게 된다.

```
local state: 각각의 Component가 가지는 state. 어플리케이션은 이 state를 기반으로 만들어진다.
global state: 예를 들어, 유저의 로그인의 유무에 따라 어플리케이션의 state가 달리 보이는 것을 들 수 있다. 어플리케이션 전체에서 global state는 유지, 즉 local state는 global state를 공유하게 되는 것이다.
```



## Redux 기본 개념

. **Actions(액션):** 어플리케이션의 store(스토어), 즉 저장소로 data를 보내는 방법이다. view에서 정의되어있는 액션을 호출하면 action creators(액션 생성자)는 어플리케이션의 state(상태)를 변경하여 준다. 공식문서에 나와있는 예제를 살펴보자.

```
const ADD_TODO = 'ADD_TODO' // action의 type을 정의
```

action의 type은 일반적으로 문자열 상수로 정의된다. 정의된 action type은 **action creators(액션 생성자)**를 통해 사용된다.

```
function addTodo(text) {
  return {
    type: ADD_TODO,
    text
  }
}
```

**. Reducers(리듀서):** action을 통해 어떠한 행동을 정의했다면, 그 결과 어플리케이션의 상태가 어떻게 바뀌는지는 특정하게 되는 함수이다.

```
function todoApp(state = initialState, action) {
  switch (action.type) {
  case SET_VISIBILITY_FILTER:
    return Object.assign({}, state, {
      visibilityFilter: action.filter
    });
  default:
    return state
  }
}
```

리듀서 함수에서는 action의 type에 따라 변화된 state를 반환하게 된다.

**. Store(스토어): “**무엇이 일어날지”를 나타내는 action, 그리고 action에 따라 상태를 수정하는 reducer를 저장하는 어플리케이션에 있는 단 하나의 객체이다.

```
import { createStore } from 'redux';
import todoApp from './reducers';

let store = createStore(todoApp);
```

이처럼 store을 생성하고 reducer을 연결하여 어플리케이션에 연결하게 된다.