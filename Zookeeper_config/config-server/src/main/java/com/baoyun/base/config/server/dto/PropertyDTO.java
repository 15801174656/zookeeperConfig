package com.baoyun.base.config.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
	private String groupName;
	private String propertyKey;
	private String propertyValue;
	private String propertyDesc;
	private String updateTime; 
}
