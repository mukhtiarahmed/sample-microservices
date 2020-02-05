/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.tenhawks.person.service;

import com.tenhawks.person.domain.BaseEntity;
import com.tenhawks.person.dto.ListResponseDTO;
import com.tenhawks.person.dto.SearchCriteria;
import com.tenhawks.exception.AssignmentException;

import java.util.List;


/**
 * The generic service that expose the list method.
 *
 * @param <T> the generic entity type
 * @author mukhtiar.ahmed
 * @version 1.0
 */
public interface GenericListableService<T extends BaseEntity, ID> extends GenericService<T, ID> {

    /**
     * List all entities.
     *
     * @return the entity list
     * @throws AssignmentException if there is any error
     */
    List<T> list();

    ListResponseDTO<T> list(SearchCriteria searchCriteria);
}
