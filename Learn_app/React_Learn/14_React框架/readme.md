# 全局安裝 React框架 命令
    $ npm install -g create-react-app

# 至安奘路徑
    $ create-react-app project_name

# public 內文件
    favicon.ico => 網站小圖標
    index.html => 主網頁容器
    logo192 => 移動端小圖
    logo512 => 移動端大圖
    mamifest.json => 應用套殼配置文件
    robots => 爬蟲配置文件

# src 內文件
    App.css => App組件的css
    App.js => App組件的js
    APP.test.js => 除錯js
    index.css => 全局的css
    index.js => 在此將React的App組件,加入到主網頁容器中
    logo.svg => 圖檔
    reportWebVitals.js => React的性能測試工具
    setupTests.js => 單元組件測試工具


# 053 _ 樣式的模塊化
    令組件樣式副檔名為 .module.css
    使用範例代碼:{
        import {Component} from 'react'
        // 樣式的模塊化
        import hello from './Hello.module.css'

        export default class Hello extends Component{
            render(){
                return(
                    <div className={hello.title}>
                        Hello, React!
                    </div>
                )
            }
        }
    }

# 054 vscode中React的好用插件
    名稱: ES7 React/Redux/GraphQL/React-Native snippets
    
    一些快速模板命令:
        1. rcc -> {
            import React, { Component } from 'react'

            export default class Component_name extends Component {
            render() {
                return (
                <div>
                    
                </div>
                )
            }
            }
        }

        2. rcf -> {
            import React from 'react'

            export default function Component_name() {
            return (
                <div>
                
                </div>
            )
            }
        }

        3. imp -> {import moduleName from 'module'}

# 55 組件畫編碼流程
    1. 拆分組件
    2. 實現靜態組件
    3. 實現動態組件
        3.1 顯示初始化數據
            3.1.1 數據類型
            3.1.2 數據名稱
            3.1.3 保存在哪個組件中
        3.2 交互(從邦定事件聆聽開始)

# 58 好用工具
    uuid
    輕量版 nanoid
    唯一 id 工具 
        npm install nanoid
        釋例 => {
            import nanoid from 'nanoid';
            console.log(nanoid());
        }

# react UI
    ant Design
    npm install antd
    https://ant.design/index-cn

