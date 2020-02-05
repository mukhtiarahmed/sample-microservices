package com.tenhawks.person.controller;


import com.tenhawks.bean.ApiResponse;
import com.tenhawks.bean.Meta;
import com.tenhawks.person.annotations.LogMethod;
import com.tenhawks.person.domain.Colour;
import com.tenhawks.person.dto.ColourDTO;
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
import java.util.List;
import java.util.stream.Collectors;

import static com.tenhawks.bean.CommonHelper.checkConfigNotNull;
import static com.tenhawks.person.util.PersonHelper.API_VER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * The Colour REST controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@RestController
@RequestMapping(API_VER + "/colour")
public class ColourController {


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

    /**
     * Get All Colours.
     *
     * @return the ApiResponse
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse<List> getAll()  {
        List<Colour> colours = serviceLocator.getColourService().activeColourList();
        List<ColourDTO> colourDTOS = colours.stream().map(
                Mapper::toColourDTO).collect(Collectors.toList());
        return new ApiResponse<>(new Meta(HttpStatus.OK),  null, colourDTOS);
    }

    /**
     * Get a single Colour.
     *
     * @param id the Colour ID
     * @return the ApiResponse
     * @throws InvalidDataException    if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws AssignmentException     if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    @ResponseBody
    public ApiResponse<ColourDTO> get(@PathVariable String id) throws AssignmentException {
        Colour colour = serviceLocator.getColourService().get(id);
        return new ApiResponse<>(new Meta(HttpStatus.OK),  null, Mapper.toColourDTO(colour));

    }

    /**
     * Create the Colour.
     *
     * @param dto the ColourDTO dto.
     * @return the ApiResponse
     * @throws InvalidDataException if entity is null or not valid
     * @throws AssignmentException  if any other error occurred during operation
     */
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse<ColourDTO> create(@RequestBody ColourDTO dto) throws AssignmentException {
        Colour entity = new Colour();
        entity.setName(dto.getName());
        entity.setHex(dto.getHex());
        Colour colour = serviceLocator.getColourService().create(entity);
        return new ApiResponse<>(new Meta(HttpStatus.OK),  null, Mapper.toColourDTO(colour));

    }


    /**
     * Update the Colour.
     *
     * @param id     the Colour ID
     * @param dto the ColourDTO dto.
     * @return the ApiResponse
     * @throws InvalidDataException if entity is null or not valid, or id is not positive
     * @throws AssignmentException  if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse<ColourDTO> update(@PathVariable String id, @RequestBody ColourDTO dto) throws AssignmentException {
        Colour entity = new Colour();
        entity.setName(dto.getName());
        entity.setHex(dto.getHex());
        Colour colour = serviceLocator.getColourService().update(id, entity);
        return new ApiResponse<>(new Meta(HttpStatus.OK),  null, Mapper.toColourDTO(colour));
    }

    /**
     * Delete the Colour.
     *
     * @param id the Colour ID
     * @throws InvalidDataException if id is not positive
     * @throws AssignmentException  if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    @LogMethod
    @PreAuthorize("hasAnyRole({'SUPER_ADMIN', 'ADMIN'})")
    @ResponseBody
    public ApiResponse delete(@PathVariable String id) throws AssignmentException {
        serviceLocator.getColourService().delete(id);
        return new ApiResponse<>(new Meta(HttpStatus.OK),  "Delete Colour Successfully", null);
    }
}
