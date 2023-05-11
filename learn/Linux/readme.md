# Linux

1. 常用 shell 命令
   1. ping {IP位置|主機名稱} #檢查是否可以連線
   2. ls | grep [篩選文字] #篩選
   3. pwd #查看當前路徑
2. 網路配置 
   1. 修改靜態IP
   2. 配置主機名
   3. 配置IP主機名對照表
3. 遠端登入
   1. ssh [帳號@目的主機]
   2. scp [帳號@來源主機]:來源檔案 [帳號@目的主機]:目的檔案
   3. 遠端工具 下載地址:https://www.netsarang.com/en/free-for-home-school/
      1. 命令 => Xshell 
      2. 文件 => Xftp 
4. Linux 服務管理
   1. systemclt {start|stop|restart|status} [服務名] #CentOS 7
   2. service [服務名] {start|stop|restart|status} #CentOS 6
5. 錯誤解決

<br/>

## 1. 常用 shell 命令

### 1-1. ping {IP位置|主機名稱} #檢查是否可以連線

```shell
# 發送網路封包給指定地址
$ ping {IP位置|主機名稱}
$ ping 192.168.100.10
$ ping google.com

# 查看 Linux kernel 版本
$ uname -r

# 升級軟件包與內核
$ yum update

# 重啟網路程序
$ service restart network

# 重啟 NetworkManager 服務(UI介面) 
$ systemctl restart NetworkManager
$ systemctl restart network.device
```

<br/>

### 1-2. ls | grep [篩選文字] #篩選

```shell
# 篩選
[root@ChiaMingCentOS etc]# ls /etc/ | grep host
ghostscript
host.conf
hostname
hosts
hosts.allow
hosts.deny
```

<br/>

### 1-3. pwd #查看當前路徑

```shell
# 查看當前路徑
[root@minglinux100 ~]# pwd
/root
```

## 2. 網路配置

### 2-1. Setting Static IP Address

#### 2-2-1. 查看連線地址(範例為有線網路 ens33)

```shell
[root@ChiaMingCentOS network-scripts]# ifconfig
ens33: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.198.128  netmask 255.255.255.0  broadcast 192.168.198.255
        inet6 fe80::8213:32e9:d8c:8dba  prefixlen 64  scopeid 0x20<link>
        ether 00:0c:29:00:fc:1e  txqueuelen 1000  (Ethernet)
        RX packets 642  bytes 72771 (71.0 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 129  bytes 15289 (14.9 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
```

#### 2-2-2. 修改配置文檔

```shell
[root@ChiaMingCentOS network-scripts]# vim /etc/sysconfig/network-scripts/ifcfg-ens33
```

##### 2-2-2-1. 修改如下

```vim
TYPE=Ethernet
PROXY_METHOD=none
BROWSER_ONLY=no
# IP Configuration method {none|static|bootp|dhcp}
BOOTPROTO=static
DEFROUTE=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=ens33
UUID=668513a7-ade3-4d4c-93b3-a24ee11cb879
DEVICE=ens33
ONBOOT=no

# Static IP address
IPAADDR=192.168.198.100
# Gateway
GATEWAY=192.168.198.2
# DNS
DNS1=192.168.198.2
```

#### 2-2-3. 重啟網路連線，檢查IP是否為靜態IP

```shell
[root@ChiaMingCentOS network-scripts]# service network restart
Restarting network (via systemctl):                        [  OK  ]
[root@ChiaMingCentOS network-scripts]# ifconfig
ens33: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.198.100  netmask 255.255.255.0  broadcast 192.168.198.255
        inet6 fe80::8213:32e9:d8c:8dba  prefixlen 64  scopeid 0x20<link>
        ether 00:0c:29:00:fc:1e  txqueuelen 1000  (Ethernet)
        RX packets 2538  bytes 203998 (199.2 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 308  bytes 32320 (31.5 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
```

<br/>

### 2-2. 配置主機名

> $ hostnamectl #查看主機名相關資訊
> 
> $ hostnamectl set-hostname {新名稱} #變更主機名

```shell
#查看主機名相關資訊
[root@ChiaMingCentOS network-scripts]# hostnamectl
   Static hostname: ChiaMingCentOS
         Icon name: computer-vm
           Chassis: vm
        Machine ID: db13e9cddd114d3ea8923595ad9e6f71
           Boot ID: 01320f5dbb5e462ebe897c3f6d81455e
    Virtualization: vmware
  Operating System: CentOS Linux 7 (Core)
       CPE OS Name: cpe:/o:centos:centos:7
            Kernel: Linux 3.10.0-1160.el7.x86_64
      Architecture: x86-64
You have mail in /var/spool/mail/root
```

