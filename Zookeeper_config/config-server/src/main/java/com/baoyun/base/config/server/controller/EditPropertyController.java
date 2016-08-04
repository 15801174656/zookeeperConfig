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
public class EditPropertyController {
	@Autowired
	PropertyService propertyService;
	
	@RequestMapping(value = "/editProperty", method = RequestMethod.GET)
	@ResponseBody
	public NoPageResultModel addProperty(
			@RequestParam(value = "groupName", required = true) String groupName,
			@RequestParam(value = "propertyKey", required = true) String propertyKey,
			@RequestParam(value = "propertyValue", required = true) String propertyValue,
			@RequestParam(value = "propertyDesc", required = true) String propertyDesc,
			HttpServletRequest request) {
		String txNo = "EDIT_PROPERTY" + UUID.randomUUID().toString();
		log.info(
				"txNo: {}, groupName: {}, propertyKey: {}, propertyValue: {}, propertyDesc: {}",
				txNo, groupName, propertyKey, propertyValue, propertyDesc);		
	    try {
	    	groupName = StringCheckUtils.checkGroupName(groupName);
	    	propertyKey = StringCheckUtils.checkPropertyKey(propertyKey);
	    	NoPageResultModel restModel = propertyService.editProperty(txNo, groupName, propertyKey, propertyValue, propertyDesc);
			if((boolean) restModel.getData())  
				log.info("--edit property success groupName:{} propertyKey:{} propertyValue:{} propertyDesc:{} txNo:{}",
						groupName,propertyKey,propertyValue,propertyDesc,txNo);
			else
				log.info("--edit property fail groupName:{} propertyKey:{} propertyValue:{} propertyDesc:{} txNo:{}",
						groupName,propertyKey,propertyValue,propertyDesc,txNo);
			return restModel;		
		} catch (ParamInvalidException e) {
            log.error("edit property groupName:{} propertyKey:{} propertyValue:{} error:{} ", groupName,propertyKey,propertyValue,ExceptionUtils.getStackTrace(e));
            return new NoPageResultModel(RestResponseCode.PARAMETER_ERROR, RestResponseCode.PARAMETER_ERROR_DESC + e.getFieldName());
        } catch (Exception e) {
        	log.error("edit property groupName:{} propertyKey:{} propertyValue:{} error:{} ", groupName,propertyKey,propertyValue,ExceptionUtils.getStackTrace(e));
            return new NoPageResultModel(RestResponseCode.INTERNAL_ERROR, RestResponseCode.INTERNAL_ERROR_DESC);
        }
	}

}
