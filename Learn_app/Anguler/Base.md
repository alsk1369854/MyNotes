# Angular Base

1. 開發目錄
2. Component 目錄
3. 在 HTML 中使用 TS 的變數
4. Pipe 管道
5. 變數雙向綁定
6. *ngFor (for渲染)
7. click (事件)
8. input (事件)
9. *ngIf (條件顯示)
10. class 狀態邦定
11. 組件調用
12. 組件參數傳遞
13. Service 註冊區域
14. Observable<{type}> 觀察請求數據
15. 在 HTML 中，直接使用 Service 數據
16. 配置 Router
17. Router 預設頁面
18. 使用 Router 頁面
19. Router 連結
20. TS (Location) 瀏覽器路由導航
21. 使用 Http 工具包
22. HTML tag 標記
23. AsyncPipe 異步資料渲染

### 開發目錄

```bash
{angular_project}/src/app
```

### Component 目錄

```bash
# HTML
{component_name}.component.html

# CSS
{component_name}.component.scss

# TypeScript
{component_name}.component.ts

# Test
{component_name}.component.spec.ts
```

### 在 HTML 中使用 TS 的變數

component.html

```html
<h1>{{title}}</h1>
```

component.ts

```typescript
export class AppComponent {
  title = 'Tour of Heroes';
}
```

## Pipe 管道

```html
<!-- uppercase : 全大寫 -->
<h2>{{hero.name | uppercase}} Details</h2>
```

## 變數雙向綁定

雙向綁定 hero.name 的值

```html
<div>
  <label for="name">Hero name: </label>
  <input id="name" [(ngModel)]="hero.name" placeholder="name">
</div>
```

為 app.module.ts 導入依賴 

```typescript
....
import { FormsModule } from '@angular/forms';

@NgModule({
    ....
  imports: [
    FormsModule
  ],
})
export class AppModule { }
```

## *ngFor (for渲染)

HTML

```html
<li *ngFor="let hero of heroes">
  <span class="badge">{{hero.id}}</span>
  <span class="name">{{hero.name}}</span>
</li>
```

TS

```typescript
export class HeroesComponent  {
    heroes = [
        { id: 12, name: 'Dr. Nice' },
        { id: 13, name: 'Bombasto' },
        { id: 14, name: 'Celeritas' }
    ];
}
```

## click (事件)

HTML

```html
<li *ngFor="let hero of heroes">
  <!-- (click)="function" -->
  <button type="button" (click)="onSelect(hero)">
    <span class="badge">{{hero.id}}</span>
    <span class="name">{{hero.name}}</span>
  </button>
</li>
```

TS

```typescript
export class HeroesComponent  {
  heroes = [
        { id: 12, name: 'Dr. Nice' },
        { id: 13, name: 'Bombasto' },
        { id: 14, name: 'Celeritas' }
    ];

  selectedHero?: Hero;
  onSelect(hero: Hero): void {
    this.selectedHero = hero;
  }
}
```

## input (事件)

HTML 文件

```html

```

TS 文件

```typescript

```

## *ngIf (條件顯示)

HTML

```html
<li *ngFor="let hero of heroes">
  <button type="button" (click)="onSelect(hero)">
    <span class="badge">{{hero.id}}</span>
    <span class="name">{{hero.name}}</span>
  </button>
</li>

<!-- 當 selectedHero 有值時才會顯示 -->
<div *ngIf="selectedHero">
  <h2>{{selectedHero.name | uppercase}} Details</h2>
  <div>id: {{selectedHero.id}}</div>
  <div>
    <label for="hero-name">Hero name: </label>
    <input id="hero-name" [(ngModel)]="selectedHero.name" placeholder="name">
  </div>
</div>
```

TS

```typescript
export class HeroesComponent {
    heroes = [
        { id: 12, name: 'Dr. Nice' },
        { id: 13, name: 'Bombasto' },
        { id: 14, name: 'Celeritas' }
    ];

  selectedHero?: Hero;
  onSelect(hero: Hero): void {
    this.selectedHero = hero;
  }
}
```

## class 狀態邦定

HTML

```html
<li *ngFor="let hero of heroes">
  <!-- [class.{樣式}]="條件" -->
  <button [class.selected]="hero === selectedHero" type="button" (click)="onSelect(hero)">
    <span class="badge">{{hero.id}}</span>
    <span class="name">{{hero.name}}</span>
  </button>
</li>
```

TS

```typescript
export class HeroesComponent implements OnInit {
    heroes = [
        { id: 12, name: 'Dr. Nice' },
        { id: 13, name: 'Bombasto' },
        { id: 14, name: 'Celeritas' }
    ];

    selectedHero?: Hero;
    onSelect(hero: Hero): void {
        this.selectedHero = hero;
    }
}
```

