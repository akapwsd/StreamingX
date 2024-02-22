package com.code.bean;

public class AwsStsBean {
    private String accessKeyId = "";
    private String accessKeySecret = "";
    private String securityToken = "";
    private String expiration = "";
    private int statusCode = 0;
    private String paasImPrefix = "";

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPaasImPrefix() {
        return paasImPrefix;
    }

    public void setPaasImPrefix(String paasImPrefix) {
        this.paasImPrefix = paasImPrefix;
    }

    @Override
    public String toString() {
        return "AwsStsBean{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", securityToken='" + securityToken + '\'' +
                ", expiration='" + expiration + '\'' +
                ", statusCode=" + statusCode +
                ", paasImPrefix='" + paasImPrefix + '\'' +
                '}';
    }
}
