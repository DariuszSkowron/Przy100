package com.skowrondariusz.przy100.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class SpotifyTest {

    private static final String accessToken = "BQBQC-gJFc16B6Fi1xvAI8M5KUtP7SPlcSnNitHOJYT00_FL0TIPXk8CiHfPCfaSuEGibXo22WFrcBA4CsI";
    private static final String id = "6DAQjwwMGZ9QgqHhIkU7H0";


    private static final String clientId = "765ae1d3c8d44d9bbcd2a691a095cbc6";
    private static final String clientSecret = "1b5e3868efbe42b6941250954a0c7442";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();
    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();



    public static String clientCredentials_Sync() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
            System.out.println(clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return spotifyApi.getAccessToken().toString();
    }

    private static final SpotifyApi spotifysApi = new SpotifyApi.Builder()
            .setAccessToken(clientCredentials_Sync())
            .build();

    public static void kupa(){
        clientCredentials_Sync();
        getArtistsAlbums_Async();
    }

//    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
//            .setAccessToken(accessToken)
//            .build();
    private static final GetArtistsAlbumsRequest getArtistsAlbumsRequest = spotifysApi.getArtistsAlbums(id)
//          .album_type("album")
//          .limit(10)
//          .offset(0)
//          .market(CountryCode.SE)
            .build();

    public static void getArtistsAlbums_Sync() {
        try {
            final Paging<AlbumSimplified> albumSimplifiedPaging = getArtistsAlbumsRequest.execute();

//            System.out.println("Total: " + albumSimplifiedPaging.getTotal());
            for (AlbumSimplified item : albumSimplifiedPaging.getItems()) {
                System.out.println(item.getName());
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getArtistsAlbums_Async() {
        try {
            final CompletableFuture<Paging<AlbumSimplified>> pagingFuture = getArtistsAlbumsRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Paging<AlbumSimplified> albumSimplifiedPaging = pagingFuture.join();

            System.out.println("Total: " + albumSimplifiedPaging.getTotal());
            for (AlbumSimplified item : albumSimplifiedPaging.getItems()) {
                System.out.println(item.getName());
            }
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }
}
