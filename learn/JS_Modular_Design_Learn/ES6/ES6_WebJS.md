# ES6

> ## Mac

### 獲取最高權限

sudo -s

> ## 前言

```
需要打包處裡
// babel ES6 => ES5 
有的瀏覽器不支持需要將 ES6 打包成 ES5 運行

browserify 編譯打包
// 將 ES5(含有require) 打包成 瀏覽器認識的js
```

> ## 安裝

### 包介紹

    babel-cli // 命令行接口
    babel-preset-es2015 // ES6 => ES5 工具
    browserify // 將 require 打包成 瀏覽器認識的js 工具

### 安裝命令

    npm install babel-cli browserify -g
    npm install babel-preset-es2015 --save-dev // -dev為在package.json標註為開發階段使用(只是說明而已，真正打包看的是 內的文件)

### 配置 .babelrc 文件

```json
{
    "presets" : ["es2015"]
}
```

> ## 編譯
> 
>     使用 Babel 將 ES6 => ES5(但包含CommonJS語法): 
>         babel js/src -d js/lib
>     使用 Browserify 編譯 js (讓瀏覽器可以認識CommonJS語法):
>         browserify js/lib/main.js -o js/lib/bundle.js

    個人喜歡
        babel src -d build
        browserify build/main.js -o index.js
