package com.algaworks.algafood.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
public class Problem {
	
	@ApiModelProperty(position = 0)
	private Integer status;
	
	@ApiModelProperty(position = 1)
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(position = 2)
	private String type;
	
	@ApiModelProperty(position = 3)
	private String title;
	
	@ApiModelProperty(position = 4)
	private String detail;
	
	@ApiModelProperty(position = 5)
	private String userMessage;
	
	@ApiModelProperty(position = 6)
	private List<Field> fields;
	
	public Problem() {}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}