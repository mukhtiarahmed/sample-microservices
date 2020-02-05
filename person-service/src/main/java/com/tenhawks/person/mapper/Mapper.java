package com.tenhawks.person.mapper;


import com.tenhawks.person.domain.Colour;
import com.tenhawks.person.domain.Hobby;
import com.tenhawks.person.domain.Person;
import com.tenhawks.person.dto.ColourDTO;
import com.tenhawks.person.dto.HobbyDTO;
import com.tenhawks.person.dto.PersonDTO;
import com.tenhawks.person.dto.SimplePersonDTO;
import com.tenhawks.person.util.PersonHelper;

import java.util.stream.Collectors;

/**
 * The Mapper class
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
public class Mapper {

    private Mapper() {

    }

    public static SimplePersonDTO toSimplePersonDTO(Person entity) {
        if (entity == null) return null;

        SimplePersonDTO dto = new SimplePersonDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAge(PersonHelper.calculateAge(entity.getDateOfBirth()));
        dto.setFavouriteColour(entity.getFavouriteColour().getName());
        dto.setHobby(entity.getHobbies().stream().map(Hobby::getName).collect(Collectors.toList()));
        return dto;

    }

    public static PersonDTO toPersonDTO(Person entity) {
        if (entity == null) return null;
        PersonDTO dto = new PersonDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAge(PersonHelper.calculateAge(entity.getDateOfBirth()));
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setColourId(entity.getFavouriteColour().getId());
        dto.setHobbies(entity.getHobbies().stream().map(Hobby::getId).collect(Collectors.toList()));
        return dto;

    }

    public static HobbyDTO toHobbyDTO(Hobby hobby) {
        if (hobby == null) return null;

        HobbyDTO dto = new HobbyDTO();
        dto.setId(hobby.getId());
        dto.setName(hobby.getName());
        return dto;
    }

    public static ColourDTO toColourDTO(Colour colour) {
        if (colour == null) return null;

        ColourDTO dto = new ColourDTO();
        dto.setId(colour.getId());
        dto.setName(colour.getName());
        dto.setHex(colour.getHex());
        return dto;
    }

}
