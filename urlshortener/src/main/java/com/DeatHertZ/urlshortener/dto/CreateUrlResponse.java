package com.DeatHertZ.urlshortener.dto;

public class CreateUrlResponse { // DTO: “Here is what our API promises to return.”

    private final String shortCode;
    private final String shortUrl;
    private final String url;

    public CreateUrlResponse(String shortCode, String shortUrl, String url) {
        this.shortCode = shortCode;
        this.shortUrl = shortUrl;
        this.url = url;
    }
    // The getters determine the JSON field names Spring returns.
    public String getShortCode() {
        return shortCode;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getUrl() {
        return url;
    }
}
