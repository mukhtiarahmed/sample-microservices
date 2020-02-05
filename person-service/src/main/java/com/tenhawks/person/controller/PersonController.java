package com.tenhawks.person.controller;


import com.tenhawks.bean.ApiResponse;
import com.tenhawks.bean.Meta;
import com.tenhawks.person.annotations.LogMethod;
import com.tenhawks.person.domain.Hobby;
import com.tenhawks.person.domain.Person;
import com.tenhawks.person.dto.*;
import com.tenhawks.exception.AssignmentException;
import com.tenhawks.exception.ConfigurationException;
import com.tenhawks.exception.EntityNotFoundException;
import com.tenhawks.exception.InvalidDataException;
import com.tenhawks.person.mapper.Mapper;
import com.tenhawks.person.service.ServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tenhawks.bean.CommonHelper.checkConfigNotNull;
import static com.tenhawks.person.util.PersonHelper.API_VER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 * The Person REST controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@RestController
@RequestMapping(API_VER + "/person")
public class PersonController {

    @Autowired
    private ServiceLocator serviceLocator;


    /**
     * Check the configuration.
     *
     * @throws ConfigurationException if there's any error in configuration
     */
    @PostConstruct
    public void checkConfiguration() {
        checkConfigNotNull(serviceLocator, "serviceLocator");
    }


    @LogMethod
    @RequestMapping(value = "/list",
            method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ListResponseDTO<SimplePersonDTO> pagination(@RequestParam(value = "orderBy", required = false) String orderBy,
               @RequestParam(value = "direction", required = false) String direction,
               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
               @RequestParam(value = "pageSize", required = false, defaultValue = "${page.size}") int pageSize,
               @RequestParam(value = "search", required = false) String search) {

        SearchCriteria searchCriteria = SearchCriteria.builder()
                .withSearchString(search).
                        withPageSize(pageSize).
                        withPage(page).
                        withSortColumn(orderBy).
                        withSortOrder(direction).withActiveOnly(Boolean.TRUE).build();
        ListResponseDTO<Person> listResponseDTO = serviceLocator.getPersonService().list(searchCriteria);
        List<SimplePersonDTO> dtoList = listResponseDTO.getData().stream().map(p ->
                Mapper.toSimplePersonDTO(p)).collect(Collectors.toList());

        ListResponseDTO<SimplePersonDTO> responseDTO = new ListResponseDTO<>();
        responseDTO.setData(dtoList);
        responseDTO.setTotalElement(listResponseDTO.getTotalElement());
        responseDTO.setStatus(new Meta(HttpStatus.OK));
        return responseDTO;

    }

    @LogMethod
    @RequestMapping(value = "/admin/list",
            method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ListResponseDTO<PersonDTO> paginationForAdmin(@RequestParam(value = "orderBy", required = false) String orderBy,
                   @RequestParam(value = "direction", required = false) String direction,
                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                   @RequestParam(value = "pageSize", required = false,  defaultValue = "${page.size}") int pageSize,
                   @RequestParam(value = "search", required = false) String search) {

        SearchCriteria searchCriteria = SearchCriteria.builder()
                .withSearchString(search).
                        withPageSize(pageSize).
                        withPage(page).
                        withSortColumn(orderBy).
                        withSortOrder(direction).build();
        ListResponseDTO<Person> listResponseDTO = serviceLocator.getPersonService().list(searchCriteria);
        List<PersonDTO> dtoList = listResponseDTO.getData().stream().map(
                Mapper::toPersonDTO).collect(Collectors.toList());

        ListResponseDTO<PersonDTO> responseDTO = new ListResponseDTO<>();
        responseDTO.setData(dtoList);
        responseDTO.setTotalElement(listResponseDTO.getTotalElement());
        responseDTO.setStatus(new Meta(HttpStatus.OK));
        return responseDTO;
    }


    /**
     * Get a single Person.
     *
     * @param id the Person ID
     * @return the ApiResponse
     * @throws InvalidDataException    if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws AssignmentException     if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    @ResponseBody
    public ApiResponse get(@PathVariable String id) throws AssignmentException {
        Person person = serviceLocator.getPersonService().get(id);
        return new ApiResponse<>(new Meta(HttpStatus.OK), null, Mapper.toSimplePersonDTO(person));
    }

    /**
     * Get a single Person.
     *
     * @param id the Person ID
     * @return the ApiResponse
     * @throws InvalidDataException    if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws AssignmentException     if any other error occurred during operation
     */
    @RequestMapping(value = "/admin/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse getPersonDTO(@PathVariable String id) throws AssignmentException {
        Person person = serviceLocator.getPersonService().get(id);
        return new ApiResponse<>(new Meta(HttpStatus.OK),  null, Mapper.toPersonDTO(person));
    }


    /**
     * Create the Person.
     *
     * @param personDTO the PersonDTO.
     * @return the ResponseDTO
     * @throws InvalidDataException if entity is null or not valid
     * @throws AssignmentException  if any other error occurred during operation
     */
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse create(@RequestBody @Valid PersonDTO personDTO) throws AssignmentException {
        Person entity = toPerson(personDTO);
        Person person = serviceLocator.getPersonService().create(entity);
        return new ApiResponse<>(new Meta(HttpStatus.OK),  null, Mapper.toPersonDTO(person));
    }


    /**
     * Update the Person.
     *
     * @param id        The Person ID
     * @param personDTO the PersonDTO
     * @return the ResponseDTO
     * @throws InvalidDataException if entity is null or not valid, or id is not positive
     * @throws AssignmentException  if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody PersonDTO personDTO) throws AssignmentException {
        Person entity = toPerson(personDTO);
        Person person = serviceLocator.getPersonService().update(id, entity);
        return new ApiResponse<>(new Meta(HttpStatus.OK),  null, Mapper.toPersonDTO(person));
    }

    /**
     * Delete the Person.
     *
     * @param id the Person ID
     * @throws InvalidDataException if id is not positive
     * @throws AssignmentException  if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse delete(@PathVariable String id) throws AssignmentException {
        serviceLocator.getPersonService().delete(id);
        return new ApiResponse<>(new Meta(HttpStatus.OK),  "Delete Person Successfully", null);
    }


    private Person toPerson(PersonDTO dto) throws AssignmentException {
        Person entity = new Person();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setFavouriteColour(serviceLocator.getColourService().get(dto.getColourId()));
        List<Hobby> hobbies = new ArrayList<>(dto.getHobbies().size());
        for (String id : dto.getHobbies()) {
            hobbies.add(serviceLocator.getHobbyService().get(id));
        }

        entity.setHobbies(hobbies);
        if (entity.getDateOfBirth() != null) {
            entity.setDateOfBirth(dto.getDateOfBirth());
        } else if (dto.getAge() > 0) {
            LocalDate dateOfBirth = LocalDate.now().minusYears(dto.getAge()).withMonth(Month.JANUARY.getValue())
                    .withDayOfMonth(1);
            entity.setDateOfBirth(dateOfBirth);
        }

        return entity;
    }

}



