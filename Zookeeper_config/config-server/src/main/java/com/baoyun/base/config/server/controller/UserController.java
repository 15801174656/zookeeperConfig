package com.baoyun.base.config.server.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baoyun.base.config.server.exception.ParamInvalidException;
import com.baoyun.base.config.server.response.NoPageResultModel;
import com.baoyun.base.config.server.response.RestResponseCode;
import com.baoyun.base.config.server.service.PropertyService;
import com.baoyun.base.config.server.util.StringCheckUtils;

@Slf4j
@RestController
public class UserController {
	
	@Autowired
	PropertyService propertyService;

	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	@ResponseBody
	public NoPageResultModel addProperty(
			@RequestParam(value = "groupName", required = true) String groupName,			
			HttpServletRequest request) {
		String txNo = "ADD_GROUP" + UUID.randomUUID().toString();
		log.info("--addGroup groupName:{} txNo:{}", groupName,txNo);	
		
    try {
    	//参数处理
    	groupName = StringCheckUtils.checkGroupName(groupName);
    	
    	NoPageResultModel restModel = propertyService.addGroup(txNo, groupName);	
			if((boolean) restModel.getData())   
				log.info("add group success groupName:{} txNo:{}", groupName, txNo);
			else
				log.info("add group fail groupName:{} txNo:{}", groupName, txNo);
			return restModel;
		} catch (ParamInvalidException e) {
	        log.error("add group groupName:{} error:{} ", groupName,ExceptionUtils.getStackTrace(e));
	        return new NoPageResultModel(RestResponseCode.PARAMETER_ERROR, RestResponseCode.PARAMETER_ERROR_DESC +e.getErrorInfo());
	    } catch (Exception e) {
	    	log.error("add group groupName:{} error:{} ", groupName,ExceptionUtils.getStackTrace(e));
	        return new NoPageResultModel(RestResponseCode.INTERNAL_ERROR, RestResponseCode.INTERNAL_ERROR_DESC);
	    }
	}
}
