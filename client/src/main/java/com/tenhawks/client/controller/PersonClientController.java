package com.tenhawks.client.controller;

import com.tenhawks.bean.ApiResponse;
import com.tenhawks.bean.CommonHelper;
import com.tenhawks.client.annotations.LogMethod;
import com.tenhawks.client.dto.ColourDTO;
import com.tenhawks.client.dto.HobbyDTO;
import com.tenhawks.client.dto.ListResponseDTO;
import com.tenhawks.client.dto.PersonDTO;
import com.tenhawks.client.dto.SearchCriteria;
import com.tenhawks.client.dto.SimplePersonDTO;
import com.tenhawks.client.service.PersonService;
import com.tenhawks.client.util.ClientHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import java.util.Collections;
import java.util.List;

import static com.tenhawks.bean.CommonHelper.ERROR;

import static com.tenhawks.bean.CommonHelper.SUCCESS;
import static com.tenhawks.bean.CommonHelper.checkConfigNotNull;

/**
 * @author Mukhtiar Ahmed
 */
@Slf4j
@Controller
@RequestMapping("/person")
public class PersonClientController {

	@Autowired
	private PersonService personService;

	private List<HobbyDTO> hobbies = Collections.emptyList();

	private List<ColourDTO> colours  = Collections.emptyList();;

	@PostConstruct
	public void init() {
		checkConfigNotNull(personService, "PersonService");

	}

	/**
	 * Person Listing
	 *
	 * @return String view name
	 */
	@GetMapping("/list")
    @LogMethod
	public String person(ModelMap model,
						  @RequestParam(value ="pageId", defaultValue = "1", required = false)  int pageId,
						  @RequestParam(value = "pageSize", required = false, defaultValue = "${page.size}") int pageSize,
						  @RequestParam(value = "searchString", required = false, defaultValue = "") String searchString) {

		SearchCriteria searchCriteria = SearchCriteria.builder()
				.withSearchString(searchString)
				.withPageSize(pageSize)
				.withPage(pageId).build();

		ListResponseDTO listResponse =  personService.list(searchCriteria);
		model.addAttribute("listResponse", listResponse);
		model.addAttribute("searchCriteria", searchCriteria);
		return "person";
	}

	/**
	 * Person Listing
	 *
	 * @return String view name
	 */
	@PostMapping("/list")
    @LogMethod
	public String personSearch(@ModelAttribute SearchCriteria searchCriteria, ModelMap model) {
		log.info("searchCriteria {}", searchCriteria);
		ListResponseDTO listResponse =  personService.list(searchCriteria);
		model.addAttribute("listResponse", listResponse);
		model.addAttribute("searchCriteria", searchCriteria);
		return "personTable";
	}

	/**
	 * Person View
	 *
	 * @return String view name
	 */
	@GetMapping("/view/{id}")
    @LogMethod
	public String viewPerson(@PathVariable("id") String id, ModelMap model,
							 RedirectAttributes redirectAttributes)
			 {
		ApiResponse<SimplePersonDTO> apiResponse = personService.viewPerson(id);
		if(apiResponse.getStatus().getStatus() == HttpStatus.OK.value()) {
			model.addAttribute("person", apiResponse.getData());
			return "viewPerson";
		} else {
			redirectAttributes.addAttribute(ERROR, apiResponse.getMessage());
			return "redirect:/person/list";
		}
	}

	/**
	 * Add Person View
	 *
	 * @return String view name
	 */
	@GetMapping("/add")
    @LogMethod
	public String viewAddPerson(ModelMap model) {
		PersonDTO form =new PersonDTO();
		model.addAttribute("person", form);
		addLookUpData(model);
		return "addPerson";
	}

	/**
	 * Add Person View
	 *
	 * @return String view name
	 */
	@PostMapping("/add")
    @LogMethod
	public String addPerson(@Valid @ModelAttribute("person") PersonDTO form,
							 BindingResult bindingResult,  ModelMap model, RedirectAttributes redirectAttributes) {


		if(ClientHelper.validatePerson(form, bindingResult)) {
			addLookUpData(model);
			return "addPerson";
		}

		ApiResponse<String> apiResponse = personService.createPerson(form);
		if(apiResponse.getStatus().getStatus() == HttpStatus.OK.value()) {
			redirectAttributes.addAttribute(SUCCESS, "Add Person Successfully");
			return "redirect:/person/list";
		} else {
			redirectAttributes.addAttribute(ERROR, apiResponse.getMessage());
			addLookUpData(model);
			return "addPerson";
		}

	}

	/**
	 * Person Edit View
	 *
	 * @return String view name
	 */
	@GetMapping("/edit/{id}")
    @LogMethod
	public String editPerson(@PathVariable("id") String id, ModelMap model,  RedirectAttributes redirectAttributes)
			 {
		ApiResponse<PersonDTO> apiResponse = personService.editPerson(id);
		if(apiResponse.getStatus().getStatus() == HttpStatus.OK.value()) {
			PersonDTO dto = apiResponse.getData();
			dto.setDob(CommonHelper.getDateFormat(dto.getDateOfBirth()));
			model.addAttribute("person", apiResponse.getData());
			addLookUpData(model);
			return "editPerson";
		} else {
			redirectAttributes.addAttribute(ERROR, apiResponse.getMessage());
			return "redirect:/person/list";
		}

	}

	/**
	 * Person View
	 *
	 * @return String view name
	 */
	@PostMapping("/update/{id}")
    @LogMethod
	public String updatePerson(@PathVariable("id") String id,
								@Valid @ModelAttribute("person") PersonDTO form,
								BindingResult bindingResult,  ModelMap model, RedirectAttributes redirectAttributes) {

		if(ClientHelper.validatePerson(form, bindingResult)) {
			addLookUpData(model);
			return "addPerson";
		}

		ApiResponse<String> apiResponse = personService.updatePerson(id, form);
		if(apiResponse.getStatus().getStatus() == HttpStatus.OK.value()) {
			redirectAttributes.addAttribute(SUCCESS, "Update Person Successfully");
			return "redirect:/person/list";
		} else {
			redirectAttributes.addAttribute(ERROR, apiResponse.getMessage());
			addLookUpData(model);
			return "editPerson";
		}
	}

	/**
	 *  Delete Person by Id.
	 *
	 * @return String view name
	 */
	@GetMapping("/delete/{id}")
    @LogMethod
	public String deletePerson(@PathVariable("id") String id, ModelMap model,
								RedirectAttributes redirectAttributes) {
		 ApiResponse<String> apiResponse = personService.deletePerson(id);
		if(apiResponse.getStatus().getStatus() == HttpStatus.OK.value()) {
			redirectAttributes.addAttribute(SUCCESS, "Delete Person Successfully");
		} else {
			redirectAttributes.addAttribute(ERROR, apiResponse.getMessage());
		}
		return "redirect:/person/list";
	}

	public void addLookUpData(ModelMap model) {
		if(colours == null || colours.isEmpty()) {
			colours = personService.getActiveColours();
		}

		if(hobbies == null || hobbies.isEmpty()) {
			hobbies = personService.getActiveHobbies();
		}

		model.addAttribute("colours", colours);
		model.addAttribute("hobbyList", hobbies);
	}


}

