# Jest Unit test

## Installation
```shell
# 建立 npm 專案
npm init -y

# 安裝 TypeScript
npm install -D typescript

# 安裝與 Jest 和 TS 相關的環境
npm install -D jest ts-jest @types/jest
```

## 設定 jest.config.js
```shell
npx ts-jest config:init
```

自訂一些配置:
+ coverageDirectory：Jest 輸出的 coverage 檔案要放在哪個目錄
  + 預設：undefined
+ preset：設定 preset
  + 預設：undefined
  + preset 應指向 root 目錄上具有 jest-preset.json 或 jest-preset.js 檔案的 npm 模組
+ testEnvironment：用於測試的測試環境
  + 預設："jsdom" (類似瀏覽器的環境)
  + 若要建置 node service，可用 "node" 來使用類似 node 的環境
+ testRegex：Jest 只執行 match 此 pattern 的測試檔案
  + 預設：(/__tests__/.*|(\\.|/)(test|spec))\\.[jt]sx?$
    + 即在 __tests__ 目錄內的 .js、.jsx、.ts 和 .tsx 檔，以及帶有 .test 或 .spec 後綴的任何檔案，例如：sum.test.ts 或 sum.spec.ts
  + Jest 會用 testRegex 配置的 pattern 來嘗試 match 測試檔案的絕對路徑

```javascript
module.exports = {
  coverageDirectory: "coverage",
  preset: 'ts-jest',
  testEnvironment: "node",
  testRegex: "(/__tests__/.*|(\\.|/)(test|spec))\\.tsx?$"
};
```

## 設定 package.json
```json
{
  "scripts": {
    "test": "jest --coverage"
  }
}
```

## 建立測試
```typescript
// src/__tests__/sum.test.ts
test('adds 1 + 2 to equal 3', () => {
  expect(1 + 2).toBe(3);
});
```

## 執行測試
```shell
npm run test
```