package com.tenhawks.person.service;

import com.tenhawks.exception.ConfigurationException;
import com.tenhawks.person.util.PersonHelper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.tenhawks.bean.CommonHelper.checkConfigNotNull;


/**
 * The Service Locator
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Getter
@Component
public class ServiceLocator {


    @Autowired
    private PersonService personService;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private ColourService colourService;


    /**
     * Check the configuration.
     *
     * @throws ConfigurationException if there's any error in configuration
     */
    @PostConstruct
    public void checkConfiguration() {
        checkConfigNotNull(personService, "personService");
        checkConfigNotNull(hobbyService, "hobbyService");
        checkConfigNotNull(colourService, "colourService");
    }


}
