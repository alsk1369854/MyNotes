## 路由的基本使用
        npm install react-router-dom
        import {BrowserRouter} from 'react-router-dom'
        import {HashRouter} from 'react-router-dom'
        import {Link, Route} from 'react-router-dom'
        
        1. 明確好介面的導航區, 展示區
        2. 導航區的 a標籤 該寫為 Link標籤
            <Link to="/about">About</Link>

        3. 展示區寫Route標籤進行路徑的匹配
	        <Routes>
            	<Route path="/about" component={About}/>
				<Route path="/home" component={Home}/>
			</Routes>

        4. <App>的最外側包裹了一個<BrowserRouter>或<HashRouter>
	        ReactDOM.render(
				<BrowserRouter>
					<App/>
				</BrowserRouter>,
			document.getElementById("root"));


## 路由組件與一般組件
    1. 寫法不同:
        一般組件: <Demo/>
        路由組件: <Route path="/demo" component={Demo}>

    2. 存放位置不同:
        一般組件: components
        路由組件: pages

    3. 接收到的props不同:
        一般組件: 寫組件標籤時傳遞了什麼, 就能收到什麼
        路由組件: 接收到三個固定的屬性
            history:
                go: f go(n)
                goBack: f goBack()
                goForward: f foForward()
                push: f push(path, state)
                replace: f replace(path, state)
            location:
                pathname: "/about"
                search: ""
                state: undefined
            match:
                params: {}
                path: "/about"
                url: "/about"

	4. 若一般組件想使用路由組件的API:
		import React, { Component } from 'react'
		import { withRouter } from 'react-router-dom'
		
		class GeneralComponent extends Component{
			...
		}

		export default withRouter(GeneralComponent)


## NavLink與封裝NavLink
    1. NavLink可以實現路由連結的高亮, 透過activeClassName指定樣式名
	    <NavLink activeClassName="like_on_hover">Home</NavLink>

    2. 標籤體內容是一個特殊的標籤屬性
    3. 透過this.props.children可以獲取標籤體內容

## Switch的使用
    1. 通常情況下, path和component是一一對應的關係
    2. Switch可以提高路由匹配效率(單一匹配)

## 解決多級路徑刷新頁面樣式丟失的問題
    1. public/index.html 中引入樣式時不寫 ./　寫　/ (常用)
    2. public/index.html 中引入樣式時不寫 ./　寫　%PUBLTC_URL% (常用)
    3. 使用HashRouter

## 路由的嚴格匹配與模糊匹配
    1. 默認使用的是模糊匹配(簡單記:【輸入的路徑】必須包含要【匹配的路徑】, 且順序要一致)
    2. 開啟嚴格匹配: <Route exact={true} path="/about" component={About}/>
    3. 嚴格匹配不要隨便開啟, 需要再開, 有些時候開啟會導致無法繼續匹配二級路由

## Redirect的使用【類似Java Switch 中的 defualt】 
    1. 一般寫在所有路由註冊的最下方, 當所有路由都無法匹配時, 跳轉到Redirect指定的路由
    2. 具體編碼:
        import {Redirect, Switch, Route} from 'react-router-dom'
        <Switch>
            <Route path="/about" component={About}/>
            <Route path="/home" component={Home}/>
            <Redirect to="/about"/>
        </Switch>

## 嵌套路由【在路由中還有路由】
    1. 註冊子路由時要寫上父路由path值
    2. 路由的匹配是按照註冊路由的順序進行的

## 向路由組件傳遞參數
    1. params參數
        路由連接(攜帶參數): <Link to="/demo/test/tom/18">詳情</Link>
        註冊路由(聲明接收): <Route path="/demo/test/:name/:age" component={Test}/>
        接收參數: const {name, age} = this.props.match.params

    2. search參數
        路由連結(攜帶參數): <Link to='/demo/test?name=tom&age=18'>詳情</Link>
        註冊路由(無須聲明, 正常註冊即可): <Route path='/demo/test' component={Test}/>
        接收參數: const {search} = this.props.location
        備註: 獲取收到的search是urlencoded編碼字符串, 需要借助querystring解析
        解取釋例:
            import qr from 'querystring'
            const {search} = this.props.location;
            const result = qr.stringify(search.slice(1));
            console.log(result);

    3. staten參數 【推薦】
        路由連結(攜帶參數): <Link to={{pathname='/demo/test', state={name:'tom', age: 18}}}>詳情</Link>
        註冊路由(無須聲明, 正常註冊即可): <Route path='/demo/test' component={Test}/>
        接收參數: const {state} = this.props.location
        備註: 刷新也可以保留住參數
        
