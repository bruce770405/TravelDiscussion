package javaserver.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class StatusModel implements Serializable{
	
	/**
	 * <code>serialVersionUID</code> 的註解.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 伺服器回傳狀態
	 */
	private Object resultStatus;
	private String message;
	private Exception error;
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object isResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Object resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Exception getError() {
		return error;
	}

	public void setError(Exception error) {
		this.error = error;
	}
}
