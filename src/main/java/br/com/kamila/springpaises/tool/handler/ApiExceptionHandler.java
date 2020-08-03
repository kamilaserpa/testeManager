package br.com.kamila.springpaises.tool.handler;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.kamila.springpaises.dto.ResponseDTO;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	String PROBLEMA_NO_SISTEMA = "Problema no Sistema";
	
	@Override
	public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String problemType = String.format("A propriedade '%s' do tipo '%s' não é compatível com o valor '%s' associado.",
				ex.getPropertyName(), ex.getRequiredType().getSimpleName(), ex.getValue());

		ResponseDTO response = createResponseDTO(ex, problemType, status);

		return handleExceptionInternal(ex, response, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (ObjectUtils.isEmpty(body)) {
			body = createResponseDTO(ex, PROBLEMA_NO_SISTEMA, status);
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {
		ResponseDTO response = createResponseDTO(ex, PROBLEMA_NO_SISTEMA, ex.getStatusCode());
		return handleExceptionInternal(ex, response, ex.getResponseHeaders(), ex.getStatusCode(), request);
	}
	
	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException ex, WebRequest request) {
		ResponseDTO response = createResponseDTO(ex, PROBLEMA_NO_SISTEMA, ex.getStatusCode());
		return handleExceptionInternal(ex, response, ex.getResponseHeaders(), ex.getStatusCode(), request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleDefaultException(Exception ex, WebRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	
		ResponseDTO response = createResponseDTO(ex, PROBLEMA_NO_SISTEMA, status);
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
	}
		
	private ResponseDTO createResponseDTO(Exception ex, String problemType, HttpStatus status) {
		
		Problem problem = new Problem();
		problem.setError(ex.getClass().getCanonicalName());
		problem.setMessage(ex.getMessage());
		problem.setStatus(status.value());
		problem.setTrace(ex.getStackTrace());
		
		ResponseDTO response = new ResponseDTO(problemType, false, problem);
		return response;
	}
	
}
