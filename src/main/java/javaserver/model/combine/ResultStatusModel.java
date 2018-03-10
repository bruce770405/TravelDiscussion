package javaserver.model.combine;

//@Entity
public class ResultStatusModel {
	private boolean resultStatus;
	private String message;
	private Object model;

	public ResultStatusModel() {
		super();
	}

	public ResultStatusModel(boolean resultStatus, String message, Object model) {
		this.setResultStatus(resultStatus);
		this.message = message;
		this.model = model;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}

	public boolean isResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(boolean resultStatus) {
		this.resultStatus = resultStatus;
	}
}