CSS

```css
.selected{
  background: red;
}
```

## 組件調用

### 父組件 HeroesComponent

HTML

```html
<h2>My Heroes</h2>
<ul class="heroes">
  <li *ngFor="let hero of heroes">
    <button [class.selected]="hero === selectedHero" type="button" (click)="onSelect(hero)">
      <span class="badge">{{hero.id}}</span>
      <span class="name">{{hero.name}}</span>
    </button>
  </li>
</ul>

<!-- 使用子組件的 selector 做為標籤名稱，來插入組件 -->
<app-hero-detail [hero]="selectedHero"></app-hero-detail>
```

### 子組件 HeroDetailComponent

TS

```typescript
@Component({
    // 調用組件的標籤名稱 
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.scss']
})
export class HeroDetailComponent {
  @Input() hero?: Hero;
}
```

## 組件參數傳遞

### 父組件 HeroesComponent

HTML

```html
<h2>My Heroes</h2>
<ul class="heroes">
  <li *ngFor="let hero of heroes">
    <button [class.selected]="hero === selectedHero" type="button" (click)="onSelect(hero)">
      <span class="badge">{{hero.id}}</span>
      <span class="name">{{hero.name}}</span>
    </button>
  </li>
</ul>

<!-- [{子組件的變數名}]="{父組件的變數名}" -->
<app-hero-detail [hero]="selectedHero"></app-hero-detail>
```

### 子組件 HeroDetailComponent

TS

```typescript
// 導入 Input 包
import { Component, Input } from '@angular/core';
@Component({
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.scss']
})
export class HeroDetailComponent {
    // 宣告 @Input() 表示參數是傳入的 
    @Input() hero?: Hero;
}
```

## Service 註冊區域

全域訪問

```typescript
// 直接在 service 中指定
@Injectable({
  providedIn: 'root'
})

// 直接註冊到 app.module.ts (刪除原 service 檔案 providedIn 設定)
@NgModule({
  providers: [],
})
export class AppModule { }
```

局部訪問

```typescript
// 註冊至組件中 (刪除原 service 檔案 providedIn 設定)
@Component({
    ....
  providers:[

  ]
})
```

## Observable<{type}> 觀察請求數據

### hero.service.ts 提供 heros 數據請求服務

```typescript
// HEROES 為模擬的 ajax 請求數據為 Promise 型態
export class HeroService {
  // getHeroes 回傳 Observable 型態數據，回傳數據使用 of() 包裝
  getHeroes(): Observable<Hero[]> {
    return of(HEROES);
  }
}
```

### hero.component.ts 調用 service 服務

```typescript
export class HeroesComponent implements OnInit {
  heroes: Hero[] = [];

  // 注入 HeroService 依賴
  constructor(private heroService: HeroService) { }

  getHeroes(): void {
    // 調用 service 發法獲取數據
    // 使用 Observable.subscribe 方法取得請求資料 
    this.heroService.getHeroes()
      .subscribe(heroes => this.heroes = heroes);
  }
}
```

## 在 HTML 中，直接使用 Service 數據

### 在 Component 中，使用 public 注入 service

```typescript
export class MessagesComponent {
  // 使用 public 宣告注入 service
  constructor(public messageService: MessageService){}
}
```

### 在 HTML 中使用 service 變數名稱調用資料

```html
<!-- 直接可以透過，Service 選告的注入名稱調用參數與方法  -->
<div *ngIf="messageService.messages.length">
  <h2>Messages</h2>
  <button
    type="button"
    class="clear"
    (click)="messageService.clear()"
    >
    Clear messages
  </button>

  <div *ngFor="let message of messageService.messages">
    {{message}}
  </div>
</div>
```

## 配置 Router

### 配置 app-routing.module.ts

```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// 導入 Component
import { HeroesComponent } from './heroes/heroes.component';

const routes: Routes = [
  // 配置 Route 名稱對應的 Component
  { path: 'heroes', component: HeroesComponent }
];

// 初始化配置
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```

### 註冊至 app.module.ts (使用命令創建 module 將會自動完成註冊)

```typescript
// 導入 Router module 配置
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  ....
  // 註冊 Router module 使其生效
  imports: [
    AppRoutingModule
  ],
})
export class AppModule { }
```

## Router 預設頁面

### 配置 app-routing.module.ts

```typescript
const routes: Routes = [
  { path: 'heroes', component: HeroesComponent },
  { path: 'dashboard', component: DashboardComponent },
  // 配置預設 Route 當沒有匹配或為 "/" 時導向 "/dashboard"
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
];
```

