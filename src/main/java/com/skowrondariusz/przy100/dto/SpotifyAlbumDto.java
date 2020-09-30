package com.skowrondariusz.przy100.dto;

public class SpotifyAlbumDto {

    private String albumName;
    private String albumId;




    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public SpotifyAlbumDto() {
    }

    public SpotifyAlbumDto(String albumName, String albumId) {
        this.albumName = albumName;
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "SpotifyAlbumDto{" +
                "albumName='" + albumName + '\'' +
                ", albumId='" + albumId + '\'' +
                '}';
    }
}
