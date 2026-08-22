package com.DeatHertZ.urlshortener.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator {

    private static final String URL_REGEX =
            "^https?://(?:www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}" +
                    "\\.[a-zA-Z0-9()]{1,6}\\b[-a-zA-Z0-9()@:%_+.~#?&/=]*$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static boolean isValidUrl(String url) {
        if (url == null) {
            return false;
        }
        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }
}