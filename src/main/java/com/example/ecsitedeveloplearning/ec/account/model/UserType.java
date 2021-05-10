package com.example.ecsitedeveloplearning.ec.account.model;

import lombok.Getter;

public enum UserType {

	ADMIN("admin"),
	USER("user");
	
	@Getter
	private String value;

	private UserType(String value) {
		this.value = value;
	}
	
}