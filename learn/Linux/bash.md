### 移除 port 監聽

```bash
# 查詢對應端口使用狀態
sudo lsof -i :8080

# 刪除運行的 PID
sudo kill 1234
```


