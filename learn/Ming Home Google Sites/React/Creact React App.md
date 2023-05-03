## Installation
	// Windows
	$ npm i -g creact-react-app

## Creact React App project
	*** 專案名稱只能夠用 小寫英文字母 與 _ 組成 ***
	*** 創建路徑中不可有中文字的路徑 ***
	
	// Windows
	$ creact-react-app [專案名稱]
		// Project with TypeScript
		$ create-react-app [專案名稱] --template typescript		


	// Mac 
	$ npx creact-react-app [專案名稱]
		// Project with TypeScript
		npx create-react-app [專案名稱] --template typescript
	
## public 內文件
    favicon.ico => 網站小圖標
    index.html => 主網頁容器
    logo192 => 移動端小圖
    logo512 => 移動端大圖
    mamifest.json => 應用套殼配置文件
    robots => 爬蟲配置文件

## src 內文件
    App.css => App組件的css
    App.js => App組件的js
    APP.test.js => 除錯js
    index.css => 全局的css
    index.js => 在此將React的App組件,加入到主網頁容器中
    logo.svg => 圖檔
    reportWebVitals.js => React的性能測試工具
    setupTests.js => 單元組件測試工具

## React Project Cli
	// 啟動專案(開發模式)
	$ npm start

	// 將專案打包成正式網頁
	$ npm run build

	// 顯示 webpage 配置文件
	** 此行為是不可逆的(顯示了就不能再隱藏了) **
	$ npm run eject