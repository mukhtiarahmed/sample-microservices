package com.tenhawks.person.service;


import com.tenhawks.person.domain.Hobby;

import java.util.List;

/**
 * The Hobby service interface
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */

public interface HobbyService extends GenericListableService<Hobby, String> {


    List<Hobby> activeHobbyList();


}
