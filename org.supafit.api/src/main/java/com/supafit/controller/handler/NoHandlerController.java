package com.supafit.controller.handler;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@CrossOrigin
public class NoHandlerController {

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<String> requestHandlingNoHandlerFound(
			HttpServletRequest req, NoHandlerFoundException ex) {
		String errorMessage = "No Service found!";
		String errorURL = req.getRequestURL().toString();
		JSONObject jo = new JSONObject();
		jo.put("url", errorURL);
		jo.put("error", errorMessage);
		return new ResponseEntity<String>(jo.toString(), HttpStatus.BAD_REQUEST);
	}
}
