package com.finartz.controller.ajax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.finartz.data.enums.Status;
import com.finartz.exception.PersonException;
import com.finartz.statics.Constants;
import com.finartz.util.JSONUtil;

public abstract class AjaxAbstractController {
	
	private final Logger logger = LoggerFactory.getLogger(AjaxAbstractController.class.getName());


	@ExceptionHandler(PersonException.class)
	protected @ResponseBody ObjectNode handleException(PersonException exception) {
		logger.error(exception.getMessage());
		ObjectNode message = JSONUtil.makeResponse(Status.FAIL, exception.getMessage(), null);
		return message;
	}
	
	@ExceptionHandler(Exception.class)
	protected @ResponseBody ObjectNode handleException(Exception exception) {
		logger.error(exception.getMessage());
		ObjectNode message = JSONUtil.makeResponse(Status.FAIL, Constants.SUNUCU_HATASI, null);
		return message;
	}
}
