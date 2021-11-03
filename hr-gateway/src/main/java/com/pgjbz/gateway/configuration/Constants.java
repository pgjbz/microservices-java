package com.pgjbz.gateway.configuration;

import java.util.List;

public class Constants {

	private Constants() {
	}

	public static final List<String> OPEN_ENDPOINTS = List.of("/oauth/oauth/token");

	public static final List<String> OPERATOR = List.of("/worker");

	public static final List<String> ADMIN = List.of("/payroll",
			"/user",
			"/users",
			"/users/search",
			"/actuator",
			"/worker/actuator",
			"/oauth/actuator",
			"/oauth/users/search");

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_OPERATOR = "ROLE_OPERATOR";
}
