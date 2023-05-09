# Angular Cli

## Install angular cli (安裝  Angular 命令)

- --global : install for system level (全局安裝) 

```bash
npm install --global @angular/cli
```

## Create new project (建立新專案)

```bash
ng new {project name}
```

## Virtual serve (虛擬伺服器)

- --open : auto open on browser (自動在瀏覽器開啟端口訪問)

```bash
ng serve --open
```

## Component (組件)

### create component (創建 component)

```bash
ng g c {path}
```

### declaration in module (在 module 中聲明)

```typescript
// Component 聲明至 app.module.ts
....
import { HeroesComponent } from './heroes/heroes.component';

@NgModule({
 declarations: [
     AppComponent,
     HeroesComponent // add my component
 ],
 ....
})
export class AppModule { }
```

## Service (服務組件)

```bash
ng add {package_name}
```

## Module (模型組件)

### create module (建立模型組件)

- ng generate module {module_path}

```bash
ng g m ..../my-module
```

### import module (導入模型組件)

```typescript
// Component 聲明至 app.module.ts
....
import { MyModule } from './my-module/my-module.module.ts';

@NgModule({
 ....
 imports: [
     MyModule // add my module
 ],
})
export class AppModule { }
```

### router module (路由組件)

```bash
# --flat 把這個檔案放進了 src/app 中，而不是單獨的目錄中。
# --module=app 告訴 ng generate 把它註冊到 AppModule 的 imports 陣列中。
ng generate module app-routing --flat --module=app
```

## Tools

### vscode

- angular.ng-template : angular 語法檢查