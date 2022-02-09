package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.*;
import java.io.Serializable;
@ToString
@NoArgsConstructor
public class Admin extends User implements Serializable {

    public Admin( String idName,
                  String firstName,
                 String lastName,
                  String password,
                 String age,
                  String gender,
                  Address address) {
        super(idName, firstName, lastName, password, age, gender, address);
    }
//    public Admin(){
//
//    }
}
