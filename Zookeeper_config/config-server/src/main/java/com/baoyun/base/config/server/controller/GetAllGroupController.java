package com.baoyun.base.config.server.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baoyun.base.config.server.exception.ParamInvalidException;
import com.baoyun.base.config.server.response.NoPageResultModel;
import com.baoyun.base.config.server.response.RestResponseCode;
import com.baoyun.base.config.server.service.PropertyService;

@Slf4j
@RestController
public class GetAllGroupController {

	@Autowired
	PropertyService propertyService;

	@RequestMapping(value = "/getAllGroup", method = RequestMethod.GET)
	@ResponseBody
	public NoPageResultModel getAllGroup(HttpServletRequest request) {
		String txNo = "get all group" + UUID.randomUUID().toString();
		log.info("txNo: {}", txNo);

		try {
			// 出现错误返回空, 否则返回group列表
			List<String> allGroups = propertyService.getAllGroup(txNo);
			if (allGroups != null) {
				log.info("get all group success, txNo: {}, result: {}", txNo, allGroups);
				return new NoPageResultModel(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS_DESC, allGroups);
			} else {
				log.error("get all group failed, txNo: {}", txNo);
				return new NoPageResultModel(RestResponseCode.FAILURE,RestResponseCode.FAILURE_DESC, null);
			}
		} catch (ParamInvalidException e) {
	        log.error("getAllGroup error:{} ", ExceptionUtils.getStackTrace(e));
	        return new NoPageResultModel(RestResponseCode.PARAMETER_ERROR, RestResponseCode.PARAMETER_ERROR_DESC + e.getFieldName());
	    } catch (Exception e) {
	    	log.error("getAllGroup error:{} ", ExceptionUtils.getStackTrace(e));
	        return new NoPageResultModel(RestResponseCode.INTERNAL_ERROR, RestResponseCode.INTERNAL_ERROR_DESC);
	    }

	}
}