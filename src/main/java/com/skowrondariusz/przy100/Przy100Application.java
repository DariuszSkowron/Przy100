package com.skowrondariusz.przy100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.skowrondariusz.przy100.service.SpotifyTest.getArtistsAlbums_Async;
import static com.skowrondariusz.przy100.service.SpotifyTest.kupa;
import static com.skowrondariusz.przy100.utility.ClientCreditentials.clientCredentials_Sync;


@SpringBootApplication
public class Przy100Application {

    public static void main(String[] args) {
        SpringApplication.run(Przy100Application.class, args);
//        clientCredentials_Sync();
        getArtistsAlbums_Async();
    }

}
