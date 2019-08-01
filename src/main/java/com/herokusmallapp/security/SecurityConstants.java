package com.herokusmallapp.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/adduser";

    public static String[] permitGetZone() {
        return new String[]{
                "/api/getevents",
                "/api/news",
                "/api/runners"
        };
    }

    public static String[] authenticationPostZone() {
        return new String[]{
                "/api/addevent",
                "/api/runner",
                "/api/addnews"
        };
    }

    public static String[] authenticationDelZone() {
        return new String[]{
                "/api/deleteevent",
                "/api/deletenews",
                "/api/deleterunner"

        };
    }
}