```shell
#變更主機名
[root@ChiaMingCentOS network-scripts]# hostnamectl set-hostname minglinux100
[root@ChiaMingCentOS network-scripts]# hostnamectl
   Static hostname: minglinux100
         Icon name: computer-vm
           Chassis: vm
        Machine ID: db13e9cddd114d3ea8923595ad9e6f71
           Boot ID: 01320f5dbb5e462ebe897c3f6d81455e
    Virtualization: vmware
  Operating System: CentOS Linux 7 (Core)
       CPE OS Name: cpe:/o:centos:centos:7
            Kernel: Linux 3.10.0-1160.el7.x86_64
      Architecture: x86-64
```

<br/>

### 2-3. 配置IP主機名對照表

```vim
# CentOS Path: /etc/hosts
# Windows Path: C:\Windows\System32\drivers\etc\hosts
# 在文本最下方加上 IP hostname

192.168.198.100 minglinux100
192.168.198.101 minglinux101
192.168.198.102 minglinux102
192.168.198.103 minglinux103
192.168.198.104 minglinux104
```

<br/>

## 3. 遠端登入

### 3-1. ssh [帳號@目的主機]

```cmd
C:\WINDOWS\system32>ssh root@minglinux100
The authenticity of host 'minglinux100 (192.168.198.100)' can't be established.
ECDSA key fingerprint is SHA256:ilThWZoa3vIPczHTiRyervzMtjFRxHOmfdn0AfND4gE.
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added 'minglinux100,192.168.198.100' (ECDSA) to the list of known hosts.
root@minglinux100's password:
Last login: Tue Aug  9 06:57:24 2022
[root@minglinux100 ~]# ls
anaconda-ks.cfg  Documents  initial-setup-ks.cfg  Pictures  Templates
Desktop          Downloads  Music                 Public    Videos
[root@minglinux100 ~]#
```

<br/>

### 3-2. scp [帳號@來源主機]:來源檔案 [帳號@目的主機]:目的檔案

#### 3-2-1. 從本地端複製到遠端

```shell
# 從本地端複製到遠端
scp /path/file1 myuser@192.168.0.1:/path/file2
```

#### 3-2-2. 從遠端複製到本地端

```shell
# 從遠端複製到本地端
scp myuser@192.168.0.1:/path/file2 /path/file1
```

#### 3-2-3. 複製目錄

```shell
# 複製目錄
scp -r /path/folder1 myuser@192.168.0.1:/path/folder2
```

#### 3-2-4. 保留檔案時間與權限

```shell
# 保留檔案時間與權限
scp -p /path/file1 myuser@192.168.0.1:/path/file2
```

#### 3-2-5. 資料壓縮

```shell
# 資料壓縮
scp -C /path/file1 myuser@192.168.0.1:/path/file2
```

#### 3-2-6. 限制傳輸速度為

```shell
# 限制傳輸速度為 400 Kbit/s
scp -l 400 /path/file1 myuser@192.168.0.1:/path/file2
```

<br/>

## 4. Linux 服務管理

### 4-1. systemclt {start|stop|restart|status} [服務名] #CentOS 7

```shell
# 服務管理: systemclt {start|stop|restart|status} [服務名]
# 服務位址: /usr/lib/systemd/system
# .service => 服務
# .target => 服務集合
[root@minglinux100 system]# systemctl status NetworkManager
● NetworkManager.service - Network Manager
   Loaded: loaded (/usr/lib/systemd/system/NetworkManager.service; enabled; vendor preset: enabled)
   Active: active (running) since Tue 2022-08-09 06:56:45 CST; 1 day 13h ago
     Docs: man:NetworkManager(8)
 Main PID: 842 (NetworkManager)
    Tasks: 3
   CGroup: /system.slice/NetworkManager.service
           └─842 /usr/sbin/NetworkManager --no-daemon
```

<br/>

### 4-2. service [服務名] {start|stop|restart|status} #CentOS 6

```shell
# 服務管理: service [服務名] {start|stop|restart|status}
# 服務位址: /etc/init.d
# .d => daemon 常駐程式
[root@minglinux100 system]# service network restart
Restarting network (via systemctl):                        [  OK  ]
```

<br/>

## 文件修改權限修改

```shell
# 修改為可修改文件
$ chmod -v u+w {filepath}

# 修改為唯獨文件
$ chmod -v u-w {filepath}
```

<br/>

## 錯誤解決

### 網路重啟錯誤(Job for network.service failed because the control process exited with error code.)

```shell
# 停止 NetworkManager 程序
$ systemctl stop NetworkManager
$ systemctl disable NetworkManager

# 重啟網卡
$ systemctl restart network
```
