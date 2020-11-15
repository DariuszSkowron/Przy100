package com.skowrondariusz.przy100.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpotifyCredentials{

    static String CLIENT_ID;
    static String CLIENT_SECRET;
    private static String ARTIST_ID;


    @Value("${spotify.clientId}")
    public void setClientId(String clientId){
        CLIENT_ID = clientId;
    }

    @Value("${spotify.client-secret}")
    public void setClientSecret(String clientSecret){
        CLIENT_SECRET = clientSecret;
    }

    @Value("${spotify.ArtistId}")
    public void setArtistId(String artistId){
        ARTIST_ID = artistId;
    }


    public static String getArtistId() {
        return ARTIST_ID;
    }
}