## 使用 Router 頁面

### 在 app.component.html 中嵌入 router 連結對應的頁面

```html
<!-- 在 HTML 中使用 router-outlet 嵌入 Route 頁面 -->
<router-outlet></router-outlet>
```

## Router 連結

### 未帶參數 Link

```html
<nav>
  <!-- 開頭 "/" 代表根，類似於: http://localhost/ -->
  <!-- heroes 代表要進入的 route 頁面 -->
  <a routerLink="/heroes">Heroes</a>
</nav>
```

### 帶參數 Link

#### Route 連結的 HTML 頁面

```html
<h2>My Heroes</h2>
<ul class="heroes">
  <li *ngFor="let hero of heroes">
    <!-- 開頭 "/" 代表根，類似於: http://localhost/ -->
    <!-- detail 代表要進入的 route 頁面 -->
    <!-- {{hero.id}} 表示攜帶的參數 -->
    <a routerLink="/detail/{{hero.id}}">
      <span class="badge">{{hero.id}}</span> {{hero.name}}
    </a>
  </li>
</ul>
```

#### 在 Component 中取出 Router 帶入的參數

```typescript
 ....
// 導入 Route 工具包
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

 ....
export class HeroDetailComponent {
  hero?: Hero;

  constructor(
    // Route 工具包注入
    private route: ActivatedRoute,
    private heroService: HeroService,
    private location: Location
  ) {}

  getHero(): void {
    // 取出 Route 所攜帶，名稱為 id 的參數
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.heroService.getHero(id)
      .subscribe(hero => this.hero = hero);
  }
}
```

## TS (Location) 瀏覽器路由導航

TS

```typescript
 ....
//  導入 Location 工具包
import { Location } from '@angular/common';
 ....
export class HeroDetailComponent implements OnInit {
  constructor(
    // 注入工具包依賴
    private location: Location
  ) {}

  goBack(): void {
    // 控制瀏覽器返回上一頁
    this.location.back();
  }
}
```

## 使用 Http 工具包

### 為 Angular 導入包，在 app.module.ts 加入配置

```typescript
 ....
// 導入包
import { HttpClientModule } from '@angular/common/http';
@NgModule({
   ....
  imports: [
    // 註冊 HTTP 工具
    HttpClientModule,
  ],
})
```

### Http 工具發送 "GET" 請求(查詢)

#### service.ts 文件

#### 調用處 Observable.subscribe() 必須被執行請求才會生效

```typescript
 ....
// 導入 http 工具包
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class HeroService {
  // API 請求地址 (模擬)
  private heroesUrl = 'api/heroes';

  constructor(
    // 注入 HttpClient 工具
    private http: HttpClient) { }

  getHeroes(): Observable<Hero[]> {
    // 使用 http 工具送 "GET" 請求，回傳為 Hero[]
    return this.http.get<Hero[]>(this.heroesUrl)
  }
}
```

### Http 工具發送 "PUT" 請求(更新)

#### service.ts 文件

#### 調用處 Observable.subscribe() 必須被執行請求才會生效

```typescript
// 導入 http 工具包
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class HeroService {
  // API 請求地址 (模擬)
  private heroesUrl = 'api/heroes';
  // 請求配置，傳送內容為 json 格式
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    // 注入 HttpClient 工具
    private http: HttpClient) { }

  updateHero(hero: Hero): Observable<any> {
    // 使用 http 工具送 "PUT" 請求，更新數據
    return this.http.put(this.heroesUrl, hero, this.httpOptions)
  }
}
```

### Http 工具發送 "POST" 請求(新增)

#### service.ts 文件

#### 調用處 Observable.subscribe() 必須被執行請求才會生效

```typescript
// 導入 http 工具包
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class HeroService {
  // API 請求地址 (模擬)
  private heroesUrl = 'api/heroes';
  // 請求配置，傳送內容為 json 格式
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    // 注入 HttpClient 工具
    private http: HttpClient) { }

  addHero(hero: Hero): Observable<Hero> {
    // 使用 http 工具送 "POST" 請求，新增數據
    return this.http.post<Hero>(this.heroesUrl, hero, this.httpOptions)
  }
}
```

### Http 工具發送 "DELETE" 請求(刪除)

#### service.ts 文件

#### 調用處 Observable.subscribe() 必須被執行請求才會生效

