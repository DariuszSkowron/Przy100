package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.controller.SpotifyAlbumClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {


    public static String getToken() {
        String token = null;
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        }
        return token;
    }


    public static void saveSongs() {

    }


}
