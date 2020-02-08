package com.tenhawks.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class PersonDTO {


    private String id;

    @NotBlank(message = "First Name is mandatory")
    @Size(max = 25, message = "First Name is not more then 25 char")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Size(max = 25, message = "Last Name is not more then 25 char")
    private String lastName;

    @NotNull(message = "Colour is mandatory")
    private String colourId;

    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonIgnore
    private String dob;

    private List<String> hobbies = new ArrayList<>();



}
