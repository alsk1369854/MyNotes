## 安裝 scrapy

```bash
pip install scrapy
```

#### 安裝可能發生錯誤

1. building 'twisted.test.raiser' extension

```
- 解決 1: https://www.lfd.uci.edu/~gohlke/pythonlibs/#twisted
- Twisted‑20.3.0‑cp39‑cp39‑win_amd64.whl
- cp 是 python 版本 例如:3.9.0
- cmd python
- amd 是系統位元
```

2. 提示 python -m pip install --upgrade pip

```
- 解決 2: 運行 python -m pip install --upgrade pip
```

3. win32 的錯誤

```
- 解決 pip install pypiwin32
```

4. 還是有錯就下載 anaconda



## Scrapy 專案

### 創建專案

```bash
# scrapy startproject {projectname}
scrapy startproject project_01
```

### 關閉機器人協定

#### project_01/project_01/settings.py

```python
# 註解掉 ROBOTSTXT_OBEY 配置
# ROBOTSTXT_OBEY = True
```

### 創建爬蟲文件

```bash
# 文件必須創建在專案中的 spiders 目錄下
# 切換到專案 spiders 目錄
cd project_01/project_01/spiders


# 創建爬蟲文件
# scrapy gensipider {爬蟲名稱} {爬取地址(不加 http 前墜)}
scrapy genspider google www.google.com
```

ˊ

### 運行爬蟲文件

```bash
# scrapy srawl {爬蟲名稱}
scrapy crawl google
```
