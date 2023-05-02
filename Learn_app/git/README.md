## Git flow (Git 開發模式)

![](Git_And_GitHub_Images/git開發模式.png)



## Git  constructor (Git 結構)

![](Git_And_GitHub_Images/git架構.png)







# Git

## init (建立版本控制)

### 為當前目錄建立版本控制

```bash
git init
```

## clone (克隆)

### 克隆遠程庫到本地

- git clone {remote_address}

```bash
git clone https://github.com/alsk1369854/FunctionCaller.git
```



## status (狀態)

### 查看當前狀態

```bash
git status
```



## add (添加文件)

### 添加所有文件

- . : 所有文件

```bash
git add .
```

### 添加指定文件

- git add {file_name}

```bash
git add myfile.txt
```



## commit (提交修改)

### 提交所有修改

- -a : 所有文件

- -m "{commit_msg}" : 提交描述訊息

```bash
git commit -a -m "my commit message"
```

### 提交指定文件修改

- git commit -m "{commit_message}" {commit_file}

- -m "{commit_msg}" : 提交描述訊息

```bash
git commit -m "my commit message" myfile.txt
```



## push (推送)

### 本地推送至遠程分支

- git push {remote} {remote_branch_name}

```bash
git push origin myRemoteBranch
```

### 強制覆蓋遠程分支 (會覆蓋 log)

- git push -f {remote} {remote_branch_name}

- -f : 強制推送

```bash
git push -f origin myRemoteBranch
```



## fetch (拿取)

### 拿取遠程庫分支置本地

- git fetch {remote} {branch_name}

- 拿取遠程庫分支，並給予一個存時的存放空間

```bash
# 拿取遠程庫分支
git fetch origin myRemoteBranch

# 進入遠程庫分支
git checkout origin/myRemoteBranch
```



## merge (合併)

### 合併分支(一般)

- git merge {new_content_branch}

- 將指定的分支合併置當前分支

```bash
git merge myNewContentBranch 
```



### 解合併衝突

#### 1. 刪除特殊符號，選擇採用內容

- 保留當前分支內容:
  
  - git checkout --ours word.txt

- 保留拉取分支內容:
  
  - git checkout --theirs word.txt

```typescript
<<<<<<< HEAD
commit 當前分支內容
=======
pull 拉取分支內容
>>>>>>> myNewContentBranch
```

#### 2. 再次提交

```bash
git add .
git commit -a -m "merge myNewContentBranch"
```



## pull (拉取)

### 拉取遠程庫分支置當前本地分隻

- git pull {remote} {branch_name}

- 直接拉取遠程分支內容，合併置當前本地分支 (確保不會發生版本衝突才使用)

```bash
git pull origin myRemoteBranch
```



## branch (分支)

### 查看分支

#### 查看本地分支

```bash
git branch -v
```

#### 查看遠程分支

```bash
git branch -r
```



### 創建分支

- git branch {my_branch_name}

- 會根據當前所在的分支延伸出一個新的分支

```bash
git branch mybranch
```



### 刪除分支

#### 刪除本地分支

- git branch {-d | -D} {branch_name}

```bash
# 刪除以被推送或合併的分支
git branch -d mybranch

# 強制刪除
git branch -D mybranch
```



#### 刪除遠程分支

- git push {remote} --delete {remote_branch_name}

```bash
git push origin --delete myRemoteBranch
```



## log (日誌)

- Q : 離開

- Z : 下一頁

- P : 上一頁

### 查看摘要日志

```bash
git reflog -v
```

### 查看詳細日志

```bash
git log -v
```



## reset (重置)

### 強制回到指定紀錄點

- git reset --hart {log_commit_id}

```bash
git reset --hard 2650fcb
```



## remote (遠程地址別名)

### 查看遠程別名清單

```bash
git remote -v
```



### 建立遠程地址別名

- git remote add {remote_name} {remote_address}

```bash
git remote add remoteName https://github.com/alsk1369854/FunctionCaller.git  
```



## diff (比較差異)

- 頁面操作:
  
  - Q : 離開
  
  - Z : 下一頁
  
  - P : 上一頁

- 比較訊息說明:
  
  - diff --git a/file.txt b/file.txt
    
    - a : 比較方 
    
    - b : 被比較方
  
  - @@  -1  +1  @@
    
    - \- : 比較方文件行數
    
    - \+ : 被比較方文件行數
  
  - 差異內容
    
    - \- : 刪除行
    
    - \+ : 新增行
    
    - 空白 : 無變動

### 工作區 vs. 站存區

```bash
git diff
```



### 工作區 vs. 日誌

```bash
git diff HEAD
```



### 站存區 vs. 日誌

```bash
git diff --cached HEAD
```



### 日誌 vs. 日誌

```bash
git diff HEAD^ HEAD
```



## config (配置)

### 內容比對區分大小寫

- `--local` > `--global` > `--system`

```bash
git config --local core.ignorecase false
```

### 建立簽名

- git config [LEVLE] user.name {name}

- git config [LEVEL] user.email {email}

#### 項目級別

```bash
git config user.name ming
git config user.email ming@gmail.com
```



#### 系統級別

```bash
git config --global user.name ming
git config --global user.email ming@gmail.com
```




