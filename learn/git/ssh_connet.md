


# SSH登入

    1. 進入當前用戶的家目錄
        $ cd ~
    
    2. 刪除 .ssh 目錄
        $ rm -rvf .ssh
    
    3. 運行命令生成 .ssh 密鑰目錄
        $ ssh-keygen -t rsa -C alsk1369854@gmail.com
        [注意: 這裡 -C 這個參數是大寫 C]
    
    4. 進入 .ssh 目錄查看文件列表
        $ cd .ssh
        $ ls -lF
    
    5. 查看 id_rsa.pub 文件內容
        $ cat id_rsa.pub
    
    6. 複製 id_rsa.pub 文件內容
    
    7. 登入GitHub，點擊用戶頭像 => Setting => SSH and GPG Keys => New SSH key
        輸入複製的密鑰訊息
    
    8. 回到 Git bash 創建SSH遠程地址別名
        git remote add origin_ssh git@github.com:alsk1369854/FunctionCaller.git
    
    9. 推送文件進行測試