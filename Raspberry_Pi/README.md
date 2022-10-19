# Raspberry Pi 

## 關機/重開機
```
// 關機
sudo shutdown -h now

// 重開機
sudo shutdown -r now
```

## 系統設定
```
sudo raspi-config
```

## 攝相機指令
```
// 拍照並存到桌面上
sudo libcamera-still -o ~/Desktop/output.jpg

// 相機配置設定
sudo vim /boot/config.txt

...
[all]
gpu

ov5
```