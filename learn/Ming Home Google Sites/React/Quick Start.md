	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Hello, React!</title>
	</head>
	<body>
	    <!-- 容器 -->
	    <div id="test"></div>
	
	    <!-- React 核心庫 -->
	    <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script>
	    <!-- React 拓展功能: 用於支持React與DOM溝通 -->
	    <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script>
	    <!-- 語言翻譯: ES6 => ES5 ; jsx => js -->
	    <script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
	
	    <script type="text/babel"> // type="text/babel" 一定要寫babel
	        // 1. 創建類式組件
	        class MyComponent extends React.Component{
	            render(){
	                return(
	                    <div>Hello, React!</div>
	                )
	            }
	        }
	        // 2. 渲染組件到頁面
	        ReactDOM.render(<MyComponent/>, document.getElementById('test'))
	    </script>
	</body>
	</html>