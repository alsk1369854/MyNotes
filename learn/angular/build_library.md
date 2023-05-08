# Build angular library

## Check lib name (檢查包名)

- 在 [NPM](https://www.npmjs.com/) 中搜尋包名，確認想要的名稱是否已被別人使用了

- angular lib 建議以 "ngx-" 做為包名開頭

## Create angular library app (建立庫應用)

- ng new {app_name} --create-application=false
  
  - {app_name} : 專案名稱
  
  - --create-application=false : 表示此專案不是一個應用服務

```bash
# create angular lib app
ng new ngx-my-test-lib --create-application=false

# change directory to angular lib app
cd ngx-my-test-lib/
```

## Create library project (建立庫專案)

- ng generate library {lib_name}
  
  - {lib_name} : library 名稱 (建議以 ngx 開頭，表示這是一個 angular library) 

```bash
# create angular lib project
# ./projects/ngx-my-test
ng generate library ngx-my-test
```

## Declare dependencies on other libs (聲明對其他庫的依賴)

```bash
# install angular bootstrap
ng add @ng-bootstrap/ng-bootstrap
```

### my-lib/package.json (庫的包配置文件)

```json
{
  "name": "ngx-my-test",
  "version": "0.0.1",
  "peerDependencies": {
    "@angular/common": "^15.2.0",
    "@angular/core": "^15.2.0",
    "@ng-bootstrap/ng-bootstrap": "^14.1.1" // add dependencies
  },
  "dependencies": {
    "tslib": "^2.3.0"
  },
  "sideEffects": false
}
```

## Editing library (編輯庫組件)

### ngx-my-test.component.ts

```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'ngx-my-test',
  template: `
    <div 
      *ngFor="let alertContent of alertContentList" 
      class="alert alert-success" 
      role="alert">
      {{alertContent}}
    </div>

    <button 
      type="button" 
      class="btn btn-primary"
      (click)="onClickButton()">
      add alert
    </button>
  `,
  styles: [
  ]
})
export class NgxMyTestComponent {
  count: number = 1;
  alertContentList: string[] = [];

  onClickButton() {
    this.alertContentList.push(`alert ${this.count++}`);
  }

}
```

## Build angular library and publish to npm (打包與發布)

### 1. Update lib version tag (更新庫版本標籤)

```json
{
  "name": "ngx-my-test",
  "version": "0.0.1", // update your new version, must be change 
  "peerDependencies": {
    "@angular/common": "^15.2.0",
    "@angular/core": "^15.2.0",
    "bootstrap": "^5.2.3"
  },
  "dependencies": {
    "tslib": "^2.3.0"
  },
  "sideEffects": false
}
```



### 2. build and publish to npm

```bash
# build my angular libs
ng build

# change directory to lib of dist
cd dist/ngx-my-test

# login npm
npm login

# check login user
npm whoami

# publish lib to npm
npm publish
```

## Publish to npm (發布至 npm)

### login npm (登入)
