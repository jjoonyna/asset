package com.jjoony.assetmanagement.domain.auth.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private Map<String, Object> attributes;

    private Map<String, Object> properties;

    private Map<String, Object> account;

    public KakaoResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.properties = (Map<String, Object>) attributes.get("properties");
        this.account = (Map<String, Object>) attributes.get("kakao_account");
    }

    @Override
    public Map<String, Object> getAttributes() { return this.attributes; }

    @Override
    public String getProvider() { return "kakao"; }

    @Override
    public String getProviderId() { return attributes.get("id").toString(); }

    @Override
    public String getNickname() { return properties.get("nickname").toString(); }

    @Override
    public String getEmail() { return account.get("email").toString(); }
}
