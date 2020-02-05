/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.tenhawks.person.service;

import com.tenhawks.person.annotations.LogMethod;
import com.tenhawks.person.domain.BaseEntity;
import com.tenhawks.person.dto.ListResponseDTO;
import com.tenhawks.person.dto.SearchCriteria;
import com.tenhawks.exception.AssignmentException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


/**
 * The base service exposing the list method.
 *
 * @param <T> the generic entity type
 * @author mukhtiar.ahmed
 * @version 1.0
 */
public abstract class BaseListableService<T extends BaseEntity, ID> extends BaseService<T, ID>
        implements GenericListableService<T, ID> {

    /**
     * List all entities.
     *
     * @return the entity list
     * @throws AssignmentException if there is any error
     */
    @Override
    @LogMethod
    public List<T> list() {

        return repository.findAll();
    }

    @Override
    @LogMethod
    public ListResponseDTO<T> list(SearchCriteria searchCriteria) {
        ListResponseDTO responseDTO = new ListResponseDTO();
        int pageId = searchCriteria.getPage();
        // PageRequest pageId start from 0
        if(pageId > 0) {
            pageId--;
        } else if(pageId < 0) {
            pageId = 0;
        }

        Pageable pageable;
        if (StringUtils.isEmpty(searchCriteria.getSortColumn())) {
            pageable = PageRequest.of(pageId, searchCriteria.getPageSize());
        } else {
            Sort sort = Sort.by(new Sort.Order(Sort.Direction.fromString(searchCriteria.getSortOrder()),
                    searchCriteria.getSortColumn()));
            pageable = PageRequest.of(pageId, searchCriteria.getPageSize(), sort);
        }
        Page<T> page = repository.findAll(pageable);

        responseDTO.setData(page.getContent());
        responseDTO.setTotalElement(page.getTotalElements());
        return responseDTO;

    }


}
