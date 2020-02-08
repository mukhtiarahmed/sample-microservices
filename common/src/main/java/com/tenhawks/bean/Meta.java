package com.tenhawks.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta  implements Serializable {

	private static final long serialVersionUID = 12112526L;

	private int status;
	private String message;

	public void setStatus(Number number) {
		this.status = number.intValue();
	}


}
