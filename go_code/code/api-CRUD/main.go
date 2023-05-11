package main

import (
	"api-CRUD/initializers"
	"errors"
	"net/http"
	"os"
	"strconv"
	"time"

	"github.com/gin-gonic/gin"
)

type person struct {
	ID       int       `json:"id"`
	Name     string    `json:"name"`
	Age      int       `json:"age"`
	Gender   string    `json:"gender"`
	Birthday time.Time `json:"birthday"`
}

// d,_ := time.Parse(time.RFC3339, "2018-12-12T11:45:26")
var persons = []person{
	{ID: 0, Name: "ming", Age: 20, Gender: "male", Birthday: time.Now()},
	{ID: 1, Name: "marly", Age: 30, Gender: "female", Birthday: time.Now()},
	{ID: 2, Name: "hen", Age: 19, Gender: "male", Birthday: time.Now()},
}
var personIdCount = len(persons)

func getPersonId() int {
	personIdCount++
	return personIdCount
}

func getPersonAll(c *gin.Context) {
	gender, ok := c.GetQuery("gender")
	if ok {
		persons := getPersonAllByGenderFunc(gender)
		c.IndentedJSON(http.StatusOK, persons)
		return
	}
	c.IndentedJSON(http.StatusOK, persons)
}

func getPersonAllByGenderFunc(gender string) []person {
	var result []person
	for i := 0; i < len(persons); i++ {
		temp := persons[i]
		if temp.Gender == gender {
			result = append(result, temp)
		}
	}

	return result
}

func createPerson(c *gin.Context) {
	var newPerson person

	jsonParseErr := c.Bind(&newPerson)
	if jsonParseErr != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"status":  http.StatusBadRequest,
			"message": "person JSON parse error",
		})
		return
	}

	newPerson.ID = getPersonId()
	persons = append(persons, newPerson)
	c.IndentedJSON(http.StatusCreated, newPerson)
}

func getPersonById(c *gin.Context) {
	strId := c.Param("id")

	id, parseErr := strconv.Atoi(strId)
	if parseErr != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"status":  http.StatusBadRequest,
			"message": "Id is not a number",
		})
		return
	}

	person, foundErr := getPersonByIdFunc(id)
	if foundErr != nil {
		c.IndentedJSON(http.StatusNotFound, gin.H{
			"status":  http.StatusNotFound,
			"message": "Person id: " + strId + " not found.",
		})
		return
	}
	c.IndentedJSON(http.StatusOK, person)
}

func getPersonByIdFunc(id int) (*person, error) {
	for i, p := range persons {
		if p.ID == id {
			return &persons[i], nil
		}
	}
	return nil, errors.New("Person not found")
}

func updatePersonById(c *gin.Context) {
	var newPerson person

	jsonParseErr := c.Bind(&newPerson)
	if jsonParseErr != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"status":  http.StatusBadRequest,
			"message": "person JSON parse error",
		})
		return
	}

	personId := newPerson.ID
	srcPerson, foundErr := getPersonByIdFunc(personId)
	if foundErr != nil {
		c.IndentedJSON(http.StatusNotFound, gin.H{
			"status":  http.StatusNotFound,
			"message": "Person id: " + string(rune(personId)) + " not found.",
		})
		return
	}

	srcPerson.Age = newPerson.Age
	srcPerson.Gender = newPerson.Gender
	srcPerson.Name = newPerson.Name
	srcPerson.Birthday = newPerson.Birthday
	c.IndentedJSON(http.StatusOK, srcPerson)
}

func deletePersonById(c *gin.Context) {
	strId := c.Param("id")
	personId, parseErr := strconv.Atoi(strId)
	if parseErr != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{
			"status":  http.StatusBadRequest,
			"message": "Id is not a number",
		})
		return
	}
	deleteErr := deletePersonByIdFunc(personId)
	if deleteErr != nil {
		c.IndentedJSON(http.StatusNotFound, gin.H{
			"status":  http.StatusNotFound,
			"message": deleteErr,
		})
		return
	}
	c.String(http.StatusOK, "Person id: "+strId+" delete success.")
}

func deletePersonByIdFunc(personId int) error {
	var personIndex = -1
	for i, p := range persons {
		if p.ID == personId {
			personIndex = i
		}
	}
	if personIndex == -1 {
		return errors.New("Person not found")
	}
	persons = append(persons[:personIndex], persons[personIndex+1:]...)
	return nil
}

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
	router.GET("/person/:id", getPersonById)
	router.GET("/person/all", getPersonAll)
	router.POST("/person", createPerson)
	router.PUT("/person", updatePersonById)
	router.DELETE("/person/:id", deletePersonById)

	router.Run("localhost:" + os.Getenv("port"))
}
