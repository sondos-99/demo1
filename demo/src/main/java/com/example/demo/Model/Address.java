package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Address implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("city")
    private String city;
    @JsonProperty("street")
    private String street;

    public Address (){

    }
    public Address(String idCity, String city, String country) {
        this.id = idCity;
        this.city = city;
        this.street = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