## 編程式路由導航 【動態路由導航】
    借助this.props.history對象上的API操作路由轉跳, 前進, 後退
        -this.props.history.push()
        -this.props.history.replace()
        -this.props.history.goBack()
        -this.props.history.goForward()
        -this.props.history.go()

## BrowserRouter與HashRouter的區別
    1. 底層原理不一樣:
        BrowserRouter使用的是H5的history API, 不兼容IE9及以下版本。
        HashRouter使用的是URL的哈希值。

    2. url表現形式不一樣
        BrowserRouter的路徑中沒有#, 例如: localhost:3000/demo/test
        HashRouter的路徑包含#,例如: localhost:3000/#/demo/test

    3. 刷新後對路由state參數的影響
        (1). BrowserRouter沒有任何影響, 因為state保存在history對象中。
        (2). HashRouter刷新後會導致路由state參數的丟失

    4. 備註: HashRouter可以用於解決一些路徑錯誤相關的問題。

## LazyLoad 路由組件懶加載
	import React, { Component, lazy, Suspense } from 'react'
	import { Route, Switch } from 'react-router-dom'

	//1. 透過React的lazy函數配合import()函數動態加載路由組件 ===> 路由組件代碼會被分開打包
	const Login = lazy(()=>import('@/pages/Login'))

	//2. 透過<Suspense>指定在加載得到路由打包文件前顯示一個自訂義loading介面
	<Suspense fallback={<h1>loading...</h1>}>
		<Switch>
			<Route path="/login" component={Login}/>
		</Switch>
	</Suspense>

## History Tool 【React Router 是建立在這個工具上的】
	react 路由是建立在 history 工具上協助操作 BOM 的 
    
	$ npm install history
    /*
        const history = History.createBrowseHistory();
        // const history = History.createHashHistory();

        history.push(location)
        history.replace(location)
        history.go(n)
        history.goBack()
        history。goForward()
    */


## Demo code
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <script src='https://unpkg.com/react@16.3.1/umd/react.production.min.js'></script>
	    <script src='https://unpkg.com/react-dom@16.3.1/umd/react-dom.production.min.js'>    </script>
	    <script src='https://unpkg.com/react-router-dom@5.0.0/umd/react-router-dom.min.js'></script>
	    <script src='https://unpkg.com/babel-standalone@6.26.0/babel.js'></script>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
	    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	    <style>
	        body{
	            background-color: rgba(0,0,0,0);
	        }
	        li{
	            list-style-type: none;
	        }
	        .container{
	            display:flex;
	        }
	        .link_list, .main_container{
	            text-align: center;
	        }
	        .main_container{
	            margin-left: 20px;
	        }
	        .link_item{
	            width: 100px;
	            border-radius:10px;
	            border-style:solid;
	            border-width:1px;
	            border-color:#0d6efd;
	        }
	        .main{
	            width: 200px;
	            height: 200px;
	            border-radius:10px;
	            border-style:solid;
	            border-width:1px;
	            border-color:#0d6efd;
	        }
	    </style>
	
	    <title>React Router</title>
	</head>
	<body>
	    <!-- 容器 -->
	    <div id="root"></div>
	
	    <script type="text/babel"> // type="text/babel" 一定要寫babel
	        const Link = ReactRouterDOM.Link;
	        const Route = ReactRouterDOM.Route;
	        const Routes = ReactRouterDOM.Routes;
	        class App extends React.Component{
	            render(){
	                return(
	                    <div className="container">
	                        <div className="link_list">
	                            Link Bar
	                            <div className="link_item"><Link className="navbar-brand" to="/">Home</Link></div>
	                            <div className="link_item"><Link className="navbar-brand" to="/about">About</Link></div>
	                        </div>
	                        <div className="main_container">
	                            Main Window
	                            <div className="main">
	                                <Route path="/" exact component={Home} />
	                                <Route path="/about" component={About}/>
	                            </div>
	                        </div>
	                    </div>
	                )
	            }
	        }
	        const Home = () => <h1>Home...</h1>
	        const About = () => <h1>About...</h1>
	        ReactDOM.render(
	            <ReactRouterDOM.HashRouter>
	                <App /> 
	            </ReactRouterDOM.HashRouter>,
	        document.querySelector('#root'));
	    </script>
	</body>
	</html>