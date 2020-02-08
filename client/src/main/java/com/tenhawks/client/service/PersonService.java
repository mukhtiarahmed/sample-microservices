package com.tenhawks.client.service;


import com.tenhawks.bean.ApiResponse;
import com.tenhawks.client.client.PersonServiceClient;
import com.tenhawks.client.dto.ColourDTO;
import com.tenhawks.client.dto.HobbyDTO;
import com.tenhawks.client.dto.ListResponseDTO;
import com.tenhawks.client.dto.PersonDTO;
import com.tenhawks.client.dto.SearchCriteria;
import com.tenhawks.client.dto.SimplePersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.time.LocalDate;
import java.util.List;

import static com.tenhawks.bean.CommonHelper.checkConfigNotNull;

/**
 * The Person service
 *
 * @author  mukhtiar.ahmed
 * @version 1.0
 */

@Slf4j
@Service
public class PersonService {


    @Autowired
    private PersonServiceClient personServiceClient;

    public ListResponseDTO<SimplePersonDTO> list(SearchCriteria searchCriteria) {

        return personServiceClient.list( searchCriteria.getSortColumn(), searchCriteria.getSortOrder(),
                searchCriteria.getPage(), searchCriteria.getPageSize(), searchCriteria.getSearchString());
    }

    public ApiResponse<SimplePersonDTO> viewPerson(String personId) {
        return personServiceClient.get(personId);
    }

    public ApiResponse<PersonDTO> editPerson(String personId) {
        return personServiceClient.getPersonDTO(personId);
    }
    public ApiResponse<String>  createPerson(PersonDTO personDTO) {
        personDTO.setDateOfBirth(LocalDate.parse(personDTO.getDob()));
        return personServiceClient.create(personDTO);
    }

    public ApiResponse<String> updatePerson(String personId, PersonDTO personDTO) {
        personDTO.setDateOfBirth(LocalDate.parse(personDTO.getDob()));
        return personServiceClient.update(personId, personDTO);
    }

    public ApiResponse<String> deletePerson(String personId) {
        return personServiceClient.delete(personId);
    }

    public List<HobbyDTO> getActiveHobbies() {
        return personServiceClient.getAllActiveHobbies().getData();
    }

    public List<ColourDTO> getActiveColours() {
        return personServiceClient.getAllActiveColours().getData();
    }
}
