package com.example.usersystem1.services;

import java.io.IOException;

public interface SeedService {

    void seedTowns() throws IOException;
    void seedUsers() throws IOException;
    void seedPictures() throws IOException;
    void seedAlbums() throws IOException;

    default void seedAll() throws IOException {
        seedTowns();
        seedUsers();
        seedPictures();
        seedAlbums();
    }
}
