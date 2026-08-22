package com.DeatHertZ.urlshortener.service;

public class ShortCodeResult {

    private final String shortCode;
    private final boolean created;

    public ShortCodeResult(String shortCode, boolean created){
        this.shortCode = shortCode;
        this.created = created;
    }

    public String getShortCode() {
        return shortCode;
    }

    public boolean isCreated() {
        return created;
    }
}