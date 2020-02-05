package com.tenhawks.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenhawks.bean.UserDetail;
import com.tenhawks.person.domain.Colour;
import com.tenhawks.person.domain.Hobby;
import com.tenhawks.person.domain.Person;
import com.tenhawks.person.dto.PersonDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestUtils {
    public static final String FIRST_NAME = "Ahmed";
    public static  final String LAST_NAME = "Mukhtiar";

    public static String convertObjectToJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static Person createPerson() {
               return createPerson(FIRST_NAME, LAST_NAME);
    }

    public static Colour createColour(){
        final Colour colour = new Colour();

        colour.setHex("#00FF00");
        colour.setName("Test Colour");
        return colour;
    }

    public static Hobby createHobby(){
        final Hobby hobby = new Hobby();
        hobby.setName("Test Hobby");
        return hobby;
    }

    private static Person createPerson(String firstName,  String lastName) {

        final Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setDateOfBirth( LocalDate.of(2000, Month.JANUARY, 1));
        return person;
    }

    public  static PersonDTO createPersonDTO() {
        return createPersonDTO(FIRST_NAME, LAST_NAME);
    }

    private static PersonDTO createPersonDTO(String firstName, String lastName) {

        final PersonDTO person = new PersonDTO();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setColourId("00298aa3-17ff-4f4a-a647-1fd06e4caab7");
        person.setAge(45);
        person.setHobbies(Arrays.asList("fba2f4e2-9dff-421b-9696-c6e3fa77653b", "fbc0b795-9f64-4f3c-b046-343bbc361e1f"));
        return person;
    }


    public static UserDetail getUserDetail() {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserName("ahmed");
        userDetail.setActive(true);
        userDetail.setRoles(Arrays.asList("ROLE_SUPER_ADMIN", "ROLE_ADMIN"));
        return userDetail;
    }


}
