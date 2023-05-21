package main

import (
	"aws-lambda-server/src/main/controllers"
	"aws-lambda-server/src/main/db"
	"aws-lambda-server/src/main/initializers"
	"context"
	"net/http"
	"os"

	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
	ginadapter "github.com/awslabs/aws-lambda-go-api-proxy/gin"
	"github.com/gin-gonic/gin"
)

var ginLambda *ginadapter.GinLambda

func index(c *gin.Context) {
	c.HTML(http.StatusOK, "index.html", nil)
}

func Handler(ctx context.Context, request events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	return ginLambda.ProxyWithContext(ctx, request)
}

func init() {
	env := os.Getenv("GIN_MODE")
	if env != "release" {
		initializers.LoadEnv()
	}
	db.ConnectDB()
}

func main() {
	router := gin.Default()
	// http://localhost/static/text.txt
	router.Static("/static", "./resources/static")
	router.LoadHTMLGlob("resources/templates/*.html")

	router.GET("/index", index)

	// person controller routers init
	controllers.InitPersonControllerRouters(router)

	env := os.Getenv("GIN_MODE")
	if env == "release" {
		ginLambda = ginadapter.New(router)

		lambda.Start(Handler)
	} else {
		router.Run(":8080")
	}
}
