package com.skowrondariusz.przy100.utility;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

//@Configuration
//@ConfigurationProperties(prefix = "spotify")
@Configuration
@PropertySource(value = { "classpath:application.properties" }, ignoreResourceNotFound = false)
public class SpotifyCredentials implements EnvironmentAware {

    private static Environment env;

    public static String getProperty(String key) {
        return env.getProperty(key);
    }

    @Override
    public void setEnvironment(Environment env) {
        SpotifyCredentials.env = env;
    }

    public static String kupa(){
        return SpotifyCredentials.getProperty("clientId");
    }
}
