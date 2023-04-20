# Angular Cli

### 建立專案

```bash
ng new {project name}
```

### 啟動虛擬 serve

```bash
ng serve --open
```

### 建立 Component

```bash
# 全名
ng generate component {path}
# 簡寫
ng g c {path}
```

### 建立 Service

```bash
# 全名
ng generate service {path}
# 簡寫
ng g s {path}
```

#### 會自動完成 Component 的建立

1. 建立 html

2. 建立 scss

3. 建立 ts

4. 註冊至 app.module.ts
   
   ```typescript
   // Component 註冊至 app.module.ts
   ....
   import { HeroesComponent } from './heroes/heroes.component';
   
   @NgModule({
    declarations: [
    AppComponent,
    HeroesComponent
    ],
    ....
   })
   export class AppModule { }
   ```

### 安裝工具包

```
```bash
ng add {package_name}
```

### 創建 Router module

```bash
# --flat 把這個檔案放進了 src/app 中，而不是單獨的目錄中。
# --module=app 告訴 ng generate 把它註冊到 AppModule 的 imports 陣列中。
ng generate module app-routing --flat --module=app
```