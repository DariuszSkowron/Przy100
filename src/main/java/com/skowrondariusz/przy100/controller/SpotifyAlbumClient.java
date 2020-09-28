package com.skowrondariusz.przy100.controller;


import com.skowrondariusz.przy100.dto.SpotifyAlbumDto;

import com.skowrondariusz.przy100.dto.SpotifySongDto;
import com.skowrondariusz.przy100.repository.SongRepository;
import com.skowrondariusz.przy100.service.SpotifyService;
import com.skowrondariusz.przy100.spotifyModel.AlbumTracks;
import com.skowrondariusz.przy100.spotifyModel.ArtistAlbums;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SpotifyAlbumClient {


    private SongRepository songRepository;
    private SpotifyService spotifyService;

    public SpotifyAlbumClient(SongRepository songRepository, SpotifyService spotifyService) {
        this.songRepository = songRepository;
        this.spotifyService = spotifyService;
    }

    //    @GetMapping("/album/{authorName}")
////    public SpotifyAlbum getAlbumsByAuthor (OAuth2Authentication details, @PathVariable String authorName){
////
////        String jwt = ((OAuth2AuthenticationDetails)details.getDetails()).getTokenValue();
////        RestTemplate restTemplate = new RestTemplate();
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.add("Authorization", "Bearer " + jwt);
////        HttpEntity httpEntity = new HttpEntity(httpHeaders);
////
////        ResponseEntity<SpotifyAlbum> exchange = restTemplate.exchange("https://api.spotify.com/v1/search?q="+ authorName + "&type=track&market=US&limit=50&offset=5",
////                HttpMethod.GET,
////                httpEntity,
////                SpotifyAlbum.class);
////
//////        List<SpotifyAlbumDto> spotifyAlbumDtos = exchange.getBody().getTracks().getAlbumItems().stream()
//////                .map(item -> new SpotifyAlbumDto(item.getName(), item.getAlbum().getImages().get(0).getUrl()))
//////                .collect(Collectors.toList());
////
//////        List<SpotifyAlbumDto> spotifyAlbumDtos = exchange.getBody().getTracks().getAlbumItems().stream()
//////                .map(item -> new SpotifyAlbumDto(item.getName(), item.getAlbum().getImages().get(0).getUrl()))
//////                .collect(Collectors.toList());
//////        return spotifyAlbumDtos;
////        return exchange.getBody();
////    }

    @GetMapping("/artists/{authorName}")
    public List<SpotifyAlbumDto> getAuthorAlbums (@PathVariable String authorName){

//        String jwt = ((OAuth2AuthenticationDetails)details.getDetails()).getTokenValue();
//        String jwt = spotifyService.getToken();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + SpotifyService.getToken());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<ArtistAlbums> exchange = restTemplate.exchange("https://api.spotify.com/v1/artists/"+ "6DAQjwwMGZ9QgqHhIkU7H0" + "/albums",
                HttpMethod.GET,
                httpEntity,
                ArtistAlbums.class);



        List<SpotifyAlbumDto> spotifyAlbumDtos = exchange.getBody().getItems().stream()
                .map(item -> new SpotifyAlbumDto(item.getName(), item.getId()))
                .collect(Collectors.toList());

//        List<SpotifyAlbumDto> spotifyAlbumDtos = exchange.getBody().getTracks().getAlbumItems().stream()
//                .map(item -> new SpotifyAlbumDto(item.getName(), item.getAlbum().getImages().get(0).getUrl()))
//                .collect(Collectors.toList());
        return spotifyAlbumDtos;
//        return exchange.getBody();
    }

    @GetMapping("/albums/{albumId}")
    public List<SpotifySongDto> getAlbumSongs(@PathVariable String albumId) {

//        List<SpotifyAlbumDto> kupa = getAuthorAlbums("huj");

//        String jwt = ((OAuth2AuthenticationDetails)details.getDetails()).getTokenValue();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + SpotifyService.getToken());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<AlbumTracks> exchange = restTemplate.exchange("https://api.spotify.com/v1/albums/" + albumId + "/tracks",
                HttpMethod.GET,
                httpEntity,
                AlbumTracks.class);


        List<SpotifySongDto> spotifySongDtos = exchange.getBody().getAlbumItems().stream()
                .map(item -> new SpotifySongDto(item.getName(), item.getPreviewUrl()))
                .collect(Collectors.toList());

//        List<SpotifyAlbumDto> spotifyAlbumDtos = exchange.getBody().getTracks().getAlbumItems().stream()
//                .map(item -> new SpotifyAlbumDto(item.getName(), item.getAlbum().getImages().get(0).getUrl()))
//                .collect(Collectors.toList());
        return spotifySongDtos;
//        return exchange.getBody();
//        }
    }

    public void saveSongs(){
        String token1 = SpotifyService.getToken();
        List<SpotifyAlbumDto> albums = getAuthorAlbums("Sentino");
        albums.forEach(spotifyAlbumDto -> System.out.println(spotifyAlbumDto.getAlbumName()) );

    }


    @GetMapping("/xx")
    String tokenPrint(){
        return spotifyService.getToken();
    }


}
