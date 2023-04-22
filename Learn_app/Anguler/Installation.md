# Anguler installation

### 環境準備

- node V18 以上

### 安裝 Anguler

```bash
npm install --global @angular/cli
```

### VS Code 延伸模組

1. doggy8088.angular-extension-pack

## 專寫標準(柏鈞)

- src/app/side: 前端component目錄
- src/service: api接口服務目錄
- src/assets: 靜態資源目錄

## Router 三步驟

### 1. 創建 Router component

```typescript
import { Component } from '@angular/core';
import { ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  constructor(
    // 獲取 Route 功能類
    private route: ActivatedRoute
  ) {
    // 獲取 Route 攜帶參數
    this.route.queryParamMap.subscribe((query)=>{
      //或取 Route 攜帶參數名為 id 的值  
      alert(query.get("id"))
    })
  }
}
```

### 2. 將 Route 註冊於 app.module.ts

```typescript
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// 導入 Route component
import { HomeComponent } from './site/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    // 註冊 Route component
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

### 3. 配置 app-routing.module.ts

```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// 導入 Route component
import { HomeComponent } from './site/home/home.component';

const routes: Routes = [
  // 配置 Route 的匹配名稱
  { path: 'home/index', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```
