package com.tenhawks.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by Mukhtiar on 11/6/2019.
 */
@Data
@NoArgsConstructor
public class CompanyMasterDTO {


    private String compCode;

    private String compCodeHRIS;

    private String companyName;

    private String companyAbbrName;

    private String compRegNo;

    private String companyLogo;

    private Date activeDate;

    private Boolean isActive;

    private Date createdOn;

    private String createdBy;

    private Date lastModifiedOn;

    private String lastModifiedBy;

    private Date deactivatedOn;

    private String deactivatedBy;

    private Date reactivatedOn;

    private String reactivatedBy;

    private MultipartFile logoFile;

}
