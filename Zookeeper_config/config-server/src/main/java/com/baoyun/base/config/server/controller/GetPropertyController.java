package com.baoyun.base.config.server.controller;

import java.util.ArrayList;
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
public class GetPropertyController {
	@Autowired
	PropertyService propertyService;

	
	@RequestMapping(value = "/getProperty", method = RequestMethod.GET)
	@ResponseBody
	public NoPageResultModel queryProperty(
			@RequestParam(value = "groupName", required = true) String groupName,
			@RequestParam(value = "propertyKey", required = true) String propertyKey,		
			HttpServletRequest request) {
		String txNo = "GET_PROPERTY" + UUID.randomUUID().toString();
		log.info("txNo: {}, groupName: {}, propertyKey: {}, ",txNo, groupName, propertyKey);
		try {
			if(groupName == null || groupName.isEmpty()){
				return new NoPageResultModel(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS_DESC,new ArrayList<String>());
			}
			groupName = StringCheckUtils.checkGroupName(groupName);
			
			NoPageResultModel restModel = propertyService.getProperty(txNo, groupName, propertyKey);
			if(restModel.getData() != null)
				log.info("get property success groupName:{} propertyKey:{} txNo:{}",groupName,propertyKey,txNo);
			else
				log.info("get property fail groupName:{} propertyKey:{} txNo:{}",groupName,propertyKey,txNo);
			return restModel;
		} catch (ParamInvalidException e) {
			log.error("getProperty groupName:{} propertyKey:{} error:{} ",groupName,propertyKey,ExceptionUtils.getStackTrace(e));
	        return new NoPageResultModel(RestResponseCode.PARAMETER_ERROR, RestResponseCode.PARAMETER_ERROR_DESC + e.getFieldName());
	    } catch (Exception e) {
	    	log.error("getProperty groupName:{} propertyKey:{} error:{} ",groupName,propertyKey,ExceptionUtils.getStackTrace(e));
	        return new NoPageResultModel(RestResponseCode.INTERNAL_ERROR, RestResponseCode.INTERNAL_ERROR_DESC);
	    }
	}
}
