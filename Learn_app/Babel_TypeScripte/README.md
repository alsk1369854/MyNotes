# Learn babel parser typescript

## Install
```shell
# general install
npm install --save-dev @babel/cli
npm install --save-dev @babel/core
npm install --save-dev @babel/preset-typescript

# babel config browser support
npm install --save-dev @babel/preset-env

# multi-commands util
npm install --save-dev concurrently

# all install
npm install --save-dev @babel/cli @babel/core @babel/preset-typescript @babel/preset-env concurrently
```

## 基本命令
```shell
# tsconfig.json
tsc --init
  
# compile code and build source maps
npx babel src --extensions .ts --out-dir lib --source-maps

# build types .d.ts
tsc 

# display debug error from .ts files(run with source maps)
node --enable-source-maps lib/index.js

```

## tsconfig.json
run "$tsc --init" command to build tsconfig.json file   
```json
/* Follow the settings below */
{
  "compilerOptions": {
    /*another config ....*/

    "rootDir": "./src",             /* Specify the root folder within your source files. */
    "declaration": true,            /* Generate .d.ts files from TypeScript and JavaScript files in your project. */
    "emitDeclarationOnly": true,    /* Only output d.ts files and not JavaScript files. */
    "outDir": "./lib",              /* Specify an output folder for all emitted files. */

    /*another config ....*/
  }
}
```

# babel.config.json
Create babel config file
- bash: 
  - $ touch babel.config.json
- Windows command: 
  - $ type nul > babel.config.json

```json
/* Follow the settings below */
{
    "presets": [
        "@babel/preset-typescript",
        [
            "@babel/preset-env",
            {
                "targets": "> 0.25%, not dead"
            }
        ]
    ]
}
```