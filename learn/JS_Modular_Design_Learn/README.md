# npm package

> ## npm cli
```shell
# 建立 npm package
npm init
# 建立預設 @<scope>
npm init --scope=<your scope>

# 登入 npm 用戶 
npm login
# 添加本地用戶
npm adduser

# npm 一般推送更新
npm publish
# npm @<scope>/<package> 推送更新
npm publish --access=public 

# npm 版本更新 
# current version 1.0.0
npm version patch # update to 1.0.1 
npm version minor # update to 1.1.0
npm version major # update to 2.0.0

# npm 外部依賴包安裝
npm install --save <package name> # 運行依賴
npm install --save-dev <package name> # 開發依賴

```

> ## package.json 說明

```json
{
    // 包名
    "name": "commonjs_nodejs",
    // 版本
    "version": "1.0.0",
    // 描述
    "description": "> ## 配置 package.json",
    // 程式進入點
    "main": "app.js",
    // 內至腳本
    "scripts": {
        "build": "babel src -d dist && browserify dist/app.js -o index.js"
    },
    // 關鍵字
    "keywords": [
        "alsk1369854"
    ],
    // github連結
    "repository": {
        "type": "git",
        "url": "git+https://github.com/alsk1369854/alsk1369854.git"
    },
    "bugs": {
        "url": "https://github.com/alsk1369854/alsk1369854/issues"
    },
    "homepage": "https://github.com/alsk1369854/alsk1369854#readme",
    // 創作者
    "author": "alsk1369854",
    // 許可規範，讓使用者知道你的應用可以被做那些利用
    "license": "ISC",
    // 外部依賴包 npm install --save <package name>
    "dependencies": {
        "babel-cli": "^6.26.0",
        "browserify": "^17.0.0"
    },
    // 開發外部依賴包 npm install --save-dev <package name>
    "devDependencies": {
        "babel-preset-es2015": "^6.24.1"
    },
}

```