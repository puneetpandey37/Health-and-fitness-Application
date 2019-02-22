package com.supafit.common.exceptions;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class SupaFitExceptionHandler {

	private MessageSource messageSource;
	@Autowired
    public SupaFitExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

	@ExceptionHandler(UserDataExistsException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)// 409
	public ExceptionResponse elyxUserDatExistsException(
			UserDataExistsException dataAlreadyExistsException) {
		String errorMessage = dataAlreadyExistsException.getExceptionMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.CONFLICT);
		exceptionResponse.setError(errorMessage);
		return exceptionResponse;
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionResponse elyxInvalidRequestException(
			InvalidRequestException invalidRequestException) {
		String errorMessage = invalidRequestException.getExceptionMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
		exceptionResponse.setError(errorMessage);
		return exceptionResponse;
	} 
	
	@ExceptionHandler(UserNotExistsException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ExceptionResponse elyxUserNotExistsException(
			UserNotExistsException userNotExistsException) {
		String errorMessage = userNotExistsException.getExceptionMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.UNAUTHORIZED);
		exceptionResponse.setError(errorMessage);
		return exceptionResponse;
	}
	
	@ExceptionHandler(OTPNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public ExceptionResponse elyxOTPNotFoundException(
			OTPNotFoundException oTPNotFoundException) {
		String errorMessage = oTPNotFoundException.getExceptionMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.FORBIDDEN);
		exceptionResponse.setError(errorMessage);
		return exceptionResponse;
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ExceptionResponse elyxBadCredentialsException(BadCredentialsException badCredentialsException){
		String errorMessage = badCredentialsException.getExceptionMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.UNAUTHORIZED);
		exceptionResponse.setError(errorMessage);
		return exceptionResponse;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ValidationErrorDTO RequestBodyValidationException(MethodArgumentNotValidException exception){
		BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
	}
	
	private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();
        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        return dto;
    }
	
	private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String fieldErrorCodes = fieldError.getDefaultMessage();
            localizedErrorMessage = fieldErrorCodes;
        }
        return localizedErrorMessage;
    }
}
