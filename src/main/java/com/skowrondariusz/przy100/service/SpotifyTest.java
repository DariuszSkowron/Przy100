package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.dto.SpotifyAlbumDto;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static com.skowrondariusz.przy100.utility.SpotifyClientCredentials.getClientAccessToken;

public class SpotifyTest {

    private static final String id = "6DAQjwwMGZ9QgqHhIkU7H0";



    private static final SpotifyApi spotifysApi = new SpotifyApi.Builder()
            .setAccessToken(getClientAccessToken())
            .build();


    private static final GetArtistsAlbumsRequest getArtistsAlbumsRequest = spotifysApi.getArtistsAlbums(id)
//          .album_type("album")
//          .limit(10)
//          .offset(0)
//          .market(CountryCode.SE)
            .build();

    public static List<SpotifyAlbumDto> getArtistsAlbums_Sync() {
        List<SpotifyAlbumDto> result = new ArrayList<SpotifyAlbumDto>();
        try {
            final Paging<AlbumSimplified> albumSimplifiedPaging = getArtistsAlbumsRequest.execute();

//            System.out.println("Total: " + albumSimplifiedPaging.getTotal());
            for (AlbumSimplified item : albumSimplifiedPaging.getItems()) {
//                System.out.println(item.getName());
                result.add(new SpotifyAlbumDto(item.getName(), item.getId()));
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public static void getArtistsAlbums_Async() {
        try {
            final CompletableFuture<Paging<AlbumSimplified>> pagingFuture = getArtistsAlbumsRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Paging<AlbumSimplified> albumSimplifiedPaging = pagingFuture.join();

            System.out.println("Total: " + albumSimplifiedPaging.getTotal());
            for (AlbumSimplified item : albumSimplifiedPaging.getItems()) {
                System.out.println(item.getName() + item.getId());
            }
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }





    public static void getAlbumsTracks_Sync(String id) {
         GetAlbumsTracksRequest getAlbumsTracksRequest = spotifysApi.getAlbumsTracks(id)
//          .limit(10)
//          .offset(0)
//          .market(CountryCode.SE)
                .build();

        try {
            final Paging<TrackSimplified> trackSimplifiedPaging = getAlbumsTracksRequest.execute();

            System.out.println("Total: " + trackSimplifiedPaging.getTotal());
            for (TrackSimplified track : trackSimplifiedPaging.getItems()) {
                System.out.println(track.getName() + " " + track.getPreviewUrl());
            }


        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void test(){
        List<SpotifyAlbumDto> albums = getArtistsAlbums_Sync();
        System.out.println("kupa");
        for (SpotifyAlbumDto albumDto : albums) {
            System.out.println("kupa2");
            getAlbumsTracks_Sync(albumDto.getAlbumId());
        }
    }
}
