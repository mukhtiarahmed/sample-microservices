package com.tenhawks.person.controller;

import com.tenhawks.bean.ApiResponse;
import com.tenhawks.bean.CommonHelper;
import com.tenhawks.bean.Meta;
import com.tenhawks.person.annotations.LogMethod;
import com.tenhawks.person.domain.Hobby;
import com.tenhawks.person.dto.HobbyDTO;
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
import java.util.List;
import java.util.stream.Collectors;

import static com.tenhawks.person.util.PersonHelper.API_VER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


/**
 * The Hobby REST controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */

@RestController
@RequestMapping(API_VER + "/hobby")
public class HobbyController {

    @Autowired
    private ServiceLocator serviceLocator;


    /**
     * Check the configuration.
     *
     * @throws ConfigurationException if there's any error in configuration
     */
    @PostConstruct
    public void checkConfiguration() {
        CommonHelper.checkConfigNotNull(serviceLocator, "serviceLocator");
    }

    /**
     * Get All Hobbies.
     *
     * @return the ResponseDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse<List> getAll() throws AssignmentException {
        List<Hobby> hobbyList = serviceLocator.getHobbyService().activeHobbyList();
        List<HobbyDTO> hobbyDTOS = hobbyList.stream().map(
                Mapper::toHobbyDTO).collect(Collectors.toList());
        ApiResponse<List> response = new ApiResponse<>();
        response.setStatus(new Meta(HttpStatus.OK));
        response.setData(hobbyDTOS);
        return response;
    }

    /**
     * Get a single Hobby.
     *
     * @param id the Hobby ID
     * @return the Hobby entity
     * @throws InvalidDataException    if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws AssignmentException     if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    @ResponseBody
    public ApiResponse<HobbyDTO> get(@PathVariable String id) throws AssignmentException {
        Hobby hobby = serviceLocator.getHobbyService().get(id);
        ApiResponse<HobbyDTO> response = new ApiResponse<>();
        response.setStatus(new Meta(HttpStatus.OK));
        response.setData(Mapper.toHobbyDTO(hobby));
        return response;
    }

    /**
     * Create the Hobby.
     *
     * @param dto the Hobby dto.
     * @return the entity
     * @throws InvalidDataException if entity is null or not valid
     * @throws AssignmentException  if any other error occurred during operation
     */
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse<HobbyDTO> create(@RequestBody @Valid HobbyDTO dto) throws AssignmentException {
        Hobby entity = new Hobby();
        entity.setName(dto.getName());
        Hobby hobby = serviceLocator.getHobbyService().create(entity);
        ApiResponse<HobbyDTO> response = new ApiResponse<>();
        response.setStatus(new Meta(HttpStatus.OK));
        response.setData(Mapper.toHobbyDTO(hobby));
        return response;
    }


    /**
     * Update the Hobby.
     *
     * @param id     the Hobby ID
     * @param dto the Hobby entity
     * @return the updated entity
     * @throws InvalidDataException if entity is null or not valid, or id is not positive
     * @throws AssignmentException  if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse<HobbyDTO> update(@PathVariable String id, @RequestBody @Valid HobbyDTO dto) throws AssignmentException {
        Hobby entity = new Hobby();
        entity.setName(dto.getName());
        Hobby hobby = serviceLocator.getHobbyService().update(id, entity);
        ApiResponse<HobbyDTO> response = new ApiResponse<>();
        response.setStatus(new Meta(HttpStatus.OK));
        response.setData(Mapper.toHobbyDTO(hobby));
        return response;
    }

    /**
     * Delete the Hobby.
     *
     * @param id the Hobby ID
     * @throws InvalidDataException if id is not positive
     * @throws AssignmentException  if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse<String> delete(@PathVariable String id) throws AssignmentException {
        serviceLocator.getHobbyService().delete(id);
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(new Meta(HttpStatus.OK));
        response.setMessage("Delete Hobby");
        return response;

    }
}
