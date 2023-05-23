package services

import (
	"aws-lambda-server/src/main/db"
	"aws-lambda-server/src/main/enums"
	"aws-lambda-server/src/main/exceptions"
	"aws-lambda-server/src/main/models"
)

func GetPersonAll() ([]*models.Person, error) {
	persons := []*models.Person{}
	db.DB.Find(&persons)
	return persons, nil
}

func GetPersonAllByGender(gender enums.PersonGender) ([]*models.Person, error) {
	persons := []*models.Person{}

	// db.DB.Find(&persons, "gender = ?", gender)
	db.DB.Where(&models.Person{
		Gender: gender,
	}).Find(&persons)
	return persons, nil
}

func CreatePerson(newPerson *models.Person) (*models.Person, error) {

	result := db.DB.Create(newPerson)

	if result.Error != nil {
		return &models.Person{}, exceptions.DatabaseCreateException()
	}

	return newPerson, nil
}

func GetPersonById(personId string) (*models.Person, error) {

	person := &models.Person{}
	result := db.DB.First(person, personId)

	if result.Error != nil {
		return &models.Person{}, exceptions.PersonIdNotFoundException(personId)
	}

	return person, nil
}

func UpdatePersonById(personId string, srcPerson *models.Person) (*models.Person, error) {

	destPerson := &models.Person{}
	result := db.DB.First(destPerson, personId)
	if result.Error != nil {
		return &models.Person{}, exceptions.PersonIdNotFoundException(personId)
	}

	db.DB.Model(destPerson).Updates(&models.Person{
		Name:     srcPerson.Name,
		Age:      srcPerson.Age,
		Gender:   srcPerson.Gender,
		Birthday: srcPerson.Birthday,
	})

	return destPerson, nil
}

func DeletePersonById(personId string) (string, error) {
	result := db.DB.Delete(&models.Person{}, personId)

	if result.Error != nil {
		return "", exceptions.DatabaseDeleteException()
	}

	return "person id: " + personId + " delete success", nil
}
