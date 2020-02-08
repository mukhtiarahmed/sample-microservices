package com.tenhawks.person.controller;


import com.tenhawks.person.PersonApplication;
import com.tenhawks.person.TestUtils;
import com.tenhawks.person.domain.Colour;
import com.tenhawks.person.domain.Person;
import com.tenhawks.person.dto.PersonDTO;
import com.tenhawks.exception.AssignmentException;
import com.tenhawks.person.mapper.Mapper;
import com.tenhawks.person.repository.ColourRepository;
import com.tenhawks.person.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static com.tenhawks.person.TestUtils.getUserDetail;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The Person Controller Test class.
 *
 * @author mukhtiar.ahmed
 */
@Slf4j
@RunWith(SpringRunner.class)
@Sql({"classpath:import.sql"})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ColourRepository colourRepository;

    private Person person;

    private MockMvc mockMvc;

    @Before
    public void setup() throws AssignmentException {
        MockitoAnnotations.initMocks(this);
        Colour colour =  colourRepository.save(TestUtils.createColour());
        Person person = TestUtils.createPerson();
        person.setHobbies(Arrays.asList(TestUtils.createHobby()));
        person.setFavouriteColour(colour);
        this.person = personRepository.save(person);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/1.0/person";
    }


    @WithMockUser
    @Test
    public void paginationTest() throws Exception {

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "1");
        requestParams.add("pageSize", "10");
        ResultActions result  = mockMvc.perform(get(getRootUrl() +"/list").params(requestParams)
                .with(user(getUserDetail())));
        result.andExpect(status().isOk()).andExpect(jsonPath("$.data").value(is(notNullValue()))).andDo(print());

    }


    @Test
    @WithMockUser(username="ahmed",roles={"ADMIN"})
    public void paginationAdminTest() throws Exception {

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "1");
        requestParams.add("pageSize", "10");
        ResultActions result  = mockMvc.perform(get(                getRootUrl() + "/admin/list")
                .params(requestParams) .with(csrf()).with(user(getUserDetail())));
        result.andExpect(status().isOk()).andExpect(jsonPath("$.data").value(is(notNullValue()))).andDo(print());

    }


    @Test
    public void createPersonTest() throws Exception {

        PersonDTO dto = TestUtils.createPersonDTO();
        String personJson = TestUtils.convertObjectToJson(dto);
        log.info(personJson);
        ResultActions result  = mockMvc.perform(post(getRootUrl()).with(csrf()).with(user(getUserDetail()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(personJson).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Person added successfully")).andDo(print());

    }

    @Test
    public void updatePersonTest() throws Exception {
        person.setDateOfBirth( LocalDate.of(2001, Month.MARCH, 25));
        PersonDTO personDTO = Mapper.toPersonDTO(person);
        personDTO.setFirstName("Muhammad");
        personDTO.setLastName("Saleem");

        String personJson = TestUtils.convertObjectToJson(personDTO);
        ResultActions result  = mockMvc.perform(put(getRootUrl() + "/" + person.getId())
                .with(csrf()).with(user(getUserDetail()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(personJson));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Person updated successfully"))
                .andDo(print());
    }

    @Test
    public void getPersonByIdTest() throws Exception {


        PersonDTO personDTO = Mapper.toPersonDTO(person);
        ResultActions result  = mockMvc.perform(get(getRootUrl() + "/" + personDTO.getId())
                .with(csrf()).with(user(getUserDetail())));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.firstName").value(personDTO.getFirstName()))
                .andExpect(jsonPath("$.data.lastName").value(personDTO.getLastName()))
                .andExpect(jsonPath("$.data.age").value(personDTO.getAge()))
                .andDo(print());

    }

    @Test
    public void deletePersonByIdTest() throws Exception {


        PersonDTO personDTO = Mapper.toPersonDTO(person);
        ResultActions result  = mockMvc.perform(delete(getRootUrl() + "/" + personDTO.getId())
                .with(csrf()).with(user(getUserDetail())));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Delete Person Successfully"))
                .andDo(print());

    }



}