```typescript
// 導入 http 工具包
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class HeroService {
  // API 請求地址 (模擬)
  private heroesUrl = 'api/heroes';
  // 請求配置，傳送內容為 json 格式
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    // 注入 HttpClient 工具
    private http: HttpClient) { }

  deleteHero(id: number): Observable<Hero> {
    const url = `${this.heroesUrl}/${id}`;
    // 使用 http 工具送 "DELETE" 請求，刪除數據
    return this.http.delete<Hero>(url, this.httpOptions)
  }
}
```

### 錯誤處理

service.ts

```typescript
// 導入錯誤處理工具包 catchError
import { catchError, map, tap } from 'rxjs/operators';
 ....
@Injectable({
  providedIn: 'root'
})
export class HeroService {
  // API 請求地址 (模擬)
  private heroesUrl = 'api/heroes';

  constructor(
    // 注入 HttpClient 工具
    private http: HttpClient) { }

  // 錯誤處理器
  private handleError<T>(operation = 'operation', result?: T) {
    // 錯誤處裡函數
    return (error: any): Observable<T> => {
      // 於console 介面打印 error 訊息
      console.error(error);
      // 紀錄日誌
      this.log(`${operation} failed: ${error.message}`);
      // 返回替代方案
      return of(result as T);
    };
  }

  getHeroes(): Observable<Hero[]> {
    // 使用 http 工具送 "GET" 請求，回傳為 Hero[]
    return this.http.get<Hero[]>(this.heroesUrl)
      // 另用 .pipe() 來擴充 Observable 的結果
      .pipe(
        // 使用 catchError() 捕獲請求錯誤發生
        catchError(this.handleError<Hero[]>('getHeroes', []))
      );
  }
}
```

### 檢視 Observable 值

service.ts

```typescript
// 導入檢視工具包 tap
import { catchError, map, tap } from 'rxjs/operators';
 ....
export class HeroService {
  private heroesUrl = 'api/heroes';

  constructor(
    private http: HttpClient) { }

  getHeroes(): Observable<Hero[]> {
    return this.http.get<Hero[]>(this.heroesUrl)
      // 另用 .pipe() 來擴充 Observable 的結果
      .pipe(
        // 使用 tap() 把檢視 Observable 的值，可以對值做些變化，真實值並不會受到改變
        tap(_ => this.log('fetched heroes'))
      );
  }
}
```

## HTML tag 標記

### HTML 文件

```html
<div>
  <label for="new-hero">Hero name: </label>
  <!-- 使用 #{變數名稱} 使標籤可在模板中調用 -->
  <input id="new-hero" #heroName />

  <!-- 在 (click) 中透過 #{變數名稱} 訪問與編輯標籤參數 -->
  <button
    type="button"
    class="add-button"
    (click)="add(heroName.value); heroName.value=''">
    Add hero
  </button>
</div>
```

## AsyncPipe 異步資料渲染

### HTML 文件

```html
<div id="search-component">
  <label for="search-box">Hero Search</label>
  <input #searchBox id="search-box" (input)="search(searchBox.value)" />

  <ul class="search-result">
    <!-- heroes$ 是一個 Observable -->
    <!-- async 會自動執行 Observable.subscribes() 所以不用再 TS 中訂閱了 -->
    <li *ngFor="let hero of heroes$ | async" >
      <a routerLink="/detail/{{hero.id}}">
        {{hero.name}}
      </a>
    </li>
  </ul>
</div>
```

### TS 文件

```typescript
import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Hero } from '../hero';
import { HeroService } from '../hero.service';

@Component({
  selector: 'app-hero-search',
  templateUrl: './hero-search.component.html',
  styleUrls: [ './hero-search.component.scss' ]
})
export class HeroSearchComponent implements OnInit {
  // 動態的請求資料，通道的資料對象
  heroes$!: Observable<Hero[]>;
  // 實現 Subject 來製作查詢通道
  // Subject 本身也是 Observable
  // Subject 也可以被 subscribe()
  private searchTerms = new Subject<string>();

  // 注入 service
  constructor(private heroService: HeroService) {}

  search(term: string): void {
    // 呼叫 Subject.next() 往通道推入值
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    // 初始化通道
    this.heroes$ = this.searchTerms.pipe(
      // 每次擊鍵後等待 300 毫秒，然後再考慮術語
      debounceTime(300),
      // 如果與上一個術語相同，則忽略新術語 (確保只在過濾條件變化時才傳送請求)
      distinctUntilChanged(),
      // 每次術語更改時切換到新的搜索 (從 debounce() 和 distinctUntilChanged() 中透過的搜尋詞調用搜索服務。它會取消並丟棄以前的搜尋可觀察物件，只保留最近的。)
      switchMap((term: string) => this.heroService.searchHeroes(term)),
    );
  }
}
```