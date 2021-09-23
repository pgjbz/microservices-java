package com.pgjbz.hroauth.configuration;

import com.pgjbz.hroauth.model.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

public class TokenConfig implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication auth2Authentication) {
        User principal = (User) auth2Authentication.getPrincipal();
        ((DefaultOAuth2AccessToken)accessToken)
                .setAdditionalInformation(Map.of("user_id", principal.getId()));
        return accessToken;
    }

}
