package com.example.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Component  宣告此類是一個 spring boot 組件
 * @ConfigurationProperties(prefix = "person") 表示一個配置類，讀取配置文檔中的 person 數據
 * @PropertySource(value = "classpath:person.properties") 表示讀取 application.properties 以外的 person.properties 配置文檔
 */
@Component
@ConfigurationProperties(prefix = "person")
@PropertySource(value = "classpath:person.properties")
//@Validated // 數據檢查
public class Person {


//    @Value("${person.name}")
    private String name;
//    @Value("#{10+5}")
    private Integer age;
//    @Value("true")
    private Boolean isMale;
    private Date birth;

//    @Email // 數據檢查 必須為 email 格式
    private String email;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsMale() {
        return isMale;
    }

    public void setIsMale(Boolean male) {
        isMale = male;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isMale=" + isMale +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
