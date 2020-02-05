package com.tenhawks.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



/**
 * @author Mukhtiar Ahmed
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {

  private static final long serialVersionUID = 8414024608947196037L;

  private Meta status;

  private String message;

  private T data;


}
