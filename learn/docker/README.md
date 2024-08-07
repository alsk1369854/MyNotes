# Docker

[Docker Hub](https://hub.docker.com/)

- [] : 表示為可選的

- {} : 表示則一的

## 基本常用命令

### 幫助命令

#### 版本查詢

```shell
docker version
```

#### 詳細訊息

```shell
docker info
```

#### 幫助命令

```shell
docker --help
```

### 鏡像命令

#### 查看本地鏡像

- -a : 列出本地所有鏡像(含中間映像層)

- -q : 只顯示鏡像ID

- --digests : 顯示鏡像的摘要信息

- --no-trunc : 顯示完整的鏡像描述

- 鏡像訊息意涵:
  
  - REPOSITORY : 鏡像名稱
  
  - TAG : 鏡像的版本
  
  - IMAGE ID : 鏡像ID
  
  - CREATED : 鏡像在多久以前被創建至本地
  
  - SIZE : 鏡像大小

```shell
# 顯示鏡像清單
docker imags

# 顯示鏡像清單(含中間映像層)
docker images -a

# 顯示鏡像的ID清單
docker images -q
```

#### 刪除本地鏡像

- 可在鏡像後面使用 :{TAG} 來指定刪除的版本，預設為 :latest

- -f : 強制刪除

```shell
# 刪除單個
docker rmi -f {image_name | image_ID}[:TAG]

# 刪除多個
docker rmi -f {image_name1}[:TAG] {image_name2}[:TAG]

# 刪除所有本地鏡像
docker rmi -f $(docker images -qa)
```

#### 搜索 Docker Hub 上的鏡像 (docker search)

- -s {number} : 只列出收藏數不小於 {number} 的鏡像

- --no-trunc : 顯示完整的鏡像描述

- --automated : 只列出 "自動化創建" 的鏡像

- 鏡像訊息意涵:
  
  - NAME : 鏡像的名稱
  
  - DESCRIPTION : 鏡像的描述
  
  - START : 被收藏數量
  
  - OFFICIAL : 是否來自於官方
  
  - AUTOMATED : 使否自動化創建

```shell
docker search {image_name}
```

#### 拉取 Docker Hub 上的鏡像 (docker pull)

- 可在在拉取鏡像後面使用 :{TAG} 來指定拉取的版本，預設為 :latest

```shell
docker pull {image_name}[:TAG]
```

#### 提交 新鏡像 (docker commit)

- 將現有的容器提交成為一個新的鏡像版本

- -a :  提交作者名

- -m  : 提交內容描述

```bash
docker commit -a="author_name" -m="commit_message" {container} {my_image_name}[:version] 
```

### 容器命令

- (根本前提) 有鏡像才能創建容器

- 

#### 創建並啟動容器

- docker run [OPTION] {IMAGE} [COMMAND] [ARG...]

- OPTION 說明 (常用):
  
  - --name={container_name} : 為容器指定一個名稱
  
  - -d : 後台運行，並返回容器ID，也即啟動守護式容器
  
  - -i : 以交互模式運行容器，通常與 -t 同時使用
  
  - -t : 為容器重新分配一個偽輸入終端，通常與 -i 同時使用
  
  - -P : 隨機端口映射
  
  - -p : 指定端口映射，有以下四種格式
    
    - ip:hostPort:containerPort
    
    - ip::containerPort
    
    - hostPort:containerPort
    
    - containerPort

```bash
# 創建並啟動後臺容器
docker run -d --name={container_name} {image_name | image_ID}
docker run -dt --name={container_name} {image_name | image_ID}

# 創建並啟動交互式容器
docker run -it --name={container_name} {{image_name | image_ID}}
```

#### 退出交互式容器

- 容器停止退出:
  
  - 交互終端介面輸入 exit 命令

- 容器不停止退出:
  
  - 交互終端介面按下快捷鍵 ctrl + Q + P

#### 列出當前所有正在運行的容器

- docker ps [OPTION]

- OPTION 說明 (常用):
  
  - -a : 列出當前所有**正在運行**的容器+**歷史上運行過**的容器
  - -l : 顯示最近創建的容器
  - -n : 顯示最近 n 個創建的容器
  - **-q : 靜默模式，只顯示容器編號**
  - --no-trunc : 不截斷輸出

```bash
# 當前運行的所有容器
docker ps

# 最近一個創建的容器 
docker ps -l

# 最近前 n 個創建的容器
docker ps -n {number}
```

#### 啟動容器

```bash
# 已後台運行，啟動已創建容器
docker start -d {container_name | container_ID}

# 已交互式，啟動已創建容器
docker start -it {container_name | container_ID}
```

#### 重啟容器

```bash
docker restart {container_name | container_ID}
```

#### 停止容器

```bash
# 一般停止
docker stop {container_name | container_ID}

# 強制停止
docker kill {container_name | container_ID}
```

#### 刪除容器

```bash
# 刪除已停止容器
docker rm {container_name | container_ID}

# 強制刪除容器
docker rm -f {container_name | container_ID}

# 刪除所有容器
docker rm -f $(docker ps -qa)
```

#### 查看容器日志

- docker logs [OPTION] {CONTAINER}

- OPTION 說明 (常用):
  
  - -f : 跟隨最新的日志打印
  
  - -t : 加入時間戳
  
  - --tail {number} 顯示最後多少條

```bash
docker logs -f -t --tail {number} {container_name | container_ID}
```

#### 查看容器運行執行續

- docker top {CONTAINER}

```bash
docker top {container_name | container_ID}
```

#### 查看容器內部細節

- docker inspect {CONTAINER}

```bash
docker inspect {container_name | container_ID}
```

#### 進入正在運行的容器並執行交互

- docker exec -it {CONTAINER} {bash_shell} : 不進入執行 shell 命令

- docker attach {CONTAINER} : 進入容器終端介面

```bash
# 不進入容器執行 shell 命令
docker exec -it {container_name | container_ID} {bash_shell}

# 進入容器終端介面
# 方法一
docker attach {container_name | container_ID}
# 方法二
docker exec -it {container_name | container_ID} /bin/bash
```

#### 從容器拷貝文件

- docker cp {src_path} {dest_path}

```bash
docker cp {src_path} {dest_path}
docker cp {container_name | container_ID}:/tmp/yum.log /root
```

## 數據卷 (Data Volume)

### 命令添加數據卷

- docker run [OPTION] -v {host_volume_dir_path}:{container_volume_dir_path} {IMAGE}

- 在宿主機與容器內各建立一個目錄來達到數據共享的效果

- :ro : 容器訪問共享書據為 read-only (默認為可讀可寫)

- 查看 container 訊息 : docker inspect {CONTAINER}
  
  - Volumes : 容器目錄對應宿主機目錄的位置
  
  - VolumesRW : 容器對於目錄是否有可讀可寫

- Error 解決辦法:
  
  - cannot open directory .: Permission denied
    
    - 解決 : 再 [OPTION] 加上 --privileged=true

```base
docker run -it -v /宿主機絕對路徑目錄:/容器內目錄[:ro] {IMAGE}
```

### DockerFile 添加數據卷

#### 建立步驟

1. 建立 DockerFile 文件

2. 建立 image : docker build -t {DockerFile_path} -t {this_image_name} .

3. 運行 image container : docker run -it [OPTION] {IMAGE}

4. 查看 container 訊息 : docker inspect {CONTAINER}
   
   - Volumes : 容器目錄對應宿主機目錄的位置
   
   - VolumesRW : 容器對於目錄是否有可讀可寫

#### 錯誤處理

- cannot open directory .: Permission denied
  
  - 解決 : 再 [OPTION] 加上 --privileged=true

#### DockerFile 範例

```docker
FROM centos
VOLUME ["/dataVolume1", "/dataVolume2"]
CMD echo "finished, ---------- success1"
CMD /bin/base
```

### 容器間數據卷共享

- docker run -it --volume-from {src_container} [OPTION] {IMAGE}

- --volume-from {container} : 指定參照的共享數據容器

```bash
docker run -it --volume-from {my_container1} {IMAGE}
```

#### 使用步驟

1. 建立 個容器的 DockerFile ，並在內部配置相同的 volume 目錄名稱 (參考: 數據卷 > DockerFile 添加數據卷)

2. 啟動主要的容器

3. 啟動額外的容器，並在 [OPTION] 中加入 --volumn-from {src_container} 命令，使數據卷相互相通

## DockerFile

### 保留字介紹 (Key words)

| Key Word   | Description                                                                           |
| ---------- | ------------------------------------------------------------------------------------- |
| FROM       | 基礎鏡像，當前鏡像是基於哪個鏡像的                                                                     |
| MAINTAINER | 鏡像維護者的姓名和郵箱                                                                           |
| RUN        | 容器構建時需要運行的命令                                                                          |
| EXPOSE     | 當前容器對外暴露出的端口                                                                          |
| WORKDIR    | 指定在創建容器後，終端登入進來默認的工作目錄                                                                |
| ENV        | 用來定義，在建構鏡像過程中的環境變量                                                                    |
| ADD        | 類似 COPY 拷貝文件或目錄至鏡像中。<br/>但 ADD 命令會在拷貝結束後，自動處裡 URL 和解壓縮 tar 壓縮包                        |
| COPY       | 將宿主機目錄下的文件拷貝進鏡像中。<br/>Example:<br/>    - COPY src dest<br/>    - COPY ["src", "dest"] |
| VOLUME     | 容器數據卷，用於數據保存和持久化工作                                                                    |
| CMD        | 指定一個容器要啟動時要運行的命令<br/>可以有多個 CMD 指令，但只有最後一個會生效，CMD 會被 docker run 之後的參數給替換掉              |
| ENTRYPOINT | 指定一個容器要啟動時要運行的命令<br/>ENTRYPOINT 的目的和 CMD 一樣，但是 ENTRYPOINT 不會被覆蓋，新添加的指令會拼接再原先舊指令後面     |
| ONBUILD    | 用於執行在當鏡像被繼承時，需要的預前處理工作                                                                |

### 建立鏡像 (docker build)

- docker build -f {PAHT/Dockerfile} -t {image_title}:{image_tag} {base_dir_path}

- OPTION 說明:
  
  - -f {PAHT/Dockerfile} : 指定 Dockerfile 根據 base_dir_path 下的所在位置
  
  - -t {image_title}:{image_tag} : 指定鏡像名稱與版本號
  
  - {base_dir_path} : 將哪個目錄做為選擇 Dockerfile 的根目錄 ("." 表示為當前目錄)
  
  - --platform: linux/amd64,linux/arm64：此`--platform`標誌通知 buildx 為 x86 64 位元、ARM 64 位元架構 Linux 鏡像。

```bash
docker build -f Dockerfile -t ming/centos1:1.0 . 
```

#### 範例一 (CMD 命令)

- 下方 Dockerfile 最後一行是使用 CMD 來執行命令，此時我們如果使用 **docker run -it {image} echo "Hi~"** ，來執行此 Dockerfile 創建的鏡像，此時原先的 /bin/bash 指令將會新的指令覆蓋，只單單執行下行命令
  
  - echo "Hi~"

```docker
# extends centos
FROM centos

# auther
MAINTAINER alsk1369854 <alsk1369854@gmail.com>

# defined variable
ENV BASEPATH /usr/local

# set terminal default work path and use variable
WORKDIR ${BASEPATH}

# init container
RUN touch myInit.txt

# export port number
EXPOSE 80

# default run terminal command
CMD ["/bin/bash"]
```

#### 範例二 (ENTRYPOINT 拼接命令)

- 下方 Dockerfile 最後一行是使用 ENTRYPOINT 來執行命令，此時我們如果使用 **docker run -it {image} -i** ，來執行此 Dockerfile 創建的鏡像，此時 -i 指令將會被拼接再原指令的後命變為執行下行命令
  
  - curl -s http://ip.cn -i

```dockerfile
# extends centos
FROM centos

# auther
MAINTAINER alsk1369854 <alsk1369854@gmail.com>

# defined variable
ENV BASEPATH /usr/local

# set terminal default work path and use variable
WORKDIR ${BASEPATH}

# init curl
RUN ynm install -y curl

# export port number
EXPOSE 80

# default run terminal command
ENTRYPOINT [ "curl", "-s", "http://ip.cn" ]
```

#### 範例三 (ONBUILD 被繼承時的前置準備工作)

- 下方 Dockerfile 最後一行使用了 ONBUILD ，這項會使此 Dockerfile 生成的鏡像，在被其他 Dockerfile 使用 FROM 引用建立時，將會優先執行被參照的 Dockerfile 的 ONBUILD 指令任務 

```dockerfile
# extends centos
FROM centos

# auther
MAINTAINER alsk1369854 <alsk1369854@gmail.com>

# defined variable
ENV BASEPATH /usr/local

# set terminal default work path and use variable
WORKDIR ${BASEPATH}

# init container
RUN touch myInit.txt

# export port number
EXPOSE 80

# default run terminal command
CMD ["/bin/bash"]

# when becomeing a FROM image and run this command
ONBUILD RUN echo "prepare become FROM image..."
```

#### 範例四 (COPY、ADD 添加文件至鏡像)

- COPY 指令會將宿主機的文件拷貝至鏡像中

- ADD 指令除了執行拷貝至鏡像中外，若檔案為 tar 則會自動進行解壓縮

```dockerfile
# extends centos
FROM centos

# auther
MAINTAINER alsk1369854 <alsk1369854@gmail.com>

# defined variable
ENV BASEPATH /usr/local
# set terminal default work path and use variable
WORKDIR ${BASEPATH}

# copy file
COPY myText.txt /usr/local/tempText.txt
# add file
# install from https://www.oracle.com/tw/java/technologies/javase/javase8-archive-downloads.html
ADD jdk-8u202-linux-x64.tar.gz /usr/local/
# install from https://tomcat.apache.org/download-90.cgi
ADD apache-tomcat-9.0.74.tar.gz /usr/local/

# config location variable
ENV JAVA_HOME=/usr/local/jdk1.8.0_202
ENV CLASSPATE=${JAVA_HOME}/lib/dt.jar:${JAVA_HOME}/lib/tools.jar
ENV CATALINA_HOME=/usr/local/apache-tomcat-9.0.74
ENV CATALINA_BASE=/usr/local/apache-tomcat-9.0.74
ENV PATH=$PATH:${JAVA_HOME}/bin:${CATALINA_HOME}/bin:${CATALINA_HOME}/lib

# export port number
EXPOSE 8080

# default run terminal command
CMD /usr/local/apache-tomcat-9.0.74/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.74/bin/catalina.sh
```

### 推送鏡像 (push)

- docker push {image_name}

```bash
# 登入 docker
docker login

# 添加版本號
docker tag fmsserver alsk1369854/fmsserver

# 推送鏡像至 docker hub
docker push alsk1369854/fmsserver
```

## Docker composer

### 編輯

```docker
version: "1"

services:

  fmsmysql:
    image: "mysql:8.0.33"
    container_name: fmsmysql
    ports:
      - 3306:3306
    # restart: always
    volumes:
      - ./mysql:/var/lib/mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      # MYSQL_USER: root
      # MYSQL_PASSWORD: your_password
      MYSQL_DATABASE: fms

  fmsserver:
    # build: ./my_super_app
    image: "alsk1369854/fmsserver"
    container_name: fmsserver
    ports:
      - 8080:8080
    # restart: always
    links:
      - fmsmysql:fmsmysql
    depends_on:
      - fmsmysql
    command: sh -c "/wait && /sayhello"
    environment:
      - WAIT_HOSTS=fmsmysql:3306
      - WAIT_HOSTS_TIMEOUT=300
      - WAIT_SLEEP_INTERVAL=30
      - WAIT_HOST_CONNECT_TIMEOUT=30
```

### 創建 (up)

```bash
# 背景運行創建
docker-compose up -d
```

### 啟動 (start)

```bash
docker-compose start
```

### 停止 (stop)

```bash
docker-compose stop
```

## Docker run MySQL

```bash
docker run -p 3306:3306 --name mysql 
-v /mysql/conf:/etc/mysql/conf.d 
-v /mysql/logs:/logs 
-v /mysql/data:/var/lib/mysql 
-e MYSQL_ROOT_PASSWORD=root 
-d mysql
```

### pull mysql image

```bash
docker pull mysql:8.0.25
```

### run mysql container

```bash
# MYSQL_ROOT_PASSWORD=root : 表示 root 用戶的初始密碼
# mysql:8.0.25 : 表示啟動的 mysql 版本，未指定則默認最新版
docker run --name mymysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql:8.0.25
```

## Docker compost simple

### postgres

```yml
version: "3.7"
services:
    db:
        image: postgres
        # restart: always
        # platform: inux/amd64,linux/arm64
        environment:
            POSTGRES_DB: bpm_apis
            POSTGRES_USER: postgres
            POSTGRES_PASSWORD: hu5509222
            PGDATA: /var/lib/postgresql/data
        volumes:
            - .db_data:/var/lib/postgresql/data
        ports:
            - 5432:5432

    adminer:
        image: adminer
        # restart: always
        # platform: inux/amd64,linux/arm64
        ports:
            - 8888:8080
```

## Docker image simple

### ubuntu vm

```bash
docker run -dt --name=pyhton3 -v /Users/chiaming/Documents/docker-share/python3:/root/share -p 5000:8080 ubuntu
docker run -dt --name=pyhton3 -v /home/wtleep2000/chiaming/python-workspace/docker-share:/root/share -p 7020:22 -p 8000:80 ubuntu
```