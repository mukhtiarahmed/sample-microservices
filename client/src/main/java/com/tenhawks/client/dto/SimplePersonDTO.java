package com.tenhawks.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class SimplePersonDTO implements Serializable {


    private String id;
    
    private String firstName;

    private String lastName;

    private String favouriteColour;

    private int age;

    private List<String> hobbies;

    @JsonIgnore
    public String getFormatedHobbies() {
        return String.join(", ", hobbies);
    }



}
