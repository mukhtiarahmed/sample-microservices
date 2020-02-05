package com.tenhawks.person.dto;

import com.tenhawks.bean.Meta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class ListResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 8414024608947196037L;

    private Meta status;
    private String message;
    private List<T> data;
    private long totalElement;

}
