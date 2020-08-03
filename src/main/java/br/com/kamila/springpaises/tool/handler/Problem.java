package br.com.kamila.springpaises.tool.handler;

import java.time.OffsetDateTime;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problem {
	
	private OffsetDateTime timestamp;
	
	private Integer status;

	private String error;

	private String message;

	private StackTraceElement[] trace;
	
	public Problem() {
		this.timestamp = OffsetDateTime.now();
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StackTraceElement[] getTrace() {
		return trace;
	}

	public void setTrace(StackTraceElement[] stackTraceElements) {
		this.trace = stackTraceElements;
	}

}
