package main

import (
	"api-CRUD/controllers"
	"api-CRUD/initializers"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
)

func index(c *gin.Context) {
	c.HTML(http.StatusOK, "index.html", nil)
}

func init() {
	initializers.LoadEnv()
	initializers.ConnectDB()
}

func main() {
	router := gin.Default()
	// http://localhost/static/text.txt
	router.Static("/static", "./static")
	router.LoadHTMLGlob("templates/*.html")

	router.GET("/", index)

	// person
	router.GET("/person/:id", controllers.GetPersonById)
	router.GET("/person/all", controllers.GetPersonAll)
	router.POST("/person", controllers.CreatePerson)
	router.PUT("/person/:id", controllers.UpdatePersonById)
	router.DELETE("/person/:id", controllers.DeletePersonById)

	router.Run("localhost:" + os.Getenv("port"))
}
