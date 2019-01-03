package com.caiw.config;

public class APIConfig {
    private String id;
    private String baseUrl;
    private String user;
    private String password;


    public APIConfig() {}

    public APIConfig(String id, String baseUrl, String user, String password) {
        this.id = id;
        this.baseUrl = baseUrl;
        this.user = user;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public APIConfig build(String id, String url, String user, String password) {
        return new APIConfig(id, url, user, password);
    }

}
