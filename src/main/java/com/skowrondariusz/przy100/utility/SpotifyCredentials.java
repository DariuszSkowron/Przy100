package com.skowrondariusz.przy100.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpotifyCredentials{

    static String CLIENT_ID;
    static String CLIENT_SECRET;


    @Value("${spotify.clientId}")
    public void setClientId(String clientId){
        CLIENT_ID = clientId;
    }

    @Value("${spotify.client-secret}")
    public void setClientSecret(String clientSecret){
        CLIENT_SECRET = clientSecret;
    }
}
