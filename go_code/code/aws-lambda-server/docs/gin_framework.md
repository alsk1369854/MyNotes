# Gin framework



## 註冊路由

```go
func routerHandler(c *gin.Context) {
	// 包奘響應數據
    c.IndentedJSON(http.StatusOK, gin.H{
		"data": "Hello world",
	})
}

func main() {
	router := gin.Default()
    // 註冊路由與處理函數
	router.GET("/hello", routerHandler)
```



## 請求參數獲取

```go
func routerHandler(c *gin.Context){
    // http://.../person?gender=male
    gender, ok := c.GetQuery("gender")

    // http://.../person/{id}
    id := c.Param("id")

}
```
