package com.skowrondariusz.przy100.utility;

public class Test {


    private static final String clientId = SpotifyCredentials.getProperty("spotify.clientId");
    private static final String clientSecret = SpotifyCredentials.getProperty("spotify.client-secret");


    public Test() {
    }

    public static final String getClientId() {
        return clientId;
    }

    public static final String getClientSecret() {
        return clientSecret;
    }
}
