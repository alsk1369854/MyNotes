# Build angular library (打包 Angular 庫)

[Angular creating library](https://angular.io/guide/creating-libraries)

## Table of contents (目錄)

1. 檢查包名

2. 建立庫應用

3. 建立庫專案

4. 在我的庫中安裝其他依賴

5. 編輯庫組件

6. 打包與發布

7. 在其他專案中安裝自己的 Angular 庫

## 1. Check lib name (檢查包名)

- 在 [NPM](https://www.npmjs.com/) 中搜尋包名，確認想要的名稱是否已被別人使用了

- angular lib 建議以 "ngx-" 做為包名開頭

## 2. Create angular library app (建立庫應用)

- ng new {app_name} --create-application=false
  
  - {app_name} : 專案名稱
  
  - --create-application=false : 表示此專案不是一個應用服務

```bash
# create angular lib app
ng new ngx-my-test-lib --create-application=false

# change directory to angular lib app
cd ngx-my-test-lib/
```

## 3. Create library project (建立庫專案)

- ng generate library {lib_name}
  
  - {lib_name} : library 名稱 (建議以 ngx 開頭，表示這是一個 angular library) 

```bash
# create angular lib project
# ./projects/ngx-my-test
ng generate library ngx-my-test
```

## 4. Install other dependencies in my library (在我的庫中安裝其他依賴)

### 4-1. install angular bootstrap in my library (為我的庫安裝 Angular Boostrap)

- 請確保其他專案在使用時，也為他們的專案安裝了相同的庫 (Angular 原理原則)

```bash
# install angular bootstrap
ng add @ng-bootstrap/ng-bootstrap
```

### 4-2. Add peer dependencies (添加庫的對等依賴)

#### projects/ngx-my-test/package.json

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

### 4-3. Import library module (註冊安裝的庫模組)

#### projects/ngx-my-test/src/lib/ngx-my-test.module.ts

```typescript
import { NgModule } from '@angular/core';
import { NgxMyTestComponent } from './ngx-my-test.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    NgxMyTestComponent
  ],
  imports: [
    CommonModule, // import common module
    NgbModule // import angular boostrap module
  ],
  exports: [
    NgxMyTestComponent
  ]
})
export class NgxMyTestModule { }
```

## 5. Editing library (編輯庫組件)

#### projects/ngx-my-test/src/lib/ngx-my-test.component.ts

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

## 6. Build angular library and publish to npm (打包與發布)

### 6-1. Update lib version tag (更新庫版本標籤)

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

### 6-2. Build and publish to npm (打包與發佈至 npm)

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

## 7. Installation my anguler lib in Angular project (在其他專案中安裝自己的 Angular 庫)

### 7-1. Create demo Anguler app (建立演示 Angular 應用)

```bash
# create demo angular app
ng new my-angular-libs-demo

# change directory to anguler app
cd my-angular-libs-demo/
```

### 7-2. Install my publish Angular library (安裝已發布的 Angular 庫)

```bash
npm i ngx-my-test
```

### 7-3. Import my Angular library (註冊 Angular 庫)

#### src/app/app.module.ts

```typescript
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgxMyTestModule } from 'ngx-my-test';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    NgxMyTestModule, // import my angular lib
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

### 7-4. Use my Angular library in HTML (在 HTML 中使用庫)

#### src/app/app.component.ts

```html
<ngx-my-test></ngx-my-test>
```
