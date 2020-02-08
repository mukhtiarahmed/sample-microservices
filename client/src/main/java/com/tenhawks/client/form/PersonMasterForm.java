package com.tenhawks.client.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Mukhtiar Ahmed
 */
@Data

public class PersonMasterForm implements Serializable {

  private static final long serialVersionUID = 81243617234324L;

  @NotBlank(message = "Company Code  is mandatory")
  private String compCode;

  @NotBlank(message = "Company Code HRIS is mandatory")
  private String compCodeHRIS;

  @NotBlank(message = "Company Name is mandatory")
  private String companyName;

  @NotBlank(message = "Company Abbr Name is mandatory")
  private String companyAbbrName;

  @NotBlank(message = "Active Date is mandatory")
  private String activeDate;

  private String compRegNo;

  private String companyLogo;

  private Boolean isActive = Boolean.TRUE;

  private String createdOn;

  private String createdBy;

  private String lastModifiedOn;

  private String lastModifiedBy;

  private String deactivatedOn;

  private String deactivatedBy;

  private String reactivatedOn;

  private String reactivatedBy;

  private MultipartFile logoFile;



}
