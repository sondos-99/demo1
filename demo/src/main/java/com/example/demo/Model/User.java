package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
public class User implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("firstName")
    private String firstName ;
    @JsonProperty("lastName")
    private String lastName ;
    @JsonProperty("password")
    private String password;
    @JsonProperty("age")
    private String age;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("address")
    private Address address;

    public User(){

    }
    public User(String idName, String firstName, String lastName, String password, String age, String gender, Address address) {
        this.id = idName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void display(){
    System.out.println("FirstName :" + this.firstName);
    System.out.println("LastName :" + this.lastName);
    System.out.println("Age :" + this.age);
    System.out.println("Gender :" + this.gender);
    System.out.println("Address :" + this.address.getCity()+"/" + this.address.getStreet());
    }


}
