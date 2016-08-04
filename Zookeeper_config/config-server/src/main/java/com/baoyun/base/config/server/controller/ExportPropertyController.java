package com.baoyun.base.config.server.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baoyun.base.config.server.exception.ParamInvalidException;
import com.baoyun.base.config.server.service.PropertyService;
import com.baoyun.base.config.server.util.StringCheckUtils;

@Slf4j
@RestController
public class ExportPropertyController {
	
	@Autowired
	PropertyService propertyService;
	
	@RequestMapping(value = "/exportProperty", method = RequestMethod.GET)
	@ResponseBody
	public void addProperty(
			@RequestParam(value = "groupName", required = true) String groupName,
			HttpServletRequest request,HttpServletResponse response) {
		
			String txNo = "EXPORT_PROPERTY" + UUID.randomUUID().toString();
			log.info("exportProperty txNo: {}, groupName: {},", txNo, groupName);
		try {			
			groupName = StringCheckUtils.checkGroupName(groupName);
			//TODO 判断组别是否存在
			response.setHeader("Content-disposition", "attachment; filename=" + generateFileName(groupName));
			response.setContentType("text/json;charset=utf-8");
			
			propertyService.exportProperty(txNo, groupName,response.getOutputStream());
		} catch (ParamInvalidException e) {
			log.error("ParamInvalidException, txNo: {}, {}", txNo, ExceptionUtils.getStackFrames(e));
		} catch (Exception e) {
			log.error("Exception, txNo: {}, {}", txNo, ExceptionUtils.getStackFrames(e));
		}
    }

	private String generateFileName(String groupName) {
		SimpleDateFormat dateFormatYMD = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Date now = new Date();
		return groupName +"_"+ dateFormatYMD.format(now) + ".properties";
	}

}
