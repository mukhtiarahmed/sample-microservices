package com.tenhawks.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO implements Serializable {


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

    private List<String> hobbies = new ArrayList<>();


}
