package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
)

func Hello(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{
		"data": "Hello",
	})
}

func Video(c *gin.Context) {
	videoPath := "./assets/demo.mp4" // 替換為實際的影片檔案路徑

	videoFile, err := os.Open(videoPath)
	if err != nil {
		c.String(http.StatusInternalServerError, "無法開啟影片檔案")
		return
	}
	defer videoFile.Close()

	stat, err := videoFile.Stat()
	if err != nil {
		c.String(http.StatusInternalServerError, "無法取得影片檔案資訊")
		return
	}

	c.Header("Content-Type", "video/mp4")
	c.Header("Content-Disposition", "attachment; filename=video.mp4")
	c.Header("Content-Length", fmt.Sprint(stat.Size()))

	http.ServeContent(c.Writer, c.Request, "", stat.ModTime(), videoFile)
}

func Image(c *gin.Context) {
	imageBytes, err := ioutil.ReadFile("./assets/demo.png")
	if err != nil {
		c.Status(http.StatusInternalServerError)
		return
	}
	c.Data(http.StatusOK, "image/png", imageBytes)
}

func index(c *gin.Context) {
	c.HTML(http.StatusOK, "index.html", nil)
}

func main() {
	router := gin.Default()

	// http://localhost/static/text.txt
	router.Static("/static", "./resources/static")
	router.LoadHTMLGlob("resources/templates/*.html")

	router.GET("/index", index)

	router.GET("/hello", Hello)
	router.GET("/video", Video)
	router.GET("/image", Image)
	router.Run()
}
