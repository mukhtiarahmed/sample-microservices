package com.tenhawks.person.dto;

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


    private String firstName;

    private String lastName;

    private String favouriteColour;

    private int age;

    private List<String> hobby;


}
