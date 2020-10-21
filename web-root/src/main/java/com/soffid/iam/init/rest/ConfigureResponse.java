package com.soffid.iam.init.rest;

class ConfigureResponse {
	boolean success;
	String message;
	boolean createUser;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isCreateUser() {
		return createUser;
	}
	public void setCreateUser(boolean createUser) {
		this.createUser = createUser;
	}
}