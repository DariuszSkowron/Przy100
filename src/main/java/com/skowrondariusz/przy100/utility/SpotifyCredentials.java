

package com.skowrondariusz.przy100.utility;

import com.skowrondariusz.przy100.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//
//
//
//import com.wrapper.spotify.SpotifyApi;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
//import javax.annotation.PostConstruct;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Properties;
//
////@Configuration
////@ConfigurationProperties(prefix = "spotify")
////@Configuration
////@PropertySource(value = { "classpath:application.properties" }, ignoreResourceNotFound = false)
////@Configuration
////@PropertySource("classpath:application.properties")
@Component
public class SpotifyCredentials{
//
////    @Autowired
////    private static Environment env;

//    @Autowired
//    SpotifyClientCredentials spotifyClientCredentials;
//    @Autowired
//    SpotifyService spotifyService;
//
    public static String CLIENT_ID;
//

    @Value("${spotify.clientId}")
    public void set(String clientId){
        CLIENT_ID = clientId;
    }
}
