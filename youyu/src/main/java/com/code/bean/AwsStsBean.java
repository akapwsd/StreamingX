package com.code.bean;

public class AwsStsBean {
    private String AccessKeyId = "";
    private String AccessKeySecret = "";
    private String SecurityToken = "";
    private String Expiration = "";
    private int StatusCode = 0;
    private String PaasImPrefix = "";

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        AccessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        AccessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return SecurityToken;
    }

    public void setSecurityToken(String securityToken) {
        SecurityToken = securityToken;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String expiration) {
        Expiration = expiration;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getPaasImPrefix() {
        return PaasImPrefix;
    }

    public void setPaasImPrefix(String paasImPrefix) {
        PaasImPrefix = paasImPrefix;
    }

    @Override
    public String toString() {
        return "AwsStsBean{" +
                "AccessKeyId='" + AccessKeyId + '\'' +
                ", AccessKeySecret='" + AccessKeySecret + '\'' +
                ", SecurityToken='" + SecurityToken + '\'' +
                ", Expiration='" + Expiration + '\'' +
                ", StatusCode=" + StatusCode +
                ", PaasImPrefix='" + PaasImPrefix + '\'' +
                '}';
    }
}
