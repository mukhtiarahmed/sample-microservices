package com.tenhawks.person.service;


import com.tenhawks.person.annotations.LogMethod;
import com.tenhawks.person.domain.Person;
import com.tenhawks.person.dto.ListResponseDTO;
import com.tenhawks.person.dto.SearchCriteria;
import com.tenhawks.exception.AssignmentException;
import com.tenhawks.person.repository.PersonRepository;
import com.tenhawks.person.util.PersonHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.tenhawks.bean.CommonHelper.checkNull;

/**
 * The Person service
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */

@Slf4j
@Service
@Transactional
public class PersonServiceImpl extends BaseListableService<Person, String> implements PersonService {

    private final List<String> columnCompMaster = Arrays.asList(
            "firstName", "lastName", "dateOfBirth");

    @Override
    @LogMethod
    public Person update(String id, Person entity) throws AssignmentException {
        checkNull(id, "id");
        checkNull(entity, "entity");
        Person oldEntity = get(id);
        oldEntity.setLastName(entity.getLastName());
        oldEntity.setFirstName(entity.getFirstName());
        oldEntity.setDateOfBirth(entity.getDateOfBirth());
        oldEntity.setFavouriteColour(entity.getFavouriteColour());
        oldEntity.setHobbies(entity.getHobbies());
        return repository.save(oldEntity);
    }


    @Override
    @LogMethod
    public ListResponseDTO<Person> list(SearchCriteria searchCriteria) {

        Pageable pageable;
        int pageId = searchCriteria.getPage();
        if(pageId <= 0) {
            pageId = 0;
        } else {
            pageId--;
        }

        int pageSize = searchCriteria.getPageSize() > 0 ?  searchCriteria.getPageSize() : this.pageSize;

        log.info("PersonService pageId {}, sortColumn {} ", pageId, searchCriteria.getSortColumn());
        if (columnCompMaster.contains(searchCriteria.getSortColumn())) {
            Sort sort = Sort.by(new Sort.Order(Sort.Direction.fromString(searchCriteria.getSortOrder()),
                    searchCriteria.getSortColumn()));
            pageable = PageRequest.of(pageId, pageSize , sort);
        } else {
            pageable = PageRequest.of(pageId, pageSize);
        }

        Page<Person> page;
        if (!StringUtils.isEmpty(searchCriteria.getSearchString())) {
            String searchString = searchCriteria.getSearchString().toLowerCase();
            if (searchCriteria.isActiveOnly()) {
                page = ((PersonRepository) repository).searchActivePersons(searchString, pageable);
            } else {
                page = ((PersonRepository) repository).searchPersons(searchString, pageable);
            }
        } else  if (searchCriteria.isActiveOnly())  {
            page = ((PersonRepository) repository).findByIsActiveTrue(pageable);
        } else {
            page = repository.findAll(pageable);
        }

        ListResponseDTO responseDTO = new ListResponseDTO();

        responseDTO.setData(page.getContent());
        responseDTO.setTotalElement(page.getTotalElements());
        return responseDTO;

    }
}
