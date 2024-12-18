package main

import (
	"aws-lambda/db"
	"aws-lambda/models"
	"encoding/json"
	"errors"
	"net/http"

	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
)

func HandleRequest(request events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	// 連線資料庫
	DB, err := db.GetConnect()
	if err != nil {
		return events.APIGatewayProxyResponse{
			StatusCode: http.StatusInternalServerError,
		}, errors.New("database connect error")
	}

	// 取得路由參數
	id := request.PathParameters["id"]
	path := request.Path
	pathParams := request.PathParameters
	queryParams := request.QueryStringParameters

	// 查詢資料
	var person models.Person
	result := DB.First(&person, id)
	if result.Error != nil {
		return events.APIGatewayProxyResponse{
			StatusCode: http.StatusNotFound,
		}, errors.New("person id: " + id + " not found")
	}

	// // 封裝 response body json data
	jsonPerson, err := json.Marshal(person)
	if err != nil {
		return events.APIGatewayProxyResponse{
			StatusCode: http.StatusInternalServerError,
		}, errors.New("person json parse error")
	}

	jsonPathParams, err := json.Marshal(pathParams)
	jsonQueryParams, err := json.Marshal(queryParams)

	var jsonMap = map[string]string{
		"path":            path,
		"pathParams":      string(jsonPathParams),
		"jsonQueryParams": string(jsonQueryParams),
		"person":          string(jsonPerson),
	}
	jsonData, err := json.Marshal(jsonMap)
	if err != nil {
		return events.APIGatewayProxyResponse{
			StatusCode: http.StatusInternalServerError,
		}, errors.New("json map parse error")
	}

	if err != nil {
		return events.APIGatewayProxyResponse{
			StatusCode: http.StatusInternalServerError,
		}, errors.New("person json parse error")
	}

	// 回傳 response
	return events.APIGatewayProxyResponse{
		StatusCode: http.StatusOK,
		Headers:    map[string]string{"Content-Type": "application/json"},
		Body:       string(jsonData),
	}, nil
}

func main() {
	// 註冊 lambda handle 函數
	lambda.Start(HandleRequest)
}
