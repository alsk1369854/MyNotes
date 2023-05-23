package controllers

import (
	"aws-lambda-server/src/main/exceptions"
	"aws-lambda-server/src/main/models"
	"aws-lambda-server/src/main/services"
	"net/http"

	"github.com/gin-gonic/gin"
)

func GetPersonAll(c *gin.Context) {
	gender, ok := c.GetQuery("gender")

	if !ok {
		gender = ""
	}

	persons, err := services.GetPersonAll(gender)
	if err != nil {
		c.IndentedJSON(http.StatusInternalServerError, gin.H{
			"error": err.Error(),
		})
		return
	}
	c.IndentedJSON(http.StatusOK, persons)
}

func CreatePerson(c *gin.Context) {
	newPerson := &models.Person{}

	jsonParseErr := c.Bind(newPerson)

	if jsonParseErr != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"error": exceptions.PersonJsonParseException().Error(),
		})
		return
	}

	person, err := services.CreatePerson(newPerson)

	if err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"error": err.Error(),
		})
		return
	}

	c.IndentedJSON(http.StatusCreated, person)
}

func GetPersonById(c *gin.Context) {
	id := c.Param("id")

	person, err := services.GetPersonById(id)

	if err != nil {
		c.IndentedJSON(http.StatusNotFound, gin.H{
			"error": err.Error(),
		})
		return
	}

	c.IndentedJSON(http.StatusOK, person)
}

func UpdatePersonById(c *gin.Context) {
	id := c.Param("id")

	srcPerson := &models.Person{}
	jsonParseErr := c.Bind(srcPerson)
	if jsonParseErr != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"error": exceptions.PersonJsonParseException().Error(),
		})
		return
	}

	person, err := services.UpdatePersonById(id, srcPerson)

	if err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"error": err.Error(),
		})
		return
	}

	c.IndentedJSON(http.StatusOK, person)
}

func DeletePersonById(c *gin.Context) {
	id := c.Param("id")

	message, err := services.DeletePersonById(id)
	if err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"error": err.Error(),
		})
		return
	}

	c.String(http.StatusOK, message)
}

func InitPersonControllerRouters(router *gin.Engine) {
	// /person/id
	personRouter := router.Group("/person")
	{
		personRouter.GET("/:id", GetPersonById)
		personRouter.GET("/all", GetPersonAll)
		personRouter.POST("", CreatePerson)
		personRouter.PUT("/:id", UpdatePersonById)
		personRouter.DELETE("/:id", DeletePersonById)
	}
}
