package com.skowrondariusz.przy100.service;

import org.springframework.stereotype.Service;

import static com.skowrondariusz.przy100.utility.SpotifyClientCredentials.getClientAccessToken;

//import static com.skowrondariusz.przy100.service.SpotifyTest.clientCredentials_Sync;

@Service
public class SpotifyService {


    public static String getToken() {
        String token = null;
        token = getClientAccessToken();
        return token;
//        token = clientCredentials_Sync()
    }


    public static void saveSongs() {

    }


}
