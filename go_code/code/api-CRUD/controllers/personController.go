package controllers

import (
	"api-CRUD/initializers"
	"api-CRUD/models"
	"net/http"

	"github.com/gin-gonic/gin"
)

func GetPersonAll(c *gin.Context) {
	gender, ok := c.GetQuery("gender")

	var persons []models.Person
	if ok {
		// initializers.DB.Find(&persons, "gender = ?", gender)
		initializers.DB.Where(&models.Person{
			Gender: gender,
		}).Find(&persons)
		c.IndentedJSON(http.StatusOK, persons)
		return
	}

	initializers.DB.Find(&persons)

	c.IndentedJSON(http.StatusOK, persons)
}

func CreatePerson(c *gin.Context) {
	newPerson := models.Person{}

	jsonParseErr := c.Bind(&newPerson)
	if jsonParseErr != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"status":  http.StatusBadRequest,
			"message": "Person JSON parse error",
		})
		return
	}

	result := initializers.DB.Create(&newPerson)

	if result.Error != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"status":  http.StatusBadRequest,
			"message": "Database create error",
		})
		return
	}

	c.IndentedJSON(http.StatusCreated, newPerson)
}

func GetPersonById(c *gin.Context) {
	id := c.Param("id")

	var person models.Person
	result := initializers.DB.First(&person, id)

	if result.Error != nil {
		c.IndentedJSON(http.StatusNotFound, gin.H{
			"status":  http.StatusNotFound,
			"message": "Person id: " + id + " not found.",
		})
		return
	}

	c.IndentedJSON(http.StatusOK, person)
}

func UpdatePersonById(c *gin.Context) {
	id := c.Param("id")

	var newPerson models.Person
	jsonParseErr := c.Bind(&newPerson)
	if jsonParseErr != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"status":  http.StatusBadRequest,
			"message": "Person JSON parse error",
		})
		return
	}

	var person models.Person
	result := initializers.DB.First(&person, id)
	if result.Error != nil {
		c.IndentedJSON(http.StatusNotFound, gin.H{
			"status":  http.StatusNotFound,
			"message": "Person id: " + id + " not found.",
		})
		return
	}

	initializers.DB.Model(&person).Updates(models.Person{
		Name:     newPerson.Name,
		Age:      newPerson.Age,
		Gender:   newPerson.Gender,
		Birthday: newPerson.Birthday,
	})

	c.IndentedJSON(http.StatusOK, person)
}

func DeletePersonById(c *gin.Context) {
	id := c.Param("id")

	initializers.DB.Delete(&models.Person{}, id)

	c.String(http.StatusOK, "Person id: "+id+" delete success.")
}
