/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.tenhawks.person.service;

import com.tenhawks.person.annotations.LogMethod;
import com.tenhawks.person.domain.BaseEntity;
import com.tenhawks.person.dto.ListResponseDTO;
import com.tenhawks.person.dto.SearchCriteria;
import com.tenhawks.exception.AssignmentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
public abstract class BaseListableService<T extends BaseEntity, ID> extends BaseService<T, ID>
        implements GenericListableService<T, ID> {


    @Value("${page.size:10}")
    protected int pageSize;

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
        int pageId = searchCriteria.getPage() >  0 ?  searchCriteria.getPage() : 0;
        int pageSize = searchCriteria.getPageSize() > 0 ?  searchCriteria.getPageSize() : this.pageSize;
        log.info(" PersonService pageId {}", pageId);
        Pageable pageable;
        if (StringUtils.isEmpty(searchCriteria.getSortColumn())) {
            pageable = PageRequest.of(pageId, pageSize);
        } else {
            Sort sort = Sort.by(new Sort.Order(Sort.Direction.fromString(searchCriteria.getSortOrder()),
                    searchCriteria.getSortColumn()));
            pageable = PageRequest.of(pageId, pageSize, sort);
        }
        Page<T> page = repository.findAll(pageable);

        responseDTO.setData(page.getContent());
        responseDTO.setTotalElement(page.getTotalElements());
        return responseDTO;

    }


}
