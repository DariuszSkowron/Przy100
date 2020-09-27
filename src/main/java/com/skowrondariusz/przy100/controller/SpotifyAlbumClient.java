package com.skowrondariusz.przy100.controller;


import com.skowrondariusz.przy100.dto.SpotifyAlbumDto;

import com.skowrondariusz.przy100.spotifyModel.ArtistAlbums;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SpotifyAlbumClient {

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
    public List<SpotifyAlbumDto> getAuthorAlbums (OAuth2Authentication details, @PathVariable String authorName){

        String jwt = ((OAuth2AuthenticationDetails)details.getDetails()).getTokenValue();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);
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
    public String getAlbumTracks (OAuth2Authentication details, @PathVariable String albumId){

        String jwt = ((OAuth2AuthenticationDetails)details.getDetails()).getTokenValue();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange("https://api.spotify.com/v1/albums/"+ "4s6nOCaLoi8h4WccMMz2y1" + "/tracks",
                HttpMethod.GET,
                httpEntity,
                String.class);


//        List<SpotifyAlbumDto> spotifyAlbumDtos = exchange.getBody().getItems().stream()
//                .map(item -> new SpotifyAlbumDto(item.getName(), item.getId()))
//                .collect(Collectors.toList());

//        List<SpotifyAlbumDto> spotifyAlbumDtos = exchange.getBody().getTracks().getAlbumItems().stream()
//                .map(item -> new SpotifyAlbumDto(item.getName(), item.getAlbum().getImages().get(0).getUrl()))
//                .collect(Collectors.toList());
//        return spotifyAlbumDtos;
        return exchange.getBody();
    }

}
