package com.tenhawks.client.client;

import com.tenhawks.bean.ApiResponse;
import com.tenhawks.client.config.FeignClientConfiguration;
import com.tenhawks.client.dto.ColourDTO;
import com.tenhawks.client.dto.HobbyDTO;
import com.tenhawks.client.dto.ListResponseDTO;
import com.tenhawks.client.dto.PersonDTO;
import com.tenhawks.client.dto.SimplePersonDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



/**
 * Person Service Feign Client.
 * @author Mukhtiar Ahmed
 *
 */
@RibbonClient("person-service")
@FeignClient(name = "person-service", path = "/person/api/1.0",
        configuration = FeignClientConfiguration.class, decode404 = true)
public interface PersonServiceClient {

    @GetMapping(value = "/person/list", produces = APPLICATION_JSON_VALUE)
    ListResponseDTO<SimplePersonDTO> list(@RequestParam(value = "orderBy", required = false) String orderBy,
          @RequestParam(value = "direction", required = false) String direction,
          @RequestParam(value = "page", required = false) int page,
          @RequestParam(value = "pageSize", required = false) int pageSize,
          @RequestParam(value = "search", required = false) String search);

    @GetMapping(value = "/person/{id}", produces = APPLICATION_JSON_VALUE)
    ApiResponse<SimplePersonDTO> get(@PathVariable("id") String id);

    @GetMapping(value = "/person/admin/{id}", produces = APPLICATION_JSON_VALUE)
    ApiResponse<PersonDTO> getPersonDTO(@PathVariable("id")  String id);

    @PostMapping(value = "/person", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ApiResponse<String> create(@RequestBody PersonDTO personDTO);

    @PutMapping(value = "/person/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ApiResponse<String> update(@PathVariable("id") String id, @RequestBody PersonDTO personDTO);

    @DeleteMapping(value = "/person/{id}")
    ApiResponse<String> delete(@PathVariable("id")  String id);

    @GetMapping(value = "/hobby", produces = APPLICATION_JSON_VALUE)
    ApiResponse<List<HobbyDTO>> getAllActiveHobbies();

    @GetMapping(value = "/colour", produces = APPLICATION_JSON_VALUE)
    ApiResponse<List<ColourDTO>> getAllActiveColours();

}
