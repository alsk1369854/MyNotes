## App Component
	// 最外層的容器組件
	// 所有自訂義組件的容器
	import React, { Component } from 'react'

	import Head from './component/Head'
	import Main from './component/Main'
	import Floor from './component/Floor'

	export default class App extends React.Component{
	    render(){
	        return(
	            <div>
					<Head/>
					<Main/>
					<Floor/>
				</div>
	        )
	    }
	}
	
## Render Component to Web page
	// 將APP組件喧染到頁面上
	第一步:
		// 在 public/index.html 上設置一個容器節點
		// 並將其節點 id 設置為 root
		<div id="root"></div>

	第二步:
		// 在 src/index.js 中渲染APP組件到頁面上
		import React from 'react'
		import ReactDOM from 'react-dom'
		
		import App from './App'
	
		ReactDOM.render(
			<App/>,
		document.getElementById('root'));


## 自訂義組件
### Class Component
	import React, { Component } from 'react'

	export default class MyClassComponent extends Component{
	    render(){
			// 此處的this是 MyClassComponent 組件實例對象
			console.log(this);
	        return(
	            <div>
					A Class Component...
				</div>
	        )
	    }
	}

### Class PureComponent [Component 優化]
	// 在準備渲染畫面時，會對 state 做一個淺比較，如果和之前沒有變化，就不會執行 render()
	import React, { PureComponent } from 'react'

	export default class MyClassPureComponent extends PureComponent{
	    render(){
			// 此處的this是 MyClassPureComponent 組件實例對象
			console.log(this);
	        return(
	            <div>
					A Class PureComponent...
				</div>
	        )
	    }
	}

### Function Comoponent
	import React from 'react'

	export default function MyFunctionComponent() {
	  return (
	    <div>
	      A Functional Component...
	    </div>
	  )
	}

