package com.pgjbz.hroauth.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.config")
public class SecurityConfigs {

	private String appName;
	private String appSecret;

}
